/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LoginFrame.java
 *
 * Created on 2011-12-6, 10:07:48
 */
package com.lorent.lvmc.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.painter.ImagePainter;
import org.jdesktop.swingx.painter.ImagePainter.ScaleType;

import com.lorent.common.util.ParaUtil;
import com.lorent.lvmc.controller.ControllerFacade;
import com.lorent.lvmc.util.ConfigUtil;
import com.lorent.lvmc.util.StringUtil;

/**
 *
 * @author jack
 */
public class LoginFrame extends JFrame {
	private Logger log = Logger.getLogger(LoginFrame.class);

	/** Creates new form sLoginFrame */
	public LoginFrame() {
		initComponents();
		setIconImage(Toolkit.getDefaultToolkit().createImage(
				getClass().getResource(ConfigUtil.getProperty("logo_path"))));
		this.setPreferredSize(new Dimension(334, 100));
		this.setLocationRelativeTo(null);
		try {
			BufferedImage img = ImageIO.read(getClass().getResource(
					"/com/lorent/lvmc/resource/images/banner.png"));
			ImagePainter ip = new ImagePainter(img);
			ip.setScaleToFit(true);
			ip.setScaleType(ScaleType.Distort);
			this.bannerPanel.setBackgroundPainter(ip);
			img = ImageIO.read(getClass().getResource(
					"/com/lorent/lvmc/resource/images/login_bg.png"));
			ip = new ImagePainter(img);
			ip.setScaleToFit(true);
			ip.setScaleType(ScaleType.Distort);
			this.jXPanel1.setBackgroundPainter(ip);
		} catch (IOException e) {
			log.error("LoginFrame()", e);
			e.printStackTrace();
		}
		autoLoginCb.setVisible(false);
		setTitle(StringUtil.getUIString("LoginFrame.title"));
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		bannerPanel = new org.jdesktop.swingx.JXPanel();
		jXPanel1 = new org.jdesktop.swingx.JXPanel();
		passwdIt = new com.lorent.common.component.PasswdInput();
		confirmBtn = new com.lorent.common.component.CommonButton();
		confnoIt = new com.lorent.common.component.TextInput();
		serverIPIt = new com.lorent.common.component.TextInput();
		usernameCb = new javax.swing.JComboBox();
		jLabel1 = new javax.swing.JLabel();
		savePasswdCb = new javax.swing.JCheckBox();
		autoLoginCb = new javax.swing.JCheckBox();
		confPasswdIt = new com.lorent.common.component.PasswdInput();
		registerBtn = new com.lorent.common.component.CommonButton();
		selectConfListButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setMinimumSize(new java.awt.Dimension(334, 183));
		setResizable(false);

		bannerPanel.setPreferredSize(new java.awt.Dimension(100, 64));

		javax.swing.GroupLayout bannerPanelLayout = new javax.swing.GroupLayout(
				bannerPanel);
		bannerPanel.setLayout(bannerPanelLayout);
		bannerPanelLayout.setHorizontalGroup(bannerPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 334, Short.MAX_VALUE));
		bannerPanelLayout.setVerticalGroup(bannerPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 64, Short.MAX_VALUE));

		getContentPane().add(bannerPanel, java.awt.BorderLayout.PAGE_START);

		passwdIt.setLabel("\u7528\u6237\u5bc6\u7801");

		confirmBtn.setText("\u767b\u5f55");
		confirmBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				confirmBtnActionPerformed(evt);
			}
		});

		confnoIt.setLabel("\u4f1a\u8bae\u5ba4");

		serverIPIt.setEnable(false);
		serverIPIt.setEnabled(false);
		serverIPIt.setLabel("\u670d\u52a1\u5668");

		usernameCb.setEditable(true);
		usernameCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4" }));
		usernameCb.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				usernameCbItemStateChanged(evt);
			}
		});

		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
		jLabel1.setText("\u7528\u6237\u53f7\u7801");

		savePasswdCb.setText("\u4fdd\u5b58\u5bc6\u7801");
		savePasswdCb.setOpaque(false);

		autoLoginCb.setText("\u81ea\u52a8\u767b\u5f55");
		autoLoginCb.setOpaque(false);
		autoLoginCb.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				autoLoginCbActionPerformed(evt);
			}
		});

		confPasswdIt.setLabel("\u4f1a\u8bae\u5bc6\u7801");

		registerBtn.setText("\u6ce8\u518c");
		registerBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				registerBtnActionPerformed(evt);
			}
		});

		selectConfListButton.setText("...");
		selectConfListButton.setToolTipText("\u9009\u62e9\u4f1a\u8bae\u5ba4");
		selectConfListButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		selectConfListButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						selectConfListButtonActionPerformed(evt);
					}
				});

		javax.swing.GroupLayout jXPanel1Layout = new javax.swing.GroupLayout(
				jXPanel1);
		jXPanel1.setLayout(jXPanel1Layout);
		jXPanel1Layout
				.setHorizontalGroup(jXPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jXPanel1Layout
										.createSequentialGroup()
										.addGroup(
												jXPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jXPanel1Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				registerBtn,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				64,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				182,
																				Short.MAX_VALUE)
																		.addComponent(
																				confirmBtn,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				64,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jXPanel1Layout
																		.createSequentialGroup()
																		.addGap(
																				37,
																				37,
																				37)
																		.addGroup(
																				jXPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addGroup(
																								jXPanel1Layout
																										.createSequentialGroup()
																										.addComponent(
																												savePasswdCb)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												autoLoginCb))
																						.addComponent(
																								serverIPIt,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								confPasswdIt,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addGroup(
																								jXPanel1Layout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.TRAILING,
																												false)
																										.addGroup(
																												jXPanel1Layout
																														.createSequentialGroup()
																														.addComponent(
																																jLabel1,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE)
																														.addPreferredGap(
																																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																														.addComponent(
																																usernameCb,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																167,
																																javax.swing.GroupLayout.PREFERRED_SIZE))
																										.addGroup(
																												javax.swing.GroupLayout.Alignment.LEADING,
																												jXPanel1Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.TRAILING)
																														.addComponent(
																																passwdIt,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																confnoIt,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE))))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				selectConfListButton,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				26,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		jXPanel1Layout
				.setVerticalGroup(jXPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jXPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jXPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																usernameCb,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel1))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												passwdIt,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jXPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																selectConfListButton,
																0, 0,
																Short.MAX_VALUE)
														.addComponent(
																confnoIt,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												confPasswdIt,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												serverIPIt,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jXPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																autoLoginCb)
														.addComponent(
																savePasswdCb))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												jXPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																confirmBtn,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																22,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																registerBtn,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																22,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));

		getContentPane().add(jXPanel1, java.awt.BorderLayout.CENTER);

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void selectConfListButtonActionPerformed(
			java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		ControllerFacade.execute("mainController", "showSelectConfListDialog",
				this);
	}

	private void registerBtnActionPerformed(java.awt.event.ActionEvent evt) {
		ControllerFacade.execute("mainController", "showRegisterUserDialog");
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
			//			System.out.println("usernameCbItemStateChanged" + usernameCb.getSelectedItem());
			ControllerFacade.execute("mainController", "changeUsername",
					usernameCb.getSelectedItem());
		}
	}

	private void confirmBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmBtnActionPerformed
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				ControllerFacade.execute("mainController", "doLogin", ParaUtil
						.newInstance().setString("username",
								usernameCb.getSelectedItem().toString())
						.setString("password", passwdIt.getText().trim())
						.setString("confno", confnoIt.getText().trim())
						.setString("serverIP", serverIPIt.getText().trim())
						.setString("confpassword",
								confPasswdIt.getText().trim()).setBoolean(
								"autoLogin",
								autoLoginCb.getModel().isSelected())
						.setBoolean("savePasswd",
								savePasswdCb.getModel().isSelected())

				);
			}
		});
	}//GEN-LAST:event_confirmBtnActionPerformed

	public void setLoginInfo(String[] usernames, String confno, String serverIP) {
		DefaultComboBoxModel dcb = null;
		if (usernames == null) {
			dcb = new DefaultComboBoxModel();
		} else {
			dcb = new DefaultComboBoxModel(usernames);
		}
		usernameCb.setModel(dcb);
		confnoIt.setText(confno);
		serverIPIt.setText(serverIP);

	}

	public void changeInfo(String passwd, boolean autoLogin, boolean savePasswd) {
		passwdIt.setText(passwd);
		autoLoginCb.setSelected(autoLogin);
		savePasswdCb.setSelected(savePasswd);
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
	private com.lorent.common.component.PasswdInput confPasswdIt;
	private com.lorent.common.component.CommonButton confirmBtn;
	private com.lorent.common.component.TextInput confnoIt;
	private javax.swing.JLabel jLabel1;
	private org.jdesktop.swingx.JXPanel jXPanel1;
	private com.lorent.common.component.PasswdInput passwdIt;
	private com.lorent.common.component.CommonButton registerBtn;
	private javax.swing.JCheckBox savePasswdCb;
	private javax.swing.JButton selectConfListButton;
	private com.lorent.common.component.TextInput serverIPIt;
	private javax.swing.JComboBox usernameCb;

	// End of variables declaration//GEN-END:variables

	public com.lorent.common.component.TextInput getServerIPIt() {
		return serverIPIt;
	}

	public com.lorent.common.component.TextInput getConfnoIt() {
		return confnoIt;
	}

}
