package com.lorent.whiteboard.server.text;

import static org.jhotdraw.draw.AttributeKeys.FILL_COLOR;
import static org.jhotdraw.draw.AttributeKeys.FONT_SIZE;
import static org.jhotdraw.draw.AttributeKeys.*;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import org.jhotdraw.draw.AttributeKey;
import org.jhotdraw.draw.AttributeKeys;
import org.jhotdraw.draw.BezierFigure;
import org.jhotdraw.draw.Figure;
import org.jhotdraw.geom.BezierPath;
import org.jhotdraw.samples.svg.SVGUtil;
import org.jhotdraw.samples.svg.figures.ChangeAttribute;
import org.jhotdraw.samples.svg.figures.ChangeFiguresAttributes;
import org.jhotdraw.samples.svg.figures.Delete;
import org.jhotdraw.samples.svg.figures.SVGEllipse;
import org.jhotdraw.samples.svg.figures.SVGImage;
import org.jhotdraw.samples.svg.figures.SVGLine;
import org.jhotdraw.samples.svg.figures.SVGPath;
import org.jhotdraw.samples.svg.figures.SVGRect;
import org.jhotdraw.samples.svg.figures.SVGText;
import org.jhotdraw.samples.svg.figures.Transform;
import org.jhotdraw.samples.svg.figures.TransformFigures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;
import com.lorent.whiteboard.command.impl.Attend;
import com.lorent.whiteboard.command.impl.BroadcastCloseWhiteboardCommand;
import com.lorent.whiteboard.command.impl.BroadcastCommand;
import com.lorent.whiteboard.command.impl.CreateWhiteboard;
import com.lorent.whiteboard.command.impl.SyncCheckCommand;
import com.lorent.whiteboard.model.Updater;

public class TextDeserializer {
	private static final Logger logger = LoggerFactory
			.getLogger(TextDeserializer.class);
	
	public Object deserialize(String text){
		if(text==null||text.isEmpty())
			return null;
		try{
			JSONObject jsonObject = JSON.parseObject(text);
			int type = jsonObject.getIntValue("type");
			switch(type){
			case BroadcastCommand.TYPE + SVGLine.TYPE:
				return generateLine(jsonObject);
				
			case BroadcastCommand.TYPE + SVGPath.TYPE:
				return generatePath(jsonObject);
			
			case BroadcastCommand.TYPE + SVGEllipse.TYPE:
				return generateEllipse(jsonObject);
			
			case BroadcastCommand.TYPE + SVGRect.TYPE:
				return generateRect(jsonObject);
			
			case BroadcastCommand.TYPE + SVGImage.TYPE:
				return generateImage(jsonObject);
			
			case BroadcastCommand.TYPE + SVGText.TYPE:
				return generateText(jsonObject);
				
			case BroadcastCommand.TYPE + Transform.TYPE:
				return null;
			
			case BroadcastCommand.TYPE + TransformFigures.TYPE:
				return generateTransformFigures(jsonObject);
			
			case BroadcastCommand.TYPE + Delete.TYPE:
				return generateDelete(jsonObject);
			
			case BroadcastCommand.TYPE + ChangeAttribute.TYPE:
				return generateChangeAttribute(jsonObject);
			
			case BroadcastCommand.TYPE + ChangeFiguresAttributes.TYPE:
				return generateChangeFiguresAttributes(jsonObject);
			}
			
			String meetingId=getMeetingId(jsonObject);
			switch(type){
			case Attend.TYPE:
				Attend attend = new Attend(meetingId);
				return attend;
				
			case CreateWhiteboard.TYPE:
				String whiteboardId=getWhiteboardId(jsonObject);
				CreateWhiteboard createWhiteboard;
				if(whiteboardId==null||whiteboardId.isEmpty()){
					createWhiteboard = new CreateWhiteboard(meetingId);
				}else{
					createWhiteboard = new CreateWhiteboard(meetingId,whiteboardId);
				}
				return createWhiteboard;
				
			case SyncCheckCommand.TYPE:
				return new SyncCheckCommand(meetingId, 
						(Map)jsonObject.getJSONObject("commandIds"),
						(Map)jsonObject.getJSONObject("states"));
				
			case BroadcastCloseWhiteboardCommand.TYPE:
				whiteboardId=getWhiteboardId(jsonObject);
				BroadcastCloseWhiteboardCommand command = new BroadcastCloseWhiteboardCommand(meetingId, whiteboardId);
				command.setCommandId(jsonObject.getLongValue("commandId"));
				return command;
				
			}
		}catch(RuntimeException e){
			logger.error("Data received is illegal!",e);
		}
		logger.info("Can't deserialize {}",text);
		return null;
	}
	
