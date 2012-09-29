package com.lorent.client.command.impl;

import org.apache.mina.core.session.IoSession;

import com.lorent.client.model.MemberListUpdater;
import com.lorent.whiteboard.model.Meeting;

public class SimpleClientBroadcastCommand extends ClientBroadcastCommand {

	private static final long serialVersionUID = 1L;

	public SimpleClientBroadcastCommand(String meetingId,
			MemberListUpdater updater) {
		super(meetingId, 1, updater);
	}

	@Override
	protected void sync(Meeting meeting, IoSession session) {
		
	}

	@Override
	protected CommandResult generateResult(Meeting meeting, IoSession session,
			long oid) {
		return new SimpleCommandResult(oid,commandId);
	}

	
}
