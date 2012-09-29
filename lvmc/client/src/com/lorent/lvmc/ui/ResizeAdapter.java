package com.lorent.lvmc.ui;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ResizeAdapter extends MouseAdapter {

	protected boolean isTopLeft;
	protected boolean isTop;
	protected boolean isTopRight;
	protected boolean isRight;
	protected boolean isBottomRight;
	protected boolean isBottom;
	protected boolean isBottomLeft;
	protected boolean isLeft;
	protected final static int RESIZE_WIDTH = 5;
	protected final static int MIN_WIDTH = 20;
	protected final static int MIN_HEIGHT = 20;
	protected Component c;
	protected Component w;
	protected boolean topFlag = false;
	
	public ResizeAdapter(Component c,Component w) {
		this(c, w, true);
	}
	
	public ResizeAdapter(Component c,Component w,boolean topFlag) {
		this.c = c;
		this.w = w;
		this.topFlag = topFlag;
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
	public void mouseMoved(MouseEvent event) {
		if(isMainFrameMaximized()){
			return;
		}
		int x = event.getX();
		int y = event.getY();
		int width = c.getWidth();
		int height = c.getHeight();
		int cursorType = Cursor.DEFAULT_CURSOR;
		isTopLeft = isTop = isTopRight = isRight = isBottomRight = isBottom = isBottomLeft = isLeft = false;
		if (y <= RESIZE_WIDTH) {
			if(topFlag){
				if (x <= RESIZE_WIDTH) {
					isTopLeft = true;
					cursorType = Cursor.NW_RESIZE_CURSOR;
				} else if (x >= width - RESIZE_WIDTH) {
					isTopRight = true;
					cursorType = Cursor.NE_RESIZE_CURSOR;
				} else {
					isTop = true;
					cursorType = Cursor.N_RESIZE_CURSOR;
				}
			}
		} else if (y >= height - RESIZE_WIDTH) {
			if (x <= RESIZE_WIDTH) {
				isBottomLeft = true;
				cursorType = Cursor.SW_RESIZE_CURSOR;
			} else if (x >= width - RESIZE_WIDTH) {
				isBottomRight = true;
				cursorType = Cursor.SE_RESIZE_CURSOR;
			} else {
				isBottom = true;
				cursorType = Cursor.S_RESIZE_CURSOR;
			}
		} else if (x <= RESIZE_WIDTH) {
			isLeft = true;
			cursorType = Cursor.W_RESIZE_CURSOR;
		} else if (x >= width - RESIZE_WIDTH) {
			isRight = true;
			cursorType = Cursor.E_RESIZE_CURSOR;
		}
		c.setCursor(new Cursor(cursorType));
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		int x = event.getX();
		int y = event.getY();
		setComoponetBound(x,y,w);
	}
	
	protected void setComoponetBound(int x,int y,Component w){
		int width = w.getWidth();
		int height = w.getHeight();
		if (isTopLeft) {
			if(height - y<=w.getMinimumSize().height){
				return;
			}
//			w.setLocation(w.getX() + x, w.getY() + y);
//			w.setSize(width - x, height - y);
			w.setBounds(w.getX() + x, w.getY() + y, width - x, height - y);
//			setSize(c,width - x, height - y);
		}
		if (isTop) {
			if(height - y<=w.getMinimumSize().height){
				return;
			}
//			w.setLocation(w.getX(), w.getY() + y);
//			w.setSize(width, height - y);
			w.setBounds(w.getX(), w.getY() + y, width, height - y);
//			setSize(c,width, height - y);
		}
		if (isTopRight) {
			if(height - y<=w.getMinimumSize().height){
				return;
			}
//			w.setLocation(w.getX(), w.getY() + y);
//			w.setSize(x, height - y);
			w.setBounds(w.getX(), w.getY() + y, x, height - y);
//			setSize(c,x, height - y);
		}
		if (isRight) {
//			w.setSize(x, height);
			w.setBounds(w.getX(), w.getY(), x, height);
//			setSize(c,x, height);
		}
		if (isBottomRight) {
//			w.setSize(x, y);
			w.setBounds(w.getX(), w.getY(), x, y);
//			setSize(c,x, y);
		}
		if (isBottom) {
//			w.setSize(width, y);
			w.setBounds(w.getX(), w.getY(), width, y);
//			setSize(c,width, y);
		}
		if (isBottomLeft) {
			if(width - x<=w.getMinimumSize().width){
				return;
			}
//			w.setLocation(w.getX() + x, w.getY());
//			w.setSize(width - x, y);
			w.setBounds(w.getX() + x, w.getY(), width - x, y);
//			setSize(c,width - x, y);
		}
		if (isLeft) {
			if(width - x<=w.getMinimumSize().width){
				return;
			}
//			w.setLocation(w.getX() + x, w.getY());
//			w.setSize(width - x, height);
			w.setBounds(w.getX() + x, w.getY(), width - x, height);
//			setSize(c,width - x, height);
		}
	}
	
}
