/*
 * PlayMonitorFrame.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import java.awt.Toolkit;

import com.lorent.vovo.util.Constants;

/**
 *
 * @author  __USER__
 */
public class PlayMonitorFrame extends javax.swing.JFrame {

	/** Creates new form PlayMonitorFrame */
	public PlayMonitorFrame() {
//		initComponents();
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("\u89c6\u9891\u76d1\u63a7");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().createImage(
				getClass().getResource(Constants.TRAYICON_PATH)));
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("\u89c6\u9891\u76d1\u63a7");
		setResizable(false);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400,
				Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300,
				Short.MAX_VALUE));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new PlayMonitorFrame().setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	// End of variables declaration//GEN-END:variables

}