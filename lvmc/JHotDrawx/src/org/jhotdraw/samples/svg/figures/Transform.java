package org.jhotdraw.samples.svg.figures;

import java.awt.Container;
import java.awt.geom.AffineTransform;

import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.Figure;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lorent.whiteboard.model.RemoteFigure;

public class Transform extends IdentifiableSingleUpdater {

	private static final long serialVersionUID = 1L;

	public static final int TYPE = 7;
	
	private AffineTransform affineTransform;
	
	public Transform(RemoteFigure rf, AffineTransform affineTransform) {
		super(rf);
		this.affineTransform=affineTransform;
	}


	@Override
	public void change(DefaultDrawingView view,Figure f) {
		f.translate(new IdentifiableTranslate(identifying, affineTransform));
		Container c = view.getContainer();
        c.invalidate();
        if (c.getParent() != null) c.getParent().validate();
	}


	public AffineTransform getAffineTransform() {
		return affineTransform;
	}


	public void setAffineTransform(AffineTransform affineTransform) {
		this.affineTransform = affineTransform;
	}

	@Override
	public int getType() {
		return TYPE;
	}
	
	@Override
	public JSONObject toJSON() {
		JSONArray array = new JSONArray(6);
		array.add(affineTransform.getScaleX());
		array.add(affineTransform.getShearY());
		array.add(affineTransform.getShearX());
		array.add(affineTransform.getScaleY());
		array.add(affineTransform.getTranslateX());
		array.add(affineTransform.getTranslateY());
		JSONObject result = super.toJSON();
		result.put("transform", array);
		return result;
	}
}