	private BroadcastCommand generateChangeAttribute(JSONObject jsonObject) {
		JSONObject updater = jsonObject.getJSONObject("updater");
		String key = updater.getString("key");
		AttributeKey prototype = AttributeKeys.supportedAttributeMap.get(key);
		Object value = TypeUtils.cast(updater.get("value"), prototype
				.getDefaultValue().getClass(), null);
		ChangeAttribute changeAttribute = new ChangeAttribute(
				updater.getLongValue("id"), new AttributeKey<Object>(key),
				value);
		return generateBroadcastCommand(jsonObject, changeAttribute);
	}

	private BroadcastCommand generateChangeFiguresAttributes(JSONObject jsonObject) {
		JSONObject updater = jsonObject.getJSONObject("updater");
		ChangeFiguresAttributes changeFiguresAttributes = new ChangeFiguresAttributes();
		JSONArray ids = updater.getJSONArray("ids");
		for (int i = 0; i < ids.size(); i++) {
			changeFiguresAttributes.addId(ids.getLong(i));
		}
		JSONObject attrs = updater.getJSONObject("attributes");
		for (Entry<String, Object> entry : attrs.entrySet()) {
			String key = entry.getKey();
			AttributeKey<Object> attributeKey = new AttributeKey<Object>(key);
			AttributeKey prototype = AttributeKeys.supportedAttributeMap.get(key);
			Object value = TypeUtils.cast(entry.getValue(), prototype.getDefaultValue().getClass(), null);
			changeFiguresAttributes.putAttributes(attributeKey, value);
		}
		return generateBroadcastCommand(jsonObject, changeFiguresAttributes);
	}

	public BroadcastCommand generateText(JSONObject jsonObject) {
		JSONObject updater = jsonObject.getJSONObject("updater");
		SVGText text = new SVGText();
		text.setText(updater.getString("text"));
		text.basicSetBounds(new Point2D.Double(updater.getDoubleValue("x"), updater.getDoubleValue("y")), null);
		FONT_SIZE.set(text, updater.getDouble("size"));
		initColor(updater, text);
		initAttributes(updater, text);
		return generateBroadcastCommand(jsonObject, text);
	}

	private BroadcastCommand generateTransformFigures(JSONObject jsonObject) {
		JSONObject updater = jsonObject.getJSONObject("updater");
		JSONArray ids = updater.getJSONArray("ids");
		TransformFigures tf = new TransformFigures();
		for (int i = 0; i < ids.size(); i++) {
			tf.addId(ids.getLong(i));
		}
		JSONArray t = updater.getJSONArray("transform");
		tf.setAffineTransform(new AffineTransform(t.getDoubleValue(0), t
				.getDoubleValue(1), t.getDoubleValue(2), t.getDoubleValue(3), t
				.getDoubleValue(4), t.getDoubleValue(5)));
		return generateBroadcastCommand(jsonObject, tf);
	}

	private BroadcastCommand generateDelete(JSONObject jsonObject) {
		JSONArray updater = jsonObject.getJSONArray("updater");
		Delete delete = new Delete();
		for (int i = 0; i < updater.size(); i++) {
			delete.addId(updater.getLong(i));
		}
		return generateBroadcastCommand(jsonObject, delete);
	}

	private BroadcastCommand generateRect(JSONObject jsonObject) {
		JSONObject updater = jsonObject.getJSONObject("updater");
		SVGRect rect = new SVGRect();
		double x=updater.getDoubleValue("x");
		double y=updater.getDoubleValue("y");
		double width=updater.getDoubleValue("width");
		double height=updater.getDoubleValue("height");
		rect.setBounds(new Point2D.Double(x,y), new Point2D.Double(x+width,y+height));
		rect.setArc(updater.getDoubleValue("rx"), updater.getDoubleValue("ry"));
		initColor(updater,rect);
		initAttributes(updater, rect);
		return generateBroadcastCommand(jsonObject, rect);
	}
	
