package com.lorent.whiteboard.command.impl;

import org.apache.mina.core.session.IoSession;

import com.lorent.sharefile.ShareFileCommand;
import com.lorent.sharefile.client.ShareFileClient;
import com.lorent.whiteboard.command.MeetingCommandAdaptor;
import com.lorent.whiteboard.model.Meeting;

public class RemoveMeeting extends MeetingCommandAdaptor {

	private static final long serialVersionUID = 1L;

	public RemoveMeeting(String meetingId) {
		super(meetingId);
	}

	@Override
	public void execute(Meeting meeting, IoSession session) {
		meeting.close();
		ShareFileCommand shareFileCommand = new ShareFileCommand(meetingId);
		shareFileCommand.setOperation("RemoveConferenceFiles");
		ShareFileClient client = ShareFileClient.newInstance();
		try {
			client.connect();
			client.sendCommand(shareFileCommand);
			client.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
