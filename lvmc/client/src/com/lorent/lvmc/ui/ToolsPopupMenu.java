package com.lorent.lvmc.ui;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import org.monte.screenrecorder.ScreenRecorderCompactMain;

import com.lorent.lvmc.camera.ui.ControlDialog;
import com.lorent.lvmc.controller.ControllerFacade;
import com.lorent.lvmc.controller.ViewManager;
import com.lorent.lvmc.util.DataUtil;
import com.lorent.lvmc.util.StringUtil;

public class ToolsPopupMenu extends BasicPopupMenu {

	public ToolsPopupMenu(Component c) {
		super(c);
		JMenuItem recorder = new JMenuItem(StringUtil.getUIString("com.lorent.lvmc.ui.DefaultLayoutMeetingPanel.screenrecorder.txt"));
		JMenuItem screenShot = new JMenuItem(StringUtil.getUIString("com.lorent.lvmc.ui.DefaultLayoutMeetingPanel.screenshot.txt"));
		recorder.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new ScreenRecorderCompactMain().setVisible(true);
			}
			
		});
		screenShot.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ControllerFacade.execute("screenShotController", "capture");
			}
			
		});
		this.add(recorder);
		this.add(screenShot);
	}
}
