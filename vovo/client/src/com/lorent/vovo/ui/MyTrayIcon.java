package com.lorent.vovo.ui;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import org.apache.log4j.Logger;

import com.lorent.vovo.Vovo;
import com.lorent.vovo.dto.ControllerEvent;
import com.lorent.vovo.util.Constants;


public class MyTrayIcon {
	private static final String IMAGE_PATH = "/com/lorent/vovo/resource/images/trayicon/";
	private static final String BASE_IMAGE = Constants.TRAYICON_PATH;
	private Logger log = Logger.getLogger(MyTrayIcon.class);
	private JPTrayIcon trayIcon;
	private ConcurrentLinkedQueue<ControllerEvent> events = new ConcurrentLinkedQueue<ControllerEvent>();
	private ControllerEvent currentEvent;
	private JMenuItem exitMI;
	private JMenuItem logoutMI;
	private JMenuItem aboutMI;
	private JMenuItem systemSetupMI;
	private JCheckBoxMenuItem startAtSystemMI;
	
	public void addEvent(ControllerEvent event){
		events.add(event);
		if(currentEvent == null){
			showEvent();
		}
	}
	
	public void removeEvent(ControllerEvent event){
		if(event.equals(currentEvent)){//当前事件
			showEvent();
		}else{
			ControllerEvent target = null;
			for(Iterator<ControllerEvent> it = events.iterator(); it.hasNext(); ){
				ControllerEvent temp = it.next();
				if(event.equals(temp)){
					target = temp;
					break;
				}
			}
			if(target != null){
				events.remove(event);				
			}
		}
	}
	
	private void showEvent() {
		if(!events.isEmpty()){
			currentEvent = events.poll();
			trayIcon.changeIcon(currentEvent.getImage());
		}else{
			Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource(BASE_IMAGE));
			trayIcon.changeIcon(image);
		}
	}

	public MyTrayIcon(){
		createMenu();
		createIcon();
	}
	
	private void createMenu() {
		
		exitMI = new JMenuItem(Vovo.getMyContext().getViewManager().getUIString("trayicon.menu.exit"));
		exitMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Vovo.exeC("main", "confirmExit");
			}
		});
		logoutMI = new JMenuItem(Vovo.getMyContext().getViewManager().getUIString("trayicon.menu.logout"));
		logoutMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Vovo.exeC("main", "doLogout");
				} catch (Exception e1) {
					log.error("logoutMI.actionPerformed", e1);
					e1.printStackTrace();
				}
			}
		});
		
		aboutMI = new JMenuItem(Vovo.getMyContext().getViewManager().getUIString("trayicon.menu.about"));
		aboutMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Vovo.exeC("main", "showAboutDialog");
			}
		});
		
		systemSetupMI = new JMenuItem(Vovo.getMyContext().getViewManager().getUIString("trayicon.menu.systemsetup"));
		systemSetupMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				ControllerFacade.execute("mainController", "exitFromTrayIcon");
			}
		});
		
		startAtSystemMI = new JCheckBoxMenuItem(Vovo.getMyContext().getViewManager().getUIString("trayicon.menu.startupatsystem"));
		startAtSystemMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(null, startAtSystemMI.isSelected());
//				startAtSystemMI.setSelected(false);
				Vovo.exeC("main", "setStartUpAtSystem", startAtSystemMI);
			}
		});
	}
	
	private JPopupMenu getLoginMenu(){
		JPopupMenu loginMenu = new JPopupMenu();
		loginMenu.add(exitMI);
		return loginMenu;
	}
	
	private JPopupMenu getMainMenu(){
		JPopupMenu mainMenu = new JPopupMenu();
//		mainMenu.add(systemSetupMI);
		mainMenu.add(startAtSystemMI);
		mainMenu.addSeparator();
		mainMenu.add(aboutMI);
		mainMenu.addSeparator();
		mainMenu.add(logoutMI);
		mainMenu.add(exitMI);
		mainMenu.addPopupMenuListener(new PopupMenuListener() {
			
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				log.info("popupMenuWillBecomeVisible "+e);
				Vovo.exeC("main", "getStartUpAtSystem", startAtSystemMI);
			}
			
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				log.info("popupMenuWillBecomeInvisible "+e);
			}
			
			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				log.info("popupMenuCanceled "+e);
			}
		});
		return mainMenu;
	}
	
	public void createIcon(){
		if (SystemTray.isSupported()) 
		{
			SystemTray tray = SystemTray.getSystemTray(); 
			Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource(BASE_IMAGE));
			
			trayIcon = new JPTrayIcon(image){
				@Override
				protected void myMouseClicked(MouseEvent evt) {
					if(evt.getButton() == MouseEvent.BUTTON1 
							&& evt.getClickCount() == 1){
						handleClick();
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
	
	public void handleClick(){
		log.info("click");
		if(currentEvent!=null){
			Vovo.exeC(currentEvent.getExClass(), currentEvent.getExMethod(), currentEvent.getParas());
			currentEvent = null;
			showEvent();
		}else{
			log.info("TRAYICON显示主界面");
			MainFrame mainframe = Vovo.getViewManager().getView(Constants.ViewKey.MAINFRAME.toString());
			if(mainframe != null){
	//			mainframe.setAlwaysOnTop(true);
				mainframe.setVisible(true);
	//			mainframe.setAlwaysOnTop(false);
//				mainframe.setAlwaysOnTop(true);// 设置置顶  
		        // 设置窗口状态(在最小化状态弹出显示)  
				mainframe.setExtendedState(javax.swing.JFrame.NORMAL);  
			}
		}
	}

	public void showLoginMenu() throws Exception{
		trayIcon.setJPopupMenu(getLoginMenu());
	}
	
	public void showMainMenu() throws Exception{
		trayIcon.setJPopupMenu(getMainMenu());
	}

}
