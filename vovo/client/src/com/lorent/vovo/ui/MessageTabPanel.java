/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MessageTabPanel.java
 *
 * Created on 2011-9-13, 15:35:09
 */
package com.lorent.vovo.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.painter.ImagePainter;
import org.jdesktop.swingx.painter.ImagePainter.ScaleType;

import com.jtattoo.plaf.BaseTabbedPaneUI;
import com.jtattoo.plaf.vovoglass.VoVoTabbedPaneUI;
import com.lorent.common.tree.MemberBean;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.dto.GroupInfo;
import com.lorent.vovo.util.Constants;

/**
 *
 * @author Administrator
 */
public class MessageTabPanel extends JPanel {

	private Map<String, Object[]> map = new HashMap<String, Object[]>();
	private Logger log = Logger.getLogger(MessageTabPanel.class);
	public BufferedImage areaimg;
	private BaseTabbedPaneUI baseTabbedPaneUI;

	/** Creates new form MessageTabPanel */
	public MessageTabPanel() {
		initComponents();

		//				mainTabbedPane.setUI();

		ImagePainter ip;
		try {
			//			areaimg = ImageIO.read(getClass().getResource(
			//					"/com/lorent/vovo/resource/image/c_1440_900_3386.jpg"));
			areaimg = Vovo.getMyContext().getDataManager().getValue(
					Constants.DataKey.BACKGROUND_IMAGE.toString());

			ip = new ImagePainter(areaimg);
			ip.setScaleToFit(true);
			ip.setScaleType(ScaleType.Distort);

			backgroundXPanel.setBackgroundPainter(ip);
		} catch (Exception e) {
			log.error("MessageTabPanel", e);
			e.printStackTrace();
		}
		VoVoTabbedPaneUI voVoTabbedPaneUI = new VoVoTabbedPaneUI(){

			@Override
			protected void paintTabBackground(Graphics g, int tabPlacement,
					int tabIndex, int x, int y, int w, int h, boolean isSelected) {
				super.paintTabBackground(g, tabPlacement, tabIndex, x, y, w, h, isSelected);
				if(isSelected){
					noticeIndexs.remove(tabIndex + "");
				}
				if(noticeIndexs.contains(tabIndex + "")){
					g.setColor(new Color(252, 235, 166));
					g.fillRect(x, y, w, h);
				}
			}
			
		};
		baseTabbedPaneUI = new BaseTabbedPaneUI() {
		};
		//		voVoTabbedPaneUI.setBufImage(areaimg);
		//		voVoTabbedPaneUI.setBackgroundComponent(backgroundXPanel);
		mainTabbedPane.setUI(voVoTabbedPaneUI);
	}

	public Map<String, Object[]> getMessagePanels() {
		return map;
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		backgroundXPanel = new org.jdesktop.swingx.JXPanel();
		mainTabbedPane = new javax.swing.JTabbedPane();

		setLayout(new java.awt.BorderLayout());

		backgroundXPanel.setLayout(new java.awt.BorderLayout());

		mainTabbedPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(1,
				1, 1, 1));
		mainTabbedPane
				.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
		mainTabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);
		mainTabbedPane
				.addChangeListener(new javax.swing.event.ChangeListener() {
					public void stateChanged(javax.swing.event.ChangeEvent evt) {
						MessageTabPanel.this.stateChanged(evt);
					}
				});
		backgroundXPanel.add(mainTabbedPane, java.awt.BorderLayout.CENTER);

		add(backgroundXPanel, java.awt.BorderLayout.CENTER);
	}// </editor-fold>
	//GEN-END:initComponents

	private void stateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_stateChanged
		log.debug("mainTabbedPane stateChanged");
