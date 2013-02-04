package org.jhotdraw.samples.svg.figures;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.swing.SwingUtilities;

import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.Figure;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lorent.whiteboard.model.Initializable;
import com.lorent.whiteboard.model.RemoteFigure;

public abstract class MultiUpdater extends AbstractNormalResultUpdater implements Initializable {

	private static final long serialVersionUID = 1L;
	
	private List<Long> ids=new LinkedList<Long>();
	
	private transient List<RemoteFigure> figures=new LinkedList<RemoteFigure>();

	@Override
	public void change(final DefaultDrawingView view) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				final LinkedList<Figure> figures = new LinkedList<Figure>();
				for(Long id:ids){
					for(Figure f:view.getDrawing().getFigures()){
						if(!(f instanceof RemoteFigure)){
							continue;
						}
						RemoteFigure rf=(RemoteFigure) f;
						if(!rf.isNeedToSetId()&&rf.getId()==id){
							figures.add(f);
						}
					}
				}
				change(view,figures);
			}
			
		});
	}
	
	protected abstract void change(DefaultDrawingView view,List<Figure> figures);
	
	public MultiUpdater add(RemoteFigure figure){
		if(figure==null){
			throw new IllegalArgumentException("figure == null!");
		}
		figures.add(figure);
		return this;
	}
	
	public MultiUpdater add(Figure figure){
		if(figure==null){
			throw new IllegalArgumentException("figure == null!");
		}
		if(figure instanceof RemoteFigure){
			add((RemoteFigure)figure);
		}
		return this;
	}
	
	public MultiUpdater add(Collection<Figure> figures){
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
	
	public boolean isEmpty(){
		return figures.isEmpty();
	}
	
	@Override
	public void initialize() {
		for(RemoteFigure rf:figures){
			ids.add(rf.getId());
		}
	}
	
	public void addId(Long id){
		ids.add(id);
	}
	
	public void addIds(Collection<Long> ids){
		ids.addAll(ids);
	}
	
	@Override
	public JSONObject toJSON() {
		JSONObject result = new JSONObject(true);
		JSONArray array = new JSONArray(ids.size());
		for(Long id:ids){
			array.add(id);
		}
		result.put("ids", array);
		return result;
	}
}
