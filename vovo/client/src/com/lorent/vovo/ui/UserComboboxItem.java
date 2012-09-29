/*
 * UserComboboxItem.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

/**
 *
 * @author  __USER__
 */
public class UserComboboxItem extends javax.swing.JPanel {

	/** Creates new form UserComboboxItem */
	public UserComboboxItem() {
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

		imageLabel = new javax.swing.JLabel();
		userNameLabel = new javax.swing.JLabel();

		setOpaque(false);
		setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

		imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/vovo16.png"))); // NOI18N
		add(imageLabel);

		userNameLabel.setText("jLabel2");
		add(userNameLabel);
	}// </editor-fold>
	//GEN-END:initComponents

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JLabel imageLabel;
	private javax.swing.JLabel userNameLabel;

	// End of variables declaration//GEN-END:variables

	public javax.swing.JLabel getUserNameLabel() {
		return userNameLabel;
	}

	public javax.swing.JLabel getImageLabel() {
		return imageLabel;
	}

}