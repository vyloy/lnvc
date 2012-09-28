package com.lorent.whiteboard.server;

import java.io.IOException;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lorent.whiteboard.command.Command;
import com.lorent.whiteboard.command.MeetingCommand;
import com.lorent.whiteboard.model.Meetings;
import com.lorent.whiteboard.server.db.DatabaseFactory;
import com.lorent.whiteboard.server.model.WhiteboardMeetings;

public class CommandHandler extends IoHandlerAdapter {

	private static final Logger logger = LoggerFactory
			.getLogger(CommandHandler.class);

	private final Meetings meetings = new WhiteboardMeetings();
	
	public CommandHandler() {
		DatabaseFactory.load(meetings);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		if (cause instanceof IOException) {
			meetings.kick(session);
			logger.debug(cause.getMessage());
		} else {
			logger.error("There is an unexpected exception", cause);
		}
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		if (message instanceof MeetingCommand) {
			meetings.execute(session, (MeetingCommand) message);
		} else if (message instanceof Command) {
			((Command) message).execute(session);
		}
		logger.debug("{} executed on the server, session is {}", message,
				session);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		meetings.kick(session);
	}

	public Meetings getMeetings() {
		return meetings;
	}

}
