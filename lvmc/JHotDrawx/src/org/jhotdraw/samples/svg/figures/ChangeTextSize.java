package org.jhotdraw.samples.svg.figures;

import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.Figure;
import org.jhotdraw.draw.TextFigure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lorent.whiteboard.model.RemoteFigure;

public class ChangeTextSize extends SingleUpdater {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ChangeTextSize.class);
	private float size;

	public ChangeTextSize(RemoteFigure rf, float size) {
		super(rf);
		this.size=size;
	}
	
	@Override
	public void change(DefaultDrawingView view,Figure f) {
		if(f instanceof TextFigure){
			((TextFigure) f).setFontSize(size);
		}else{
			logger.warn("The Figure {} must be TextFigure.",f);
		}
	}

}
