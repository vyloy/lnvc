package com.lorent.lvmc.camera.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ControlFrame extends JFrame {

	private JPanel contentPane;
	private ControlPanel controlPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControlFrame frame = new ControlFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ControlFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 491, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		controlPanel = new ControlPanel();
		contentPane.add(controlPanel);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
	}

	@Override
	public void dispose() {
		controlPanel.dispose();
		super.dispose();
	}

}
