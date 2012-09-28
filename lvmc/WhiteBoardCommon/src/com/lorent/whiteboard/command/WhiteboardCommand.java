package com.lorent.whiteboard.command;

import org.apache.mina.core.session.IoSession;

import com.lorent.whiteboard.model.Whiteboard;

public interface WhiteboardCommand{
	String getWhiteboardId();
	void execute(Whiteboard board,IoSession session);
}
