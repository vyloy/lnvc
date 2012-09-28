package com.lorent.lvmc.event;

import com.lorent.lvmc.controller.ViewManager;
import com.lorent.lvmc.ui.MemberListPanel;

public class InviteJoinConferenceAuthorityCheckListener extends
		AuthorityCheckAdapter {

	@Override
	public void disable() {
		try {
			MemberListPanel panel = ViewManager
			.getComponent(MemberListPanel.class);
			panel.setVisibleOfInviteButton(false);
		} catch (Exception e) {
		}
	}

	@Override
	public void enable() {
		try {
			MemberListPanel panel = ViewManager
			.getComponent(MemberListPanel.class);
			panel.setVisibleOfInviteButton(true);
		} catch (Exception e) {
		}
	}

}
