/*
 * MsgUploadFileDialog.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.lvmc.ui;

import javax.swing.DefaultListModel;

/**
 *
 * @author  __USER__
 */
public class MsgUploadFileDialog extends javax.swing.JDialog {

	/** Creates new form MsgUploadFileDialog */
	public MsgUploadFileDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		messageLabel = new javax.swing.JLabel();
		jPanel3 = new javax.swing.JPanel();
		okButton = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		messageList = new javax.swing.JList();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setMinimumSize(new java.awt.Dimension(500, 300));

		messageLabel.setText("text");
		jPanel1.add(messageLabel);

		getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

		jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER,
				70, 10));

		okButton.setText("\u786e\u5b9a");
		okButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				okButtonActionPerformed(evt);
			}
		});
		jPanel3.add(okButton);

		getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

		messageList.setModel(new DefaultListModel());
		jScrollPane1.setViewportView(messageList);

		getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				MsgUploadFileDialog dialog = new MsgUploadFileDialog(
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
	private javax.swing.JPanel jPanel1;
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