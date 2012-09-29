package com.lorent.whiteboard.model;

import java.util.Set;

import org.apache.mina.core.session.IoSession;

import com.lorent.whiteboard.command.MeetingCommand;

public interface Meetings {

	void execute(IoSession session, MeetingCommand command);

	Meeting get(String meetingId);

	Meeting create(String meetingId);

	Meeting remove(String meetingId);

	Set<String> getMeetingIds();

	void kick(IoSession session);

	Meeting add(Meeting meeting);

}