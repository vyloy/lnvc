package com.lorent.whiteboard.command.impl;

import org.apache.mina.core.session.IoSession;

import com.alibaba.fastjson.JSONObject;
import com.lorent.whiteboard.command.ClientRunnable;
import com.lorent.whiteboard.model.CommandsManager;
import com.lorent.whiteboard.model.ViewPanel;
import com.lorent.whiteboard.model.Whiteboard;

public class CreateWhiteboard extends BroadcastCommand implements ClientRunnable{

	private static final long serialVersionUID = 1L;
	
	public static final int TYPE=2;

	public CreateWhiteboard(String meetingId) {
		this(meetingId, null);
	}

	public CreateWhiteboard(String meetingId, String whiteboardId) {
		super(meetingId, whiteboardId);
		commandId=1;
	}

	@Override
	public void execute(Whiteboard board, IoSession session) {
		if(board.save(this)){
			whiteboardId=board.getId();
			session.write(this);
			board.getMeeting().broadcast(session, this);
		}
	}

	@Override
	public void run(CommandsManager manager) {
		ViewPanel panel = manager.createPanel(whiteboardId);
		panel.getView().acceptCommandId(1L);
	}

	@Override
	public String toString() {
		JSONObject object = new JSONObject(true);
		object.put("type", TYPE);
		object.put("meetingId", meetingId);
		object.put("whiteboardId", whiteboardId);
		return object.toJSONString();
	}

}
