package com.lorent.client.model;

import com.lorent.client.command.impl.ClientBroadcastCommand;

public interface ClientCommandsManager extends MissingCommander{

	ClientBroadcastCommand create(String meetingId,MemberListUpdater updater, boolean simple);
	
	void execute(ClientBroadcastCommand command);
	
	ClientBroadcastCommand getMissingCommand() throws InterruptedException;
	
	void close();

	void increaseCommandId();

	ClientBroadcastCommand broadcastSuccessed(long oldCommandId);

	long getFutureCommandIds();
}
