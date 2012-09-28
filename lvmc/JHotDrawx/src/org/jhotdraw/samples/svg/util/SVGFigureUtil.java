package org.jhotdraw.samples.svg.util;

import java.awt.Color;

import org.jhotdraw.draw.AttributeKeys;
import org.jhotdraw.draw.Figure;

import com.alibaba.fastjson.JSONObject;

public class SVGFigureUtil {
	public static void addAttributesToJSONObject(Figure f,JSONObject o){
		Color color = AttributeKeys.STROKE_COLOR.get(f);
		if(color!=null){
			o.put("stroke",color);
		}
		color = AttributeKeys.FILL_COLOR.get(f);
		if(color!=null){
			o.put("fill",color);
		}
		Double strokeWidth = AttributeKeys.STROKE_WIDTH.get(f);
		if(strokeWidth!=null){
			o.put("strokeWidth", strokeWidth);
		}
	}
}
