package com.lorent.client.command.impl;

import com.lorent.client.model.ClientCommandsManager;

public class SimpleCommandResult extends CommandResult {

	private static final long serialVersionUID = 1L;

	public SimpleCommandResult(long oldCommandId, long commandId) {
		super(oldCommandId, commandId);
	}

	@Override
	public void subsequentRun(ClientCommandsManager manager) {
		manager.broadcastSuccessed(oldCommandId);
	}

}
