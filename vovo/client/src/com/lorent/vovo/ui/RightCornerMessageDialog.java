/*
 * RightCornerMessageDialog.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import org.jivesoftware.smack.util.StringUtils;

import com.lorent.vovo.util.VovoStringUtil;

/**
 *
 * @author  __USER__
 */
public class RightCornerMessageDialog extends javax.swing.JDialog {

	/** Creates new form RightCornerMessageDialog */
	public RightCornerMessageDialog(java.awt.Frame parent, boolean modal) {
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

		jLabel1 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);

		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("jLabel1");
		jLabel1.setMaximumSize(new java.awt.Dimension(150, 100));
		jLabel1.setPreferredSize(new java.awt.Dimension(150, 100));
		getContentPane().add(jLabel1, java.awt.BorderLayout.CENTER);

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				RightCornerMessageDialog dialog = new RightCornerMessageDialog(
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
	private javax.swing.JLabel jLabel1;

	// End of variables declaration//GEN-END:variables

	public void setContent(String content) {
		jLabel1.setText("<html><center>" + VovoStringUtil.escapeForXML(content) + "</center></html>");
	}
}