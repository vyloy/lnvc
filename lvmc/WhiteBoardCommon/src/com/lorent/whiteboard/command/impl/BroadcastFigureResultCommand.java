package com.lorent.whiteboard.command.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.lorent.whiteboard.model.CommandsManager;
import com.lorent.whiteboard.model.RemoteFigure;

public class BroadcastFigureResultCommand extends BroadcastResultCommand {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory
			.getLogger(BroadcastFigureResultCommand.class);

	protected long remoteFigureId;

	public BroadcastFigureResultCommand(String whiteboardId, long oldCommandId,
			long commandId) {
		super(whiteboardId, oldCommandId, commandId);
	}

	public BroadcastFigureResultCommand(String whiteboardId, long oldCommandId,
			long commandId, long remoteFigureId) {
		super(whiteboardId, oldCommandId, commandId);
		this.remoteFigureId = remoteFigureId;
	}

	public long getRemoteFigureId() {
		return remoteFigureId;
	}

	public void setRemoteFigureId(long remoteFigureId) {
		this.remoteFigureId = remoteFigureId;
	}

	@Override
	public void subsequentlyRun(CommandsManager manager) {
		BroadcastCommand result = manager.broadcastSuccessed(whiteboardId,oldCommandId);
		if(result!=null){
			if(result.isNeedToReExecute()){
				if(result.commandId!=commandId)
					result.commandId=commandId;
				((RemoteFigure)result.updater).setId(remoteFigureId);
				result.subsequentlyRun(manager);
			}
		}else{
			logger.warn("manager.broadcastSuccessed return null");
		}
	}

	@Override
	public JSONObject toJSON() {
		JSONObject json = super.toJSON();
		json.put("type", 102);
		json.put("figureId", remoteFigureId);
		return json;
	}
}
