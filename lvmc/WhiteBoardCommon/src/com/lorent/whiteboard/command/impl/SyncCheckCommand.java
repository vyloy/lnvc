package com.lorent.whiteboard.command.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.lorent.whiteboard.command.MeetingCommandAdaptor;
import com.lorent.whiteboard.model.Meeting;
import com.lorent.whiteboard.model.Whiteboard;

public class SyncCheckCommand extends MeetingCommandAdaptor {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory
			.getLogger(SyncCheckCommand.class);

	public static final int TYPE = 3;

	private static final String LOCK_KEY = "SessionLockKey";

	protected Map<String,Long> commandIds;
	
	protected Map<String,Map<Integer,Long>> states;

	public SyncCheckCommand(String meetingId, Map<String,Long> commandIds,Map<String,Map<Integer,Long>> states) {
		super(meetingId);
		if(commandIds==null){
			throw new IllegalArgumentException("commandIds == null!");
		}
		this.commandIds=commandIds;
		this.states=states;
	}

	@Override
	public void execute(Meeting meeting, IoSession session) {
		AtomicBoolean marker = getRuningMarker(session);
		boolean can = marker.compareAndSet(false, true);
		if(!can)
			return;
		try{
			Map<String,Long> _commandIds = new HashMap<String, Long>();
			_commandIds.putAll(commandIds);
			Map<String,Map<Integer,Long>> _states=null;
			if(states!=null){
				_states = new HashMap<String, Map<Integer, Long>>();
				_states.putAll(states);
			}
			Map<String, Whiteboard> whiteboards = meeting.getWhiteboards();
			Whiteboard createdIfEmpty=null;
			if(whiteboards.size()>0){
				for(Entry<String, Whiteboard> entry:whiteboards.entrySet()){
					String whiteboardId = entry.getKey();
					Number id = commandIds.get(whiteboardId);
					Whiteboard whiteboard = entry.getValue();
					if(id!=null){
						flush(id.longValue(),whiteboard,session);
						_commandIds.remove(whiteboardId);
					}else{
						flush(1,whiteboard,session);
					}
					
					if(states==null)
						continue;
					
					Map<Integer, Long> whiteboardStates = states.get(whiteboardId);
					Map<Integer, BroadcastStateCommand> stateCommands = whiteboard.getStateCommands();
					if(whiteboardStates!=null){
						for(Entry<Integer, BroadcastStateCommand> e:stateCommands.entrySet()){
							Number pointer = whiteboardStates.get(e.getKey());
							BroadcastStateCommand command = e.getValue();
							if(pointer==null||pointer.longValue()!=command.commandId){
								session.write(command);
							}
						}
						_states.remove(whiteboardId);
					}else{
						for(Entry<Integer, BroadcastStateCommand> e:stateCommands.entrySet()){
							BroadcastStateCommand command = e.getValue();
							session.write(command);
						}
					}
				}
			}else{
				createdIfEmpty=meeting.createWhiteboardIfEmpty(session);
			}
			
			
			Set<String> whiteboardIds = new HashSet<String>();
			whiteboardIds.addAll(_commandIds.keySet());
			if(states!=null&&!states.isEmpty())
				whiteboardIds.addAll(_states.keySet());
			
			if(createdIfEmpty!=null){
				whiteboardIds.remove(createdIfEmpty.getId());
			}
			
			for(String whiteboardId:whiteboardIds){
				session.write(new BroadcastCloseWhiteboardCommand(meetingId, whiteboardId));
			}
			
			synced(session);
		}finally{
			marker.set(false);
		}
	}
	
	protected void synced(IoSession session){
		session.write(new SynchronizedCommand());
	}
	
	private void flush(long commandId, Whiteboard whiteboard, IoSession session){
		for (long newest=whiteboard.getCurrentCommandId(); commandId - newest != 1&&newest>=commandId; commandId++) {
			BroadcastCommand broadcastCommand = whiteboard.getCommand(commandId);
			logger.info(
					"Command is not sychronized! {},sending command {}",
					new Object[]{commandId, broadcastCommand});
			if (broadcastCommand == null)
				throw new IllegalArgumentException(
						"Id of command is Illegal!It is " + commandId);
			session.write(broadcastCommand);
		}
	}
	
	protected AtomicBoolean getRuningMarker(IoSession session){
		Object lock = session.getAttribute(LOCK_KEY);
		if(lock==null) {
			lock = new AtomicBoolean();
			Object before = session.setAttributeIfAbsent(LOCK_KEY, lock);
			if(before!=null)
				return (AtomicBoolean) before;
			return (AtomicBoolean) lock;
		}
		return (AtomicBoolean) lock;
	}

	@Override
	public String toString() {
		JSONObject object = new JSONObject(true);
		object.put("type", TYPE);
		object.put("meetingId", meetingId);
		object.put("commandIds", commandIds);
		object.put("states", states);
		return object.toJSONString();
	}
	
	public int getType() {
		return TYPE;
	}

}
