/*
 * VideoClipPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import java.awt.image.BufferedImage;

import javax.swing.plaf.basic.BasicToolBarUI;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.painter.ImagePainter;
import org.jdesktop.swingx.painter.ImagePainter.ScaleType;

import com.lorent.vovo.Vovo;
import com.lorent.vovo.util.Constants.DataKey;

/**
 *
 * @author  __USER__
 */
public class VideoClipPanel extends javax.swing.JPanel {

	private static Logger log = Logger.getLogger(VideoClipPanel.class);

	/** Creates new form VideoClipPanel */
	public VideoClipPanel() {
		initComponents();
		jToolBar1.setUI(new BasicToolBarUI());
		jScrollPane1.getViewport().setOpaque(false);
		BufferedImage whiteimg;
		try {
			whiteimg = Vovo.getMyContext().getDataManager().getValue(
					DataKey.WHITE_IMAGE.toString());
			ImagePainter ip = new ImagePainter(whiteimg);
			ip.setScaleToFit(true);
			ip.setScaleType(ScaleType.Distort);
			//			this.bannerPanel.setBackgroundPainter(ip);
			jXPanel1.setBackgroundPainter(ip);
		} catch (Exception e) {
			log.error("VideoClipPanel()", e);
			e.printStackTrace();
		}
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jToolBar1 = new javax.swing.JToolBar();
		reflashButton = new javax.swing.JButton();
		uploadVideoClipButton = new javax.swing.JButton();
		jXPanel1 = new org.jdesktop.swingx.JXPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		videoClipPanel = new javax.swing.JPanel();
		jPanel1 = new javax.swing.JPanel();
		prePageButton = new javax.swing.JButton();
		lastPageButton = new javax.swing.JButton();

		setOpaque(false);
		setLayout(new java.awt.BorderLayout());

		jToolBar1.setFloatable(false);
		jToolBar1.setRollover(true);
		jToolBar1.setOpaque(false);

		reflashButton.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/reflash.png"))); // NOI18N
		reflashButton.setText("\u5237\u65b0");
		reflashButton.setFocusable(false);
		reflashButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		reflashButton.setMaximumSize(new java.awt.Dimension(60, 47));
		reflashButton.setMinimumSize(new java.awt.Dimension(60, 47));
		reflashButton.setPreferredSize(new java.awt.Dimension(60, 47));
		reflashButton
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		reflashButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				reflashButtonActionPerformed(evt);
			}
		});
		jToolBar1.add(reflashButton);

		uploadVideoClipButton
				.setIcon(new javax.swing.ImageIcon(getClass().getResource(
						"/com/lorent/vovo/resource/images/edit-add-2.png"))); // NOI18N
		uploadVideoClipButton.setText("\u4e0a\u4f20\u89c6\u9891");
		uploadVideoClipButton.setContentAreaFilled(false);
		uploadVideoClipButton.setFocusable(false);
		uploadVideoClipButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		uploadVideoClipButton.setMaximumSize(new java.awt.Dimension(60, 47));
		uploadVideoClipButton.setMinimumSize(new java.awt.Dimension(60, 47));
		uploadVideoClipButton.setPreferredSize(new java.awt.Dimension(60, 47));
		uploadVideoClipButton
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		uploadVideoClipButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						uploadVideoClipButtonActionPerformed(evt);
					}
				});
		jToolBar1.add(uploadVideoClipButton);

		add(jToolBar1, java.awt.BorderLayout.PAGE_START);

		jXPanel1.setOpaque(false);
		jXPanel1.setLayout(new java.awt.BorderLayout());

		jScrollPane1.setOpaque(false);

		videoClipPanel.setOpaque(false);
		videoClipPanel.setLayout(new javax.swing.BoxLayout(videoClipPanel,
				javax.swing.BoxLayout.Y_AXIS));
		jScrollPane1.setViewportView(videoClipPanel);

		jXPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

		add(jXPanel1, java.awt.BorderLayout.CENTER);

		jPanel1.setOpaque(false);
		jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

		prePageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/arrow-left-2.png"))); // NOI18N
		prePageButton.setText("\u4e0a\u4e00\u9875");
		prePageButton.setEnabled(false);
		prePageButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				prePageButtonActionPerformed(evt);
			}
		});
		jPanel1.add(prePageButton);

		lastPageButton.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource(
						"/com/lorent/vovo/resource/images/arrow-right-2.png"))); // NOI18N
		lastPageButton.setText("\u4e0b\u4e00\u9875");
		lastPageButton.setEnabled(false);
		lastPageButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				lastPageButtonActionPerformed(evt);
			}
		});
		jPanel1.add(lastPageButton);

		add(jPanel1, java.awt.BorderLayout.SOUTH);
	}// </editor-fold>
	//GEN-END:initComponents

	private void lastPageButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Vovo.exeC("videoclip", "lastVideoClipPage");
	}

	private void prePageButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Vovo.exeC("videoclip", "preVideoClipPage");
	}

	private void reflashButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Vovo.exeC("videoclip", "reflashVideoClipPanel");
	}

	private void uploadVideoClipButtonActionPerformed(
			java.awt.event.ActionEvent evt) {
		Vovo.exeC("videoclip", "showUploadVideoClipDialog");
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JToolBar jToolBar1;
	private org.jdesktop.swingx.JXPanel jXPanel1;
	private javax.swing.JButton lastPageButton;
	private javax.swing.JButton prePageButton;
	private javax.swing.JButton reflashButton;
	private javax.swing.JButton uploadVideoClipButton;
	private javax.swing.JPanel videoClipPanel;

	// End of variables declaration//GEN-END:variables

	public javax.swing.JPanel getVideoClipPanel() {
		return videoClipPanel;
	}

	public javax.swing.JButton getLastPageButton() {
		return lastPageButton;
	}

	public javax.swing.JButton getPrePageButton() {
		return prePageButton;
	}

}