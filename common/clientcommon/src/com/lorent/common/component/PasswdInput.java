/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InputField.java
 *
 * Created on 2011-9-9, 11:03:02
 */
package com.lorent.common.component;

import java.awt.event.KeyListener;

/**
 *
 * @author jack
 */
public class PasswdInput extends javax.swing.JPanel {

	public PasswdInput() {
		initComponents();
	}

	public void setLabel(String str) {
		this.txtLbl.setText(str);
	}

	public String getLabel() {
		return this.txtLbl.getText();
	}

	public void setText(String str) {
		this.passwdInput.setText(str);
	}

	public String getText() {
		return new String(this.passwdInput.getPassword());
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		txtLbl = new javax.swing.JLabel();
		passwdInput = new javax.swing.JPasswordField();

		setOpaque(false);

		txtLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		txtLbl.setText("jLabel1");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addGap(10, 10, 10).addComponent(
						txtLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 43,
						Short.MAX_VALUE).addPreferredGap(
						javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(passwdInput,
								javax.swing.GroupLayout.PREFERRED_SIZE, 166,
								javax.swing.GroupLayout.PREFERRED_SIZE)));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createParallelGroup(
						javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(passwdInput,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(txtLbl)));
	}// </editor-fold>
	//GEN-END:initComponents
	//GEN-BEGIN:variables
	// Variables declaration - do not modify

	private javax.swing.JPasswordField passwdInput;
	private javax.swing.JLabel txtLbl;

	// End of variables declaration//GEN-END:variables
	@Override
	public void addKeyListener(KeyListener l) {
		passwdInput.addKeyListener(l);
	}

}
