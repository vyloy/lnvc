package com.lorent.vovo.ui;

import org.jdesktop.swingx.JXPanel;

import com.lorent.vovo.bean.TreeNodeInfo;

public class TreeNodeInfoPanel extends JXPanel {
	protected TreeNodeInfo info ;

	public TreeNodeInfo getInfo() {
		return info;
	}

	public void setInfo(TreeNodeInfo info) {
		this.info = info;
	}
}
