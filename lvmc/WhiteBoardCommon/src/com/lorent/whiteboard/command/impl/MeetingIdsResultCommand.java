package com.lorent.whiteboard.command.impl;

import java.util.Set;

import com.lorent.whiteboard.command.ResultCommand;
import com.lorent.whiteboard.model.CommandsManager;

public class MeetingIdsResultCommand implements ResultCommand {

	private static final long serialVersionUID = 1L;
	
	private Set<String> meetingIds;
	
	public MeetingIdsResultCommand(Set<String> meetingIds) {
		this.meetingIds = meetingIds;
	}

	@Override
	public void subsequentlyRun(CommandsManager manager) {
		System.out.println(meetingIds);
	}

}
