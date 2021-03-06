/*
 * GroupMemberListPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Enumeration;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;

import org.jdesktop.swingx.painter.ImagePainter;
import org.jdesktop.swingx.painter.ImagePainter.ScaleType;

import com.jtattoo.plaf.vovoglass.VoVoBasicListUI;
import com.lorent.common.tree.MemberBean;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.bean.DepartmentTreeNode;
import com.lorent.vovo.bean.DepartmentTreeNodeComparator;
import com.lorent.vovo.bean.GroupMemberListItemComparator;
import com.lorent.vovo.bean.TreeNodeInfo;
import com.lorent.vovo.util.DataUtil;

/**
 *
 * @author  __USER__
 */
public class GroupMemberListPanel extends javax.swing.JPanel {

	private static java.util.Comparator comparator = new GroupMemberListItemComparator();
	private TreeMap<GroupMemberListItem, String> map = new TreeMap<GroupMemberListItem, String>(
			comparator);

	private class MyListCellRenderer extends DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			GroupMemberListItem node = (GroupMemberListItem) value;
			node.setMemberBean(node.getMemberBean());
			//			node.setTextForeground(UIManager.getColor("List.foreground"));
			if (isSelected || cellHasFocus) {
				node.setBackground(new Color(0xC3E2F8));
			} else {
				node.setBackground(Color.WHITE);
			}
			return node;
		}

	}

	public TreeMap<GroupMemberListItem, String> getMemberListItems() {
		return map;
	}

	public GroupMemberListItem removeItem(String userJid) {
		DefaultListModel model = (DefaultListModel) memberList.getModel();
		Enumeration<GroupMemberListItem> e = (Enumeration<GroupMemberListItem>) model
				.elements();
		GroupMemberListItem targetItem = null;
		while (e.hasMoreElements()) {
			GroupMemberListItem item = e.nextElement();
			if (item != null && item.getMemberBean() != null
					&& item.getMemberBean().getLccAccount().equals(userJid)) {
				targetItem = item;
				break;
			}
		}
		if (targetItem != null) {
			model.removeElement(targetItem);
			map.remove(targetItem);
		}

		//		memberList.updateUI();
		//		memberList.repaint();
		return targetItem;
	}

	public void clearMemberList() {
		DefaultListModel model = (DefaultListModel) memberList.getModel();
		map.clear();
		model.removeAllElements();
	}

	public void reflashItemByStatus(String userJid, int status) {
		GroupMemberListItem item = removeItem(userJid);
		if (item != null) {
			item.getMemberBean().setState(status);
			addItem(item);
		}
	}

	public void reflashItem(MemberBean bean) {
		GroupMemberListItem item = removeItem(bean.getLccAccount());
		if (item != null) {
			item.setMemberBean(bean);
			addItem(item);
		}
	}

	public JList getMemberList() {
		return memberList;
	}

	public void addItem(GroupMemberListItem item) {
		DefaultListModel model = (DefaultListModel) memberList.getModel();
		int idx = getItemindex(item);
		model.insertElementAt(item, idx);
		//		model.addElement(item);
		//		memberList.updateUI();
	}

	public int getItemindex(GroupMemberListItem item) {
		map.put(item, "");
		int idx = -1;
		java.util.Set<GroupMemberListItem> col = map.keySet();
		for (GroupMemberListItem n : col) {
			idx++;
			if (n.equals(item)) {
				break;
			}
		}
		return idx;
	}

	/** Creates new form GroupMemberListPanel */
	public GroupMemberListPanel() {
		initComponents();
		jScrollPane1.getViewport().setOpaque(false);
		memberList.removeAll();
		memberList.setModel(new DefaultListModel());
		memberList.setCellRenderer(new MyListCellRenderer());
		memberList.setUI(new VoVoBasicListUI());
		BufferedImage whiteimg;
		try {
			whiteimg = ImageIO.read(getClass().getResource(
					"/com/lorent/vovo/resource/images/whitealpha.png"));
			ImagePainter ip = new ImagePainter(whiteimg);
			ip.setScaleToFit(true);
			ip.setScaleType(ScaleType.Distort);
			//			this.bannerPanel.setBackgroundPainter(ip);
			backgroundXPanel.setBackgroundPainter(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		backgroundXPanel = new org.jdesktop.swingx.JXPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		memberList = new javax.swing.JList();

		setOpaque(false);
		setLayout(new java.awt.BorderLayout());

		backgroundXPanel.setOpaque(false);
		backgroundXPanel.setLayout(new java.awt.BorderLayout());

		memberList.setModel(new javax.swing.AbstractListModel() {
			String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4",
					"Item 5" };

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		memberList.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				memberListMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(memberList);

		backgroundXPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

		add(backgroundXPanel, java.awt.BorderLayout.CENTER);
	}// </editor-fold>
	//GEN-END:initComponents

	private void memberListMouseClicked(java.awt.event.MouseEvent evt) {
		if(evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2){
			//left double click
			GroupMemberListItem item = (GroupMemberListItem)memberList.getSelectedValue();
			if(item != null){
				if(item.getMemberBean().getLccAccount().equals(DataUtil.getUserName())){
					;	
				}else{
					Vovo.exeC("chat", "showFriendChat", item.getMemberBean());
				}
			}
		}
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private org.jdesktop.swingx.JXPanel backgroundXPanel;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JList memberList;
	// End of variables declaration//GEN-END:variables

	private String roomJid;

	public String getRoomJid() {
		return roomJid;
	}

	public void setRoomJid(String roomJid) {
		this.roomJid = roomJid;
	}

}