package org.jhotdraw.samples.svg.figures;

import org.jhotdraw.draw.DefaultDrawingView;

import com.alibaba.fastjson.JSONObject;
import com.lorent.whiteboard.model.RemoteFigure;
import com.lorent.whiteboard.model.Updater;

public class Paste extends FiguresUpdater{

	private static final long serialVersionUID = 1L;

	@Override
	public void changeView(DefaultDrawingView view) {
		for(RemoteFigure f:figures){
			Updater<DefaultDrawingView> u = (Updater<DefaultDrawingView>) f;
			u.change(view);
		}
		
	}

	public int getType(){
		return -1;
	}

	@Override
	public JSONObject toJSON() {
		return null;
	}
}
