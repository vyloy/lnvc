package com.lorent.whiteboard.model;

import com.lorent.whiteboard.command.MeetingCommand;

public interface Sendable {

	boolean send(MeetingCommand command);

	String getMeetingId();
}
