/*
 * VodListItem.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import java.awt.Cursor;
import java.awt.Toolkit;

import com.lorent.common.dto.LCMVideoClip;
import com.lorent.vovo.VovoVod;

/**
 *
 * @author  __USER__
 */
public class VodListItem extends javax.swing.JPanel {

	private LCMVideoClip lcmVideoClip;

	public LCMVideoClip getLcmVideoClip() {
		return lcmVideoClip;
	}

	public void setLcmVideoClip(LCMVideoClip lcmVideoClip) {
		this.lcmVideoClip = lcmVideoClip;
	}

	/** Creates new form VodListItem */
	public VodListItem() {
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

		jXPanel1 = new org.jdesktop.swingx.JXPanel();
		jPanel1 = new javax.swing.JPanel();
		jPanel3 = new javax.swing.JPanel();
		videoTitleLabel = new javax.swing.JLabel();
		jPanel4 = new javax.swing.JPanel();
		videoDescriptionLabel = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		videoPictureXPanel = new org.jdesktop.swingx.JXPanel();
		durationXPanel = new org.jdesktop.swingx.JXPanel();
		durationLabel = new javax.swing.JLabel();

		setOpaque(false);
		setLayout(new java.awt.BorderLayout());

		jXPanel1.setOpaque(false);
		jXPanel1.setLayout(new java.awt.BorderLayout());

		jPanel1.setOpaque(false);
		jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1,
				javax.swing.BoxLayout.Y_AXIS));

		jPanel3.setOpaque(false);
		jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 12,
				3));

		videoTitleLabel.setFont(new java.awt.Font("微软雅黑", 1, 14));
		videoTitleLabel.setText("jLabel1");
		videoTitleLabel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				videoTitleLabelMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				videoTitleLabelMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				videoTitleLabelMouseExited(evt);
			}
		});
		jPanel3.add(videoTitleLabel);

		jPanel1.add(jPanel3);

		jPanel4.setOpaque(false);
		jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 12,
				2));

		videoDescriptionLabel.setFont(new java.awt.Font("微软雅黑", 0, 11));
		videoDescriptionLabel.setText("jLabel2");
		jPanel4.add(videoDescriptionLabel);

		jPanel1.add(jPanel4);

		jXPanel1.add(jPanel1, java.awt.BorderLayout.PAGE_END);

		jPanel2.setOpaque(false);

		videoPictureXPanel.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(204, 204, 204), 2));
		videoPictureXPanel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				videoPictureXPanelMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				videoPictureXPanelMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				videoPictureXPanelMouseExited(evt);
			}
		});
		videoPictureXPanel.setLayout(new java.awt.BorderLayout());

		durationXPanel.setOpaque(false);
		durationXPanel.setLayout(new java.awt.FlowLayout(
				java.awt.FlowLayout.LEFT));

		durationLabel.setText("jLabel1");
		durationXPanel.add(durationLabel);

		videoPictureXPanel.add(durationXPanel, java.awt.BorderLayout.SOUTH);

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel2Layout.createSequentialGroup().addContainerGap()
						.addComponent(videoPictureXPanel,
								javax.swing.GroupLayout.DEFAULT_SIZE, 141,
								Short.MAX_VALUE).addContainerGap()));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				jPanel2Layout.createSequentialGroup().addContainerGap()
						.addComponent(videoPictureXPanel,
								javax.swing.GroupLayout.DEFAULT_SIZE, 157,
								Short.MAX_VALUE).addContainerGap()));

		jXPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

		add(jXPanel1, java.awt.BorderLayout.CENTER);
	}// </editor-fold>
	//GEN-END:initComponents

	private void videoTitleLabelMouseClicked(java.awt.event.MouseEvent evt) {
		VovoVod.exeC("videoclip", "playVideoClip", getLcmVideoClip()
				.getHttpVideoUrlHyper());
	}

	private void videoPictureXPanelMouseClicked(java.awt.event.MouseEvent evt) {
		VovoVod.exeC("videoclip", "playVideoClip", getLcmVideoClip()
				.getHttpVideoUrlHyper());
	}

	private void videoTitleLabelMouseExited(java.awt.event.MouseEvent evt) {
		videoTitleLabel.setFont(new java.awt.Font("微软雅黑", 1, 14));
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	private void videoPictureXPanelMouseExited(java.awt.event.MouseEvent evt) {
		videoPictureXPanel.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(204, 204, 204), 2, false));

		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	private void videoPictureXPanelMouseEntered(java.awt.event.MouseEvent evt) {
		videoPictureXPanel.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(255, 255, 60), 2, false));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	private void videoTitleLabelMouseEntered(java.awt.event.MouseEvent evt) {
		videoTitleLabel.setFont(new java.awt.Font("微软雅黑", 3, 14));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JLabel durationLabel;
	private org.jdesktop.swingx.JXPanel durationXPanel;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private org.jdesktop.swingx.JXPanel jXPanel1;
	private javax.swing.JLabel videoDescriptionLabel;
	private org.jdesktop.swingx.JXPanel videoPictureXPanel;
	private javax.swing.JLabel videoTitleLabel;

	// End of variables declaration//GEN-END:variables
	public org.jdesktop.swingx.JXPanel getVideoPictureXPanel() {
		return videoPictureXPanel;
	}

	public javax.swing.JLabel getVideoDescriptionLabel() {
		return videoDescriptionLabel;
	}

	public javax.swing.JLabel getVideoTitleLabel() {
		return videoTitleLabel;
	}

	public javax.swing.JLabel getDurationLabel() {
		return durationLabel;
	}

	public org.jdesktop.swingx.JXPanel getDurationXPanel() {
		return durationXPanel;
	}

}