/*
 * HeadSampleImgPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;


import java.awt.Cursor;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.ImageUtil;

/**
 *
 * @author  __USER__
 */
public class HeadSampleImgPanel extends javax.swing.JPanel {

	private String fileName;

	/** Creates new form HeadSampleImgPanel */
	public HeadSampleImgPanel() {
		initComponents();
	}

	public HeadSampleImgPanel(String fileName) {
		this();
		this.fileName = fileName;
		ImageUtil.adjustLabelIcon(jLabel1, Constants.SYSTEM_HEAD_IMAGE_PATH_SYS
				+ this.fileName);
		//		Image temp = icon.getImage().getScaledInstance(60, 60,
		//				icon.getImage().SCALE_DEFAULT);
		//		this.jLabel1.setIcon(new ImageIcon(temp));
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
		jPanel1 = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		jPanel3 = new javax.swing.JPanel();
		jPanel4 = new javax.swing.JPanel();

		setMaximumSize(new java.awt.Dimension(63000, 63000));
		setMinimumSize(new java.awt.Dimension(80, 80));
		setOpaque(false);
		setPreferredSize(new java.awt.Dimension(80, 80));
		addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				formMouseClicked(evt);
			}
		});
		setLayout(new java.awt.BorderLayout());

		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(204, 204, 204)));
		jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jLabel1.setMaximumSize(new java.awt.Dimension(60, 60));
		jLabel1.setMinimumSize(new java.awt.Dimension(60, 60));
		jLabel1.setPreferredSize(new java.awt.Dimension(60, 60));
		jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel1MouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				jLabel1MouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				jLabel1MouseExited(evt);
			}
		});
		add(jLabel1, java.awt.BorderLayout.CENTER);

		jPanel1.setOpaque(false);
		add(jPanel1, java.awt.BorderLayout.NORTH);

		jPanel2.setOpaque(false);
		add(jPanel2, java.awt.BorderLayout.EAST);

		jPanel3.setOpaque(false);
		add(jPanel3, java.awt.BorderLayout.SOUTH);

		jPanel4.setOpaque(false);
		add(jPanel4, java.awt.BorderLayout.WEST);
	}// </editor-fold>
	//GEN-END:initComponents

	private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {
		//		jLabel1.setBorder(BorderFactory.createLineBorder(new Color(255, 255,
		//				255), 1));

	}

	private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {
		jLabel1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		//		jLabel1.setBorder(BorderFactory.createLineBorder(
		//				new Color(0, 127, 255), 1));
	}

	private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {
		if (evt.getClickCount() == 1 && evt.getButton() == evt.BUTTON1) {
//			this.dialog.setPreviewLabelImg(fileName);
			mouseClickListener.doAction(fileName);
		}
	}

	private void formMouseClicked(java.awt.event.MouseEvent evt) {
		if (evt.getClickCount() == 1 && evt.getButton() == evt.BUTTON1) {

		}
	}
	
	private HeadSampleImgPanelMouseClickListener mouseClickListener;
	
	public void setMouseClickListener(HeadSampleImgPanelMouseClickListener listener){
		mouseClickListener = listener;
	}
	
	public interface HeadSampleImgPanelMouseClickListener{
		public void doAction(String fileName);
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	// End of variables declaration//GEN-END:variables

}