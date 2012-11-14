/*
 * VideoClipItem.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;

import org.jdesktop.swingx.painter.ImagePainter;
import org.jdesktop.swingx.painter.ImagePainter.ScaleType;

import com.lorent.common.dto.LCMVideoClip;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.util.Constants.DataKey;

/**
 *
 * @author  __USER__
 */
public class VideoClipItem extends javax.swing.JPanel {

	private LCMVideoClip lcmVideoClip;

	public LCMVideoClip getLcmVideoClip() {
		return lcmVideoClip;
	}

	public void setLcmVideoClip(LCMVideoClip lcmVideoClip) {
		this.lcmVideoClip = lcmVideoClip;
	}

	/** Creates new form VideoClipItem */
	public VideoClipItem() {
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

		backgroundXPanel = new org.jdesktop.swingx.JXPanel();
		jPanel1 = new javax.swing.JPanel();
		pictureXPanel = new org.jdesktop.swingx.JXPanel();
		jPanel2 = new javax.swing.JPanel();
		jPanel4 = new javax.swing.JPanel();
		titleLabel = new javax.swing.JLabel();
		jPanel5 = new javax.swing.JPanel();
		descriptionLabel = new javax.swing.JLabel();
		jPanel3 = new javax.swing.JPanel();
		detailButton = new javax.swing.JButton();
		playButton = new javax.swing.JButton();
		deleteButton = new javax.swing.JButton();

		setMaximumSize(new java.awt.Dimension(2147483647, 100));
		setMinimumSize(new java.awt.Dimension(97, 100));
		setOpaque(false);
		setPreferredSize(new java.awt.Dimension(97, 100));
		setLayout(new java.awt.BorderLayout());

		backgroundXPanel.setOpaque(false);
		backgroundXPanel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				backgroundXPanelMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				backgroundXPanelMouseExited(evt);
			}
		});
		backgroundXPanel.setLayout(new java.awt.BorderLayout());

		jPanel1.setOpaque(false);

		javax.swing.GroupLayout pictureXPanelLayout = new javax.swing.GroupLayout(
				pictureXPanel);
		pictureXPanel.setLayout(pictureXPanelLayout);
		pictureXPanelLayout.setHorizontalGroup(pictureXPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 101, Short.MAX_VALUE));
		pictureXPanelLayout.setVerticalGroup(pictureXPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 72, Short.MAX_VALUE));

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout.createSequentialGroup().addContainerGap()
						.addComponent(pictureXPanel,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE).addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout.createSequentialGroup().addContainerGap()
						.addComponent(pictureXPanel,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE).addContainerGap()));

		backgroundXPanel.add(jPanel1, java.awt.BorderLayout.WEST);

		jPanel2.setOpaque(false);
		jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2,
				javax.swing.BoxLayout.Y_AXIS));

		jPanel4.setMaximumSize(new java.awt.Dimension(32767, 27));
		jPanel4.setOpaque(false);
		jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5,
				15));

		titleLabel.setText("jLabel2");
		jPanel4.add(titleLabel);

		jPanel2.add(jPanel4);

		jPanel5.setOpaque(false);
		jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		descriptionLabel.setText("jLabel4");
		jPanel5.add(descriptionLabel);

		jPanel2.add(jPanel5);

		backgroundXPanel.add(jPanel2, java.awt.BorderLayout.CENTER);

		jPanel3.setMaximumSize(new java.awt.Dimension(70, 32767));
		jPanel3.setMinimumSize(new java.awt.Dimension(70, 35));
		jPanel3.setOpaque(false);
		jPanel3.setPreferredSize(new java.awt.Dimension(70, 35));
		jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER,
				5, 8));

		detailButton.setText("\u8be6\u7ec6");
		detailButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				detailButtonActionPerformed(evt);
			}
		});
		jPanel3.add(detailButton);

		playButton.setText("\u64ad\u653e");
		playButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				playButtonActionPerformed(evt);
			}
		});
		jPanel3.add(playButton);

		deleteButton.setText("\u5220\u9664");
		deleteButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteButtonActionPerformed(evt);
			}
		});
		jPanel3.add(deleteButton);

		backgroundXPanel.add(jPanel3, java.awt.BorderLayout.EAST);

		add(backgroundXPanel, java.awt.BorderLayout.CENTER);
	}// </editor-fold>
	//GEN-END:initComponents

	private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Vovo.exeC("videoclip", "playVideoClip",lcmVideoClip.getHttpVideoUrlHigh());
	}

	private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Vovo.exeC("videoclip", "deleteVideoClip", this);
	}

	private void detailButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Vovo.exeC("videoclip", "showVideoClipInfoDialog", this);
	}

	private void backgroundXPanelMouseExited(java.awt.event.MouseEvent evt) {
		//		this.setBorder(null);
		backgroundXPanel.setBackgroundPainter(null);
	}

	private void backgroundXPanelMouseEntered(java.awt.event.MouseEvent evt) {
		//		this.setBorder(BorderFactory.createEtchedBorder());
		BufferedImage whiteimg = Vovo.getMyContext().getDataManager().getValue(
				DataKey.WHITE_IMAGE_X.toString());
		ImagePainter ip = new ImagePainter(whiteimg);
		ip.setScaleToFit(true);
		ip.setScaleType(ScaleType.Distort);
		backgroundXPanel.setBackgroundPainter(ip);
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private org.jdesktop.swingx.JXPanel backgroundXPanel;
	private javax.swing.JButton deleteButton;
	private javax.swing.JLabel descriptionLabel;
	private javax.swing.JButton detailButton;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private org.jdesktop.swingx.JXPanel pictureXPanel;
	private javax.swing.JButton playButton;
	private javax.swing.JLabel titleLabel;

	// End of variables declaration//GEN-END:variables

	public org.jdesktop.swingx.JXPanel getPictureXPanel() {
		return pictureXPanel;
	}

	public javax.swing.JLabel getDescriptionLabel() {
		return descriptionLabel;
	}

	public javax.swing.JLabel getTitleLabel() {
		return titleLabel;
	}

	public javax.swing.JButton getDeleteButton() {
		return deleteButton;
	}

}