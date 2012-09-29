package com.lorent.lvmc.ui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;


public class LoadingFileDialog extends JDialog implements com.lorent.whiteboard.model.LoadingFileDialog{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JProgressBar progressBar;

	/**
	 * Create the dialog.
	 */
	public LoadingFileDialog() {
		setModal(true);
		setUndecorated(true);
		setResizable(false);
		setBounds(100, 100, 167, 80);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{132, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 16, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		JLabel label = new JLabel("正在加载文件，请稍等！");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		contentPanel.add(label, gbc_label);
		{
			progressBar = new JProgressBar();
			progressBar.setIndeterminate(true);
			progressBar.setMinimumSize(new Dimension(150, 16));
			progressBar.setMaximumSize(new Dimension(150, 16));
			GridBagConstraints gbc_progressBar = new GridBagConstraints();
			gbc_progressBar.anchor = GridBagConstraints.NORTHWEST;
			gbc_progressBar.gridx = 0;
			gbc_progressBar.gridy = 1;
			contentPanel.add(progressBar, gbc_progressBar);
		}
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}
	
	public void loading(){
		setVisible(true);
	}
	
	public void loaded(){
		setVisible(false);
		dispose();
	}
}
