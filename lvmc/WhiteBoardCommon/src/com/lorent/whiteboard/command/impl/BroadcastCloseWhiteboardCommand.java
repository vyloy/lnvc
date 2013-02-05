package com.lorent.whiteboard.command.impl;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.lorent.whiteboard.command.ClientRunnable;
import com.lorent.whiteboard.command.SubsequentlyRunnable;
import com.lorent.whiteboard.model.CommandsManager;
import com.lorent.whiteboard.model.Whiteboard;

public class BroadcastCloseWhiteboardCommand extends BroadcastCommand implements ClientRunnable{

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory
			.getLogger(BroadcastCloseWhiteboardCommand.class);
	
	public static final int TYPE=4;
	
	public BroadcastCloseWhiteboardCommand(String meetingId,String whiteboardId) {
		super(meetingId,whiteboardId);
	}

	@Override
	protected void sync(Whiteboard board, IoSession session) {
	}

	@Override
	protected void generateResult(Whiteboard board,
			IoSession session, long oid) {
		board.getMeeting().removeWhiteboard(whiteboardId);
		board.getMeeting().broadcast(session, this);
		BroadcastResultCommand resultCommand = new BroadcastResultCommand(board.getId(),oid, commandId);
		session.write(resultCommand);
		session.setAttribute(this, resultCommand);
		logger.info("Remove whiteboard {},Remaining {}",whiteboardId,board.getMeeting().getWhiteboards());
	}

	@Override
	public void run(CommandsManager manager) {
		manager.remove(whiteboardId);
	}
	
	@Override
	public void subsequentlyRun(CommandsManager manager) {
		run(manager);
	}

	@Override
	public String toString() {
		JSONObject result = new JSONObject(true);
		result.put("type", TYPE);
		result.put("meetingId", meetingId);
		result.put("whiteboardId", whiteboardId);
		result.put("commandId", commandId);
		return result.toJSONString();
	}

	@Override
	public boolean isNeedToReExecute() {
		return true;
	}
}
