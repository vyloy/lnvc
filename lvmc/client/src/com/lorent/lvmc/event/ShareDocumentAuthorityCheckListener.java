package com.lorent.lvmc.event;

import com.lorent.lvmc.controller.ViewManager;
import com.lorent.lvmc.ui.ShareFileListPanel;

public class ShareDocumentAuthorityCheckListener extends AuthorityCheckAdapter {

	@Override
	public void disable() {
		try{
			ShareFileListPanel panel = ViewManager.getComponent(ShareFileListPanel.class);
			panel.setVisibleOfLoadBoard(false);
		}catch(Exception ex){
		}
	}

	@Override
	public void enable() {
		try{
			ShareFileListPanel panel = ViewManager.getComponent(ShareFileListPanel.class);
			panel.setVisibleOfLoadBoard(true);
		}catch(Exception ex){
		}
	}

}
