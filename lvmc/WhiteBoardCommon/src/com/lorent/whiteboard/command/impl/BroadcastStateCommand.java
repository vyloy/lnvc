package com.lorent.whiteboard.command.impl;

import org.apache.mina.core.session.IoSession;

import com.lorent.whiteboard.command.ClientRunnable;
import com.lorent.whiteboard.model.CommandsManager;
import com.lorent.whiteboard.model.Updater;
import com.lorent.whiteboard.model.Whiteboard;

public class BroadcastStateCommand extends BroadcastCommand implements ClientRunnable{

	private static final long serialVersionUID = 1L;
	
	protected final int stateType;
	
	public BroadcastStateCommand(String meetingId, String whiteboardId,long commandId,int stateType,Updater<?> updater) {
		super(meetingId, whiteboardId);
		this.commandId=commandId;
		this.stateType=stateType;
		this.updater=updater;
	}

	@Override
	public void execute(Whiteboard board, IoSession session) {
		synchronized (BroadcastStateCommand.class) {
			BroadcastStateCommand result = board.saveStateCommand(this);
			if (result != null) {
				this.commandId = result.commandId + 1;
			}
		}
		session.write(this);
		board.getMeeting().broadcast(session, this);
	}
	
	@Override
	public void run(CommandsManager manager) {
		manager.acceptState(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + stateType;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BroadcastStateCommand other = (BroadcastStateCommand) obj;
		if (stateType != other.stateType)
			return false;
		return true;
	}

	public Integer getStateType() {
		return stateType;
	}

	
	
}
