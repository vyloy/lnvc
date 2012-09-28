package com.lorent.whiteboard.model;

import java.util.Collection;
import java.util.Map;

import javax.swing.JPanel;

import com.lorent.whiteboard.command.impl.BroadcastCommand;
import com.lorent.whiteboard.command.impl.BroadcastStateCommand;

public interface View {
	boolean execute(BroadcastCommand command);
	String getId();
	void setId(String id);
	int getPage();
	/**
	 * 白板跳转到page面，并广播
	 * @param page 第几页
	 */
	void setPage(int page);
	/**
	 * 白板跳转到page面，并根据broadcast决定是否广播给其他人
	 * @param page 第几页
	 * @param broadcast 是否广播
	 */
	void setPage(int page, boolean broadcast);
	void addCommandToPage(int page, BroadcastCommand command);
	void nextPage();
	void prePage();
	void firstPage();
	void lastPage();
	boolean isNeedToRedraw(Updater<?> updater);
	long getFutureCommandId();
	boolean acceptCommandId(long commandId);
	Collection<BroadcastCommand> getPageCommands(int page);
	void executeWithoutCheck(BroadcastCommand command);
	void refreshPage(int page);
	boolean acceptState(BroadcastStateCommand command);
	Map<Integer, Long> getStateCommandIds();
	int getPagecount();
	JPanel getParentPanel();
}
