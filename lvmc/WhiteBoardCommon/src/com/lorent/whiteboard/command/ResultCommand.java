package com.lorent.whiteboard.command;

import java.io.Serializable;

import com.lorent.whiteboard.model.CommandsManager;

public interface ResultCommand extends Serializable {

	void subsequentlyRun(CommandsManager manager);

}