package com.lorent.whiteboard.server.model;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.mina.core.session.IoSession;

import com.lorent.whiteboard.command.WhiteboardCommand;
import com.lorent.whiteboard.command.impl.BroadcastCommand;
import com.lorent.whiteboard.command.impl.BroadcastStateCommand;
import com.lorent.whiteboard.model.Meeting;
import com.lorent.whiteboard.model.Meetings;
import com.lorent.whiteboard.model.Whiteboard;
import com.lorent.whiteboard.server.db.DatabaseFactory;
import com.lorent.whiteboard.server.db.DatabaseOutputStream;

public class ElectronicWhiteboard implements Whiteboard {
	private volatile long commandId = 0;

	private final ConcurrentHashMap<Long, BroadcastCommand> commands = new ConcurrentHashMap<Long, BroadcastCommand>();
	
	private final ConcurrentHashMap<Integer, BroadcastStateCommand> stateCommands = new ConcurrentHashMap<Integer, BroadcastStateCommand>();

	private final AtomicLong figureIdSeed=new AtomicLong();
	
	private volatile SoftReference<Meeting> meeting;
	
	private final String id;
	
	private volatile DatabaseOutputStream stream;
	
	public ElectronicWhiteboard(String id,Meeting meeting) {
		this.id=id;
		this.meeting = new SoftReference<Meeting>(meeting);
		stream=DatabaseFactory.getOutputStream(meeting);
	}
	
	public ElectronicWhiteboard(String id,Meeting meeting,boolean lazy) {
		this.id=id;
		this.meeting = new SoftReference<Meeting>(meeting);
	}
	
	public void setUp(){
		if(stream==null){
			stream=DatabaseFactory.getOutputStream(getMeeting());
		}
	}

	public void execute(WhiteboardCommand whiteboardCommand, IoSession session) {
		whiteboardCommand.execute(this, session);
		@SuppressWarnings("unchecked")
		Map<Integer,BroadcastStateCommand> states = (Map<Integer, BroadcastStateCommand>) session.getAttribute("states");
		if(states==null||states.size()==0)
			return;
		for(Entry<Integer,BroadcastStateCommand>e:states.entrySet()){
			session.write(e.getValue());
		}
	}

	public boolean save(BroadcastCommand command){
		return save(command,true);
	}
	
	/**
	 * @param command
	 * @param save true 保存到硬盘中
	 * @return
	 */
	public synchronized boolean save(BroadcastCommand command,boolean save){
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
	
	public void saveToDatabase(BroadcastCommand o){
		stream.write(o);
	}
	
	public long generateRemoteFigureId(){
		return figureIdSeed.incrementAndGet();
	}
	
	public BroadcastStateCommand getStateCommand(Integer StateType){
		return stateCommands.get(StateType);
	}
	
	public BroadcastStateCommand saveStateCommand(BroadcastStateCommand command){
		stream.write(command);
		return stateCommands.put(command.getStateType(), command);
	}

	public ConcurrentHashMap<Integer, BroadcastStateCommand> getStateCommands() {
		return stateCommands;
	}

	public BroadcastCommand getCommand(long id) {
		return commands.get(id);
	}
	
	public long getCurrentCommandId(){
		return commandId;
	}

	public Meeting getMeeting() {
		Meeting m = meeting.get();
		if(m!=null)
			return m;
		
		Meetings meetings = WhiteboardMeetings.currentMeetings.get();
		m = meetings.get(id);
		if(m!=null){
			meeting=new SoftReference<Meeting>(m);
			return m;
		}

		
		throw new IllegalStateException("进入白板处理阶段，但从内存和硬盘中找不到会议数据。");
	}

	public String getId() {
		return id;
	}

}
