/*
 * @(#)SVGImage.java  1.0  July 8, 2006
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
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.util.Map;
import java.util.WeakHashMap;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.jhotdraw.draw.AttributeKeys;
import org.jhotdraw.draw.AttributedFigure;
import org.jhotdraw.geom.Geom;
import org.jhotdraw.samples.svg.SVGUtil;

import com.alibaba.fastjson.JSONObject;
import com.lorent.whiteboard.model.Detailed;

/**
 * SVGImage.
 * <p>
 * FIXME - Implement me
 *
 * @author Werner Randelshofer
 * @version 1.0 July 8, 2006 Created.
 */
public class SVGImage extends AttributedFigure implements SVGFigure,Detailed,Unselectable{
	private static final long serialVersionUID = 1L;
	public static final int TYPE = 5;
	private Rectangle2D.Double bounds;
	private String imageType;
	private BufferedImage image;
	private transient Map<Double,BufferedImage> scaledImages=new WeakHashMap<Double,BufferedImage>();
    
    /** Creates a new instance. */
    public SVGImage() {
    	this(null,"jpeg");
    }
    
    public SVGImage(BufferedImage image) {
    	this(image,"jpeg");
    }
    
    public SVGImage(BufferedImage image,String imageType) {
		setImage(image);
		bounds=new Rectangle2D.Double(0, 0, image.getWidth(), image.getHeight());
		this.imageType=imageType;
		SVGUtil.setDefaults(this);
	}

    @Override
	public void draw(Graphics2D g) {
    	AffineTransform t = g.getTransform();
    	double scaleX = t.getScaleX();
    	if(scaleX==1.0){
    		g.drawImage(image, null, 0, 0);
    		return;
    	}
    	g.scale(1/scaleX, 1/scaleX);
    	BufferedImage scaledImage = scaledImages.get(scaleX);
    	if(scaledImage==null){
    		int w=(int) (image.getWidth()*scaleX);
    		int h=(int) (image.getHeight()*scaleX);
    		scaledImage = new BufferedImage(w,h,image.getType());
    		Graphics2D g2 = scaledImage.createGraphics();
    		g2.drawImage(image, 0, 0, w, h, null);
    		g2.dispose();
    		scaledImages.put((Double)scaleX, scaledImage);
    	}
    	g.drawImage(scaledImage, null, 0, 0);
		g.setTransform(t);
	}

    protected Object readResolve() throws ObjectStreamException{
    	super.readResolve();
    	this.scaledImages=new WeakHashMap<Double,BufferedImage>();
		return this;
    }

    
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		if (image == null)
			throw new IllegalArgumentException("image == null!");
		this.image = image;
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException{
		out.writeObject(imageType);
		out.writeObject(bounds);
		ImageIO.write(image, imageType, out);
	}
	
	private void readObject(ObjectInputStream in)throws IOException, ClassNotFoundException{
		imageType=(String) in.readObject();
		bounds=(Rectangle2D.Double) in.readObject();
		image=ImageIO.read(in);
	}

	public void basicTransform(AffineTransform tx) {
		Point2D.Double anchor = getStartPoint();
        Point2D.Double lead = getEndPoint();
        basicSetBounds(
                (Point2D.Double) tx.transform(anchor, anchor),
                (Point2D.Double) tx.transform(lead, lead)
                );
    }

    public void basicSetBounds(Point2D.Double anchor, Point2D.Double lead) {
    	 bounds.x = Math.min(anchor.x, lead.x);
         bounds.y = Math.min(anchor.y , lead.y);
         bounds.width = Math.max(0.1, Math.abs(lead.x - anchor.x));
         bounds.height = Math.max(0.1, Math.abs(lead.y - anchor.y));
    }

    public Rectangle2D.Double getBounds() {
        return (Rectangle2D.Double) bounds.clone();
    }


	@Override
	public Rectangle2D.Double getFigureDrawBounds() {
		double grow = AttributeKeys.getPerpendicularHitGrowth(this);
		Rectangle2D.Double b = getBounds();
		Geom.grow(b, grow, grow);
		return b;
	}

	public Object getRestoreData() {
        return getBounds();
    }

    public void restoreTo(Object restoreData) {
        bounds = (Rectangle2D.Double) ((Rectangle2D.Double) restoreData).clone();
    }

    public boolean contains(Point2D.Double p) {
        return bounds.contains(p);
    }
    
    public SVGImage clone() {
        SVGImage that = (SVGImage) super.clone();
        that.bounds = (Rectangle2D.Double) bounds.clone();
        that.scaledImages=new WeakHashMap<Double, BufferedImage>();
        return that;
    }
    
    public boolean isEmpty() {
        return false;
    }


	@Override
	protected void drawFill(Graphics2D g) {
		
	}

	@Override
	protected void drawStroke(Graphics2D g) {
		
	}
    
	@Override
	public Cursor getCursor(Point2D.Double p) {
		return Cursor.getDefaultCursor();
	}

	public static SVGImage getLoadingImage(){
		BufferedImage image;
		try {
			image = ImageIO.read(SVGImage.class.getResource("loading.jpg"));
		} catch (Exception e) {
			String text="正在加载";
			Font font = new Font("宋体",Font.PLAIN,18);
			image=new BufferedImage(150,100,BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = (Graphics2D) image.createGraphics();
			g.setColor(Color.black);
			g.setFont(font);
			int w = g.getFontMetrics().stringWidth(text);
			int h = g.getFontMetrics().getHeight();
			g.drawString(text,10, h);
			g.dispose();
		}
		return new SVGImage(image);
	}
	
	@Override
	public int getType() {
		return TYPE;
	}
	
	public JSONObject toJSON(boolean detailed) {
		JSONObject object = new JSONObject(true);
		object.put("type", imageType);
		if(detailed){
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			try {
				ImageIO.write(image, imageType, out);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			byte[] data = out.toByteArray();
			object.put("data", encode(data));
		}else{
			object.put("data", "...");
		}
		return object;
	}
	
	@Override
	public JSONObject toJSON() {
		return toJSON(false);
	}

	@Override
	public String toDetailedString() {
		return toJSON(true).toJSONString();
	}

	public static String encode(byte[] b){
		return Base64.encodeBase64String(b);
	}
	
	public static byte[] decode(String s){
		return Base64.decodeBase64(s);
	}
	
}
