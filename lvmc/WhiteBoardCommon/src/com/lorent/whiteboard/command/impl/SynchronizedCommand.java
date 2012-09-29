package com.lorent.whiteboard.command.impl;

import com.lorent.whiteboard.command.ClientRunnable;
import com.lorent.whiteboard.model.CommandsManager;

public class SynchronizedCommand implements ClientRunnable {

	private static final long serialVersionUID = 1L;

	public SynchronizedCommand() {
	}

	@Override
	public void run(CommandsManager manager) {
		manager.synced();
	}
	@Override
	public String toString() {
		return "{\"type\":104}";
	}
}