//		int index = mainTabbedPane.getSelectedIndex();
//		if (index != -1) {
//			CloseableTabTitle titlePanel = (CloseableTabTitle) mainTabbedPane
//					.getTabComponentAt(index);
//			if (titlePanel != null) {
//				titlePanel.setNotice(false);
//			}
//		}
	}//GEN-LAST:event_stateChanged
	//GEN-BEGIN:variables
	// Variables declaration - do not modify

	private org.jdesktop.swingx.JXPanel backgroundXPanel;
	private javax.swing.JTabbedPane mainTabbedPane;

	// End of variables declaration//GEN-END:variables

	public javax.swing.JTabbedPane getMainTabbedPane() {
		return mainTabbedPane;
	}

	public org.jdesktop.swingx.JXPanel getBackgroundXPanel() {
		return backgroundXPanel;
	}

	public void addTab(JPanel panel, MemberBean bean, String sessionID) {
		final String fSessionID = sessionID;
		String title = bean.getRealName();
		if (map.get(sessionID) == null) {
			mainTabbedPane.addTab(title, panel);
			int index = mainTabbedPane.getTabCount();
			CloseableTabTitle closeableTabTitle = new CloseableTabTitle(bean,
					title, true, mainTabbedPane) {

				@Override
				public void closeBtnEvent(ActionEvent evt, int tabIndex) {
					deleteTab(fSessionID);
				}
				
				@Override
				public boolean canRemove() {
					boolean flag = (Boolean)Vovo.exeC("chat", "canRemovePanel", new Object[]{new String[]{fSessionID}});
					return flag;
				}
			};
			mainTabbedPane.setTabComponentAt(index - 1, closeableTabTitle);
			map.put(sessionID, new Object[] { panel, closeableTabTitle });
		}
		setSelectTab(sessionID);
	}
	
	public void addTab(JPanel panel, String title, ImageIcon icon, String sessionID) {
		final String fSessionID = sessionID;
		if (map.get(sessionID) == null) {
			mainTabbedPane.addTab(title, panel);
			int index = mainTabbedPane.getTabCount();
			CloseableTabTitle closeableTabTitle = new CloseableTabTitle(icon,
					title, true, mainTabbedPane) {

				@Override
				public void closeBtnEvent(ActionEvent evt, int tabIndex) {
					deleteTab(fSessionID);
				}
				
				@Override
				public boolean canRemove() {
					boolean flag = (Boolean)Vovo.exeC("chat", "canRemovePanel", new Object[]{new String[]{fSessionID}});
					return flag;
				}
			};
			mainTabbedPane.setTabComponentAt(index - 1, closeableTabTitle);
			map.put(sessionID, new Object[] { panel, closeableTabTitle });
		}
		setSelectTab(sessionID);
	}

	public JPanel getTab(String sessionID) {
		Object[] objs = map.get(sessionID);
		if (objs == null) {
			return null;
		}
		return (JPanel) objs[0];
	}
	
	public <T> T getCloseableTabTitle(String sessionID){
		Object[] objs = map.get(sessionID);
		if (objs == null) {
			return null;
		}
		return (T) objs[1];
	}
	
	public void updateGroupChatTopicAndDesc(String roomJid,String topic,String desc){
		String sessionID = Constants.GROUP_CHAT_SESSION_PREFIX + roomJid;
		GroupChatPanel panel = (GroupChatPanel)this.getTab(sessionID);
		GroupInfo info = new GroupInfo(roomJid,topic,desc); 
		panel.setInfo(info);
		CloseableTabTitle closeableTabTitle = getCloseableTabTitle(sessionID);
		closeableTabTitle.setTopic(topic);
	}

	public void setSelectTab(String sessionID) {
		mainTabbedPane.setSelectedComponent(getTab(sessionID));
	}

	private List<String> noticeIndexs = new ArrayList<String>();
	
	public void setNoticeTab(String sessionID) {
		CloseableTabTitle titlePanel = (CloseableTabTitle) map.get(sessionID)[1];
		int selIndex = mainTabbedPane.getSelectedIndex();
		CloseableTabTitle titlePanel2 = (CloseableTabTitle) mainTabbedPane
				.getTabComponentAt(selIndex);
		if (titlePanel != titlePanel2) {
			int index = getTabIndex(titlePanel);
			noticeIndexs.add(index + "");
			this.repaint();
		}
	}

	private void deleteTab(String sessionID) {
		map.remove(sessionID);
	}

	private int getTabIndex(CloseableTabTitle titlePanel){
		for (int i = 0; i < mainTabbedPane.getTabCount(); i++) {
			if (titlePanel.equals(mainTabbedPane.getTabComponentAt(i))) {
				return i;
			}
		}
		return -1;
	}
	
	public void removeTab(String sessionID){
		JPanel panel = this.getTab(sessionID);
		if(panel != null){
			mainTabbedPane.remove(panel);
			this.repaint();
		}
		map.remove(sessionID);
	}
	
	public Set<String> getAllSessionIDs(){
		return map.keySet();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {

	}
}
