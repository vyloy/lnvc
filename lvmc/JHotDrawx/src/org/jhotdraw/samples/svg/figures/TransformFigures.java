package org.jhotdraw.samples.svg.figures;

import java.awt.Container;
import java.awt.geom.AffineTransform;
import java.util.List;

import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.Figure;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TransformFigures extends IdentifiableMultiUpdater{

	private static final long serialVersionUID = 1L;

	public static final int TYPE = 8;
	
	private AffineTransform affineTransform;
	
	public void change(Figure f) {
		f.translate(new IdentifiableTranslate(identifying, affineTransform));
	}


	public AffineTransform getAffineTransform() {
		return affineTransform;
	}


	public void setAffineTransform(AffineTransform affineTransform) {
		this.affineTransform = affineTransform;
	}


	@Override
	protected void change(DefaultDrawingView view, List<Figure> figures) {
		for(Figure figure:figures){
			change(figure);
		}
		Container c = view.getContainer();
        c.invalidate();
        if (c.getParent() != null) c.getParent().validate();
	}

	@Override
	public int getType() {
		return TYPE;
	}
	
	@Override
	public JSONObject toJSON() {
		JSONObject result = super.toJSON();
		JSONArray array = new JSONArray(6);
		array.add(affineTransform.getScaleX());
		array.add(affineTransform.getShearY());
		array.add(affineTransform.getShearX());
		array.add(affineTransform.getScaleY());
		array.add(affineTransform.getTranslateX());
		array.add(affineTransform.getTranslateY());
		result.put("transform", array);
		return result;
	}
}
