package com.lorent.lvmc.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.lorent.lvmc.controller.ControllerFacade;
import com.lorent.lvmc.util.DataUtil;

public class LayoutPopupMenu extends BasicPopupMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7467394680867705352L;
	private static Logger log  = Logger.getLogger(LayoutPopupMenu.class);
	
	public LayoutPopupMenu(java.awt.Component c){
		super(c);
		init(new InitLayoutPopupMenu(),new AddPanelMenuItemStrategy());
	}
	
	/*protected void init(){
		this.setLayout(new GridLayout(4, 4));
//		setBorder(javax.swing.BorderFactory.createEtchedBorder());
		com.lorent.commonconfig.ConfigUtil configUtil = new com.lorent.commonconfig.ConfigUtil(getClass().getResourceAsStream("/com/lorent/lvmc/resource/images/background/layoutConfig.conf"));
		Set<Entry<Object, Object>> set = configUtil.getProperties().entrySet();
		Collection<Object> values = configUtil.getProperties().values();
		Object[] array = values.toArray();
		Arrays.sort(array);
		for (Object object : array) {
			String value = (String)object;
//			String key = (String)entry.getKey();
//			String value = (String)entry.getValue();
			String fileName= "/com/lorent/lvmc/resource/images/background/"+value+".png";
			ImageIcon icon = new ImageIcon(this.getClass().getResource(fileName));
			
			java.awt.event.MouseAdapter mouseAdapter = new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					PopupPictureItem popupPictureItem = (PopupPictureItem)e.getSource();
					log.info("视频窗口选择了布局：" + popupPictureItem.getName());
					String selectString = popupPictureItem.getName();
					ControllerFacade.execute("videoViewsController", "changeLayoutExt",selectString);
					DataUtil.setValue(DataUtil.Key.videoLayout, selectString);
					LayoutPopupMenu.this.setVisible(false);
					
				}
			};
			this.addPanel(icon, value, value,mouseAdapter);
		}
		
	}*/
	
	public class InitLayoutPopupMenu implements InitPopupMenuInter{

		@Override
		public void init(AddMenuItemStrategyInter strategy) {
			LayoutPopupMenu.this.setLayout(new GridLayout(4, 3));
//			setBorder(javax.swing.BorderFactory.createEtchedBorder());
			com.lorent.commonconfig.ConfigUtil configUtil = new com.lorent.commonconfig.ConfigUtil(getClass().getResourceAsStream("/com/lorent/lvmc/resource/images/background/layoutConfig.conf"));
			Set<Entry<Object, Object>> set = configUtil.getProperties().entrySet();
			Collection<Object> values = configUtil.getProperties().values();
			Object[] array = values.toArray();
			Arrays.sort(array);
			for (Object object : array) {
				String value = (String)object;
//				String key = (String)entry.getKey();
//				String value = (String)entry.getValue();
				String fileName= "/com/lorent/lvmc/resource/images/background/"+value+".png";
				ImageIcon icon = new ImageIcon(this.getClass().getResource(fileName));
				
				java.awt.event.MouseAdapter mouseAdapter = new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						PopupPictureItem popupPictureItem = (PopupPictureItem)e.getSource();
						log.info("视频窗口选择了布局：" + popupPictureItem.getName());
						String selectString = popupPictureItem.getName();
						ControllerFacade.execute("videoViewsController", "changeLayoutExt",selectString);
						DataUtil.setValue(DataUtil.Key.videoLayout, selectString);
						LayoutPopupMenu.this.setVisible(false);
						
					}
				};
				strategy.addMenuItem(icon, value, value,mouseAdapter);
			}
		}
	}

}
