package org.jhotdraw.samples.svg.figures;

import org.jhotdraw.draw.BezierFigure;
import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.Figure;
import org.jhotdraw.geom.BezierPath;

import com.lorent.whiteboard.model.RemoteFigure;

public class SetNode extends SingleUpdater {

	private static final long serialVersionUID = 1L;

	private int index;
	
	private BezierPath.Node v;
	
	public SetNode(RemoteFigure rf,int index,BezierPath.Node v) {
		super(rf);
		if(v==null){
			throw new IllegalArgumentException("v == null !");
		}
		this.index=index;
		this.v=v;
	}

	@Override
	protected void change(DefaultDrawingView view, Figure figure) {
		if(figure instanceof BezierFigure){
			figure.willChange();
			((BezierFigure) figure).remoteSetNode(index, v);
			figure.changed();
		}
	}

}
