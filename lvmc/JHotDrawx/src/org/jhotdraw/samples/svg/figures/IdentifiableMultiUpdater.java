package org.jhotdraw.samples.svg.figures;

import java.util.List;

import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.Figure;

import com.lorent.whiteboard.model.Identifiable;

public abstract class IdentifiableMultiUpdater extends MultiUpdater implements
		Identifiable {

	private static final long serialVersionUID = 1L;

	protected long identifying;
	
	@Override
	public long getIdentifying() {
		return identifying;
	}

	@Override
	public void setIdentifying(long id) {
		identifying=id;
	}

	@Override
	protected abstract void change(DefaultDrawingView view, List<Figure> figures);

}
