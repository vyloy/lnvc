package com.lorent.whiteboard.model;

import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.mina.core.session.IoSession;

import com.lorent.client.command.impl.ClientBroadcastCommand;
import com.lorent.whiteboard.command.MeetingCommand;

public interface Meeting {

	void add(IoSession session);

	void execute(IoSession session, MeetingCommand command);

	boolean save(ClientBroadcastCommand command);

	void broadcast(IoSession session, MeetingCommand command);

	Meetings getMeetings();

	CopyOnWriteArrayList<IoSession> getSessions();

	void remove(IoSession session);

	void removeWhiteboard(String whiteboardId);

	Whiteboard getWhiteboard(String id);

	Whiteboard createWhiteboard(String id);

	Map<String, Whiteboard> getWhiteboards();

	long getCurrentCommandId();

	ClientBroadcastCommand getCommand(long oid);

	String getMeetingId();

	void setUp();

	void close();

	Whiteboard createWhiteboardIfEmpty(IoSession session);

}