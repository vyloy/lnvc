/*
 * @(#)SVGText.java  1.0  July 8, 2006
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

import static org.jhotdraw.draw.AttributeKeys.FILL_COLOR;
import static org.jhotdraw.draw.AttributeKeys.FONT_SIZE;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import org.jhotdraw.draw.AttributeKey;
import org.jhotdraw.draw.TextFigure;
import org.jhotdraw.samples.svg.SVGUtil;
import org.jhotdraw.samples.svg.util.SVGFigureUtil;
import org.jhotdraw.xml.DOMInput;
import org.jhotdraw.xml.DOMOutput;

import com.alibaba.fastjson.JSONObject;
/**
 * SVGText.
 * <p>
 * FIXME - Add support for transforms.
 * XXX At least on Mac OS X - Always draw text using TextLayout.getOutline(),
 * because outline layout does not match with TextLayout.draw() output.
 * Cache outline to improve performance.
 *
 * @author Werner Randelshofer
 * @version 1.0 July 8, 2006 Created.
 */
public class SVGText extends TextFigure implements SVGFigure {
    public enum TextAnchor {
        START, MIDDLE, END
    }
    
    public final static AttributeKey<TextAnchor> TEXT_ANCHOR = new AttributeKey<TextAnchor>("textAnchor",TextAnchor.START, false);
	public static final int TYPE = 6;
    
    
    /** Creates a new instance. */
    public SVGText() {
        SVGUtil.setDefaults(this);
    }
    @Override protected void drawStroke(Graphics2D g) {
        if (getText() != null || isEditable()) {
            Rectangle2D.Double bounds = getBounds();
            TextLayout layout = getTextLayout();
            AffineTransform tx = new AffineTransform();
            tx.translate(origin.x, origin.y + layout.getAscent());
            Shape outline = layout.getOutline(tx);
            g.draw(outline);
        }
    }
    @Override protected void drawFill(Graphics2D g) {
        if (getText() != null || isEditable()) {
            TextLayout layout = getTextLayout();
            layout.draw(g, (float) origin.x, (float) (origin.y + layout.getAscent()));
        }
    }
    @Override protected void drawText(java.awt.Graphics2D g) {
    }
    
    @Override public void write(DOMOutput out) throws IOException {
        out.addAttribute("x", origin.x);
        out.addAttribute("y", origin.y);
        out.addText(getText());
        writeAttributes(out);
    }
    protected void writeAttributes(DOMOutput out) throws IOException {
        SVGUtil.writeAttributes(this, out);
    }
    
    @Override public void read(DOMInput in) throws IOException {
        origin.x = SVGUtil.getDimension(in, "x");
        origin.y = SVGUtil.getDimension(in, "y");
        setText(in.getText());
        readAttributes(in);
        AffineTransform tx = SVGUtil.getTransform(in, "transform");
        basicTransform(tx);
        
        Rectangle2D.Double r = getBounds();
        switch (TEXT_ANCHOR.get(this)) {
            case START :
                break;
            case MIDDLE :
                origin.x -= r.width / 2d;
                break;
            case END :
                origin.x -= r.width;
                break;
        }
//        origin.y -= FONT_SIZE.get(this);
    }
    protected void readAttributes(DOMInput in) throws IOException {
        SVGUtil.readAttributes(this, in);
    }
    public Color getTextColor() {
        return FILL_COLOR.get(this);
    }
    public Color getFillColor() {
        return Color.white;
    }
    public boolean isEmpty() {
        return getText() == null || getText().length() == 0;
    }
    
    
    @Override
	public void setText(String newText) {
		super.setText(newText);
	}

    @Override
    public int getType() {
    	return TYPE;
    }
    
    @Override
    public JSONObject toJSON() {
    	JSONObject result = super.toJSON();
    	result.put("x", origin.x);
    	result.put("y", origin.y);
    	result.put("text", getText());
    	result.put("size", FONT_SIZE.get(this));
    	SVGFigureUtil.addAttributesToJSONObject(this, result);
		return result;
    }
}
