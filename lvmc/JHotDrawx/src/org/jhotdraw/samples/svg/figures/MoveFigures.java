package org.jhotdraw.samples.svg.figures;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingUtilities;

import org.jhotdraw.draw.AbstractFigure;
import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.Figure;

import com.alibaba.fastjson.JSONObject;
import com.lorent.whiteboard.model.RemoteFigure;

public class MoveFigures extends AbstractNormalResultUpdater {

	private static final long serialVersionUID = 1L;
	private Map<Long,Rectangle2D.Double> data=new HashMap<Long,Rectangle2D.Double>();
	public static final int TYPE=13;
	
	@Override
	public void change(final DefaultDrawingView view) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				Collection<Figure> figures = view.getDrawing().getFigures();
				OUTER:
				for(Map.Entry<Long, Rectangle2D.Double> e:data.entrySet()){
					Long id = e.getKey();
					for(Figure f:figures){
						if(!(f instanceof RemoteFigure)){
							continue;
						}
						RemoteFigure rf=(RemoteFigure) f;
						if(!rf.isNeedToSetId()&&rf.getId()==id){
							((AbstractFigure)f).setBounds(e.getValue());
							continue OUTER;
						}
					}
				}
			}
			
		});
	}
	
	public void add(RemoteFigure f){
		data.put(f.getId(), f.getBounds());
	}
	
	public void add(Figure f){
		if(f instanceof RemoteFigure){
			add((RemoteFigure)f);
		}
	}
	
	public void add(Collection<Figure> fs){
		for(Figure f:fs){
			add(f);
		}
	}

	@Override
	public int getType() {
		return TYPE;
	}
	
	@Override
	public JSONObject toJSON() {
		JSONObject result = new JSONObject(true);
		for(Map.Entry<Long, Rectangle2D.Double> e:data.entrySet()){
			StringBuilder s = new StringBuilder();
			Double v = e.getValue();
			s.append(v.x);
			s.append(",");
			s.append(v.y);
			s.append(",");
			s.append(v.width);
			s.append(",");
			s.append(v.height);
			result.put(String.valueOf(e.getKey()), s.toString());
		}
		return result;
	}
}
