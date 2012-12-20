/*
 * VodFrame.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import java.awt.CardLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.painter.ImagePainter;
import org.jdesktop.swingx.painter.ImagePainter.ScaleType;

import com.lorent.vovo.VovoVod;
import com.lorent.vovo.util.Constants;

/**
 *
 * @author  __USER__
 */
public class VodFrame extends javax.swing.JFrame {

	private Logger log = Logger.getLogger(VodFrame.class);

	/** Creates new form VodFrame */
	public VodFrame() {
		initComponents();
		ImagePainter imagePainter = null;
		try {
			String logofilepath = Constants.USER_DIR + "\\logo\\vod.png";
			setIconImage(Toolkit.getDefaultToolkit().createImage(logofilepath));

			imagePainter = new ImagePainter(ImageIO
					.read(getClass().getResource(
							"/com/lorent/vovo/resource/images/Heise15.jpg")));
		} catch (IOException e) {
			log.error("reflashVideoClipPanel ", e);
			e.printStackTrace();
		}
		imagePainter.setScaleToFit(true);
		imagePainter.setScaleType(ScaleType.Distort);
		jXPanel1.setBackgroundPainter(imagePainter);

	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		buttonGroup1 = new javax.swing.ButtonGroup();
		jXPanel1 = new org.jdesktop.swingx.JXPanel();
		vodListCardPanel = new javax.swing.JPanel();
		jToolBar1 = new javax.swing.JToolBar();
		categoryPanel = new javax.swing.JPanel();
		jToggleButton1 = new javax.swing.JToggleButton();
		jToggleButton2 = new javax.swing.JToggleButton();
		jToggleButton3 = new javax.swing.JToggleButton();
		jToggleButton4 = new javax.swing.JToggleButton();
		jToggleButton5 = new javax.swing.JToggleButton();
		jToggleButton6 = new javax.swing.JToggleButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("\u89c6\u9891\u70b9\u64ad");
		setResizable(false);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(java.awt.event.WindowEvent evt) {
				formWindowClosed(evt);
			}

			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}
		});

		jXPanel1.setLayout(new java.awt.BorderLayout());

		vodListCardPanel.setOpaque(false);
		vodListCardPanel.setLayout(new java.awt.CardLayout());
		jXPanel1.add(vodListCardPanel, java.awt.BorderLayout.CENTER);

		jToolBar1.setFloatable(false);
		jToolBar1.setRollover(true);

		categoryPanel.setOpaque(false);
		categoryPanel.setLayout(new java.awt.FlowLayout(
				java.awt.FlowLayout.CENTER, 30, 5));

		buttonGroup1.add(jToggleButton1);
		jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("/com/lorent/vovo/resource/images/vod/mov.png"))); // NOI18N
		jToggleButton1.setText("\u7535\u5f71");
		jToggleButton1
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jToggleButton1
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jToggleButton1ActionPerformed(evt);
			}
		});
		categoryPanel.add(jToggleButton1);

		buttonGroup1.add(jToggleButton2);
		jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("/com/lorent/vovo/resource/images/vod/tv.png"))); // NOI18N
		jToggleButton2.setText("\u5267\u96c6");
		jToggleButton2
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jToggleButton2
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jToggleButton2ActionPerformed(evt);
			}
		});
		categoryPanel.add(jToggleButton2);

		buttonGroup1.add(jToggleButton3);
		jToggleButton3.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("/com/lorent/vovo/resource/images/vod/news.png"))); // NOI18N
		jToggleButton3.setText("\u8d44\u8baf");
		jToggleButton3
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jToggleButton3
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jToggleButton3ActionPerformed(evt);
			}
		});
		categoryPanel.add(jToggleButton3);

		buttonGroup1.add(jToggleButton4);
		jToggleButton4.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("/com/lorent/vovo/resource/images/vod/game.png"))); // NOI18N
		jToggleButton4.setText("\u6e38\u620f");
		jToggleButton4
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jToggleButton4
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jToggleButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jToggleButton4ActionPerformed(evt);
			}
		});
		categoryPanel.add(jToggleButton4);

		buttonGroup1.add(jToggleButton5);
		jToggleButton5
				.setIcon(new javax.swing.ImageIcon(getClass().getResource(
						"/com/lorent/vovo/resource/images/vod/dongm.png"))); // NOI18N
		jToggleButton5.setText("\u52a8\u6f2b");
		jToggleButton5
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jToggleButton5
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jToggleButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jToggleButton5ActionPerformed(evt);
			}
		});
		categoryPanel.add(jToggleButton5);

		buttonGroup1.add(jToggleButton6);
		jToggleButton6
				.setIcon(new javax.swing.ImageIcon(getClass().getResource(
						"/com/lorent/vovo/resource/images/vod/other.png"))); // NOI18N
		jToggleButton6.setText("\u5176\u4ed6");
		jToggleButton6
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jToggleButton6
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jToggleButton6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jToggleButton6ActionPerformed(evt);
			}
		});
		categoryPanel.add(jToggleButton6);

		jToolBar1.add(categoryPanel);

		jXPanel1.add(jToolBar1, java.awt.BorderLayout.SOUTH);

		getContentPane().add(jXPanel1, java.awt.BorderLayout.CENTER);

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void formWindowClosing(java.awt.event.WindowEvent evt) {
		System.out.println("formWindowClosing");
		VovoVod.exeC("videoclip", "killPlayer");
	}

	private void formWindowClosed(java.awt.event.WindowEvent evt) {
		System.out.println("formWindowClosed");
	}

	private void jToggleButton5ActionPerformed(java.awt.event.ActionEvent evt) {
		CardLayout layout = (CardLayout) vodListCardPanel.getLayout();
		layout.show(vodListCardPanel, "动漫");
	}

	private void jToggleButton4ActionPerformed(java.awt.event.ActionEvent evt) {
		CardLayout layout = (CardLayout) vodListCardPanel.getLayout();
		layout.show(vodListCardPanel, "游戏");
	}

	private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		CardLayout layout = (CardLayout) vodListCardPanel.getLayout();
		layout.show(vodListCardPanel, "资讯");
	}

	private void jToggleButton6ActionPerformed(java.awt.event.ActionEvent evt) {
		CardLayout layout = (CardLayout) vodListCardPanel.getLayout();
		layout.show(vodListCardPanel, "其他");
	}

	private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		CardLayout layout = (CardLayout) vodListCardPanel.getLayout();
		layout.show(vodListCardPanel, "电视");
	}

	private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		CardLayout layout = (CardLayout) vodListCardPanel.getLayout();
		layout.show(vodListCardPanel, "电影");
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new VodFrame().setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.JPanel categoryPanel;
	private javax.swing.JToggleButton jToggleButton1;
	private javax.swing.JToggleButton jToggleButton2;
	private javax.swing.JToggleButton jToggleButton3;
	private javax.swing.JToggleButton jToggleButton4;
	private javax.swing.JToggleButton jToggleButton5;
	private javax.swing.JToggleButton jToggleButton6;
	private javax.swing.JToolBar jToolBar1;
	private org.jdesktop.swingx.JXPanel jXPanel1;
	private javax.swing.JPanel vodListCardPanel;

	// End of variables declaration//GEN-END:variables

	public javax.swing.JPanel getVodListCardPanel() {
		return vodListCardPanel;
	}

}