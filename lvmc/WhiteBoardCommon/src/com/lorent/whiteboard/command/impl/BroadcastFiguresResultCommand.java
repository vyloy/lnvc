package com.lorent.whiteboard.command.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.lorent.whiteboard.model.CommandsManager;
import com.lorent.whiteboard.model.RemoteFigure;
import com.lorent.whiteboard.model.RemoteFigures;


public class BroadcastFiguresResultCommand extends BroadcastResultCommand{

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(BroadcastFiguresResultCommand.class);
	
	protected List<Long> ids;

	public BroadcastFiguresResultCommand(String whiteboardId,long oid, long id, List<Long> ids) {
		super(whiteboardId,oid, id);
		if(ids==null)
			throw new IllegalArgumentException("ids == null");
		this.ids=ids;
	}

	@Override
	public void subsequentlyRun(CommandsManager manager) {
		BroadcastCommand result = manager.broadcastSuccessed(whiteboardId,oldCommandId);
		if(result!=null){
			if(result.isNeedToReExecute()){
				if(result.commandId!=commandId)
					result.commandId=commandId;
				List<RemoteFigure> remoteFigures = ((RemoteFigures)result.updater).getRemoteFigures();
				for (int i = 0; i < remoteFigures.size(); i++) {
					remoteFigures.get(i).setId(ids.get(i));
				}
				result.subsequentlyRun(manager);
			}
		}else{
			logger.warn("manager.broadcastSuccessed return null");
		}
	}

	@Override
	public JSONObject toJSON() {
		JSONObject json = super.toJSON();
		json.put("type", 103);
		json.put("figureIds", ids);
		return json;
	}
	
}
