package com.lorent.lvmc.ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import net.infonode.docking.RootWindow;
import net.infonode.docking.ViewSerializer;

public class MyRootWindow extends RootWindow{

	public MyRootWindow(boolean arg0, ViewSerializer arg1) {
		super(arg0, arg1);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3289008407692222971L;

	
	public void paintComponent(Graphics g) {
    	BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = img.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));// 设置合成规则
		g2d.setColor(Color.white);// 设置背景色
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(img, 0, 0, null);
	}
}
