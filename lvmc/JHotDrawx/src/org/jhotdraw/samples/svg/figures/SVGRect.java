/*
 * @(#)SVGRect.java  1.0  July 8, 2006
 *
 * Copyright (c) 1996-2006 by the original authors of JHotDraw
 * and all its contributors ("JHotDraw.org")
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * JHotDraw.org ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * JHotDraw.org.
 */

package org.jhotdraw.samples.svg.figures;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import org.jhotdraw.draw.AttributeKeys;
import org.jhotdraw.draw.RoundRectangleFigure;
import org.jhotdraw.samples.svg.SVGUtil;
import org.jhotdraw.samples.svg.util.SVGFigureUtil;
import org.jhotdraw.xml.DOMInput;
import org.jhotdraw.xml.DOMOutput;

import com.alibaba.fastjson.JSONObject;

/**
 * SVGRect.
 * <p>
 * FIXME - Add support for transforms.
 *
 * @author Werner Randelshofer
 * @version 1.0 July 8, 2006 Created.
 */
public class SVGRect extends RoundRectangleFigure implements SVGFigure {
    
    public static final int TYPE = 4;

	/** Creates a new instance. */
    public SVGRect() {
        SVGUtil.setDefaults(this);
    }
    
    public void read(DOMInput in) throws IOException {
        double x = SVGUtil.getDimension(in, "x");
        double y = SVGUtil.getDimension(in, "y");
        double w = SVGUtil.getDimension(in, "width");
        double h = SVGUtil.getDimension(in, "height");
        setBounds(new Point2D.Double(x,y), new Point2D.Double(x+w,y+h));
        setArc(
               SVGUtil.getDimension(in, "rx"),
               SVGUtil.getDimension(in, "ry")
        );
        readAttributes(in);
        AffineTransform tx = SVGUtil.getTransform(in, "transform");
        basicTransform(tx);
    }
    protected void readAttributes(DOMInput in) throws IOException {
        SVGUtil.readAttributes(this, in);
    }
    
    public void write(DOMOutput out) throws IOException {
        Rectangle2D.Double r = getBounds();
        out.addAttribute("x", r.x);
        out.addAttribute("y", r.y);
        out.addAttribute("width", r.width);
        out.addAttribute("height", r.height);
        out.addAttribute("rx", getArcWidth());
        out.addAttribute("ry", getArcHeight());
        writeAttributes(out);
    }
    protected void writeAttributes(DOMOutput out) throws IOException {
        SVGUtil.writeAttributes(this, out);
    }
    public boolean isEmpty() {
        Rectangle2D.Double b = getBounds();
        return b.width <= 0 || b.height <= 0;
    }
    
    @Override
    public JSONObject toJSON() {
    	JSONObject result = super.toJSON();
		Rectangle2D.Double r = getBounds();
		result.put("x", r.x);
		result.put("y", r.y);
		result.put("width", r.width);
		result.put("height", r.height);
		result.put("rx", getArcWidth());
		result.put("ry", getArcHeight());
		SVGFigureUtil.addAttributesToJSONObject(this, result);
		return result;
    }
    
    @Override
    public int getType() {
    	return TYPE;
    }
}
