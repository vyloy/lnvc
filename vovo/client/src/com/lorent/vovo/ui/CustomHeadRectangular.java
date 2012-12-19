package com.lorent.vovo.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.lorent.vovo.bean.ResultImageInfo;

public class CustomHeadRectangular {

	private BufferedImage bufferedImage;
	private int px1;
	private int py1;
	private int width;
	private int imageX;
	private int imageY;
	private int imageMaxW;
	private int imageMaxH;
	private int px2;
	private int py2;
	protected boolean isTopLeft;
	protected boolean isTop;
	protected boolean isTopRight;
	protected boolean isRight;
	protected boolean isBottomRight;
	protected boolean isBottom;
	protected boolean isBottomLeft;
	protected boolean isLeft;
	private boolean isInner;
	private Component component;
	private int imageOriginX;
	private int imageOriginY;
	private int imageMaxLen;
	private ResultImageInfo resultImageInfo = new ResultImageInfo();
	
	public CustomHeadRectangular(Component c,BufferedImage bufferedImage,int px,int py){
		this.bufferedImage = bufferedImage;
		this.px1 = px;
		this.py1 = py;
		imageOriginX = px;
		imageOriginY = py;
		if(bufferedImage.getWidth()>=bufferedImage.getHeight()){
			this.width = (int)(bufferedImage.getHeight()/2);
		}else if(bufferedImage.getWidth()<bufferedImage.getHeight()){
			this.width = (int)(bufferedImage.getWidth()/2);
		}
		imageMaxW = bufferedImage.getWidth();
		imageMaxH = bufferedImage.getHeight();
		px2 = px1 + width;
		py2 = py1 + width;
		component = c;
		imageMaxLen = imageMaxW<=imageMaxH ? imageMaxW:imageMaxH;
	}
	
	public ResultImageInfo draw(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
//		confirmArea();
		int ox = px1<=px2?px1:px2;
		int oy = py1<=py2?py1:py2;
		
		imageX = ox - imageOriginX;
		imageY = oy - imageOriginY;
//		System.out.println("imageX:"+imageX + "; imageY:"+imageY+"; ox:"+ox+"; oy:"+oy);
		try{
			Color color = g2.getColor();
			Color color1 = new Color(0x138bee);
			g2.setColor(color1);
			g2.drawRect(ox-1, oy-1, width+1, width+1);
			g2.setColor(color);
			resultImageInfo.setResultImage(bufferedImage.getSubimage(imageX, imageY, width, width));
			resultImageInfo.setX(imageX);
			resultImageInfo.setY(imageY);
			resultImageInfo.setWidth(width);
			g2.drawImage(resultImageInfo.getResultImage(), ox, oy, null);
			return resultImageInfo;
		}catch(Exception ex){
			System.out.println("图片无法获取");
			g2.drawRect(ox, oy, width, width);
			return null;
		}
		
	}

	public void mouseMoved(MouseEvent evt) {
		int x = evt.getX();
		int y = evt.getY();
		System.out.println();
		isInner=isTopLeft = isTop = isTopRight = isRight = isBottomRight = isBottom = isBottomLeft = isLeft = false;
		int cursorType = Cursor.DEFAULT_CURSOR;
		if(y==py1){
			if(x>px1 && x<px2){
				isTop = true;
				cursorType = Cursor.N_RESIZE_CURSOR;
			}else if(x==px1){
				isTopLeft = true;
				cursorType = Cursor.NW_RESIZE_CURSOR;
			}else if(x==px2){
				isTopRight = true;
				cursorType = Cursor.NE_RESIZE_CURSOR;
			}
		}else if(y==py2){
			if(x>px1 && x<px2){
				isBottom = true;
				cursorType = Cursor.S_RESIZE_CURSOR;
			}else if(x==px1){
				isBottomLeft = true;
				cursorType = Cursor.SW_RESIZE_CURSOR;
			}else if(x==px2){
				isBottomRight = true;
				cursorType = Cursor.SE_RESIZE_CURSOR;
			}
		}else if(x==px1){
			if(y>py1 && y<py2){
				isLeft = true;
				cursorType = Cursor.W_RESIZE_CURSOR;
			}
		}else if(x==px2){
			if(y>py1 && y<py2){
				isRight = true;
				cursorType = Cursor.E_RESIZE_CURSOR;
			}
		}else if(x>px1 && x<px2 && y>py1 && y<py2){
			isInner = true;
			cursorType = Cursor.MOVE_CURSOR;
		}
		component.setCursor(new Cursor(cursorType));
	}
	
