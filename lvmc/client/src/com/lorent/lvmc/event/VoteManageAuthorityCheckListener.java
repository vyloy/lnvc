package com.lorent.lvmc.event;

import com.lorent.lvmc.controller.ViewManager;
import com.lorent.lvmc.ui.voteMainPanel;

public class VoteManageAuthorityCheckListener extends AuthorityCheckAdapter {

	private voteMainPanel panel;
	
	public VoteManageAuthorityCheckListener(){
		try {
			panel = ViewManager.getComponent(voteMainPanel.class);
		} catch (Exception e) {
		}
	}
	
	@Override
	public void disable() {
		if(panel!=null){
			panel.setVoteManage(false);
		}
	}

	@Override
	public void enable() {
		if(panel!=null){
			panel.setVoteManage(true);
		}
	}

}
