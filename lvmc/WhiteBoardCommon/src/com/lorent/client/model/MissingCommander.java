package com.lorent.client.model;

public interface MissingCommander {
	Object getMissingCommand() throws InterruptedException;

	void clearMissingCommands();
}
