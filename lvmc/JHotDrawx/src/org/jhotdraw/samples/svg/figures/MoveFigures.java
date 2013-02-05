package org.jhotdraw.samples.svg.figures;

import java.awt.geom.Point2D;
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
	private Map<Long,Point2D.Double> data=new HashMap<Long,Point2D.Double>();
	public static final int TYPE=14;
	
	@Override
	public void change(final DefaultDrawingView view) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				Collection<Figure> figures = view.getDrawing().getFigures();
				OUTER:
				for(Map.Entry<Long, Point2D.Double> e:data.entrySet()){
					Long id = e.getKey();
					for(Figure f:figures){
						if(!(f instanceof RemoteFigure)){
							continue;
						}
						RemoteFigure rf=(RemoteFigure) f;
						if(!rf.isNeedToSetId()&&rf.getId()==id){
							java.awt.geom.Point2D.Double p = e.getValue();
							Double bounds = f.getBounds();
							Double b = new Double(p.x,p.y,bounds.width,bounds.height);
							((AbstractFigure) f).setBounds(b);
							continue OUTER;
						}
					}
				}
			}
			
		});
	}
	
	public void add(RemoteFigure f){
		Double bounds = f.getBounds();
		data.put(f.getId(), new Point2D.Double(bounds.x,bounds.y));
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
		for(Map.Entry<Long, Point2D.Double> e:data.entrySet()){
			StringBuilder s = new StringBuilder();
			Point2D.Double v = e.getValue();
			s.append(v.x);
			s.append(",");
			s.append(v.y);
			result.put(String.valueOf(e.getKey()), s.toString());
		}
		return result;
	}
}
