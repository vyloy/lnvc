/*
 * ConferenceListItem.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.painter.ImagePainter;
import org.jdesktop.swingx.painter.ImagePainter.ScaleType;

import com.lorent.common.dto.LCMConferenceDto;

/**
 *
 * @author  __USER__
 */
public class ConferenceListItem extends javax.swing.JPanel {

	private static Logger log = Logger.getLogger(ConferenceListItem.class);
	private LCMConferenceDto lcmConferenceDto;
	private BufferedImage whiteimg;

	public LCMConferenceDto getLcmConferenceDto() {
		return lcmConferenceDto;
	}

	public void setLcmConferenceDto(LCMConferenceDto lcmConferenceDto) {
		this.lcmConferenceDto = lcmConferenceDto;
	}

	/** Creates new form ConferenceListItem */
	public ConferenceListItem() {
		initComponents();
		try {
			whiteimg = ImageIO.read(getClass().getResource(
					"/com/lorent/vovo/resource/images/whitealpha.png"));
			ImagePainter ip = new ImagePainter(whiteimg);
			ip.setScaleToFit(true);
			ip.setScaleType(ScaleType.Distort);
			//			backgroundXPanel.setBackgroundPainter(ip);
		} catch (IOException e) {
			log.error("ConferenceListItem()", e);
			e.printStackTrace();
		}
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		backgroundXPanel = new org.jdesktop.swingx.JXPanel();
		iconLabel = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		infoPanel1 = new javax.swing.JPanel();
		infoTopLabel = new javax.swing.JLabel();
		infoPanel2 = new javax.swing.JPanel();
		infoCenterLabel = new javax.swing.JLabel();

		setOpaque(false);
		setLayout(new java.awt.BorderLayout());

		backgroundXPanel.setOpaque(false);
		backgroundXPanel.setLayout(new java.awt.BorderLayout());

		iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/conference0.png"))); // NOI18N
		iconLabel.setMaximumSize(new java.awt.Dimension(48, 48));
		iconLabel.setMinimumSize(new java.awt.Dimension(48, 48));
		iconLabel.setPreferredSize(new java.awt.Dimension(48, 48));
		backgroundXPanel.add(iconLabel, java.awt.BorderLayout.WEST);

		jPanel1.setOpaque(false);
		jPanel1.setLayout(new java.awt.GridLayout(2, 1));

		infoPanel1.setOpaque(false);
		infoPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,
				10, 1));

		infoTopLabel.setFont(new java.awt.Font("微软雅黑", 1, 12));
		infoTopLabel.setText("jLabel2");
		infoPanel1.add(infoTopLabel);

		jPanel1.add(infoPanel1);

		infoPanel2.setOpaque(false);
		infoPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,
				10, 1));

		infoCenterLabel.setFont(new java.awt.Font("微软雅黑", 0, 11));
		infoCenterLabel.setText("jLabel1");
		infoPanel2.add(infoCenterLabel);

		jPanel1.add(infoPanel2);

		backgroundXPanel.add(jPanel1, java.awt.BorderLayout.CENTER);

		add(backgroundXPanel, java.awt.BorderLayout.CENTER);
	}// </editor-fold>
	//GEN-END:initComponents

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private org.jdesktop.swingx.JXPanel backgroundXPanel;
	private javax.swing.JLabel iconLabel;
	private javax.swing.JLabel infoCenterLabel;
	private javax.swing.JPanel infoPanel1;
	private javax.swing.JPanel infoPanel2;
	private javax.swing.JLabel infoTopLabel;
	private javax.swing.JPanel jPanel1;

	// End of variables declaration//GEN-END:variables
	public javax.swing.JLabel getIconLabel() {
		return iconLabel;
	}

	public javax.swing.JLabel getInfoCenterLabel() {
		return infoCenterLabel;
	}

	public javax.swing.JLabel getInfoTopLabel() {
		return infoTopLabel;
	}

}