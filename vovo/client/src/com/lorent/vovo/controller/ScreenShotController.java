package com.lorent.vovo.controller;

import javax.swing.SwingUtilities;

import com.lorent.common.controller.BaseController;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.ui.MainFrame;
import com.lorent.vovo.ui.screenshot.VovoScreenShotListener;
import com.lorent.vovo.util.Constants;

public class ScreenShotController extends BaseController {

	public void capture()throws Exception{
//		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
//	    Rectangle rect = new Rectangle(d);
//	    BufferedImage desktopImg = new BufferedImage((int)d.getWidth(), (int)d.getHeight(), 6);
//	    GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
//	    GraphicsDevice device = environment.getDefaultScreenDevice();
//	    Robot robot = new Robot(device);
//	    desktopImg = robot.createScreenCapture(rect);
//	    final BufferedImage di = desktopImg;
	    SwingUtilities.invokeLater(new Runnable(){
	    	public void run(){
	    		new com.lorent.common.screenshot.CaptureView((MainFrame)Vovo.getViewManager().getView(Constants.ViewKey.MAINFRAME.toString()),new VovoScreenShotListener());
	    	}
	    });
//	    new Thread(){
//	    	public void run(){
//	    		new CaptureView(di);
//	    	}
//	    }.start();
	    
	    
//	    new Thread(){
//	    	public void run(){
//	    		try {
//					Thread.sleep(5000);
//					ScreenShotController.this.showErrorDialog(null, "test");
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//	    		
//	    	}
//	    }.start();
	}
	
}
