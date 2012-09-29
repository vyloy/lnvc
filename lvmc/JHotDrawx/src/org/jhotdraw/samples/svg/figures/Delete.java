package org.jhotdraw.samples.svg.figures;

import java.util.List;

import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.Figure;

public class Delete extends MultiUpdater{

	private static final long serialVersionUID = 1L;
	public static final int TYPE = 9;

	@Override
	protected void change(DefaultDrawingView view, List<Figure> figures) {
		view.delete(figures);
	}

	@Override
	public int getType() {
		return TYPE;
	}
	
}
