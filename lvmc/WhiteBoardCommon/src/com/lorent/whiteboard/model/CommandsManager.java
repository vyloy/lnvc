package com.lorent.whiteboard.model;

import java.util.Map;

import javax.swing.JTabbedPane;

import com.lorent.client.model.MissingCommander;
import com.lorent.whiteboard.command.ClientRunnable;
import com.lorent.whiteboard.command.ResultCommand;
import com.lorent.whiteboard.command.impl.BroadcastCloseWhiteboardCommand;
import com.lorent.whiteboard.command.impl.BroadcastCommand;
import com.lorent.whiteboard.command.impl.BroadcastStateCommand;

public interface CommandsManager extends MissingCommander{
	void execute(BroadcastCommand command);
	void execute(ClientRunnable message);
	void execute(ResultCommand message);
	BroadcastCommand create(String meetingId, Updater<?> updater,View view);
	BroadcastStateCommand createState(String meetingId, Updater<?> object, View view,Integer stateType);
	BroadcastCommand getBroadcastCommand(String whiteboardId,long id);
	BroadcastCommand broadcastSuccessed(String whiteboardId,long id);
	boolean acceptCommandId(String viewId,long commandId);
	long getFutureCommandId(String viewId);
	BroadcastCommand getMissingCommand() throws InterruptedException;
	void close();
	Map<String, Long> getFutureCommandIds();
	void remove(String vid);
	BroadcastCloseWhiteboardCommand createCloseWhiteboardCommand(
			String meetingId, String whiteboardId);
	View getView(String id);
	JTabbedPane getContainer();
	void acceptState(BroadcastStateCommand command);
	Map<String, Map<Integer, Long>> getStateCommandIds();
	void removeAll();
	Sendable getSendable();
	void synced();
	ViewPanel createPanel();
	ViewPanel createPanel(String whiteboardId);
	ViewPanel createPanel(String whiteboardId,long commandId);
	boolean isWritable();
	boolean isEmpty();
	Object putAttribute(Object key,Object value); 
	Object getAttribute(Object key);
	Object removeAttribute(Object key);
	Object putAttributeIfAbsent(Object key, Object value);
}
