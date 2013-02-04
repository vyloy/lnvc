package com.lorent.whiteboard.command;

import com.lorent.whiteboard.model.CommandsManager;

public interface SubsequentlyRunnable {
	public void subsequentlyRun(CommandsManager manager);
	public boolean isNeedToReExecute();
}
