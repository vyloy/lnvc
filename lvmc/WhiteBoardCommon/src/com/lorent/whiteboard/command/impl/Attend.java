package com.lorent.whiteboard.command.impl;

import org.apache.mina.core.session.IoSession;

import com.alibaba.fastjson.JSONObject;
import com.lorent.whiteboard.command.MeetingCommandAdaptor;
import com.lorent.whiteboard.model.Meeting;
import com.lorent.whiteboard.model.Meetings;


public class Attend extends MeetingCommandAdaptor{
	
	private static final long serialVersionUID = 1L;
	
	public static final int TYPE=1;
	
	public Attend(String meetingId) {
		super(meetingId);
	}

	@Override
	public void execute(Meeting meeting, IoSession session) {
		session.setAttribute(Attend.class, meetingId);
		meeting.add(session);
	}

	@Override
	public void execute(Meetings meetings, IoSession session) {
		execute(meetings.create(meetingId),session);
	}

	@Override
	public String toString() {
		JSONObject object = new JSONObject(true);
		object.put("type", TYPE);
		object.put("meetingId", meetingId);
		return object.toJSONString();
	}
	
}