	 public void confirmArea()
	  {
	    
	    if (this.px2 < this.px1) {
	      if (this.py2 < this.py1) {
	        int temp = this.px1;
	        this.px1 = this.px2;
	        this.px2 = temp;
	        temp = this.py1;
	        this.py1 = this.py2;
	        this.py2 = temp;
	      } else {
	        int temp = this.px1;
	        this.px1 = this.px2;
	        this.px2 = temp;
	      }
	    }
	    else if (this.py2 < this.py1) {
	      int temp = this.py1;
	      this.py1 = this.py2;
	      this.py2 = temp;
	    }
	  }

	public void mouseDragged(MouseEvent evt) {
		int x = evt.getX();
		int y = evt.getY();
		if(isInner){
			computeMove(x,y);
		}else if(isTop){
			this.py1 = computePY1(y);
//			this.width = computeWidth(Math.abs(py2 - py1));
		}else if(isTopLeft){
			computeTopLeft(x,y);
//			this.width = computeWidth(Math.abs(py2 - py1));
		}else if(isTopRight){
			computeTopRight(x,y);
//			this.width = computeWidth(Math.abs(py2 - py1));
		}else if(isLeft){
			this.px1 = computePX1(x);
//			this.width = computeWidth(Math.abs(px2 - px1));
		}else if(isRight){
			this.px2 = computePX2(x);
//			this.width = computeWidth(Math.abs(px2 - px1));
		}else if(isBottom){
			this.py2 = computePY2(y);
//			this.width = computeWidth(Math.abs(py2 - py1));
		}else if(isBottomLeft){
			computeBottomLeft(x,y);
		}else if(isBottomRight){
			computeBottomRight(x,y);
		}
		this.width = computeWidth(Math.abs(py2 - py1));
	}
	
	
	public void computeBottomLeft(int x,int y){
		int tx = x;
		int ty = y;
		if(x<this.imageOriginX){
			tx = this.imageOriginX;
		}else if(x>this.imageOriginX+this.imageMaxW){
			tx = this.imageOriginX+this.imageMaxW;
		}
		if(y<this.imageOriginY){
			ty = this.imageOriginY;
		}else if(y>this.imageOriginY+this.imageMaxH){
			ty = this.imageOriginY+this.imageMaxH;
		}
		int xlen = Math.abs(px2 - tx);
		int ylen = Math.abs(py1 - ty);
		if(xlen<ylen){
			px1 = tx;
			if(ty>py1){
				py2 = py1 + xlen;
			}else{
				py2 = py1 - xlen;
			}
		}else{
			py2 = ty;
			if(tx>px2){
				px1 = px2 + ylen;
			}else{
				px1 = px2 - ylen;
			}
		}
	}
	
	public void computeBottomRight(int x,int y){
		int tx = x;
		int ty = y;
		if(x<this.imageOriginX){
			tx = this.imageOriginX;
		}else if(x>this.imageOriginX+this.imageMaxW){
			tx = this.imageOriginX+this.imageMaxW;
		}
		if(y<this.imageOriginY){
			ty = this.imageOriginY;
		}else if(y>this.imageOriginY+this.imageMaxH){
			ty = this.imageOriginY+this.imageMaxH;
		}
		int xlen = Math.abs(px1 - tx);
		int ylen = Math.abs(py1 - ty);
		if(xlen<ylen){
			px2 = tx;
			if(ty>py1){
				py2 = py1 + xlen;
			}else{
				py2 = py1 - xlen;
			}
		}else{
			py2 = ty;
			if(tx>px1){
				px2 = px1 + ylen;
			}else{
				px2 = px1 - ylen;
			}
		}
	}
	
