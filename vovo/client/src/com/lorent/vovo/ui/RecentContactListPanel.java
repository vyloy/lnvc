/*
 * GroupListPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.tree.DefaultMutableTreeNode;

import org.jdesktop.swingx.painter.ImagePainter;
import org.jdesktop.swingx.painter.ImagePainter.ScaleType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jidesoft.swing.JideLabel;
import com.jtattoo.plaf.vovoglass.VoVoBasicListUI;
import com.lorent.common.tree.MemberBean;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.bean.TreeNodeInfo;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.DataUtil;
import com.lorent.vovo.util.RecentContactManager;
import com.lorent.vovo.util.RecentContactManager.RecentContact;
import com.lorent.vovo.util.RecentContactManager.RecentContactInfo;
import com.lorent.vovo.util.TreeUtil;

/**
 *
 * @author  __USER__
 */
public class RecentContactListPanel extends javax.swing.JPanel {
	
	private static final Logger logger = LoggerFactory.getLogger(RecentContactListPanel.class);

	private class MyListCellRenderer extends DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			if(value==null)
				return null;
			RecentContact rc = (RecentContact) value;
			JComponent node = null;
			switch(rc.getInfo()){
			case 4:
				GroupListPanel groupListPanel = Vovo.getMyContext().getViewManager().getView(Constants.ViewKey.GroupListPanel.toString());
				node = groupListPanel.findItemByRoomJid(rc.getContact()).init4RecentContactListPanel();
				break;
			case 2:
				MemberTreeNodePanel memberPanel = new MemberTreeNodePanel();
				memberPanel.setMemberInfo(TreeUtil.getMemberBeanByLccno(rc.getContact()));
				node=memberPanel;
				break;
			}
			if(node==null){
				JLabel l = new JLabel(rc.getContact());
				l.setVisible(true);
				return l;
			}
			if (isSelected || cellHasFocus) {
				node.setBackground(new Color(0xC3E2F8));
			} else {
				node.setBackground(Color.WHITE);
			}
			return node;
		}

	}

	public JList getList() {
		return list;
	}

	public void addItem(Object item) {
		DefaultListModel model = (DefaultListModel) list.getModel();
		model.addElement(item);
		this.repaint();
	}

	/** Creates new form GroupListPanel */
	public RecentContactListPanel() {
		initComponents();
		scrollPane.getViewport().setOpaque(false);
		list.removeAll();
		model = new DefaultListModel();
		list.setModel(model);
		list.setCellRenderer(new MyListCellRenderer());
		list.setUI(new VoVoBasicListUI());
		BufferedImage whiteimg;
		try {
			whiteimg = ImageIO.read(getClass().getResource(
					"/com/lorent/vovo/resource/images/whitealpha.png"));
			ImagePainter ip = new ImagePainter(whiteimg);
			ip.setScaleToFit(true);
			ip.setScaleType(ScaleType.Distort);
			//			this.bannerPanel.setBackgroundPainter(ip);
			backgroundPanel.setBackgroundPainter(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1 && e.getClickCount()==2){
					Point point = e.getPoint();
			    	Rectangle cellBounds = list.getCellBounds(0, list.getLastVisibleIndex());
			    	if(!cellBounds.contains(point)){
			    		return;
			    	}
			    	int index = list.locationToIndex(point);
			    	RecentContact c = (RecentContact)list.getModel().getElementAt(index);
			    	if((c.getInfo()&RecentContactInfo.FRIEND.mask)!=0){
			    		Vovo.exeC("chat", "showFriendChat", TreeUtil.getMemberBeanByLccno(c.getContact()));
			    	}else if((c.getInfo()&RecentContactInfo.GROUP.mask)!=0){
			    		Vovo.exeC("groupChat", "fetchOneGroupChatAuthority", c.getContact());
			    		GroupListPanel groupListPanel = Vovo.getMyContext().getViewManager().getView(Constants.ViewKey.GroupListPanel.toString());
			    		GroupListItem item = groupListPanel.findItemByRoomJid(c.getContact());
			    		Vovo.exeC("groupChat", "showGroupChat", item.getInfo());
			    	}
				}
			}
			
		});
	}
	
	public void init(){
		try{
			RecentContactManager r = RecentContactManager.newInstance(this);
			Set<RecentContact> h = r.init();
			for(RecentContact rc:h)
				model.add(0, rc);
		}catch(IOException e){
			logger.error(e.getMessage(),e);
		}
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		backgroundPanel = new org.jdesktop.swingx.JXPanel();
		scrollPane = new javax.swing.JScrollPane();
		list = new javax.swing.JList();

		setOpaque(false);
		setLayout(new java.awt.BorderLayout());

		backgroundPanel.setOpaque(false);
		backgroundPanel.setLayout(new java.awt.BorderLayout());

		scrollPane.setOpaque(false);

		list.setOpaque(false);
		list.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				groupListMouseClicked(evt);
			}
		});
		scrollPane.setViewportView(list);

		backgroundPanel.add(scrollPane, java.awt.BorderLayout.CENTER);

		add(backgroundPanel, java.awt.BorderLayout.CENTER);
	}// </editor-fold>

	private void groupListMouseClicked(java.awt.event.MouseEvent evt) {
		if (evt.getButton() == java.awt.event.MouseEvent.BUTTON1
				&& evt.getClickCount() == 2) {
//			int locationToIndex = list.locationToIndex(evt.getPoint());
//			list.setSelectedIndex(locationToIndex);
//			Object selectedValue = list.getSelectedValue();
//			GroupListItem item = (GroupListItem) selectedValue;
//			String roomJid = item.getRoomJid();
//
//			Vovo.exeC("groupChat", "fetchOneGroupChatAuthority", roomJid);
////						Vovo.exeC("groupChat", "showGroupMemberListPanel", roomJid);
//			Vovo.exeC("groupChat", "showGroupChat", item.getInfo());
		}
	}

	public DefaultListModel getModel() {
		return model;
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private org.jdesktop.swingx.JXPanel backgroundPanel;
	private javax.swing.JList list;
	private javax.swing.JScrollPane scrollPane;
	// End of variables declaration//GEN-END:variables
	private DefaultListModel model;

}