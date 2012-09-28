package com.lorent.lvmc.ui;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrameResizeAdapter extends ResizeAdapter {

	
	Point loc = null;
	Point tmp = null;
	boolean isDragged = false;

	public MainFrameResizeAdapter(Component c,Component w) {
		super(c,w);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
//		System.out.println("mouse pressed ==========" + isTopLeft + isTopRight + isTop);
		if(!isTopLeft && !isTopRight && !isTop && !isRight && !isLeft && c instanceof MainFrame){
			MainFrame frame = (MainFrame)c;
			javax.swing.JPanel panel =  frame.getTitlePanel();
			Point p = panel.getLocation();
			int x = e.getX();
			int y = e.getY();
//			System.out.println("x========="+x+";"+"y============"+y+";"+"px======="+p.x+";"+"py========="+p.y+";"+"panel.size======="+panel.getSize().width+":"+panel.getSize().height);
			if(x>p.x && x<p.x + panel.getSize().width){
				if(y>p.y && y<p.y + panel.getSize().height){
					tmp = new Point(x, y);
					if(frame.getIsMaximized()!=1){
						isDragged = true;
						frame.setCursor(new Cursor(Cursor.MOVE_CURSOR));
					}
				}
			}
		}
	}

	public boolean isMainFrameMaximized(){
		if(w instanceof MainFrame){
			MainFrame frame = (MainFrame)w;
			if(frame.getIsMaximized() == 1){
				return true;
			}
		}
		return false;
	}
	
	

	@Override
	public void mouseDragged(MouseEvent event) {
		super.mouseDragged(event);
		if (isDragged && c instanceof MainFrame) {
			MainFrame frame = (MainFrame)c;
			loc = new Point(
					frame.getLocation().x + event.getX() - tmp.x,
					frame.getLocation().y + event.getY() - tmp.y);
			frame.setLocation(loc);
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(c instanceof MainFrame){
			isDragged = false;
		}
	}
	
	
	
}
