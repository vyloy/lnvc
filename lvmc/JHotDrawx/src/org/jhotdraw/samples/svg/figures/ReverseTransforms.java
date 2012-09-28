package org.jhotdraw.samples.svg.figures;

import java.util.Collection;

import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.Figure;

import com.lorent.whiteboard.model.RemoteFigure;

public class ReverseTransforms extends SingleUpdater{

	private static final long serialVersionUID = 1L;
	
	private Collection<IdentifiableTransform> affineTransforms;
	
	public ReverseTransforms(RemoteFigure rf) {
		super(rf);
	}
	

	public ReverseTransforms(RemoteFigure rf, Collection<IdentifiableTransform> affineTransforms) {
		super(rf);
		if(affineTransforms==null)
			throw new IllegalArgumentException("affineTransforms == null!");
		this.affineTransforms = affineTransforms;
	}

	@Override
	protected void change(DefaultDrawingView view, Figure figure) {
		for(IdentifiableTransform tx:affineTransforms){
			figure.translate(tx,true);
		}
	}

}
