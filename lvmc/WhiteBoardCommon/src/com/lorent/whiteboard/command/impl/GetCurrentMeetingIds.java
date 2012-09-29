package com.lorent.whiteboard.command.impl;

import java.util.HashSet;
import java.util.Set;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lorent.whiteboard.command.MeetingCommandAdaptor;
import com.lorent.whiteboard.model.Meetings;

public class GetCurrentMeetingIds extends MeetingCommandAdaptor {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory
			.getLogger(GetCurrentMeetingIds.class);

	public GetCurrentMeetingIds() {
		super(null);
	}

	@Override
	public void execute(Meetings meetings, IoSession session) {
		Set<String> meetingIds = meetings.getMeetingIds();
		session.write(new MeetingIdsResultCommand(new HashSet<String>(meetingIds)));
		logger.debug("writed result {}",meetingIds);
	}
	
	

}
