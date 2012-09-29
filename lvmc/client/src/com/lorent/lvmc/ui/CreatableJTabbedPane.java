package com.lorent.lvmc.ui;



import javax.swing.JTabbedPane;

import com.jtattoo.plaf.CreatableTabPanel;
import com.lorent.whiteboard.client.Client;
import com.lorent.whiteboard.command.impl.CreateWhiteboard;

public class CreatableJTabbedPane extends JTabbedPane implements CreatableTabPanel {

	private static final long serialVersionUID = 1L;
	public CreatableJTabbedPane() {
		this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}

	@Override
	public void createTab() {
		Client client = Client.getInstance();
		client.send(new CreateWhiteboard(client.getMeetingId()));
	}
	
}
