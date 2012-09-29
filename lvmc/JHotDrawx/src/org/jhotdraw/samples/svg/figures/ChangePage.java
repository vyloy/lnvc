package org.jhotdraw.samples.svg.figures;

import org.jhotdraw.draw.DefaultDrawingView;

import com.lorent.whiteboard.command.impl.State;
import com.lorent.whiteboard.model.StateUpdater;

public class ChangePage extends AbstractNormalResultUpdater implements StateUpdater<DefaultDrawingView>{

	private static final long serialVersionUID = 1L;
	
	private int page;
	
	public ChangePage(int page) {
		this.page = page;
	}

	@Override
	public void change(DefaultDrawingView view) {
		view.setPage(page,false);
	}

	@Override
	public int getStateType() {
		return State.ChangePage.ordinal();
	}

}