	public void computeMove(final int x,final int y){
		int tx1 = px1;
		int tx2 = px2;
		int ty1 = py1;
		int ty2 = py2;
		System.out.println("x:"+x+"; y:"+y+"; pressX"+pressX+"; pressY:"+pressY);
		if(x==pressX && y<pressY){//上移
			
			py1 = py1 - Math.abs(pressY - y);
			if(py1<this.imageOriginY){
				py1 =  this.imageOriginY;
				py2 = py2 - Math.abs(ty1 - py1);
			}else{
				py2 = py2 - Math.abs(ty1 - py1);
			}
			System.out.println("上移"+py2);
		}else if(x>pressX && y<pressY){//右上移动
			System.out.println("右上移动");
			px2 = px2 + Math.abs(pressX - x);
			py1 = py1 - Math.abs(pressY - y);
			if(px2<=(this.imageOriginX+this.imageMaxW)){
				px1 = px1 + Math.abs(pressX - x);
			}
			if(py1>=this.imageOriginY){
				py2 = py2 - Math.abs(pressY - y);
			}
			if(px2>this.imageOriginX+this.imageMaxW){
				px2 = this.imageOriginX+this.imageMaxW;
				px1 = px1 + Math.abs(tx2-px2);
			}
			if(py1<this.imageOriginY){
				py1 = this.imageOriginY;
				py2 = py2 - Math.abs(ty1 - py1);
			}
		}else if(x>pressX && y==pressY){//右移
			System.out.println("右移");
			px2 = px2 + Math.abs(pressX - x);
			if(px2>this.imageOriginX+this.imageMaxW){
				px2 = this.imageOriginX+this.imageMaxW;
				px1 = px1 + Math.abs(tx2-px2);
			}else{
				px1 = px1 + Math.abs(tx2-px2);
			}
		}else if(x>pressX && y>pressY){//右下移动
			System.out.println("右下移动");
			px2 = px2 + Math.abs(pressX - x);
			py2 = py2 + Math.abs(pressY - y);
			if(px2<=(this.imageOriginX+this.imageMaxW)){
				px1 = px1 + Math.abs(pressX - x);
			}
			if(py2<=(this.imageOriginY + this.imageMaxH)){
				py1 = py1 + Math.abs(pressY - y);
			}
			if(px2>(this.imageOriginX+this.imageMaxW)){
				px2 = this.imageOriginX+this.imageMaxW;
				px1 = px1 + Math.abs(px2 - tx2);
			}
			if(py2>(this.imageOriginY + this.imageMaxH)){
				py2 = this.imageOriginY + this.imageMaxH;
				py1 = py1 + Math.abs(py2-ty2);
			}
		}else if(x==pressX && y >pressY){//向下移动
			System.out.println("向下移动"+py2);
			py2 = py2 + Math.abs(pressY - y);
			
			if(py2>(this.imageOriginY + this.imageMaxH)){
				System.out.println("if");
				py2 = this.imageOriginY + this.imageMaxH;
				py1 = py1 + Math.abs(py2-ty2);
			}else{
				System.out.println("else");
				py1 = py1 + Math.abs(py2-ty2);
			}
		}else if(x<pressX && y>pressY){//左下移动
			System.out.println("左下移动");
			px1 = px1 - Math.abs(x - pressX);
			py2 = py2 + Math.abs(y - pressY);
			if(px1>=this.imageOriginX){
				px2 = px2 - Math.abs(x - pressX);
				
			}
			if(py2<=(this.imageOriginY + this.imageMaxH)){
				py1 = py1 + Math.abs(y - pressY);
			}
			if(px1<this.imageOriginX){
				px1 = this.imageOriginX;
				px2 = px2 - Math.abs(tx1-px1);
			}
			if(py2>(this.imageOriginY + this.imageMaxH)){
				py2 = this.imageOriginY + this.imageMaxH;
				py1 = py1 + Math.abs(py2-ty2);
			}
		}else if(x<pressX && y ==pressY){//向左移动
			System.out.println("向左移动");
			px1 = px1 - Math.abs(x - pressX);
			if(px1<this.imageOriginX){
				px1 = this.imageOriginX;
				px2 = px2 - Math.abs(px1-tx1);
			}else{
				px2 = px2 - Math.abs(px1-tx1);
			}
		}else if(x<pressX && y<pressY){//左上移动
			System.out.println("左上移动");
			px1 = px1 - Math.abs(x - pressX);
			py1 = py1 - Math.abs(y - pressY);
			if(px1>=this.imageOriginX){
				px2 = px2 - Math.abs(x - pressX);
			}
			if(py1>=this.imageOriginY){
				py2 = py2 - Math.abs(y - pressY);
			}
			if(px1<this.imageOriginX){
				px1 = this.imageOriginX;
				px2 = px2 - Math.abs(tx1-px1);
			}
			if(py1<this.imageOriginY){
				py1 = this.imageOriginY;
				py2 = py2 - Math.abs(ty1 - py1);
			}
		}
		pressX = x;
		pressY = y;
	}
	
	
	public void computeTopRight(int x,int y){
		int tx = x;
		int ty = y;
		if(x<this.imageOriginX){
			tx = this.imageOriginX;
		}else if(x>this.imageOriginX+this.imageMaxW){
			tx = this.imageOriginX+this.imageMaxW;
		}
		if(y<this.imageOriginY){
			ty = this.imageOriginY;
		}else if(y>this.imageOriginY+this.imageMaxH){
			ty = this.imageOriginY+this.imageMaxH;
		}
		int xlen = Math.abs(px1 - tx);
		int ylen = Math.abs(py2 - ty);
		if(xlen<ylen){
			px2 = tx;
			if(ty>py2){
				py1 = py2 + xlen;
			}else{
				py1 = py2 - xlen;
			}
		}else{
			py1 = ty;
			if(tx>px1){
				px2 = px1 + ylen;
			}else{
				px2 = px1 - ylen;
			}
		}
	}
	
