/*
 * MemberTreeNodePanel.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import javax.swing.ImageIcon;

import com.lorent.common.tree.MemberBean;
import com.lorent.vovo.util.Constants;

/**
 *
 * @author  Jack
 */
public class MemberTreeNodePanel extends javax.swing.JPanel {

	/** Creates new form MemberTreeNodePanel */
	public MemberTreeNodePanel() {
		initComponents();

	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		jPanel2 = new javax.swing.JPanel();
		userPanel = new javax.swing.JPanel();
		jPanel3 = new javax.swing.JPanel();
		sign = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		name = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();
		username = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();

		setOpaque(false);
		setLayout(new java.awt.GridBagLayout());

		jPanel2.setMaximumSize(new java.awt.Dimension(40, 42));
		jPanel2.setMinimumSize(new java.awt.Dimension(40, 42));
		jPanel2.setOpaque(false);
		jPanel2.setPreferredSize(new java.awt.Dimension(40, 42));
		jPanel2.setLayout(new java.awt.GridBagLayout());

		userPanel.setMaximumSize(new java.awt.Dimension(40, 40));
		userPanel.setMinimumSize(new java.awt.Dimension(40, 40));
		userPanel.setOpaque(false);
		userPanel.setPreferredSize(new java.awt.Dimension(40, 40));
		userPanel.setLayout(new java.awt.BorderLayout());
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = 2;
		jPanel2.add(userPanel, gridBagConstraints);

		jPanel3.setMaximumSize(new java.awt.Dimension(40, 2));
		jPanel3.setMinimumSize(new java.awt.Dimension(40, 2));
		jPanel3.setOpaque(false);
		jPanel3.setPreferredSize(new java.awt.Dimension(40, 2));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		jPanel2.add(jPanel3, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		add(jPanel2, gridBagConstraints);

		sign.setText("  ");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		add(sign, gridBagConstraints);

		jPanel1.setOpaque(false);
		jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2,
				2));

		name.setText("Jack");
		jPanel1.add(name);

		jLabel1.setText("(");
		jPanel1.add(jLabel1);

		username.setText("1001");
		jPanel1.add(username);

		jLabel3.setText(")");
		jPanel1.add(jLabel3);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		add(jPanel1, gridBagConstraints);
	}// </editor-fold>
	//GEN-END:initComponents

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JLabel name;
	private javax.swing.JLabel sign;
	private javax.swing.JPanel userPanel;
	private javax.swing.JLabel username;
	// End of variables declaration//GEN-END:variables

	private MemberBean memberBean;

	public void setMemberInfo(MemberBean bean) {
		this.memberBean = bean;
		this.name.setText(bean.getRealName());
		this.username.setText(bean.getLccAccount());
		if (bean.getSign() != null) {
			this.sign.setText(bean.getSign());
		}
		this.userPanel.add(new HeadImagePanel(bean.getDefaultImg(), bean
				.getState(), 40, 40), java.awt.BorderLayout.CENTER);
		/*if (bean.getDefaultImg() == null || bean.getDefaultImg().equals("")) {
			switch (bean.getState()) {
			case Constants.STATUS_ONLINE:
				this.userhead.setIcon(new ImageIcon(getClass().getResource(
						Constants.SYSTEM_HEAD_IMAGE_PATH + "default40.png")));
				break;
			case Constants.STATUS_OFFLINE:
				this.userhead.setIcon(new ImageIcon(getClass().getResource(
						Constants.SYSTEM_HEAD_IMAGE_PATH
								+ "default40offline.png")));
				break;
			case Constants.STATUS_AWAY:
				this.userhead
						.setIcon(new ImageIcon(getClass().getResource(
								Constants.SYSTEM_HEAD_IMAGE_PATH
										+ "default40away.png")));
				break;
			case Constants.STATUS_BUSY:
				this.userhead
						.setIcon(new ImageIcon(getClass().getResource(
								Constants.SYSTEM_HEAD_IMAGE_PATH
										+ "default40busy.png")));
				break;

			}

		}*/
		//		System.out.println("isSelect:" + isSelect + "&isFocus:" + isFocus);
	}
}