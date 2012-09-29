package com.lorent.lvmc.controller;

import javax.swing.SwingUtilities;

import com.lorent.lvmc.event.LvmcScreenShotListener;
import com.lorent.lvmc.ui.MainFrame;
import com.lorent.lvmc.util.DataUtil;

public class ScreenShotController  extends BaseController{

	public void capture(){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				try {
					new com.lorent.common.screenshot.CaptureView((MainFrame)DataUtil.getTopWindow(),new LvmcScreenShotListener());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
