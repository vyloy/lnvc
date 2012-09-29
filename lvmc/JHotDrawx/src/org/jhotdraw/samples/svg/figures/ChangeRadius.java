package org.jhotdraw.samples.svg.figures;

import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.Figure;
import org.jhotdraw.draw.RoundRectangleFigure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lorent.whiteboard.model.RemoteFigure;

public class ChangeRadius extends SingleUpdater {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(ChangeRadius.class);
	
	private double x;
	
	private double y;

	public ChangeRadius(RemoteFigure rf, double x,double y) {
		super(rf);
		this.x=x;
		this.y=y;
	}

	@Override
	protected void change(DefaultDrawingView view,Figure f) {
		if (f instanceof RoundRectangleFigure) {
			RoundRectangleFigure rf = (RoundRectangleFigure) f;
			rf.setArc(x, y);
		}else{
			logger.warn("The Figure {} must be RoundRectangleFigure.",f);
		}
	}

}