	public void  computeTopLeft(int x,int y){
		int tx = x;
		int ty = y;
		if(x<this.imageOriginX){
			tx = this.imageOriginX;
		}else if(x>this.imageOriginX+this.imageMaxW){
			tx = this.imageOriginX+this.imageMaxW;
		}
		if(y<this.imageOriginY){
			ty = this.imageOriginY;
		}else if(y>this.imageOriginY+this.imageMaxH){
			ty = this.imageOriginY+this.imageMaxH;
		}
		int xlen = Math.abs(px2 - tx);
		int ylen = Math.abs(py2 - ty);
		if(xlen<ylen){
			px1 = tx;
			if(ty>py2){
				py1 = py2 + xlen;
			}else{
				py1 = py2 - xlen;
			}
		}else{
			py1 = ty;
			if(tx>px2){
				px1 = px2 + ylen;
			}else{
				px1 = px2 - ylen;
			}
		}
		
	}
	
	public int computePX2(int x){
		int temp = x;
		//计算是否超出上下边界
		if(x<imageOriginX){
			temp = imageOriginX;
		}else if(x>imageOriginX + this.imageMaxW){
			temp = imageOriginX + this.imageMaxW;
		}
		//计算是否大于最小宽度
		int len1 = Math.abs(temp - px1);
		if(len1>this.imageMaxLen){
			if(x>px1){//鼠标在PX1右边
				temp = px1 + this.imageMaxLen;
			}else{//鼠标在PX1左边
				temp = px1 - this.imageMaxLen;
			}
			len1 = this.imageMaxLen;
		}
		py2 = py1 + len1;
		if(py2<this.imageOriginY){
			py2 = this.imageOriginY;
		}else if(py2>this.imageOriginY + this.imageMaxH){
			py2 = this.imageOriginY + this.imageMaxH;
		}
		int len2 = Math.abs(py2 - py1);
		if(len2<len1){
			if(x>px1){
				temp = px1 + len2;
			}else{
				temp = px1 - len2;
			}
		}
		return temp;
	}
	
	
	public int computePX1(int x){
		int temp = x;
		//计算是否超出上下边界
		if(x<imageOriginX){
			temp = imageOriginX;
		}else if(x>imageOriginX + this.imageMaxW){
			temp = imageOriginX + this.imageMaxW;
		}
		//计算是否大于最小宽度
		int len1 = Math.abs(temp - px2);
		if(len1>this.imageMaxLen){
			if(x>px2){
				temp = px2 + this.imageMaxLen;
			}else{
				temp = px2 - this.imageMaxLen;
			}
			len1 = this.imageMaxLen;
		}
		py2 = py1 + len1;
		if(py2<this.imageOriginY){
			py2 = this.imageOriginY;
		}else if(py2>this.imageOriginY + this.imageMaxH){
			py2 = this.imageOriginY + this.imageMaxH;
		}
		int len2 = Math.abs(py2 - py1);
		if(len2<len1){
			if(x>px2){
				temp = px2 + len2;
			}else{
				temp = px2 - len2;
			}
		}
		return temp;
	}
	
