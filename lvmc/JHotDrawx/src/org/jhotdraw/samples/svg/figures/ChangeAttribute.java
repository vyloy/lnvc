package org.jhotdraw.samples.svg.figures;

import org.jhotdraw.draw.AttributeKey;
import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.Figure;

import com.alibaba.fastjson.JSONObject;
import com.lorent.whiteboard.model.RemoteFigure;

public class ChangeAttribute extends SingleUpdater {
	private static final long serialVersionUID = 1L;
	public static final int TYPE = 10;
	private AttributeKey<?> key;
	private Object value;

	public ChangeAttribute(RemoteFigure rf, AttributeKey<?> key,
			Object value) {
		super(rf);
		this.key = key;
		this.value = value;
	}
	
	public ChangeAttribute(long id,AttributeKey<?> key,
			Object value){
		super(id);
		this.key = key;
		this.value = value;
	}

	public AttributeKey<?> getKey() {
		return key;
	}

	public void setKey(AttributeKey<?> key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public void change(DefaultDrawingView view,Figure f) {
		f.setAttribute(key, value);
	}

	@Override
	public int getType() {
		return TYPE;
	}
	
	@Override
	public JSONObject toJSON() {
		JSONObject result = super.toJSON();
		result.put("key",key.getKey());
		result.put("value", value);
		return result;
	}
}
