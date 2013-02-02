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
		JMenuItem panTiltItem = new JMenuItem(StringUtil.getUIString("SystemSetupPopupMenu.panTiltItem.txt"));
		recorder.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ScreenRecorderCompactMain dialog = ViewManager.getComponent(ScreenRecorderCompactMain.class);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		screenShot.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ControllerFacade.execute("screenShotController", "capture");
			}
			
		});
		panTiltItem.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panTiltItemPerformed(e);
			}
			
		});
		this.add(recorder);
		this.add(screenShot);
		this.add(panTiltItem);
	}

	public void panTiltItemPerformed(ActionEvent e) {
		this.setVisible(false);
		try {
			ControlDialog dialog = ViewManager.getComponent(ControlDialog.class, new Class[]{Frame.class,boolean.class}, new Object[]{(java.awt.Frame)DataUtil.getTopWindow(),true});
			ViewManager.setWindowCenterLocation(dialog);
			dialog.setVisible(true);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
