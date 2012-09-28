package com.lorent.client.command.impl;

import java.util.Map;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lorent.whiteboard.command.impl.SyncCheckCommand;
import com.lorent.whiteboard.model.Meeting;

public class ClientSyncCommand extends SyncCheckCommand {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory
			.getLogger(ClientSyncCommand.class);
	
	protected long commandId;

	public ClientSyncCommand(String meetingId, Map<String, Long> commandIds,long commandId,Map<String,Map<Integer,Long>> states) {
		super(meetingId, commandIds, states);
		this.commandId=commandId;
	}

	@Override
	public void execute(Meeting meeting, IoSession session) {
		long commandId=this.commandId;
		for (long newest=meeting.getCurrentCommandId(); commandId - newest != 1&&newest>=commandId; commandId++) {
			ClientBroadcastCommand command = meeting.getCommand(commandId);
			logger.info(
					"Command is not sychronized! {},sending command {}",
					new Object[]{commandId, command});
			if (command == null)
				throw new IllegalArgumentException(
						"Id of command is Illegal!It is " + commandId);
			session.write(command);
		}
		
		super.execute(meeting, session);
	}

	@Override
	public String toString() {
		return "ClientSyncCommand [commandId=" + commandId + ", commandIds="
				+ commandIds + ", meetingId=" + meetingId + "]";
	}

}
