/*
 * TestCustomHeadFrame.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import java.awt.Graphics;

/**
 *
 * @author  __USER__
 */
public class TestCustomHeadFrame extends javax.swing.JFrame {

	CustomHeadOperatePanel customHeadOperatePanel = null;
	/** Creates new form TestCustomHeadFrame */
	public TestCustomHeadFrame() {
		initComponents();
		CustomHeadOperatePanel customHeadOperatePanel = new CustomHeadOperatePanel(
				"C://Documents and Settings//test//桌面//20121123140058_337_PSY_1080P_4800K_mp4.jpg");
//		截图.png
//		CustomHeadOperatePanel customHeadOperatePanel = new CustomHeadOperatePanel(
//		"C://Documents and Settings//test//桌面//head_1.jpg");
		System.out.println("h:" + customHeadOperatePanel.getPreferredSize().height + ";" + "w:"
				+ customHeadOperatePanel.getPreferredSize().width);
		jPanel1.add(customHeadOperatePanel);
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

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jPanel1.setPreferredSize(new java.awt.Dimension(400, 300));
		jPanel1.setLayout(new java.awt.BorderLayout());
		getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new TestCustomHeadFrame().setVisible(true);
			}
		});
	}
	

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JPanel jPanel1;
	// End of variables declaration//GEN-END:variables

}