/*
 * ConferenceCardInfoPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import java.awt.image.BufferedImage;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.painter.ImagePainter;
import org.jdesktop.swingx.painter.ImagePainter.ScaleType;

import com.lorent.vovo.Vovo;
import com.lorent.vovo.util.Constants;

/**
 *
 * @author  __USER__
 */
public class ConferenceCardInfoPanel extends javax.swing.JPanel {

	private static Logger log = Logger.getLogger(ConferenceCardInfoPanel.class);

	/** Creates new form ConferenceCardInfoPanel */
	public ConferenceCardInfoPanel() {
		initComponents();
		//		BufferedImage whiteimg;
		try {
			BufferedImage backgroundimg = Vovo.getMyContext().getDataManager()
					.getValue(
							Constants.DataKey.BACKGROUND_GAUSSIAN_IMAGE
									.toString());
			ImagePainter ip = new ImagePainter(backgroundimg);
			//			ip.setScaleToFit(true);
			ip.setScaleType(ScaleType.Distort);
			//			this.bannerPanel.setBackgroundPainter(ip);
			backgroundXPanel.setBackgroundPainter(ip);

			BufferedImage whiteImage = Vovo.getMyContext().getDataManager()
					.getValue(Constants.DataKey.WHITE_IMAGE.toString());
			ImagePainter ip2 = new ImagePainter(whiteImage);
			ip2.setScaleToFit(true);
			ip2.setScaleType(ScaleType.Distort);
			backgroundXPanel1.setBackgroundPainter(ip2);
			this.showSpecLabel.setVisible(false);
			this.showSpecLabel.setCursor(new java.awt.Cursor(
					java.awt.Cursor.HAND_CURSOR));
		} catch (Exception e) {
			log.error("ConferenceCardInfoPanel()", e);
			e.printStackTrace();
		}
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		backgroundXPanel = new org.jdesktop.swingx.JXPanel();
		backgroundXPanel1 = new org.jdesktop.swingx.JXPanel();
		jPanel3 = new javax.swing.JPanel();
		iconLabel = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		conferenceNameLabel = new javax.swing.JLabel();
		confNoLabel = new javax.swing.JLabel();
		conferenceRoleLabel = new javax.swing.JLabel();
		creatorLabel = new javax.swing.JLabel();
		needApplyLabel = new javax.swing.JLabel();
		topicLabel = new javax.swing.JLabel();
		jPanel4 = new javax.swing.JPanel();
		descriptionLabel = new javax.swing.JLabel();
		showSpecLabel = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();

		setMaximumSize(new java.awt.Dimension(350, 200));
		setMinimumSize(new java.awt.Dimension(350, 200));
		setOpaque(false);
		setPreferredSize(new java.awt.Dimension(350, 200));
		setLayout(new java.awt.BorderLayout());

		backgroundXPanel.setLayout(new java.awt.BorderLayout());

		backgroundXPanel1.setMinimumSize(new java.awt.Dimension(100, 300));
		backgroundXPanel1.setOpaque(false);
		backgroundXPanel1.setLayout(new java.awt.BorderLayout());

		jPanel3.setOpaque(false);

		iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/video-display.png"))); // NOI18N

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout.createSequentialGroup().addContainerGap()
						.addComponent(iconLabel,
								javax.swing.GroupLayout.DEFAULT_SIZE, 129,
								Short.MAX_VALUE).addContainerGap()));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout.createSequentialGroup().addContainerGap()
						.addComponent(iconLabel,
								javax.swing.GroupLayout.DEFAULT_SIZE, 154,
								Short.MAX_VALUE).addContainerGap()));

		backgroundXPanel1.add(jPanel3, java.awt.BorderLayout.WEST);

		jPanel1.setOpaque(false);
		jPanel1.setLayout(new java.awt.GridLayout(0, 1, 0, 10));

		conferenceNameLabel.setText("conferenceName");
		jPanel1.add(conferenceNameLabel);

		confNoLabel.setText("confNo");
		jPanel1.add(confNoLabel);

		conferenceRoleLabel.setText("conferenceRole");
		jPanel1.add(conferenceRoleLabel);

		creatorLabel.setText("creator");
		jPanel1.add(creatorLabel);

		needApplyLabel.setText("needApply");
		jPanel1.add(needApplyLabel);

		topicLabel.setText("topic");
		jPanel1.add(topicLabel);

		jPanel4.setOpaque(false);
		jPanel4.setLayout(new java.awt.BorderLayout());

		descriptionLabel.setText("description");
		jPanel4.add(descriptionLabel, java.awt.BorderLayout.CENTER);

		showSpecLabel.setForeground(new java.awt.Color(51, 102, 255));
		showSpecLabel.setText("\u67e5\u770b");
		showSpecLabel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				showSpecLabelMouseClicked(evt);
			}
		});
		jPanel4.add(showSpecLabel, java.awt.BorderLayout.EAST);

		jPanel1.add(jPanel4);

		backgroundXPanel1.add(jPanel1, java.awt.BorderLayout.CENTER);

		backgroundXPanel.add(backgroundXPanel1, java.awt.BorderLayout.CENTER);

		jPanel2.setOpaque(false);

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 350,
				Short.MAX_VALUE));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 18,
				Short.MAX_VALUE));

		backgroundXPanel.add(jPanel2, java.awt.BorderLayout.SOUTH);

		add(backgroundXPanel, java.awt.BorderLayout.CENTER);
	}// </editor-fold>
	//GEN-END:initComponents

	private void showSpecLabelMouseClicked(java.awt.event.MouseEvent evt) {
		Vovo.exeC("conference", "showConferenceDescDialog",
				getConferenceListItem());
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private org.jdesktop.swingx.JXPanel backgroundXPanel;
	private org.jdesktop.swingx.JXPanel backgroundXPanel1;
	private javax.swing.JLabel confNoLabel;
	private javax.swing.JLabel conferenceNameLabel;
	private javax.swing.JLabel conferenceRoleLabel;
	private javax.swing.JLabel creatorLabel;
	private javax.swing.JLabel descriptionLabel;
	private javax.swing.JLabel iconLabel;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JLabel needApplyLabel;
	private javax.swing.JLabel showSpecLabel;
	private javax.swing.JLabel topicLabel;
	// End of variables declaration//GEN-END:variables

	private ConferenceListItem conferenceListItem;

	public ConferenceListItem getConferenceListItem() {
		return conferenceListItem;
	}

	public void setConferenceListItem(ConferenceListItem conferenceListItem) {
		this.conferenceListItem = conferenceListItem;
	}

	public javax.swing.JLabel getConfNoLabel() {
		return confNoLabel;
	}

	public javax.swing.JLabel getConferenceNameLabel() {
		return conferenceNameLabel;
	}

	public javax.swing.JLabel getConferenceRoleLabel() {
		return conferenceRoleLabel;
	}

	public javax.swing.JLabel getCreatorLabel() {
		return creatorLabel;
	}

	public javax.swing.JLabel getDescriptionLabel() {
		return descriptionLabel;
	}

	public javax.swing.JLabel getIconLabel() {
		return iconLabel;
	}

	public javax.swing.JLabel getNeedApplyLabel() {
		return needApplyLabel;
	}

	public javax.swing.JLabel getTopicLabel() {
		return topicLabel;
	}

	public javax.swing.JLabel getShowSpecLabel() {
		return showSpecLabel;
	}

}