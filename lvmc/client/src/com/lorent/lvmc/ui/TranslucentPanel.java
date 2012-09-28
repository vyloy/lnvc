package com.lorent.lvmc.ui;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TranslucentPanel extends JPanel {
	BufferedImage img;

	public TranslucentPanel() {
//		setOpaque(false);
	}

	public void paintComponent(Graphics g) {
		BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = img.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));// ���úϳɹ���
		g2d.setColor(Color.white);// ���ñ���ɫ
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(img, 0, 0, null);
	}

//	public static void main(String args[]) {
//		JFrame f = new JFrame("translucent test panel");
////		f.add(new javax.swing.JButton("hello"));
//		ImageIcon img = new ImageIcon(Toolkit.getDefaultToolkit().createImage(TranslucentPanel.class.getResource("/com/lorent/test/images/Blue hills.jpg")));
//		JLabel imgLabel = new JLabel(img);
//		f.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
//		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
//		((JPanel)f.getContentPane()).setOpaque(false);
//		f.setIconImage(Toolkit.getDefaultToolkit().createImage(TranslucentPanel.class.getResource("/com/lorent/test/images/Blue hills.jpg")));
//		JPanel panel = new TranslucentPanel();
//		panel.add(new javax.swing.JButton("aaa"));
//		f.add(panel);
//		
//		f.setSize(400, 300);
//		f.setVisible(true);
//	}
}
