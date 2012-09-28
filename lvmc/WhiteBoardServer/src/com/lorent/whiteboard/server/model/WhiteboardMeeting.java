package com.lorent.whiteboard.server.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JOptionPane;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lorent.client.command.impl.ClientBroadcastCommand;
import com.lorent.util.LCMUtil;
import com.lorent.whiteboard.command.MeetingCommand;
import com.lorent.whiteboard.command.WhiteboardCommand;
import com.lorent.whiteboard.command.impl.BroadcastCommand;
import com.lorent.whiteboard.command.impl.BroadcastConvertedCommand;
import com.lorent.whiteboard.command.impl.CreateWhiteboard;
import com.lorent.whiteboard.command.impl.ShowMessage;
import com.lorent.whiteboard.model.Meeting;
import com.lorent.whiteboard.model.Meetings;
import com.lorent.whiteboard.model.Whiteboard;
import com.lorent.whiteboard.server.db.DatabaseFactory;
import com.lorent.whiteboard.server.db.DatabaseOutputStream;

public class WhiteboardMeeting implements Meeting {

	private static final Logger logger = LoggerFactory
			.getLogger(WhiteboardMeeting.class);
	
	private final AtomicBoolean inited=new AtomicBoolean();
	
	private final CopyOnWriteArrayList<IoSession> sessions=new CopyOnWriteArrayList<IoSession>();
	
	private final Map<String,Whiteboard> boards=new LinkedHashMap<String,Whiteboard>();
	
	private final Meetings meetings;
	
	private final String meetingId;
	
	private volatile long commandId = 0;
	
	private final ConcurrentHashMap<Long, ClientBroadcastCommand> commands = new ConcurrentHashMap<Long, ClientBroadcastCommand>();
	
	private volatile DatabaseOutputStream stream;
	
	private int whiteboardIdSeed=0;
	
	public final int maxBoards;
	
	public WhiteboardMeeting(Meetings meetings, String meetingId) {
		this(meetings,meetingId,false);
	}
	
	public WhiteboardMeeting(Meetings meetings, String meetingId,boolean lazy) {
		this.meetings=meetings;
		this.meetingId=meetingId;
		int count;
		try {
			count=Integer.parseInt(LCMUtil.getWhiteBoardNumber(meetingId));
			logger.info("Get maxboards from lcm,set to value {}",count);
		} catch (Exception e) {
			logger.error("Cant get maxboards from lcm,set to default value 3.",e);
			count=3;
		}
		maxBoards=count;
		if(!lazy){
			stream=DatabaseFactory.getOutputStream(this);
		}
	}
	
	public void setUp(){
		if(stream==null){
			stream=DatabaseFactory.getOutputStream(this);
		}
		for(Entry<String,Whiteboard> entry:boards.entrySet()){
			entry.getValue().setUp();
		}
	}
	
	public void close(){
		meetings.remove(meetingId);
		if(stream!=null){
			stream.close();
			stream.delete();
		}
		logger.debug("Meeting {} closed",meetingId);
	}

	public void add(IoSession session) {
		sessions.addIfAbsent(session);
		logger.debug("{} attend meeting {}",session,this);
	}
	
	public void execute(IoSession session, MeetingCommand command){
		command.setSessionId(session.getId());
		if(command instanceof WhiteboardCommand){
			WhiteboardCommand whiteboardCommand = (WhiteboardCommand) command;
			String whiteboardId = whiteboardCommand.getWhiteboardId();
			Whiteboard whiteboard = whiteboardId==null?null:getWhiteboard(whiteboardId);
			if(whiteboard==null){
				if(whiteboardCommand instanceof BroadcastCommand){
					if(((BroadcastCommand) whiteboardCommand).getCommandId()==1){
						whiteboard=createWhiteboard(whiteboardId);
						if(whiteboard==null){
							session.write(new ShowMessage("根据服务器配置，已经达到最大白板个数。", 
									"尊敬的用户", JOptionPane.INFORMATION_MESSAGE));
							logger.info("{} Whiteboard {} create failed because of maxBoards!",this,whiteboardId);
							return;
						}
							
					}else{
						logger.info("{} Whiteboard {} has removed!",this,whiteboardId);
						return;
					}
				}
			}else if(command instanceof BroadcastConvertedCommand&&whiteboard.getCurrentCommandId()!=0){
				logger.info("{} Whiteboard {} has loaded!",this,whiteboardId);
				return;
			}
			whiteboard.execute(whiteboardCommand,session);
		}else{
			command.execute(this, session);
		}
	}
	
	public boolean save(ClientBroadcastCommand command){
		return save(command,true);
	}
	
	public synchronized boolean save(ClientBroadcastCommand command,boolean save){
		long id = command.getCommandId();
		if(id-commandId==1){
			commands.put(id, command);
			commandId=id;
			if(save){
				stream.write(command);
			}
			return true;
		}
		return false;
	}
	
	public void broadcast(IoSession session, MeetingCommand command) {
		command.setSessionId(session.getId());
		for(IoSession _session:sessions){
			if(_session!=session){
				_session.write(command);
				logger.debug("broadcast to {}",_session);
			}
		}
	}
	
	public Meetings getMeetings() {
		return meetings;
	}

	public CopyOnWriteArrayList<IoSession> getSessions() {
		return sessions;
	}
	
	public void remove(IoSession session) {
		sessions.remove(session);
		logger.debug("{} exit meeting {}",session,this);
	}
	
	public void removeWhiteboard(String whiteboardId){
		synchronized(boards){
			Whiteboard removed = boards.remove(whiteboardId);
			if(removed!=null){
				DatabaseFactory.removeWhiteboardData(meetingId, whiteboardId);
			}
		}
	}

	public Whiteboard getWhiteboard(String id){
		synchronized(boards){
			return boards.get(id);
		}
	}
	
	public Whiteboard createWhiteboard(String id){
		synchronized(boards){
			if(id==null){
				if(boards.size()<maxBoards){
					do{
						id=String.valueOf(++whiteboardIdSeed);
					}while(boards.containsKey(id));
				}else
					return null;
			}else{
				Whiteboard whiteboard = boards.get(id);
				if(whiteboard!=null)
					return whiteboard;
				if(boards.size()==maxBoards)
					return null;
			}
			Whiteboard w = new ElectronicWhiteboard(id,this);
			boards.put(id, w);
			return w;
		}
	}
	
	public Whiteboard createWhiteboardIfEmpty(IoSession session){
		if(!inited.compareAndSet(false, true))
			return null;
		synchronized(boards){
			if(boards.size()!=0)
				return null;
			String id=String.valueOf(++whiteboardIdSeed);
			Whiteboard w = new ElectronicWhiteboard(id,this);
			boards.put(id, w);
			execute(session, new CreateWhiteboard(meetingId,id));
			return w;
		}
	}
	
	public Whiteboard putWhiteboard(Whiteboard board){
		if(board==null)
			return null;
		inited.set(true);
		synchronized(boards){
			return boards.put(board.getId(), board);
		}
	}
	
	public void putWhiteboards(Map<String,Whiteboard> boards){
		if(boards==null||boards.isEmpty())
			return;
		inited.set(true);
		synchronized(this.boards){
			this.boards.putAll(boards);
		}
	}

	public Map<String, Whiteboard> getWhiteboards() {
		return new LinkedHashMap<String,Whiteboard>(boards);
	}

	public long getCurrentCommandId() {
		return commandId;
	}

	public ClientBroadcastCommand getCommand(long oid) {
		return commands.get(oid);
	}

	public String getMeetingId() {
		return meetingId;
	}
}
