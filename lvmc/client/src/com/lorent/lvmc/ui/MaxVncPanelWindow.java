package com.lorent.lvmc.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JWindow;

public class MaxVncPanelWindow extends JWindow {
	
	public MaxVncPanelWindow(){
		centerPanel = new JPanel();
		add(centerPanel,BorderLayout.CENTER);
	}
	
	private JPanel centerPanel;
	
	public JPanel getCenterPanel() {
		return centerPanel;
	}

	private VncViewPanel vncViewPanel;

	public VncViewPanel getVncViewPanel() {
		return vncViewPanel;
	}

	public void setVncViewPanel(VncViewPanel vncViewPanel) {
		this.vncViewPanel = vncViewPanel;
	}
	
	public static void main(String args[]) {
		MaxVncPanelWindow maxWindow = new MaxVncPanelWindow();
		JPanel jPanel = new JPanel();
		jPanel.setSize(800, 600);
		maxWindow.getCenterPanel().removeAll();
		maxWindow.getCenterPanel().add(jPanel,BorderLayout.CENTER);
//		maxWindow.rootPane.removeAll();
//		maxWindow.rootPane.add(jPanel,BorderLayout.CENTER);
		
		
		
//		maxWindow.add(jPanel,BorderLayout.CENTER);
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    	maxWindow.setSize(1024,768);
    	maxWindow.setAlwaysOnTop(true);
    	maxWindow.setVisible(true);
	}
}
