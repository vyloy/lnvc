package com.lorent.vovo.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

public class DefaultRightCornerPopMessageShowStrategy implements RightCornerPopMessageShowStrategy,Runnable{
	
	private Integer screenWidth; // 屏幕宽度
	private Integer screenHeight; // 屏幕高度
	private Integer windowWidth = 200; // 设置提示窗口宽度
	private Integer windowHeight = 100; // 设置提示窗口高度
	private Integer bottmToolKitHeight; // 底部任务栏高度，如果没有任务栏则为零
	private Integer stayTime = 10000; // 提示框停留时间
	private Integer x; // 窗口起始X坐标
	private Integer y; // 窗口起始Y坐标
	private javax.swing.JDialog dialog;
	public void setDialog(javax.swing.JDialog d){
		dialog = d;
	}
	
	public void show(){
		init();
		Thread t = new Thread(this);
		t.start();
	}
	
	public void init(){
		windowWidth = dialog.getPreferredSize().width;
		windowHeight = dialog.getPreferredSize().height;
		bottmToolKitHeight = Toolkit.getDefaultToolkit().getScreenInsets(
				dialog.getGraphicsConfiguration()).bottom;
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = dimension.width;
		screenHeight = dimension.height;

		x = screenWidth - windowWidth;
		y = screenHeight;
		dialog.setLocation(x, y - bottmToolKitHeight - windowHeight);
		dialog.setSize(windowWidth, windowHeight);
		dialog.setAlwaysOnTop(true);
//		Toolkit.getDefaultToolkit().beep(); // 播放系统声音，提示一下
		dialog.setVisible(true);
	}
	
	
	@Override
	public void run() {
		Integer delay = 2;
		Integer step = 1;
		Integer end = windowHeight + bottmToolKitHeight;
		while (true) {
			try {
				step++;
				y = y - 1;
				dialog.setLocation(x, y);
				if (step > end) {
					Thread.sleep(stayTime);
					break;
				}
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		step = 1;
		while (true) {
			try {
				step++;
				y = y + 1;
				dialog.setLocation(x, y);
				if (step > end) {
					dialog.dispose();
					break;
				}
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
