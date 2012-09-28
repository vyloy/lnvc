package org.jhotdraw.samples.svg.figures;

import java.util.Collection;

import javax.swing.SwingUtilities;

import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.Figure;

import com.alibaba.fastjson.JSONObject;
import com.lorent.whiteboard.model.Initializable;
import com.lorent.whiteboard.model.RemoteFigure;
import com.lorent.whiteboard.model.Updater;

public abstract class SingleUpdater extends AbstractNormalResultUpdater implements Updater<DefaultDrawingView>,Initializable{

	private static final long serialVersionUID = 1L;
	protected long id;
	private transient RemoteFigure figure;

	public SingleUpdater(RemoteFigure rf) {
		if(rf==null)
			throw new IllegalArgumentException("rf == null!");
		this.figure=rf;
	}
	
	public SingleUpdater(long id){
		this.id=id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public void change(final DefaultDrawingView view){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Collection<Figure> figures = view.getDrawing().getFigures();
				for (final Figure f : figures) {
					if (!(f instanceof RemoteFigure)) {
						continue;
					}
					RemoteFigure rf = (RemoteFigure) f;
					if (!rf.isNeedToSetId()&&rf.getId()==id) {
						change(view, f);
						break;
					}
				}
			}

		});
	}
	
	@Override
	public void initialize() {
		if(figure!=null)
			this.id = figure.getId();
	}

	protected abstract void change(DefaultDrawingView view, Figure figure);
	
	@Override
	public JSONObject toJSON() {
		JSONObject result = new JSONObject(true);
		result.put("id", id);
		return result;
	}

}