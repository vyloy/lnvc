package com.lorent.lvmc.ui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

import com.lorent.lvmc.controller.ViewManager;

public class LookAndFeelPopupMenu extends BasicPopupMenu {
	private Logger log = Logger.getLogger(LookAndFeelPopupMenu.class);
	public LookAndFeelPopupMenu(Component c) {
		super(c);
		gridLayout1 = new GridLayout(3,3);
		confPath = "/com/lorent/lvmc/resource/images/background/lookandfeelConfig.conf";
		imagePath = "/com/lorent/lvmc/resource/images/background/";
		mouseAdapter = new java.awt.event.MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1) {
					java.awt.Component c = (java.awt.Component)e.getSource();
					String theme = c.getName();
                    getCurrentObj().setVisible(false);
                    try {
						ViewManager.changeLookAndFeel(theme);
                    } catch (Exception e1) {
                    	log.error("LookAndFeelPopupMenu", e1);
						e1.printStackTrace();
					}
                }
			}
        };
        init(new LookAndFeelInitPopupMenu(),new AddPanelMenuItemStrategy());
	}
	
	
	public class LookAndFeelInitPopupMenu implements InitPopupMenuInter{

		@Override
		public void init(AddMenuItemStrategyInter strategy) {
			LookAndFeelPopupMenu.this.setLayout(gridLayout1);
			String fileName = "";
			com.lorent.commonconfig.ConfigUtil configUtil = new com.lorent.commonconfig.ConfigUtil(getClass().getResourceAsStream(confPath));
			Set<Entry<Object, Object>> set = configUtil.getProperties().entrySet();
			
			for(Entry<Object, Object> entry:set){
				String key = (String)entry.getKey();
				String value = (String)entry.getValue();
				fileName= imagePath + key;//"/com/lorent/lvmc/resource/images/background/"
				ImageIcon icon = new ImageIcon(this.getClass().getResource(fileName));
				strategy.addMenuItem(icon, value, key.substring(0, key.lastIndexOf(".")), mouseAdapter);
			}
		}
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -510640887043451853L;

}
