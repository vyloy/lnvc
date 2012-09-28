/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CloseableTabTitle.java
 *
 * Created on 2011-9-14, 14:39:51
 */
package com.lorent.vovo.ui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import com.lorent.common.tree.MemberBean;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.ImageUtil;

/**
 *
 * @author jack
 */
public class CloseableTabTitle extends javax.swing.JPanel {

	private JTabbedPane tabbedPane = null;

	private boolean canClose;

	/** Creates new form CloseableTabTitle */
	public CloseableTabTitle() {
		initComponents();
		closeBtn.setVisible(false);
	}

	public void setCloseBtnVisible(boolean flag) {
		if (canClose) {
			closeBtn.setVisible(flag);
		}
	}

	public CloseableTabTitle(ImageIcon icon, String title, boolean canClose,
			JTabbedPane tabbedPane) {
		initComponents();
		iconLbl.setIcon(icon);
		titleLbl.setText(title);
		this.canClose = canClose;
		closeBtn.setVisible(canClose);
		this.tabbedPane = tabbedPane;
	}

	public CloseableTabTitle(MemberBean bean, String title, boolean canClose,
			JTabbedPane tabbedPane) {
		initComponents();
		this.jPanel2.removeAll();
		this.jPanel2.add(new HeadImagePanel(bean.getDefaultImg(), bean
				.getState(), 40, 40), java.awt.BorderLayout.CENTER);
		titleLbl.setText(title);
		this.canClose = canClose;
		closeBtn.setVisible(canClose);
		this.tabbedPane = tabbedPane;
	}

	public void setNotice(boolean flag) {
		if (flag) {
			//			titleLbl.setForeground(Color.red);
			//			Font oldFont = titleLbl.getFont();
			//			Font newFont = new Font(oldFont.getFontName(), Font.BOLD, oldFont
			//					.getSize());
			//			titleLbl.setFont(newFont);
			this.setOpaque(true);
			this.setBackground(new Color(252, 235, 166));
		} else {
			//			Font oldFont = titleLbl.getFont();
			//			Font newFont = new Font(oldFont.getFontName(), Font.PLAIN, oldFont
			//					.getSize());
			//			titleLbl.setFont(newFont);
			//			titleLbl.setForeground(UIManager.getColor("Label.foreground"));
			this.setOpaque(false);
		}
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		closeBtn = new javax.swing.JButton();
		jPanel1 = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		iconLbl = new javax.swing.JLabel();
		titleLbl = new javax.swing.JLabel();

		setMaximumSize(new java.awt.Dimension(170, 80));
		setMinimumSize(new java.awt.Dimension(170, 40));
		setOpaque(false);
		setPreferredSize(new java.awt.Dimension(170, 50));
		setLayout(new java.awt.BorderLayout());

		closeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/dialog-no-2.png"))); // NOI18N
		closeBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1,
				1));
		closeBtn.setContentAreaFilled(false);
		closeBtn.setPreferredSize(new java.awt.Dimension(24, 24));
		closeBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				closeBtnClick(evt);
			}
		});
		add(closeBtn, java.awt.BorderLayout.EAST);

		jPanel1.setOpaque(false);

		jPanel2.setOpaque(false);
		jPanel2.setLayout(new java.awt.BorderLayout());

		iconLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/systemheads/default40.png"))); // NOI18N
		jPanel2.add(iconLbl, java.awt.BorderLayout.CENTER);

		jPanel1.add(jPanel2);

		titleLbl.setText("jLabel2");
		jPanel1.add(titleLbl);

		add(jPanel1, java.awt.BorderLayout.WEST);
	}// </editor-fold>
	//GEN-END:initComponents

	public void closeBtnEvent(java.awt.event.ActionEvent evt, int tabIndex) {
	}

	public boolean canRemove() {
		return true;
	}

	private void closeBtnClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnClick
		for (int i = 0; i < tabbedPane.getTabCount(); i++) {
			if (this.equals(tabbedPane.getTabComponentAt(i))) {
				if (canRemove()) {
					tabbedPane.removeTabAt(i);
					closeBtnEvent(evt, i);
				}
				break;
			}
		}
	}//GEN-LAST:event_closeBtnClick
	//GEN-BEGIN:variables
	// Variables declaration - do not modify

	private javax.swing.JButton closeBtn;
	private javax.swing.JLabel iconLbl;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JLabel titleLbl;

	// End of variables declaration//GEN-END:variables

	public void setTopic(String topic) {
		this.titleLbl.setText(topic);
	}

	public void setMemberInfo(MemberBean bean) {
		this.setTopic(bean.getRealName());
		this.jPanel2.removeAll();
		this.jPanel2.add(new HeadImagePanel(bean.getDefaultImg(), bean
				.getState(), 40, 40), java.awt.BorderLayout.CENTER);
	}
}