	public int computePY1(int y){
		int temp = y;
		//计算是否超出上下边界
		if(y<imageOriginY){
			temp = imageOriginY;
		}else if(y>imageOriginY + this.imageMaxH){
			temp = imageOriginY + this.imageMaxH;
		}
		//计算是否大于最小宽度
		int len1 = Math.abs(temp - py2);
		if(len1>this.imageMaxLen){
			if(y>py2){
				temp = py2 + this.imageMaxLen;
			}else{
				temp = py2 - this.imageMaxLen;
			}
			len1 = this.imageMaxLen;
		}
		//计算右下角坐标x值
		px2 = px1 + len1;
		if(px2<imageOriginX){
			px2 = imageOriginX;
		}else if(px2>imageOriginX + this.imageMaxW){
			px2 = imageOriginX + this.imageMaxW;
		}
		int len2 = Math.abs(px2 - px1);
		if(len2<len1){
			if(y>py2){
				temp = py2 + len2;
			}else{
				temp = py2 - len2;
			}
		}
		return temp;
	}
	
	public int computePY2(int y){
		int temp = y;
		//计算是否超出上下边界
		if(y<imageOriginY){
			temp = imageOriginY;
		}else if(y>imageOriginY + this.imageMaxH){
			temp = imageOriginY + this.imageMaxH;
		}
		int len1 = Math.abs(temp - py1);
		//计算是否大于最小宽度
		if(len1>this.imageMaxLen){
			if(y>py1){
				temp = py1 + this.imageMaxLen;
			}else{
				temp = py1 - this.imageMaxLen;
			}
			len1 = this.imageMaxLen;
		}
		//计算右下角坐标x值
		px2 = px1 + len1;
		if(px2<imageOriginX){
			px2 = imageOriginX;
		}else if(px2>imageOriginX + this.imageMaxW){
			px2 = imageOriginX + this.imageMaxW;
		}
		int len2 = Math.abs(px2 - px1);
		if(len2<len1){
			if(y>py1){
				temp = py1 + len2;
			}else{
				temp = py1 - len2;
			}
		}
		return temp;
	}
	
	public int computeWidth(int w){
		int w1 = w;
		if(w>imageMaxLen){
			w1 = imageMaxLen;
		}
		return w1;
	}

	public void mouseReleased(MouseEvent evt) {
		confirmArea();
	}

	private int pressX;
	private int pressY;
	public void mousePressed(MouseEvent evt) {
		if(this.isInner){
			pressX = evt.getX();
			pressY = evt.getY();
		}
	}
	
}
