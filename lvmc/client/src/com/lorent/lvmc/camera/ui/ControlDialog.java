package com.lorent.lvmc.camera.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ControlDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ControlPanel controlPanel=new ControlPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ControlDialog dialog = new ControlDialog(null,true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public void init() {
		setTitle("云台控制");
		setResizable(false);
		setBounds(100, 100, 486, 215);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.add(controlPanel);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
	}

	public ControlDialog(Frame topWindow, boolean b) {
		super(topWindow, b);
		init();
	}
	
	@Override
	public void dispose() {
		controlPanel.dispose();
		super.dispose();
	}

}
