package org.jhotdraw.samples.svg.figures;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jhotdraw.draw.AttributeKey;
import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.Figure;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ChangeFiguresAttributes extends MultiUpdater {
	private static final long serialVersionUID = 1L;
	public static final int TYPE = 11;
	private Map<AttributeKey, Object> attributes=new HashMap<AttributeKey, Object>();
	
	public Map<AttributeKey, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<AttributeKey, Object> attributes) {
		this.attributes.putAll(attributes);
	}
	
	public void putAttributes(AttributeKey key,Object value){
		this.attributes.put(key, value);
	}

	public void change(Figure f) {
		for (Map.Entry<AttributeKey, Object> entry : attributes.entrySet()) {
            f.setAttribute(entry.getKey(), entry.getValue());
        }
	}

	@Override
	protected void change(DefaultDrawingView view, List<Figure> figures) {
		for(Figure f:figures){
			change(f);
		}
	}
	
	@Override
	public int getType() {
		return TYPE;
	}

	@Override
	public JSONObject toJSON() {
		JSONObject result = super.toJSON();
		HashMap<String, Object> attr = new HashMap<String,Object>();
		for (Entry<AttributeKey, Object> entry : attributes.entrySet()) {
			attr.put(entry.getKey().getKey(), entry.getValue());
		}
		result.put("attributes", attr);
		return result;
	}
}
