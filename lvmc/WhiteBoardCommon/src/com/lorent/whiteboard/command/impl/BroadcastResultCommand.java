package com.lorent.whiteboard.command.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.lorent.whiteboard.command.ResultCommand;
import com.lorent.whiteboard.model.CommandsManager;

public class BroadcastResultCommand implements ResultCommand {

	private static final long serialVersionUID = 1L;
	protected long oldCommandId;
	protected long commandId;
	protected String whiteboardId;
	private static final Logger logger = LoggerFactory
			.getLogger(BroadcastResultCommand.class);
	
	public BroadcastResultCommand(String whiteboardId,long oldCommandId, long commandId) {
		if(whiteboardId==null)
			throw new IllegalArgumentException("whiteboardId == null!");
		this.whiteboardId=whiteboardId;
		this.oldCommandId = oldCommandId;
		this.commandId = commandId;
	}

	public long getOldCommandId() {
		return oldCommandId;
	}

	public long getCommandId() {
		return commandId;
	}

	public void setOldCommandId(long oldCommandId) {
		this.oldCommandId = oldCommandId;
	}

	public void setCommandId(long commandId) {
		this.commandId = commandId;
	}

	@Override
	public void subsequentlyRun(CommandsManager manager) {
		BroadcastCommand result = manager.broadcastSuccessed(whiteboardId,oldCommandId);
		if(result!=null){
//			boolean accepted = manager.acceptCommandId(result.getWhiteboardId(),commandId);
//			if (result instanceof SubsequentlyRunnable && accepted) {
//				((SubsequentlyRunnable) result).subsequentlyRun(manager);
//			}
			if(result.isNeedToReExecute()){
				result.commandId=commandId;
				result.subsequentlyRun(manager);
			}
		}else{
			logger.warn("manager.broadcastSuccessed return null");
		}
	}

	@Override
	public String toString() {
		return toJSON().toJSONString();
	}
	
	public JSONObject toJSON(){
		JSONObject result = new JSONObject(true);
		result.put("type", 101);
		result.put("whiteboardId", whiteboardId);
		result.put("oldCommandId", oldCommandId);
		result.put("commandId", commandId);
		return result;
	}
}
