package com.lorent.whiteboard.command.impl;

import org.apache.mina.core.session.IoSession;

import com.lorent.whiteboard.command.MeetingCommandAdaptor;
import com.lorent.whiteboard.model.Meeting;


public class Exit extends MeetingCommandAdaptor{
	
	private static final long serialVersionUID = 1L;
	
	public Exit(String meetingId) {
		super(meetingId);
	}

	@Override
	public void execute(Meeting meeting, IoSession session) {
		meeting.remove(session);
	}
	
}
