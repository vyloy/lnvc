package org.jhotdraw.samples.svg.figures;

import org.jhotdraw.draw.DefaultDrawingView;

import com.alibaba.fastjson.JSON;
import com.lorent.whiteboard.command.impl.BroadcastResultCommand;
import com.lorent.whiteboard.model.Updater;
import com.lorent.whiteboard.model.Whiteboard;

public abstract class AbstractNormalResultUpdater implements Updater<DefaultDrawingView>{

	private static final long serialVersionUID = 1L;

	@Override
	public BroadcastResultCommand createResultCommand(Whiteboard board, long oid, long id) {
		BroadcastResultCommand resultCommand = new BroadcastResultCommand(board.getId(),oid,id);
		return resultCommand;
	}

	public int getType(){
		return -1;
	}
	
	@Override
	public JSON toJSON() {
		return null;
	}
}