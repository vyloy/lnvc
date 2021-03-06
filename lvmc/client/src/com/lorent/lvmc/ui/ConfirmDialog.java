/*
 * ConfirmDialog.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.lvmc.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.lorent.common.component.FadeWindow;

/**
 *
 * @author  __USER__
 */
public class ConfirmDialog extends javax.swing.JDialog {

	/** Creates new form ConfirmDialog */
	public ConfirmDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		iconLabel = new javax.swing.JLabel();
		messageLabel = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		okButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);

		jPanel1.setMinimumSize(new java.awt.Dimension(282, 83));
		jPanel1.setPreferredSize(new java.awt.Dimension(282, 83));
		jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER,
				10, 20));

		iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/lvmc/resource/images/configButton.png"))); // NOI18N
		iconLabel.setMinimumSize(new java.awt.Dimension(50, 0));
		jPanel1.add(iconLabel);
		jPanel1.add(messageLabel);

		getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

		jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER,
				70, 10));

		okButton.setText("\u662f");
		okButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				okButtonActionPerformed(evt);
			}
		});
		jPanel2.add(okButton);

		cancelButton.setText("\u5426");
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});
		jPanel2.add(cancelButton);

		getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
	}

	private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				final ConfirmDialog confirmDialog = new ConfirmDialog(null,
						false);
				confirmDialog.getOkButton().addActionListener(
						new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
//								JOptionPane.showMessageDialog(null, "dfdfsf");
								confirmDialog.dispose();
								
							}
						});

				confirmDialog
						.addWindowListener(new java.awt.event.WindowAdapter() {
							public void windowClosing(
									java.awt.event.WindowEvent e) {
//								System.exit(0);
								JOptionPane.showMessageDialog(null, "closing");
							}
						});

				//				JLabel.setText("<html>auto new line</html>");
				//				JLabel.setText("<html>line<br>force new line</html>");
				confirmDialog.getMessageLabel().setText(
						"xxxxxxxxxxx   申请控制，是否允许？");
				//				confirmDialog.getMessageLabel().setText(
				//						"<html><br>方式发 申请控制，是否允许？<html>");
				FadeWindow fadeWindow = new FadeWindow(confirmDialog);
				fadeWindow.setVisible(true);
				fadeWindow.startRuning();
			}
		});
	}

	public javax.swing.JButton getCancelButton() {
		return cancelButton;
	}

	public javax.swing.JLabel getIconLabel() {
		return iconLabel;
	}

	public javax.swing.JLabel getMessageLabel() {
		return messageLabel;
	}

	public javax.swing.JButton getOkButton() {
		return okButton;
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton cancelButton;
	private javax.swing.JLabel iconLabel;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JLabel messageLabel;
	private javax.swing.JButton okButton;
	// End of variables declaration//GEN-END:variables

}