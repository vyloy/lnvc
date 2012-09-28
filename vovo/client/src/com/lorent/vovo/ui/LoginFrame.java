/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LoginFrame.java
 *
 * Created on 2011-12-6, 10:07:48
 */
package com.lorent.vovo.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.painter.ImagePainter;
import org.jdesktop.swingx.painter.ImagePainter.ScaleType;

import com.lorent.common.util.ParaUtil;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.controller.LoginStatusManager;
import com.lorent.vovo.dto.LoginInfo;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.UserInfoUtil;

/**
 *
 * @author jack
 */
public class LoginFrame extends JFrame {
	private Logger log = Logger.getLogger(LoginFrame.class);

	/** Creates new form sLoginFrame */
	public LoginFrame() {
		initComponents();
		setIconImage(Toolkit
				.getDefaultToolkit()
				.createImage(
						getClass()
								.getResource(
										Constants.TRAYICON_PATH)));
		this.setPreferredSize(new Dimension(334, 100));
		this.setLocationRelativeTo(null);
		try {
			BufferedImage img = ImageIO.read(getClass().getResource(
					"/com/lorent/vovo/resource/images/banner.png"));
			ImagePainter ip = new ImagePainter(img);
			ip.setScaleToFit(true);
			ip.setScaleType(ScaleType.Distort);
			this.bannerPanel.setBackgroundPainter(ip);
			img = ImageIO.read(getClass().getResource(
					"/com/lorent/vovo/resource/images/login_bg.png"));
			ip = new ImagePainter(img);
			ip.setScaleToFit(true);
			ip.setScaleType(ScaleType.Distort);
			this.jXPanel1.setBackgroundPainter(ip);
		} catch (IOException e) {
			log.error("LoginFrame()", e);
			e.printStackTrace();
		}

	}

	class UserComboboxCellRenderer implements ListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {

			UserComboboxItem userComboboxItem = new UserComboboxItem();
			userComboboxItem.setSize(100, 50);
			if (isSelected) {
				userComboboxItem.setBorder(BorderFactory.createEtchedBorder());
			} else {
				userComboboxItem.setBorder(null);
			}

			if (value instanceof LoginInfo) {
				LoginInfo info = (LoginInfo) value;
			} else if (value instanceof String) {
				try {
					LoginInfo info = UserInfoUtil.getInfo(value + "");
					if (info == null || info.getUsername() == null
							|| info.getUsername().equals("")) {
						userComboboxItem.getUserNameLabel().setText("");
						userComboboxItem.getImageLabel().setIcon(null);
					} else {
						String nickname = info.getNickName() == null ? ""
								: info.getNickName() + " ";
						userComboboxItem.getUserNameLabel().setText(
								"  " + nickname + info.getUsername());
					}

				} catch (Exception e) {
					log.error("UserComboboxCellRenderer", e);
					e.printStackTrace();
				}
			}

