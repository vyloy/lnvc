package com.lorent.whiteboard.model;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.session.IoSession;

import com.lorent.whiteboard.command.WhiteboardCommand;
import com.lorent.whiteboard.command.impl.BroadcastCommand;
import com.lorent.whiteboard.command.impl.BroadcastStateCommand;

public interface Whiteboard {

	void execute(WhiteboardCommand whiteboardCommand, IoSession session);

	boolean save(BroadcastCommand command);

	long generateRemoteFigureId();

	BroadcastStateCommand getStateCommand(Integer StateType);

	BroadcastStateCommand saveStateCommand(BroadcastStateCommand command);

	ConcurrentHashMap<Integer, BroadcastStateCommand> getStateCommands();

	BroadcastCommand getCommand(long id);

	long getCurrentCommandId();

	Meeting getMeeting();

	String getId();

	void setUp();

	boolean save(BroadcastCommand command, boolean save);

	void saveToDatabase(BroadcastCommand o);

}