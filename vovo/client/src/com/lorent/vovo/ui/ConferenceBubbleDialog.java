/*
 * ConferenceBubbleDialog.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.painter.ImagePainter;
import org.jdesktop.swingx.painter.ImagePainter.ScaleType;

import com.lorent.vovo.Vovo;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.Constants.DataKey;

/**
 *
 * @author  __USER__
 */
public class ConferenceBubbleDialog extends javax.swing.JDialog {

	private static Logger log = Logger.getLogger(ConferenceBubbleDialog.class);

	private String confNo;

	public String getConfNo() {
		return confNo;
	}

	public void setConfNo(String confNo) {
		this.confNo = confNo;
	}

	/** Creates new form ConferenceBubbleDialog */
	public ConferenceBubbleDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		jPanel3.setVisible(false);
		jScrollPane1.getViewport().setOpaque(false);
		try {
			BufferedImage resultImage = Vovo.getMyContext().getDataManager()
					.getValue(
							Constants.DataKey.BACKGROUND_GAUSSIAN_IMAGE
									.toString());

			ImagePainter ip = new ImagePainter(resultImage);
			//			ip.setScaleToFit(true);
			ip.setScaleType(ScaleType.Distort);

			backgroundXPanel.setBackgroundPainter(ip);

			BufferedImage whiteimg = Vovo.getMyContext().getDataManager().getValue(DataKey.WHITE_IMAGE.toString());
			ImagePainter ip2 = new ImagePainter(whiteimg);
			ip2.setScaleToFit(true);
			ip2.setScaleType(ScaleType.Distort);
			whiteXPanel.setBackgroundPainter(ip2);
		} catch (Exception e) {
			log.error("ConferenceBubbleDialog()", e);
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

		backgroundXPanel = new org.jdesktop.swingx.JXPanel();
		jPanel1 = new javax.swing.JPanel();
		iconLabel = new javax.swing.JLabel();
		whiteXPanel = new org.jdesktop.swingx.JXPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		msgTextPane = new javax.swing.JTextPane();
		jPanel3 = new javax.swing.JPanel();
		joinConferenceButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setMinimumSize(new java.awt.Dimension(395, 175));

		backgroundXPanel.setLayout(new java.awt.BorderLayout());

		jPanel1.setOpaque(false);
		jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER,
				10, 10));

		iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/emesene-48.png"))); // NOI18N
		jPanel1.add(iconLabel);

		backgroundXPanel.add(jPanel1, java.awt.BorderLayout.WEST);

		whiteXPanel.setOpaque(false);
		whiteXPanel.setLayout(new java.awt.BorderLayout());

		jScrollPane1.setBorder(null);
		jScrollPane1.setOpaque(false);

		msgTextPane.setContentType("text/html");
		msgTextPane.setEditable(false);
		msgTextPane.setOpaque(false);
		jScrollPane1.setViewportView(msgTextPane);

		whiteXPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

		jPanel3.setOpaque(false);
		jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT,
				10, 5));

		joinConferenceButton.setText(Vovo.getMyContext().getViewManager()
				.getUIString("ConferenceBubbleDialog.joinConference"));
		joinConferenceButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						joinConferenceButtonActionPerformed(evt);
					}
				});
		jPanel3.add(joinConferenceButton);

		whiteXPanel.add(jPanel3, java.awt.BorderLayout.SOUTH);

		backgroundXPanel.add(whiteXPanel, java.awt.BorderLayout.CENTER);

		getContentPane().add(backgroundXPanel, java.awt.BorderLayout.CENTER);

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void joinConferenceButtonActionPerformed(
			java.awt.event.ActionEvent evt) {
		Vovo.exeC("conference", "joinConference", confNo);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				ConferenceBubbleDialog dialog = new ConferenceBubbleDialog(
						new javax.swing.JFrame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private org.jdesktop.swingx.JXPanel backgroundXPanel;
	private javax.swing.JLabel iconLabel;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JButton joinConferenceButton;
	private javax.swing.JTextPane msgTextPane;
	private org.jdesktop.swingx.JXPanel whiteXPanel;

	// End of variables declaration//GEN-END:variables
	public javax.swing.JLabel getIconLabel() {
		return iconLabel;
	}

	public javax.swing.JTextPane getMsgTextPane() {
		return msgTextPane;
	}

}