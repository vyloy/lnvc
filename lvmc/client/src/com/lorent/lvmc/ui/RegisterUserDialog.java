/*
 * RegisterUserDialog.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.lvmc.ui;

import com.lorent.lvmc.controller.ControllerFacade;

/**
 *
 * @author  __USER__
 */
public class RegisterUserDialog extends javax.swing.JDialog {

	/** Creates new form RegisterUserDialog */
	public RegisterUserDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jXPanel1 = new org.jdesktop.swingx.JXPanel();
		jPanel1 = new javax.swing.JPanel();
		usernameInput = new com.lorent.common.component.TextInput();
		realnameInput = new com.lorent.common.component.TextInput();
		emailInput = new com.lorent.common.component.TextInput();
		mobileInput = new com.lorent.common.component.TextInput();
		phoneInput = new com.lorent.common.component.TextInput();
		passwdInput = new com.lorent.common.component.PasswdInput();
		rePasswdInput = new com.lorent.common.component.PasswdInput();
		jPanel3 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		genderComboBox = new javax.swing.JComboBox();
		jPanel2 = new javax.swing.JPanel();
		okButton = new com.lorent.common.component.CommonButton();
		cancelButton = new com.lorent.common.component.CommonButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("\u6ce8\u518c");
		setResizable(false);
		setUndecorated(true);

		jXPanel1.setLayout(new java.awt.BorderLayout());

		usernameInput.setLabel("\u6635\u79f0");

		realnameInput.setLabel("\u771f\u5b9e\u59d3\u540d");

		emailInput.setLabel("\u90ae\u7bb1\u5730\u5740");

		mobileInput.setLabel("\u624b\u673a\u53f7\u7801");

		phoneInput.setLabel("\u56fa\u5b9a\u7535\u8bdd");

		passwdInput.setLabel("\u5bc6\u7801");

		rePasswdInput.setLabel("\u91cd\u590d\u5bc6\u7801");

		jLabel1.setLabelFor(genderComboBox);
		jLabel1.setText("\u6027\u522b");

		genderComboBox.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				jPanel3Layout.createSequentialGroup().addContainerGap(43,
						Short.MAX_VALUE).addComponent(jLabel1).addPreferredGap(
						javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(genderComboBox,
								javax.swing.GroupLayout.PREFERRED_SIZE, 167,
								javax.swing.GroupLayout.PREFERRED_SIZE)));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout.createParallelGroup(
						javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(genderComboBox,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel1)));

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				emailInput,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				44,
																				Short.MAX_VALUE)
																		.addComponent(
																				mobileInput,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				usernameInput,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				44,
																				Short.MAX_VALUE)
																		.addComponent(
																				realnameInput,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				phoneInput,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				44,
																				Short.MAX_VALUE)
																		.addComponent(
																				jPanel3,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				passwdInput,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				44,
																				Short.MAX_VALUE)
																		.addComponent(
																				rePasswdInput,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				228,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));

		jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL,
				new java.awt.Component[] { jPanel3, mobileInput, rePasswdInput,
						realnameInput });

		jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL,
				new java.awt.Component[] { emailInput, passwdInput, phoneInput,
						usernameInput });

		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGap(21, 21, 21)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				realnameInput,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				mobileInput,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				usernameInput,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				emailInput,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																phoneInput,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jPanel3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																passwdInput,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																rePasswdInput,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(22, Short.MAX_VALUE)));

		jXPanel1.add(jPanel1, java.awt.BorderLayout.CENTER);

		jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER,
				50, 2));

		okButton.setText("\u786e\u5b9a");
		okButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				okButtonActionPerformed(evt);
			}
		});
		jPanel2.add(okButton);

		cancelButton.setText("\u53d6\u6d88");
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});
		jPanel2.add(cancelButton);

		jXPanel1.add(jPanel2, java.awt.BorderLayout.SOUTH);

		getContentPane().add(jXPanel1, java.awt.BorderLayout.CENTER);

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {
		ControllerFacade.execute("mainController", "doRegisterUser", this);
	}

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				RegisterUserDialog dialog = new RegisterUserDialog(
						new javax.swing.JFrame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private com.lorent.common.component.CommonButton cancelButton;
	private com.lorent.common.component.TextInput emailInput;
	private javax.swing.JComboBox genderComboBox;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private org.jdesktop.swingx.JXPanel jXPanel1;
	private com.lorent.common.component.TextInput mobileInput;
	private com.lorent.common.component.CommonButton okButton;
	private com.lorent.common.component.PasswdInput passwdInput;
	private com.lorent.common.component.TextInput phoneInput;
	private com.lorent.common.component.PasswdInput rePasswdInput;
	private com.lorent.common.component.TextInput realnameInput;
	private com.lorent.common.component.TextInput usernameInput;
	// End of variables declaration//GEN-END:variables
	public com.lorent.common.component.TextInput getEmailInput() {
		return emailInput;
	}

	public javax.swing.JComboBox getGenderComboBox() {
		return genderComboBox;
	}

	public com.lorent.common.component.TextInput getMobileInput() {
		return mobileInput;
	}

	public com.lorent.common.component.PasswdInput getPasswdInput() {
		return passwdInput;
	}

	public com.lorent.common.component.TextInput getPhoneInput() {
		return phoneInput;
	}

	public com.lorent.common.component.PasswdInput getRePasswdInput() {
		return rePasswdInput;
	}

	public com.lorent.common.component.TextInput getRealnameInput() {
		return realnameInput;
	}

	public com.lorent.common.component.TextInput getUsernameInput() {
		return usernameInput;
	}

}