			return userComboboxItem;
		}

	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jXPanel1 = new org.jdesktop.swingx.JXPanel();
		jXPanel2 = new org.jdesktop.swingx.JXPanel();
		usernameCb = new javax.swing.JComboBox();
		jLabel1 = new javax.swing.JLabel();
		savePasswdCb = new javax.swing.JCheckBox();
		autoLoginCb = new javax.swing.JCheckBox();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		passwdIt = new javax.swing.JPasswordField();
		serverIPIt = new javax.swing.JTextField();
		statusCombobox = new com.lorent.vovo.ui.StatusCombobox();
		bannerPanel = new org.jdesktop.swingx.JXPanel();
		jPanel1 = new javax.swing.JPanel();
		confirmBtn = new com.lorent.common.component.CommonButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("\u767b\u5f55");
		setMinimumSize(new java.awt.Dimension(334, 183));
		setResizable(false);
		addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				formKeyReleased(evt);
			}
		});

		jXPanel1.setLayout(new java.awt.BorderLayout());

		jXPanel2.setOpaque(false);
		jXPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		usernameCb.setEditable(true);
		usernameCb.setModel(new DefaultComboBoxModel(UserInfoUtil
				.getAllUserLoginInfos().toArray()));
		usernameCb.setMaximumSize(new java.awt.Dimension(32767, 22));
		usernameCb.setMinimumSize(new java.awt.Dimension(120, 22));
		usernameCb.setRenderer(new UserComboboxCellRenderer());
		usernameCb.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				usernameCbItemStateChanged(evt);
			}
		});
		usernameCb.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				usernameCbKeyReleased(evt);
			}
		});
		jXPanel2.add(usernameCb,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 190,
						-1));

		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
		jLabel1.setText("\u7528\u6237\u540d");
		jXPanel2.add(jLabel1,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1,
						-1));

		savePasswdCb.setText("\u4fdd\u5b58\u5bc6\u7801");
		savePasswdCb.setOpaque(false);
		savePasswdCb.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				savePasswdCbActionPerformed(evt);
			}
		});
		jXPanel2.add(savePasswdCb,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, -1,
						-1));

		autoLoginCb.setText("\u81ea\u52a8\u767b\u5f55");
		autoLoginCb.setOpaque(false);
		autoLoginCb.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				autoLoginCbActionPerformed(evt);
			}
		});
		jXPanel2.add(autoLoginCb,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, -1,
						-1));

		jLabel2.setText("\u5bc6\u7801");
		jXPanel2.add(jLabel2,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1,
						-1));

		jLabel3.setText("\u670d\u52a1\u5668");
		jXPanel2.add(jLabel3,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1,
						-1));

		passwdIt.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				passwdItKeyReleased(evt);
			}
		});
		jXPanel2.add(passwdIt,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 190,
						-1));

		serverIPIt.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				serverIPItKeyReleased(evt);
			}
		});
		jXPanel2.add(serverIPIt,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100,
						190, -1));
		jXPanel2.add(statusCombobox,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 70,
						-1));

		jXPanel1.add(jXPanel2, java.awt.BorderLayout.CENTER);

		bannerPanel.setPreferredSize(new java.awt.Dimension(100, 64));

		javax.swing.GroupLayout bannerPanelLayout = new javax.swing.GroupLayout(
				bannerPanel);
		bannerPanel.setLayout(bannerPanelLayout);
		bannerPanelLayout.setHorizontalGroup(bannerPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 332, Short.MAX_VALUE));
		bannerPanelLayout.setVerticalGroup(bannerPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 64, Short.MAX_VALUE));

		jXPanel1.add(bannerPanel, java.awt.BorderLayout.NORTH);

		jPanel1.setOpaque(false);

		confirmBtn.setText("\u767b\u5f55");
		confirmBtn.setPreferredSize(new java.awt.Dimension(80, 23));
		confirmBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				confirmBtnActionPerformed(evt);
			}
		});
		confirmBtn.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				confirmBtnKeyReleased(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				jPanel1Layout.createSequentialGroup().addContainerGap(242,
						Short.MAX_VALUE).addComponent(confirmBtn,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				jPanel1Layout.createSequentialGroup().addContainerGap(
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(confirmBtn,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		jXPanel1.add(jPanel1, java.awt.BorderLayout.SOUTH);

		getContentPane().add(jXPanel1, java.awt.BorderLayout.CENTER);

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void confirmBtnKeyReleased(java.awt.event.KeyEvent evt) {
		usernameCbKeyReleased(evt);
	}

	private void formKeyReleased(java.awt.event.KeyEvent evt) {
		
	}

	private void serverIPItKeyReleased(java.awt.event.KeyEvent evt) {
		usernameCbKeyReleased(evt);
	}

	private void passwdItKeyReleased(java.awt.event.KeyEvent evt) {
		usernameCbKeyReleased(evt);
	}

	private void usernameCbKeyReleased(java.awt.event.KeyEvent evt) {
		if (evt.getKeyChar() == java.awt.event.KeyEvent.VK_ENTER) {
			Vovo.getMyContext().getExecuteManager().executeController("main",
					"doLogin", this);
		}
	}

	private void savePasswdCbActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void autoLoginCbActionPerformed(java.awt.event.ActionEvent evt) {
		if (autoLoginCb.getModel().isSelected()) {
			savePasswdCb.setSelected(true);
		}
	}

	private int preSelectUserNameIndex = -1;

	private void usernameCbItemStateChanged(java.awt.event.ItemEvent evt) {
		if (usernameCb.getSelectedIndex() != preSelectUserNameIndex) {
			preSelectUserNameIndex = usernameCb.getSelectedIndex();
			Vovo.getMyContext().getExecuteManager().executeController("main",
					"loginFrameChangeUsername", usernameCb.getSelectedItem());
		}
	}

	private void confirmBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmBtnActionPerformed
		//		SwingUtilities.invokeLater(new Runnable() {
		//
		//			@Override
		//			public void run() {
		//				ControllerFacade.execute("mainController", "doLogin", ParaUtil
		//						.newInstance().setString("username",
		//								usernameCb.getSelectedItem().toString())
		//						.setString("password", passwdIt.getText().trim())
		//						.setString("confno", confnoIt.getText().trim())
		//						.setString("serverIP", serverIPIt.getText().trim())
		//						.setBoolean("autoLogin",
		//								autoLoginCb.getModel().isSelected())
		//						.setBoolean("savePasswd",
		//								savePasswdCb.getModel().isSelected())
		//
		//				);
		//			}
		//		});
		Vovo.getMyContext().getExecuteManager().executeController("main",
				"doLogin", this);
	}//GEN-LAST:event_confirmBtnActionPerformed

	public void setUserNames(String[] usernames) {
		DefaultComboBoxModel dcb = null;
		if (usernames == null) {
			dcb = new DefaultComboBoxModel();
		} else {
			dcb = new DefaultComboBoxModel(usernames);
		}
		usernameCb.setModel(dcb);
	}

	public void changeInfo(String passwd, boolean autoLogin,
			boolean savePasswd, String serverip, int status) {
		passwdIt.setText(passwd);
		autoLoginCb.setSelected(autoLogin);
		savePasswdCb.setSelected(savePasswd);
		serverIPIt.setText(serverip);
		this.statusCombobox.setSelectStatus(status);
	}

	public void enableLoginBtn(boolean flag) {
		this.confirmBtn.setEnabled(flag);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new LoginFrame().setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JCheckBox autoLoginCb;
	private org.jdesktop.swingx.JXPanel bannerPanel;
	private com.lorent.common.component.CommonButton confirmBtn;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JPanel jPanel1;
	private org.jdesktop.swingx.JXPanel jXPanel1;
	private org.jdesktop.swingx.JXPanel jXPanel2;
	private javax.swing.JPasswordField passwdIt;
	private javax.swing.JCheckBox savePasswdCb;
	private javax.swing.JTextField serverIPIt;
	private com.lorent.vovo.ui.StatusCombobox statusCombobox;
	private javax.swing.JComboBox usernameCb;

	// End of variables declaration//GEN-END:variables

	public javax.swing.JCheckBox getAutoLoginCb() {
		return autoLoginCb;
	}

	public javax.swing.JPasswordField getPasswdIt() {
		return passwdIt;
	}

	public javax.swing.JCheckBox getSavePasswdCb() {
		return savePasswdCb;
	}

	public javax.swing.JTextField getServerIPIt() {
		return serverIPIt;
	}

	public javax.swing.JComboBox getUsernameCb() {
		return usernameCb;
	}

	public com.lorent.vovo.ui.StatusCombobox getStatusCombobox() {
		return statusCombobox;
	}

}
