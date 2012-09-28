package org.jhotdraw.samples.svg.figures;

import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.Figure;

import com.lorent.whiteboard.model.Identifiable;
import com.lorent.whiteboard.model.RemoteFigure;

public abstract class IdentifiableSingleUpdater extends SingleUpdater implements
		Identifiable {

	private static final long serialVersionUID = 1L;
	
	protected long identifying;

	public IdentifiableSingleUpdater(RemoteFigure rf) {
		super(rf);
	}

	@Override
	public long getIdentifying() {
		return identifying;
	}

	@Override
	public void setIdentifying(long id) {
		identifying=id;
	}

	@Override
	protected abstract void change(DefaultDrawingView view, Figure figure);

}
