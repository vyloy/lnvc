package com.lorent.whiteboard.server.db;

import java.io.EOFException;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lorent.client.command.impl.ClientBroadcastCommand;
import com.lorent.whiteboard.command.impl.BroadcastCommand;
import com.lorent.whiteboard.command.impl.BroadcastStateCommand;
import com.lorent.whiteboard.model.Meeting;
import com.lorent.whiteboard.model.Meetings;
import com.lorent.whiteboard.model.RemoteFigure;
import com.lorent.whiteboard.model.Whiteboard;
import com.lorent.whiteboard.server.model.ElectronicWhiteboard;
import com.lorent.whiteboard.server.model.WhiteboardMeeting;

public class DatabaseFactory {
	
	private static final Logger logger = LoggerFactory
			.getLogger(DatabaseFactory.class);
	
	private static final String DB_FOLDER_NAME = "db";

	private static final ExecutorService thread=Executors.newSingleThreadExecutor(new ThreadFactory() {
		
		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(r);
			t.setName("DatabaseIOThread");
			return t;
		}
	});
	
	private static FileLock lock;

	private static final Map<String,DatabaseOutputStream> outputStreams=new HashMap<String,DatabaseOutputStream>();
	
	public static DatabaseOutputStream getOutputStream(Meeting meeting){
		return getOutputStream(meeting.getMeetingId());
	}
	
	public static DatabaseOutputStream getOutputStream(String meetingId){
		DatabaseOutputStream stream = outputStreams.get(meetingId);
		if(stream==null){
			synchronized(outputStreams){
				stream = outputStreams.get(meetingId);
				if(stream==null){
					File file = getFileForMeeting(meetingId);
					if(!file.getParentFile().exists()){
						file.getParentFile().mkdirs();
					}
					stream = new DatabaseOutputStream(file,thread);
					outputStreams.put(meetingId, stream);
				}
			}
		}
		return stream;
	}
	
	public static List<Meeting> load(Meetings meetings,File...dbs){
		if(meetings==null)
			throw new IllegalArgumentException("meetings == null!");
		if(dbs==null||dbs.length==0)
			return null;
		LinkedList<Meeting> result = new LinkedList<Meeting>();
		for(File file:dbs){
			if(file.length()<=0)
				continue;
			DatabaseInputStream in = new DatabaseInputStream(file);
			Object object;
			HashMap<Long, ClientBroadcastCommand> clientCommands = new LinkedHashMap<Long, ClientBroadcastCommand>();
			HashMap<String,HashMap<Integer, BroadcastStateCommand>> broadcastStataCommands = new LinkedHashMap<String,HashMap<Integer, BroadcastStateCommand>>();
			HashMap<String,HashMap<Long, BroadcastCommand>> broadcastCommands = new LinkedHashMap<String,HashMap<Long, BroadcastCommand>>();
			try {
				while(true){
					object = in.read();
					if(object instanceof BroadcastStateCommand){
						BroadcastStateCommand command = (BroadcastStateCommand) object;
						String whiteboardId = command.getWhiteboardId();
						HashMap<Integer, BroadcastStateCommand> wb = broadcastStataCommands.get(whiteboardId);
						if(wb==null){
							wb=new LinkedHashMap<Integer, BroadcastStateCommand>();
							broadcastStataCommands.put(whiteboardId, wb);
						}
						wb.put(command.getStateType(),command);
					}else if(object instanceof BroadcastCommand){
						BroadcastCommand command = (BroadcastCommand) object;
						String whiteboardId = command.getWhiteboardId();
						HashMap<Long, BroadcastCommand> wb = broadcastCommands.get(whiteboardId);
						if(wb==null){
							wb=new LinkedHashMap<Long, BroadcastCommand>();
							broadcastCommands.put(whiteboardId, wb);
						}
						wb.put(command.getCommandId(),command);
					}else{
						ClientBroadcastCommand command = (ClientBroadcastCommand) object;
						clientCommands.put(command.getCommandId(),command);
					}
				}
				
				
			} catch (EOFException e) {
				//到达文件尾
			} catch (Exception e) {
				RuntimeException re = new RuntimeException("Loading file "+file.toString()+"failed",e);
				logger.error("Loading history failed",re);
				continue;
			}finally{
				in.close();
			}
			
			if(clientCommands.isEmpty()&&broadcastStataCommands.isEmpty()&&broadcastCommands.isEmpty()){
				continue;
			}else{
				logger.info("Loading meeting {} success.Having {} board(s)",
						new Object[]{file.getName(),broadcastCommands.size()});
			}
			
			WhiteboardMeeting meeting = new WhiteboardMeeting(meetings, file.getName(),true);
			
			for(long i=1;i<=clientCommands.size();i++){
				boolean success = meeting.save(clientCommands.get(i), false);
				if(!success)
					throw new IllegalStateException("load clientbroadcastcommand failed");
			}
			
			HashMap<String,Whiteboard> whiteboards=new LinkedHashMap<String,Whiteboard>();
			for(Entry<String,HashMap<Long, BroadcastCommand>> entry:broadcastCommands.entrySet()){
				ElectronicWhiteboard whiteboard = new ElectronicWhiteboard(entry.getKey(),meeting,true);
				HashMap<Long, BroadcastCommand> commands = entry.getValue();
				for(long i=1;i<=commands.size();i++){
					BroadcastCommand command = commands.get(i);
					boolean success = whiteboard.save(command, false);
					if(!success)
						throw new IllegalStateException("load broadcastcommand failed");
					if(command.getUpdater() instanceof RemoteFigure){
						whiteboard.generateRemoteFigureId();
					}
				}
				whiteboards.put(entry.getKey(),whiteboard);
			}
			
			for(Entry<String,HashMap<Integer, BroadcastStateCommand>> entry:broadcastStataCommands.entrySet()){
				HashMap<Integer, BroadcastStateCommand> whiteboardCommands = entry.getValue();
				Whiteboard whiteboard = whiteboards.get(entry.getKey());
				if(whiteboard==null){
					continue;
				}
				for (Entry<Integer, BroadcastStateCommand> commandEntry: whiteboardCommands.entrySet()) {
					BroadcastStateCommand command = commandEntry.getValue();
					whiteboard.getStateCommands().put(command.getStateType(), command);
				}
			}
			
			meeting.putWhiteboards(whiteboards);
			meeting.setUp();
			meetings.add(meeting);
			result.add(meeting);
		}
		logger.info("Loaded meetings {} histories",Arrays.asList(dbs));
		return result;
	}
	
	public static List<Meeting> load(Meetings meetings){
		boolean running=false;
		try {
			if((lock = new FileOutputStream(".lock").getChannel().tryLock())==null){
				running=true;
			}
		} catch (Exception e) {
			running=true;
		}
		
		if(running){
			throw new IllegalStateException("Service is running");
		}
		
		
		
		File dbFolder = new File(DB_FOLDER_NAME);
		if(!dbFolder.exists()){
			return null;
		}
		
		File[] dbs = dbFolder.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				return(pathname.isFile());
			}
		});
		
		return load(meetings,dbs);
	}
	
	public static void removeWhiteboardData(String meetingId, String whiteboardId){
		thread.execute(new RemoveWhiteboardDataTask(meetingId, whiteboardId));
	}
	
	public static void close(){
		thread.shutdown();
		try {
			lock.release();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static File getFileForMeeting(Meeting meeting){
		return new File(DB_FOLDER_NAME+File.separator+meeting.getMeetingId());
	}
	
	public static File getFileForMeeting(String meetingId){
		return new File(DB_FOLDER_NAME+File.separator+meetingId);
	}
}