	private BroadcastCommand generateEllipse(JSONObject jsonObject) {
		JSONObject updater = jsonObject.getJSONObject("updater");
		SVGEllipse ellipse = new SVGEllipse();
		double rx, ry;
		rx = updater.getDoubleValue("rx");
		ry = updater.getDoubleValue("ry");
        double x = updater.getDoubleValue("cx") - rx;
        double y = updater.getDoubleValue("cy") - ry;
        double w = rx * 2d;
        double h = ry * 2d;
        ellipse.setBounds(new Point2D.Double(x,y), new Point2D.Double(x+w,y+h));
        initColor(updater,ellipse);
        initAttributes(updater, ellipse);
		return generateBroadcastCommand(jsonObject, ellipse);
	}

	private BroadcastCommand generateImage(JSONObject jsonObject) {
		JSONObject updater = jsonObject.getJSONObject("updater");
		
		String data = updater.getString("data");
		String type = updater.getString("type");
		
		byte[] bytes = SVGImage.decode(data);
		SVGImage image;
		try {
			image = new SVGImage(ImageIO.read(new ByteArrayInputStream(bytes)),type);
		} catch (IOException e) {
			image=SVGImage.getLoadingImage();
		}
		return generateBroadcastCommand(jsonObject, image);
	}

	private BroadcastCommand generatePath(JSONObject jsonObject){
		JSONObject updater = jsonObject.getJSONObject("updater");
		SVGPath path = new SVGPath();
		java.util.List<BezierPath> paths;
		try {
			paths = SVGUtil.fromPathData(updater.getString("d"));
		} catch (IOException e) {
			logger.error("deserialized svgpath failed!",e);
			throw new RuntimeException(e);
		}
		for (BezierPath b : paths) {
			BezierFigure child = new BezierFigure();
			child.basicSetBezierPath(b);
			path.basicAdd(child);
		}
		if (paths.size() == 0) {
			BezierFigure child = new BezierFigure();
			path.basicAdd(child);
		}
		Color stroke = getColor(updater.getString("stroke"));
		STROKE_COLOR.set(path, stroke);
		initAttributes(updater, path);
		return generateBroadcastCommand(jsonObject, path);
	}
	
	private BroadcastCommand generateLine(JSONObject jsonObject){
		JSONObject updater = jsonObject.getJSONObject("updater");
		SVGLine line = new SVGLine();
		line.setBounds(
				new Point.Double(updater.getDoubleValue("x1"), updater
						.getDoubleValue("y1")),
				new Point.Double(updater.getDoubleValue("x2"), updater
						.getDoubleValue("y2")));
		Color stroke = getColor(updater.getString("stroke"));
		STROKE_COLOR.set(line, stroke);
		initAttributes(updater, line);
		return generateBroadcastCommand(jsonObject, line);
	}
	
	private BroadcastCommand generateBroadcastCommand(JSONObject jsonObject, Updater<?> u){
		BroadcastCommand command = new BroadcastCommand(
				getMeetingId(jsonObject), u, getCommandId(jsonObject),
				getWhiteboardId(jsonObject), getPage(jsonObject));
		return command;
	}
	
	private String getMeetingId(JSONObject jsonObject){
		String meetingId=jsonObject.getString("meetingId");
		return meetingId;
	}
	
	private String getWhiteboardId(JSONObject jsonObject){
		String whiteboardId = jsonObject.getString("whiteboardId");
		return whiteboardId;
	}
	
	private long getCommandId(JSONObject jsonObject){
		long result = jsonObject.getLongValue("commandId");
		return result;
	}
	
	private int getPage(JSONObject jsonObject){
		int result = jsonObject.getIntValue("page");
		return result;
	}
	
	private Color getColor(String text){
		if(text==null||text.isEmpty())
			return null;
		Color color = JSON.parseObject(text, Color.class);
		return color;
	}
	
	private void initAttributes(JSONObject updater,Figure f){
		initStrokeWidth(updater,f);
	}
	
	private void initStrokeWidth(JSONObject updater,Figure f){
		Double value = updater.getDouble("strokeWidth");
		if(value!=null)
			STROKE_WIDTH.set(f, value);
	}
	
	private void initColor(JSONObject updater,Figure f){
		Color stroke = getColor(updater.getString("stroke"));
		STROKE_COLOR.set(f, stroke);
		Color fill = getColor(updater.getString("fill"));
		FILL_COLOR.set(f, fill);
	}
}
