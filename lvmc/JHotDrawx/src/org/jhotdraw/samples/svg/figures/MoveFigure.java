package org.jhotdraw.samples.svg.figures;

import java.awt.geom.Rectangle2D.Double;

import org.jhotdraw.draw.AbstractFigure;
import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.Figure;

import com.alibaba.fastjson.JSONObject;
import com.lorent.whiteboard.model.RemoteFigure;

public class MoveFigure extends SingleUpdater {

	private static final long serialVersionUID = 1L;
	private double x,y;
	public static final int TYPE=13;

	public MoveFigure(RemoteFigure rf) {
		super(rf);
		Double bounds = rf.getBounds();
		x=bounds.x;
		y=bounds.y;
	}

	@Override
	protected void change(DefaultDrawingView view, Figure figure) {
		Double bounds = figure.getBounds();
		Double b = new Double(x,y,bounds.width,bounds.height);
		((AbstractFigure) figure).setBounds(b);
	}
	
	@Override
	public int getType() {
		return TYPE;
	}

	@Override
	public JSONObject toJSON() {
		JSONObject result = new JSONObject(true);
		StringBuilder s = new StringBuilder();
		s.append(x);
		s.append(",");
		s.append(y);
		result.put(String.valueOf(id), s.toString());
		return result;
	}

}
