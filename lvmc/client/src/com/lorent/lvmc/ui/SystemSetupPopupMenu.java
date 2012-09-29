package com.lorent.lvmc.ui;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import com.lorent.lvmc.camera.ui.ControlDialog;
import com.lorent.lvmc.controller.ViewManager;
import com.lorent.lvmc.util.DataUtil;
import com.lorent.lvmc.util.StringUtil;

public class SystemSetupPopupMenu extends BasicPopupMenu {

	public SystemSetupPopupMenu(Component c) {
		super(c);
		JMenuItem audioSetupItem = new JMenuItem(StringUtil.getUIString("SystemSetupPopupMenu.audioSetupItem.txt"));
		JMenuItem videoSetupItem = new JMenuItem(StringUtil.getUIString("SystemSetupPopupMenu.videoSetupItem.txt"));
		JMenuItem panTiltItem = new JMenuItem(StringUtil.getUIString("SystemSetupPopupMenu.panTiltItem.txt"));
		audioSetupItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				audioSetupItemPerformed(e);
			}
			
		});
		videoSetupItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				videoSetupItemPerformed(e);
			}
			
		});
		panTiltItem.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panTiltItemPerformed(e);
			}
			
		});
		this.add(audioSetupItem);
		this.add(videoSetupItem);
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

	public void audioSetupItemPerformed(ActionEvent e){
		this.setVisible(false);
		AudioSetupDialog dialog = new AudioSetupDialog((java.awt.Frame)DataUtil.getTopWindow(),true);
		try {
			ViewManager.setWindowCenterLocation(dialog);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		dialog.setVisible(true);
	}
	
	public void videoSetupItemPerformed(ActionEvent e){
		this.setVisible(false);
		VideoEquipmentSetupDialog dialog = new VideoEquipmentSetupDialog((java.awt.Frame)DataUtil.getTopWindow(),true);
		try {
			ViewManager.setWindowCenterLocation(dialog);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		dialog.setVisible(true);
	}
}
