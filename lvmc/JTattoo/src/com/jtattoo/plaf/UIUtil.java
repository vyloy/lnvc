package com.jtattoo.plaf;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class UIUtil {

	
	public static void BackgroudDraw(Graphics g, JComponent backgroundComponent,
			BufferedImage backgroundImg, JComponent targetComponent,float alpha) {
		int x = targetComponent.getLocationOnScreen().x
				- backgroundComponent.getLocationOnScreen().x;
		int y = targetComponent.getLocationOnScreen().y
				- backgroundComponent.getLocationOnScreen().y;

		BufferedImage zoomImage = new BufferedImage(backgroundComponent
				.getWidth(), backgroundComponent.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D zoom2d = zoomImage.createGraphics();
		zoom2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				alpha));
		zoom2d.drawImage(backgroundImg, 0, 0, backgroundComponent.getWidth(),
				backgroundComponent.getHeight(), null);
		zoom2d.dispose();

		BufferedImage subimage = zoomImage.getSubimage(x, y, targetComponent
				.getWidth(), targetComponent.getHeight());
		g.drawImage(subimage, 0, 0, targetComponent);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
