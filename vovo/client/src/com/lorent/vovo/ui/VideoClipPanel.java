/*
 * VideoClipPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.plaf.basic.BasicToolBarUI;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.painter.ImagePainter;
import org.jdesktop.swingx.painter.ImagePainter.ScaleType;

import com.jtattoo.plaf.BaseTabbedPaneUI;
import com.jtattoo.plaf.vovoglass.VoVoTabbedPaneUI;
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
		monitorToolBar.setUI(new BasicToolBarUI());
		jScrollPane1.getViewport().setOpaque(false);
		jScrollPane2.getViewport().setOpaque(false);
		jTabbedPane1.setUI(new VoVoTabbedPaneUI());
		BufferedImage whiteimg;
		try {
			whiteimg = Vovo.getMyContext().getDataManager().getValue(
					DataKey.WHITE_IMAGE.toString());
			ImagePainter ip = new ImagePainter(whiteimg);
			ip.setScaleToFit(true);
			ip.setScaleType(ScaleType.Distort);
			//			this.bannerPanel.setBackgroundPainter(ip);
			jXPanel1.setBackgroundPainter(ip);
			jXPanel2.setBackgroundPainter(ip);
		} catch (Exception e) {
			log.error("VideoClipPanel()", e);
			e.printStackTrace();
		}
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jTabbedPane1 = new javax.swing.JTabbedPane();
		jPanel2 = new javax.swing.JPanel();
		jToolBar1 = new javax.swing.JToolBar();
		reflashButton = new javax.swing.JButton();
		uploadVideoClipButton = new javax.swing.JButton();
		jXPanel1 = new org.jdesktop.swingx.JXPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		videoClipPanel = new javax.swing.JPanel();
		jPanel1 = new javax.swing.JPanel();
		prePageButton = new javax.swing.JButton();
		lastPageButton = new javax.swing.JButton();
		jPanel3 = new javax.swing.JPanel();
		monitorToolBar = new javax.swing.JToolBar();
		reflashMonitorButton = new javax.swing.JButton();
		uploadMonitorButton1 = new javax.swing.JButton();
		liveTvButton = new javax.swing.JButton();
//		liveMonitorButton = new javax.swing.JButton();
		jPanel4 = new javax.swing.JPanel();
		prePageMonitorButton = new javax.swing.JButton();
		lastPageMonitorButton = new javax.swing.JButton();
		jXPanel2 = new org.jdesktop.swingx.JXPanel();
		jScrollPane2 = new javax.swing.JScrollPane();
		monitorPanel = new javax.swing.JPanel();

		setOpaque(false);
		setLayout(new java.awt.BorderLayout());

		jTabbedPane1
				.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

		jPanel2.setOpaque(false);
		jPanel2.setLayout(new java.awt.BorderLayout());

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

		jPanel2.add(jToolBar1, java.awt.BorderLayout.PAGE_START);

		jXPanel1.setOpaque(false);
		jXPanel1.setLayout(new java.awt.BorderLayout());

		jScrollPane1.setOpaque(false);

		videoClipPanel.setOpaque(false);
		videoClipPanel.setLayout(new javax.swing.BoxLayout(videoClipPanel,
				javax.swing.BoxLayout.Y_AXIS));
		jScrollPane1.setViewportView(videoClipPanel);

		jXPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

		jPanel2.add(jXPanel1, java.awt.BorderLayout.CENTER);

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

		jPanel2.add(jPanel1, java.awt.BorderLayout.SOUTH);

		jTabbedPane1.addTab("\u89c6\u9891\u70b9\u64ad", jPanel2);

		jPanel3.setOpaque(false);
		jPanel3.setLayout(new java.awt.BorderLayout());

		monitorToolBar.setFloatable(false);
		monitorToolBar.setRollover(true);
		monitorToolBar.setOpaque(false);

		reflashMonitorButton.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("/com/lorent/vovo/resource/images/reflash.png"))); // NOI18N
		reflashMonitorButton.setText("\u5237\u65b0");
		reflashMonitorButton.setFocusable(false);
		reflashMonitorButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		reflashMonitorButton.setMaximumSize(new java.awt.Dimension(60, 47));
		reflashMonitorButton.setMinimumSize(new java.awt.Dimension(60, 47));
		reflashMonitorButton.setPreferredSize(new java.awt.Dimension(60, 47));
		reflashMonitorButton
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		reflashMonitorButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						reflashMonitorButtonActionPerformed(evt);
					}
				});
		monitorToolBar.add(reflashMonitorButton);

		uploadMonitorButton1
				.setIcon(new javax.swing.ImageIcon(getClass().getResource(
						"/com/lorent/vovo/resource/images/edit-add-2.png"))); // NOI18N
		uploadMonitorButton1.setText("\u6dfb\u52a0\u76f4\u64ad");
		uploadMonitorButton1.setContentAreaFilled(false);
		uploadMonitorButton1.setFocusable(false);
		uploadMonitorButton1
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		uploadMonitorButton1.setMaximumSize(new java.awt.Dimension(60, 47));
		uploadMonitorButton1.setMinimumSize(new java.awt.Dimension(60, 47));
		uploadMonitorButton1.setPreferredSize(new java.awt.Dimension(60, 47));
		uploadMonitorButton1
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		uploadMonitorButton1
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						uploadMonitorButton1ActionPerformed(evt);
					}
				});
		monitorToolBar.add(uploadMonitorButton1);

		liveTvButton.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/video-television.png"))); // NOI18N
		liveTvButton.setText("\u89c6\u9891\u76f4\u64ad");
		liveTvButton.setContentAreaFilled(false);
		liveTvButton.setFocusable(false);
		liveTvButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		liveTvButton.setMaximumSize(new java.awt.Dimension(60, 47));
		liveTvButton.setMinimumSize(new java.awt.Dimension(60, 47));
		liveTvButton.setPreferredSize(new java.awt.Dimension(60, 47));
		liveTvButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		liveTvButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				liveTvButtonActionPerformed(evt);
			}
		});
		monitorToolBar.add(liveTvButton);

//		liveMonitorButton
//				.setIcon(new javax.swing.ImageIcon(
//						getClass()
//								.getResource(
//										"/com/lorent/vovo/resource/images/video-television.png"))); // NOI18N
//		liveMonitorButton.setText("\u89c6\u9891\u76d1\u63a7");
//		liveMonitorButton.setContentAreaFilled(false);
//		liveMonitorButton.setFocusable(false);
//		liveMonitorButton
//				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
//		liveMonitorButton.setMaximumSize(new java.awt.Dimension(60, 47));
//		liveMonitorButton.setMinimumSize(new java.awt.Dimension(60, 47));
//		liveMonitorButton.setPreferredSize(new java.awt.Dimension(60, 47));
//		liveMonitorButton
//				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
//		liveMonitorButton
//				.addActionListener(new java.awt.event.ActionListener() {
//					public void actionPerformed(java.awt.event.ActionEvent evt) {
//						liveMonitorButtonActionPerformed(evt);
//					}
//				});
		//monitorToolBar.add(liveMonitorButton);

		jPanel3.add(monitorToolBar, java.awt.BorderLayout.PAGE_START);

		jPanel4.setOpaque(false);
		jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

		prePageMonitorButton.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource(
						"/com/lorent/vovo/resource/images/arrow-left-2.png"))); // NOI18N
		prePageMonitorButton.setText("\u4e0a\u4e00\u9875");
		prePageMonitorButton.setEnabled(false);
		prePageMonitorButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						prePageMonitorButtonActionPerformed(evt);
					}
				});
		jPanel4.add(prePageMonitorButton);

		lastPageMonitorButton.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource(
						"/com/lorent/vovo/resource/images/arrow-right-2.png"))); // NOI18N
		lastPageMonitorButton.setText("\u4e0b\u4e00\u9875");
		lastPageMonitorButton.setEnabled(false);
		lastPageMonitorButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						lastPageMonitorButtonActionPerformed(evt);
					}
				});
		jPanel4.add(lastPageMonitorButton);

		jPanel3.add(jPanel4, java.awt.BorderLayout.SOUTH);

		jXPanel2.setOpaque(false);
		jXPanel2.setLayout(new java.awt.BorderLayout());

		jScrollPane2.setOpaque(false);

		monitorPanel.setOpaque(false);
		monitorPanel.setLayout(new javax.swing.BoxLayout(monitorPanel,
				javax.swing.BoxLayout.Y_AXIS));
		jScrollPane2.setViewportView(monitorPanel);

		jXPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

		jPanel3.add(jXPanel2, java.awt.BorderLayout.CENTER);

		jTabbedPane1.addTab("\u89c6\u9891\u76f4\u64ad", jPanel3);

		add(jTabbedPane1, java.awt.BorderLayout.CENTER);
		jTabbedPane1.getAccessibleContext().setAccessibleName(
				"\u89c6\u9891\u70b9\u64ad");
	}// </editor-fold>
	//GEN-END:initComponents

	private void liveMonitorButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Vovo.exeC("videoclip", "startLiveMonitor");
	}

	private void liveTvButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void lastPageMonitorButtonActionPerformed(
			java.awt.event.ActionEvent evt) {
		Vovo.exeC("videoclip", "lastMonitorPage");
	}

	private void prePageMonitorButtonActionPerformed(
			java.awt.event.ActionEvent evt) {
		Vovo.exeC("videoclip", "preMonitorPage");
	}

	private void reflashMonitorButtonActionPerformed(
			java.awt.event.ActionEvent evt) {
		Vovo.exeC("videoclip", "reflashMonitorPanel");
	}

	private void uploadMonitorButton1ActionPerformed(
			java.awt.event.ActionEvent evt) {
		Vovo.exeC("videoclip", "showUploadMonitorDialog");
	}

	private void liveTvButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Vovo.exeC("videoclip", "startLiveTv");
	}

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
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JToolBar jToolBar1;
	private org.jdesktop.swingx.JXPanel jXPanel1;
	private org.jdesktop.swingx.JXPanel jXPanel2;
	private javax.swing.JButton lastPageButton;
	private javax.swing.JButton lastPageMonitorButton;
//	private javax.swing.JButton liveMonitorButton;
	private javax.swing.JButton liveTvButton;
	private javax.swing.JPanel monitorPanel;
	private javax.swing.JToolBar monitorToolBar;
	private javax.swing.JButton prePageButton;
	private javax.swing.JButton prePageMonitorButton;
	private javax.swing.JButton reflashButton;
	private javax.swing.JButton reflashMonitorButton;
	private javax.swing.JButton uploadMonitorButton1;
	private javax.swing.JButton uploadVideoClipButton;
	private javax.swing.JPanel videoClipPanel;

	// End of variables declaration//GEN-END:variables

	public javax.swing.JPanel getVideoClipPanel() {
		return videoClipPanel;
	}

	public javax.swing.JPanel getMonitorPanel() {
		return monitorPanel;
	}

	public javax.swing.JButton getLastPageButton() {
		return lastPageButton;
	}

	public javax.swing.JButton getPrePageButton() {
		return prePageButton;
	}

	public javax.swing.JButton getLastPageMonitorButton() {
		return lastPageMonitorButton;
	}

	public javax.swing.JButton getPrePageMonitorButton() {
		return prePageMonitorButton;
	}

}