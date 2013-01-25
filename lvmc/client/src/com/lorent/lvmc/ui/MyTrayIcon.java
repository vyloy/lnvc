package com.lorent.lvmc.ui;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.apache.log4j.Logger;

import com.lorent.lvmc.controller.ControllerFacade;
import com.lorent.lvmc.util.StringUtil;

public class MyTrayIcon {
	private static final String IMAGE_PATH = "/com/lorent/lvmc/resource/images/trayicon/";
	private static final String BASE_IMAGE = IMAGE_PATH + "trayicon_base.png";
	private Logger log = Logger.getLogger(MyTrayIcon.class);
	private JPTrayIcon trayIcon;
	private SystemTray tray;
	
	private JMenuItem exitMI;
	private JMenuItem logoutMI;
	private JMenuItem aboutMI;
	private JMenuItem systemSetupMI;
	
	
	public MyTrayIcon(){
		createMenu();
		createIcon();
	}
	
	private void createMenu() {
		exitMI = new JMenuItem(StringUtil.getUIString("trayicon.menu.exit"));
		exitMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ControllerFacade.execute("mainController", "exitFromTrayIcon");
			}
		});
		logoutMI = new JMenuItem(StringUtil.getUIString("trayicon.menu.logout"));
		logoutMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ControllerFacade.execute("mainController", "logoutFromTrayIcon");
				} catch (Exception e1) {
					log.error("logoutMI.actionPerformed", e1);
					e1.printStackTrace();
				}
			}
		});
		aboutMI = new JMenuItem(StringUtil.getUIString("trayicon.menu.about"));
		aboutMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ControllerFacade.execute("mainController", "showAboutDialog");
			}
		});
		systemSetupMI = new JMenuItem(StringUtil.getUIString("trayicon.menu.systemsetup"));
		systemSetupMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				ControllerFacade.execute("mainController", "exitFromTrayIcon");
			}
		});
	}
	
	private JPopupMenu getLoginMenu(){
		JPopupMenu loginMenu = new JPopupMenu();
		loginMenu.add(aboutMI);
		loginMenu.addSeparator();
		loginMenu.add(exitMI);
		return loginMenu;
	}
	
	private JPopupMenu getMainMenu(){
		JPopupMenu mainMenu = new JPopupMenu();
//		mainMenu.add(systemSetupMI);
		mainMenu.add(aboutMI);
		mainMenu.addSeparator();
		mainMenu.add(logoutMI);
		mainMenu.add(exitMI);
		return mainMenu;
	}
	
	public void createIcon(){
		if (SystemTray.isSupported()) 
		{
			tray = SystemTray.getSystemTray();
			Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource(BASE_IMAGE));
			
			trayIcon = new JPTrayIcon(image){
				@Override
				protected void myMouseClicked(MouseEvent evt) {
					if(evt.getButton() == MouseEvent.BUTTON1 
							&& evt.getClickCount() == 2){
						ControllerFacade.execute("mainController", "dbClickTrayIcon");
					}
				}
			};
			try {
				tray.add(trayIcon);
			} catch (AWTException e) {
				log.error("createIcon", e);
				e.printStackTrace();
			}
		}
	}

	public void removeTrayIcon(){
		tray.remove(trayIcon);
	}
	
	public void showLoginMenu() throws Exception{
		trayIcon.setJPopupMenu(getLoginMenu());
	}
	
	public void showMainMenu() throws Exception{
		trayIcon.setJPopupMenu(getMainMenu());
	}

}
