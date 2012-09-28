package com.lorent.lvmc.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import com.lorent.lvmc.controller.ViewManager;
import com.nilo.plaf.nimrod.NimRODLookAndFeel;

public class ThemePopupMenu extends BasicPopupMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7261322664237100458L;
	private Logger log = Logger.getLogger(ThemePopupMenu.class);
	
	public ThemePopupMenu(java.awt.Component c){
		super(c);
		gridLayout1 = new GridLayout(2,3);
		confPath = "/com/lorent/lvmc/resource/images/background/backgroundConfig.conf";
		imagePath = "/com/lorent/lvmc/resource/images/background/";
		mouseAdapter = new java.awt.event.MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1) {
					PopupMenuLabel label = (PopupMenuLabel)e.getSource();
					String theme = label.getName();
                    label.setOpaqueAndReflesh(false);
                    getCurrentObj().setVisible(false);
                    try {
						com.lorent.lvmc.util.ConfigUtil.setProperty("themeFileName", theme);
						ViewManager.changeLookAndFeel();
                    } catch (Exception e1) {
						e1.printStackTrace();
						log.error("ThemePopupMenu", e1);
					}
                }
			}
        	
        };
//		init();
	}
	
	
	/*private void init(){
		this.setLayout(gridLayout1);
//		this.setPreferredSize(new Dimension(28 * 9, 28 * 7));
		int filesize = 0;
		String fileName = "";
		log.warn("backgroundconfig file path =================" + getClass().getResourceAsStream("/com/lorent/lvmc/resource/images/background/backgroundConfig.conf"));
		com.lorent.commonconfig.ConfigUtil configUtil = new com.lorent.commonconfig.ConfigUtil(getClass().getResourceAsStream("/com/lorent/lvmc/resource/images/background/backgroundConfig.conf"));
		Set<Entry<Object, Object>> set = configUtil.getProperties().entrySet();
		
		for(Entry<Object, Object> entry:set){
			String key = (String)entry.getKey();
			String value = (String)entry.getValue();
			fileName= "/com/lorent/lvmc/resource/images/background/" + key;
			ImageIcon icon = new ImageIcon(this.getClass().getResource(fileName));
			final PopupMenuLabel label = new PopupMenuLabel(icon,SwingConstants.CENTER);
			label.setName(value);
			label.setPreferredSize(new Dimension(icon.getIconWidth()+ 5,icon.getIconHeight()+5));
			label.setIconCenter();
            label.addMouseListener(new java.awt.event.MouseAdapter(){
            	
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == 1) {
						String theme = label.getName();
                        label.setOpaqueAndReflesh(false);
                        ThemePopupMenu.this.setVisible(false);
                        try {
							com.lorent.lvmc.util.ConfigUtil.setProperty("themeFileName", theme);
							ViewManager.changeLookAndFeel();
                        } catch (Exception e1) {
							e1.printStackTrace();
						}
                    }
				}
            	
            });

            this.add(label);
		}
		
	}*/
	
}
