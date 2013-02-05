package org.jhotdraw.samples.svg.figures;

import org.jhotdraw.draw.AbstractFigure;
import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.Figure;

import com.alibaba.fastjson.JSONObject;
import com.lorent.whiteboard.model.RemoteFigure;

public class ChangeFigureBounds extends SingleUpdater {

	private static final long serialVersionUID = 1L;
	private java.awt.geom.Rectangle2D.Double bounds;
	public static final int TYPE=12;

	public ChangeFigureBounds(RemoteFigure rf) {
		super(rf);
		bounds=rf.getBounds();
	}

	@Override
	protected void change(DefaultDrawingView view, Figure figure) {
		((AbstractFigure) figure).setBounds(bounds);
	}
	
	@Override
	public int getType() {
		return TYPE;
	}

	@Override
	public JSONObject toJSON() {
		JSONObject result = new JSONObject(true);
		StringBuilder s = new StringBuilder();
		s.append(bounds.x);
		s.append(",");
		s.append(bounds.y);
		s.append(",");
		s.append(bounds.width);
		s.append(",");
		s.append(bounds.height);
		result.put(String.valueOf(id), s.toString());
		return result;
	}

}
