package com.lorent.lvmc.event;

import com.lorent.lvmc.controller.ViewManager;
import com.lorent.lvmc.ui.MainFrame;
import com.lorent.lvmc.util.DataUtil;

public class ScreenShareAuthorityCheckListener extends AuthorityCheckAdapter {

	@Override
	public void disable() {
		try{
			if(DataUtil.getTopWindow() instanceof MainFrame){
				MainFrame mainFrame = (MainFrame)DataUtil.getTopWindow();
//				mainFrame.getShareDesktopButton().setVisible(false);
				ViewManager.setComponentByAuthority(mainFrame.getShareDesktopButton(), false);
			}
		}catch(Exception ex){
		}
	}

	@Override
	public void enable() {
		try{
			if(DataUtil.getTopWindow() instanceof MainFrame){
				MainFrame mainFrame = (MainFrame)DataUtil.getTopWindow();
//				mainFrame.getShareDesktopButton().setVisible(true);
				ViewManager.setComponentByAuthority(mainFrame.getShareDesktopButton(), true);
			}
		}catch(Exception ex){
		}
	}

}
