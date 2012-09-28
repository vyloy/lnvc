package com.lorent.whiteboard.command.impl;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lorent.whiteboard.command.MeetingCommandAdaptor;
import com.lorent.whiteboard.model.Meeting;
import com.lorent.whiteboard.model.Meetings;

public class Create extends MeetingCommandAdaptor {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(Create.class);

	public Create(String meetingId) {
		super(meetingId);
	}

	@Override
	public void execute(Meetings meetings, IoSession session) {
		meetings.create(meetingId);
	}

	@Override
	public void execute(Meeting meeting, IoSession session) {
		logger.info("{} meeting exists!",meeting);
	}

}
