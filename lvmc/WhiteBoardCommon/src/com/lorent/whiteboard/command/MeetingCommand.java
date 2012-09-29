package com.lorent.whiteboard.command;

import java.io.Serializable;

import org.apache.mina.core.session.IoSession;

import com.lorent.whiteboard.model.Meeting;
import com.lorent.whiteboard.model.Meetings;

public interface MeetingCommand extends Serializable{
	String getMeetingId();
	
	void setSessionId(long sessionId);

	void execute(Meeting meeting, IoSession session);
	
	void execute(Meetings meetings, IoSession session);

}
