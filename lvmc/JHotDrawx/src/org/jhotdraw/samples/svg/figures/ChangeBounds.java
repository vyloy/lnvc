package org.jhotdraw.samples.svg.figures;

import java.awt.geom.Rectangle2D;

import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.Figure;

import com.lorent.whiteboard.model.RemoteFigure;

public class ChangeBounds extends IdentifiableSingleUpdater{

	private static final long serialVersionUID = 1L;
	
	private Rectangle2D.Double bounds;
	
	public ChangeBounds(RemoteFigure rf, Rectangle2D.Double bounds) {
		super(rf);
		this.bounds = bounds;
	}

	@Override
	protected void change(DefaultDrawingView view,Figure f) {
		f.translate(new IdentifiableChangeBounds(identifying,bounds));
	}

}
