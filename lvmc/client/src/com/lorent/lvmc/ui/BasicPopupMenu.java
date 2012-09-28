package com.lorent.lvmc.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.lorent.lvmc.controller.ControllerFacade;
import com.lorent.lvmc.util.DataUtil;

public class BasicPopupMenu extends JPopupMenu {

	/**
	 * 
	 */
	GridLayout gridLayout1 = null; 
	static Logger log = Logger.getLogger(BasicPopupMenu.class);
	private java.awt.Component parent;
	String confPath;
	java.awt.event.MouseAdapter mouseAdapter;
	String imagePath;
	boolean initPanelFlag = false;
	
	public BasicPopupMenu(java.awt.Component c){
		parent = c;
	}
	
	protected BasicPopupMenu getCurrentObj(){
		return this;
	}
	
	protected void init(InitPopupMenuInter initPopupMenu,AddMenuItemStrategyInter strategy){
		initPopupMenu.init(strategy);
	}
	
	public void showPopup() {
    	this.setVisible(true);
        this.show(parent, 0, parent.getSize().height);
    }
	
	public interface InitPopupMenuInter{
		public void init(AddMenuItemStrategyInter strategy);
	}
	
	public interface AddMenuItemStrategyInter{
		public void addMenuItem(ImageIcon icon,String value,String text,java.awt.event.MouseAdapter mouseAdapter);
	}
	
	protected class InitPopupMenu implements InitPopupMenuInter{
		public void init(AddMenuItemStrategyInter strategy){
			BasicPopupMenu.this.setLayout(gridLayout1);
//			this.setPreferredSize(new Dimension(28 * 9, 28 * 7));
			String fileName = "";
			com.lorent.commonconfig.ConfigUtil configUtil = new com.lorent.commonconfig.ConfigUtil(getClass().getResourceAsStream(confPath));
			Set<Entry<Object, Object>> set = configUtil.getProperties().entrySet();
			
			for(Entry<Object, Object> entry:set){
				String key = (String)entry.getKey();
				String value = (String)entry.getValue();
				fileName= imagePath + key;//"/com/lorent/lvmc/resource/images/background/"
				ImageIcon icon = new ImageIcon(this.getClass().getResource(fileName));
				strategy.addMenuItem(icon, value, value, mouseAdapter);
			}
		}
	}
	
	protected class AddPanelMenuItemStrategy implements AddMenuItemStrategyInter{
		public void addMenuItem(ImageIcon icon,String value,String text,java.awt.event.MouseAdapter mouseAdapter){
			final PopupPictureItem popupPictureItem = new PopupPictureItem();
	        popupPictureItem.setName(value);
	        popupPictureItem.getPictureLabel().setIcon(icon);
	        popupPictureItem.getTextLabel().setText(text);
	        popupPictureItem.addMouseListener(mouseAdapter);
	        BasicPopupMenu.this.add(popupPictureItem);
		}
	}
	
	protected class AddLabelMenuItemStrategy implements AddMenuItemStrategyInter{
		public void addMenuItem(ImageIcon icon,String value,String text,java.awt.event.MouseAdapter mouseAdapter){
			final PopupMenuLabel label = new PopupMenuLabel(icon,SwingConstants.CENTER,false);
			label.setName(value);
			label.setIconCenter();
			label.setPreferredSize(new Dimension(icon.getIconWidth()+ 5,icon.getIconHeight()+5));
			label.addMouseListener(mouseAdapter);
			BasicPopupMenu.this.add(label);
		}
	}
	/*
	private void initPanel(ImageIcon icon,String value){
		final PopupPictureItem popupPictureItem = new PopupPictureItem();
        popupPictureItem.setName(value);
        popupPictureItem.getPictureLabel().setIcon(icon);
        popupPictureItem.getTextLabel().setText(value);
        popupPictureItem.addMouseListener(mouseAdapter);
        this.add(popupPictureItem);
	}
	
	private void initLabel(ImageIcon icon,String value){
		final PopupMenuLabel label = new PopupMenuLabel(icon,SwingConstants.CENTER,false);
		label.setName(value);
		label.setIconCenter();
		label.setPreferredSize(new Dimension(icon.getIconWidth()+ 5,icon.getIconHeight()+5));
		label.addMouseListener(mouseAdapter);
        this.add(label);
	}
	
	protected void addPanel(ImageIcon icon,String value,String text,java.awt.event.MouseAdapter mouseAdapter){
		final PopupPictureItem popupPictureItem = new PopupPictureItem();
        popupPictureItem.setName(value);
        popupPictureItem.getPictureLabel().setIcon(icon);
        popupPictureItem.getTextLabel().setText(text);
        popupPictureItem.addMouseListener(mouseAdapter);
        this.add(popupPictureItem);
	}
	
	protected void addLabel(ImageIcon icon,String value,java.awt.event.MouseAdapter mouseAdapter){
		final PopupMenuLabel label = new PopupMenuLabel(icon,SwingConstants.CENTER,false);
		label.setName(value);
		label.setIconCenter();
		label.setPreferredSize(new Dimension(icon.getIconWidth()+ 5,icon.getIconHeight()+5));
		label.addMouseListener(mouseAdapter);
        this.add(label);
	}*/
	
	
	
	
	
}
