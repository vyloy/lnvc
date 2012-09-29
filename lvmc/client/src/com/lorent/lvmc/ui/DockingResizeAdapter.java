package com.lorent.lvmc.ui;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DockingResizeAdapter extends ResizeAdapter {

	

	public DockingResizeAdapter(Component c,Component w) {
		super(c,w,false);
	}
	
//	public void mouseReleased(MouseEvent e) {
//		isTopLeft = isTop = isTopRight = isRight = isBottomRight = isBottom = isBottomLeft = isLeft = false;
//		c.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
//	}

	
	@Override
	public void mouseDragged(MouseEvent event) {
		int x = event.getX();
		int y = event.getY();
		int cy = c.getLocation().y;
		y = y + cy;
		super.setComoponetBound(x, y, w);
		
	}
	
	
	
	private void setSize(Component c,int w,int h){
		if(c.getMinimumSize().width<=w){
			return ;
		}
		if(c.getMinimumSize().height<=h){
			return;
		}
		c.setSize(w, h);
	}
	
}
