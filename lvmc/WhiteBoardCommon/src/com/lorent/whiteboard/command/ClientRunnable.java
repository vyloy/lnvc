package com.lorent.whiteboard.command;

import com.lorent.whiteboard.model.CommandsManager;

public interface ClientRunnable extends java.io.Serializable {
	void run(CommandsManager manager);
}
