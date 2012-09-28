package com.lorent.whiteboard.server.db;

import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.lorent.whiteboard.command.impl.BroadcastCommand;

public class RemoveWhiteboardDataTask implements Runnable {
	
	private static final Logger logger = LoggerFactory
			.getLogger(RemoveWhiteboardDataTask.class);

	private final String meetingId;
	
	private final String whiteboardId;
	
	public RemoveWhiteboardDataTask(String meetingId, String whiteboardId) {
		this.meetingId = meetingId;
		this.whiteboardId = whiteboardId;
	}

	@Override
	public void run() {
		File file = DatabaseFactory.getFileForMeeting(meetingId);
		DatabaseInputStream in = new DatabaseInputStream(file);
		LinkedList<Object> result = new LinkedList<Object>();
		try{
			while(true){
				Object read = in.read();
				if(read instanceof BroadcastCommand){
					if(((BroadcastCommand) read).getWhiteboardId().equals(whiteboardId))
						continue;
				}
				result.add(read);
			}
		}catch(EOFException e){
		}catch(Exception e){
			logger.error("RemoveWhiteboardTask.read error",e);
		}
		DatabaseOutputStream outputStream = DatabaseFactory.getOutputStream(meetingId);
		try{
			outputStream.getOut().close();
			FileOutputStream out = new FileOutputStream(file);
			Kryo kryo = outputStream.getKryo();
			Output output = new Output(out);
			for(Object o:result){
				kryo.writeClassAndObject(output, o);
			}
			output.flush();
			out.flush();
			out.getChannel().force(false);
			outputStream.setOut(out,output);
		}catch(Exception e){
			logger.error("RemoveWhiteboardTask.write error",e);
		}
	}

}
