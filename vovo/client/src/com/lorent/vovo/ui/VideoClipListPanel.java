/*
 * VideoClipListPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import java.awt.image.BufferedImage;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.painter.ImagePainter;
import org.jdesktop.swingx.painter.ImagePainter.ScaleType;

import com.lorent.vovo.Vovo;
import com.lorent.vovo.util.Constants.DataKey;

/**
 *
 * @author  __USER__
 */
public class VideoClipListPanel extends javax.swing.JPanel {

	private String category;
	private int maxPageIndex;
	private int pageIndex;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getMaxPageIndex() {
		return maxPageIndex;
	}

	public void setMaxPageIndex(int maxPageIndex) {
		this.maxPageIndex = maxPageIndex;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	private Logger log = Logger.getLogger(VideoClipListPanel.class);

	/** Creates new form VideoClipListPanel */
	public VideoClipListPanel() {
		initComponents();
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

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jXPanel1 = new org.jdesktop.swingx.JXPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		videoClipPanel = new javax.swing.JPanel();
		jPanel1 = new javax.swing.JPanel();
		prePageButton = new javax.swing.JButton();
		lastPageButton = new javax.swing.JButton();

		setOpaque(false);
		setLayout(new java.awt.BorderLayout());

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
		Vovo.exeC("videoclip", "lastVideoClipPage", this);
	}

	private void prePageButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Vovo.exeC("videoclip", "preVideoClipPage", this);
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private org.jdesktop.swingx.JXPanel jXPanel1;
	private javax.swing.JButton lastPageButton;
	private javax.swing.JButton prePageButton;
	private javax.swing.JPanel videoClipPanel;

	// End of variables declaration//GEN-END:variables
	public javax.swing.JButton getLastPageButton() {
		return lastPageButton;
	}

	public javax.swing.JButton getPrePageButton() {
		return prePageButton;
	}

	public javax.swing.JPanel getVideoClipPanel() {
		return videoClipPanel;
	}

}