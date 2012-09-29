package com.lorent.client.command.impl;

import com.lorent.client.model.ClientCommandsManager;

public class CommandResult implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	protected long oldCommandId;
	protected long commandId;
	public CommandResult(long oldCommandId, long commandId) {
		this.oldCommandId = oldCommandId;
		this.commandId = commandId;
	}
	public long getOldCommandId() {
		return oldCommandId;
	}
	public void setOldCommandId(long oldCommandId) {
		this.oldCommandId = oldCommandId;
	}
	public long getCommandId() {
		return commandId;
	}
	public void setCommandId(long commandId) {
		this.commandId = commandId;
	}
	public void subsequentRun(ClientCommandsManager manager) {
		ClientBroadcastCommand result = manager.broadcastSuccessed(oldCommandId);
		if(result!=null){
			manager.increaseCommandId();
		}
	}
}
