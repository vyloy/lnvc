/*
 * ConfirmUploadFileDialog.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.lvmc.ui;

import javax.swing.DefaultListModel;

/**
 *
 * @author  __USER__
 */
public class ConfirmUploadFileDialog extends javax.swing.JDialog {

	private boolean okFlag;

	public boolean isOkFlag() {
		return okFlag;
	}

	/** Creates new form ConfirmUploadFileDialog */
	public ConfirmUploadFileDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		iconLabel = new javax.swing.JLabel();
		messageLabel = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		messageList = new javax.swing.JList();
		jPanel3 = new javax.swing.JPanel();
		okButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/lvmc/resource/images/configButton.png"))); // NOI18N
		iconLabel.setMinimumSize(new java.awt.Dimension(50, 0));
		jPanel1.add(iconLabel);

		messageLabel.setText("text");
		jPanel1.add(messageLabel);

		getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

		jPanel2.setLayout(new java.awt.BorderLayout());

		messageList.setModel(new DefaultListModel());
		jScrollPane1.setViewportView(messageList);

		jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

		getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

		jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER,
				70, 10));

		okButton.setText("\u662f");
		okButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				okButtonActionPerformed(evt);
			}
		});
		jPanel3.add(okButton);

		cancelButton.setText("\u5426");
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});
		jPanel3.add(cancelButton);

		getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
		this.hide();
	}

	private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {
		okFlag = true;
		this.hide();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				ConfirmUploadFileDialog dialog = new ConfirmUploadFileDialog(
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
	private javax.swing.JButton cancelButton;
	private javax.swing.JLabel iconLabel;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel messageLabel;
	private javax.swing.JList messageList;
	private javax.swing.JButton okButton;

	// End of variables declaration//GEN-END:variables
	public javax.swing.JLabel getMessageLabel() {
		return messageLabel;
	}

	public javax.swing.JList getMessageList() {
		return messageList;
	}

}