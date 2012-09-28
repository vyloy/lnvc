package com.lorent.lvmc.event;

import com.lorent.lvmc.controller.ViewManager;
import com.lorent.lvmc.ui.ShareFileListPanel;

public class DeleteDocumentAuthorityCheckListener extends AuthorityCheckAdapter {

	@Override
	public void disable() {
		try{
			ShareFileListPanel panel = ViewManager.getComponent(ShareFileListPanel.class);
			panel.setVisibleOfDeleteButton(false);
		}catch(Exception ex){
		}
	}

	@Override
	public void enable() {
		try{
			ShareFileListPanel panel = ViewManager.getComponent(ShareFileListPanel.class);
			panel.setVisibleOfDeleteButton(true);
		}catch(Exception ex){
		}
	}

}
