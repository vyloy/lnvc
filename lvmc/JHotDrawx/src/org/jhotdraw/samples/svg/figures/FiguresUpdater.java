package org.jhotdraw.samples.svg.figures;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.swing.SwingUtilities;

import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.Figure;

import com.lorent.whiteboard.command.impl.BroadcastFiguresResultCommand;
import com.lorent.whiteboard.command.impl.BroadcastResultCommand;
import com.lorent.whiteboard.model.Initializable;
import com.lorent.whiteboard.model.RemoteFigure;
import com.lorent.whiteboard.model.RemoteFigures;
import com.lorent.whiteboard.model.Updater;
import com.lorent.whiteboard.model.Whiteboard;

public abstract class FiguresUpdater implements Updater<DefaultDrawingView>,RemoteFigures,Initializable {

	private static final long serialVersionUID = 1L;
	
	protected List<RemoteFigure> figures=new LinkedList<RemoteFigure>();

	public FiguresUpdater add(RemoteFigure figure){
		if(figure==null){
			throw new IllegalArgumentException("figure == null!");
		}
		figures.add(figure);
		return this;
	}
	
	public FiguresUpdater add(Figure figure){
		if(figure==null){
			throw new IllegalArgumentException("figure == null!");
		}
		if(figure instanceof RemoteFigure){
			add((RemoteFigure)figure);
		}
		return this;
	}
	
	public FiguresUpdater add(Collection<Figure> figures){
		if(figures==null){
			throw new IllegalArgumentException("figures == null!");
		}
		for(Figure f:figures){
			if(f instanceof RemoteFigure){
				RemoteFigure rf=(RemoteFigure) f;
				this.figures.add(rf);
			}
		}
		return this;
	}

	@Override
	public List<RemoteFigure> getRemoteFigures() {
		return figures;
	}
	
	@Override
	public BroadcastResultCommand createResultCommand(Whiteboard board,long oid,long id) {
		List<Long> ids=new LinkedList<Long>();
		for(RemoteFigure f:this.figures){
			long fid = board.generateRemoteFigureId();
			f.setId(fid);
			ids.add(fid);
		}
		BroadcastResultCommand resultCommand = new BroadcastFiguresResultCommand(board.getId(),oid,id,ids);
		return resultCommand;
	}

	@Override
	public void initialize() {
	}

	@Override
	public void change(final DefaultDrawingView view) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				changeView(view);
			}
			
		});
	}
	public abstract void changeView(DefaultDrawingView view);
}
