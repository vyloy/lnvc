/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VoteDialog.java
 *
 * Created on 2011-12-19, 15:49:36
 */
package com.lorent.lvmc.ui;

import java.awt.BorderLayout;
import java.util.List;

import com.lorent.lvmc.bean.OptionDto;
import com.lorent.lvmc.bean.VoteDataChildDto;
import com.lorent.lvmc.bean.VoteDataDto;
import com.lorent.lvmc.controller.ViewManager;
import com.lorent.lvmc.util.Constants;
import com.lorent.lvmc.util.DataUtil;
import javax.swing.*;

/**
 *
 * @author test
 */
public class VoteDialog_Update extends javax.swing.JDialog {

	/** Creates new form VoteDialog */
	public VoteDialog_Update(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public voteMainPanel votemMainPanel;

	public VoteDialog_Update(javax.swing.JPanel jPanel, boolean modal)
			throws Exception {
		super((JFrame) DataUtil.getTopWindow(), modal);
		//        super();
		initComponents();
		this.votemMainPanel = (voteMainPanel) jPanel;
		this.dataDto = new VoteDataDto();
		this.dataDto.getList().clear();
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		ridioGroup = new javax.swing.ButtonGroup();
		topPanel = new javax.swing.JPanel();
		votejPanel = new javax.swing.JPanel();
		theme = new javax.swing.JLabel();
		themeField = new javax.swing.JTextField();
		themedescript = new javax.swing.JLabel();
		jScrollPane9 = new javax.swing.JScrollPane();
		themedescField = new javax.swing.JTextArea();
		bottom = new javax.swing.JPanel();
		finish3 = new javax.swing.JButton();
		cancel3 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		theme.setText("\u6295\u7968\u4e3b\u9898");

		themedescript.setText("\u4e3b\u9898\u63cf\u8ff0");

		themedescField.setColumns(20);
		themedescField.setRows(5);
		jScrollPane9.setViewportView(themedescField);

		javax.swing.GroupLayout votejPanelLayout = new javax.swing.GroupLayout(
				votejPanel);
		votejPanel.setLayout(votejPanelLayout);
		votejPanelLayout
				.setHorizontalGroup(votejPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								votejPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												votejPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																themedescript)
														.addComponent(theme))
										.addGap(31, 31, 31)
										.addGroup(
												votejPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																themeField)
														.addComponent(
																jScrollPane9,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																403,
																Short.MAX_VALUE))
										.addContainerGap(32, Short.MAX_VALUE)));
		votejPanelLayout
				.setVerticalGroup(votejPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								votejPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												votejPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																votejPanelLayout
																		.createSequentialGroup()
																		.addGroup(
																				votejPanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								theme)
																						.addComponent(
																								themeField,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				11,
																				Short.MAX_VALUE)
																		.addComponent(
																				jScrollPane9,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				54,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addContainerGap())
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																votejPanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				themedescript)
																		.addGap(
																				32,
																				32,
																				32)))));

		javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(
				topPanel);
		topPanel.setLayout(topPanelLayout);
		topPanelLayout.setHorizontalGroup(topPanelLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				topPanelLayout.createSequentialGroup().addComponent(votejPanel,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		topPanelLayout.setVerticalGroup(topPanelLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				votejPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		finish3.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/lvmc/resource/images/vote_icon/dialog-ok-3.png"))); // NOI18N
		finish3.setText("\u786e\u5b9a");
		finish3.setBorderPainted(false);
		finish3.setContentAreaFilled(false);
		finish3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				finish3ActionPerformed(evt);
			}
		});

		cancel3
				.setIcon(new javax.swing.ImageIcon(
						getClass()
								.getResource(
										"/com/lorent/lvmc/resource/images/vote_icon/dialog-cancel-3.png"))); // NOI18N
		cancel3.setText("\u53d6\u6d88");
		cancel3.setBorderPainted(false);
		cancel3.setContentAreaFilled(false);
		cancel3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancel3ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout bottomLayout = new javax.swing.GroupLayout(
				bottom);
		bottom.setLayout(bottomLayout);
		bottomLayout.setHorizontalGroup(bottomLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				bottomLayout.createSequentialGroup().addGap(183, 183, 183)
						.addComponent(finish3,
								javax.swing.GroupLayout.PREFERRED_SIZE, 100,
								javax.swing.GroupLayout.PREFERRED_SIZE).addGap(
								18, 18, 18).addComponent(cancel3)
						.addContainerGap(140, Short.MAX_VALUE)));
		bottomLayout.setVerticalGroup(bottomLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				bottomLayout.createSequentialGroup().addGroup(
						bottomLayout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(cancel3).addComponent(finish3))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 524,
				Short.MAX_VALUE).addComponent(bottom,
				javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addComponent(
												topPanel,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(
												bottom,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												35,
												javax.swing.GroupLayout.PREFERRED_SIZE)));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void cancel3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel3ActionPerformed
		this.dispose();
	}//GEN-LAST:event_cancel3ActionPerformed

	private VoteDataDto dataDto;
	private VoteDataChildDto childDto = null;

	private void finish3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finish3ActionPerformed
		dataDto.setTheme(this.themeField.getText());
		dataDto.setThemeDescription(this.themedescField.getText());
		//检查输入项
		if (!(null != this.themeField.getText() && this.themeField.getText()
				.length() > 0)) {
			JOptionPane.showMessageDialog(this, "请输入主题！");
			return;
		}
		if (!(null != this.themedescField.getText() && this.themedescField
				.getText().length() > 0)) {
			JOptionPane.showMessageDialog(this, "请输入主题描述！");
			return;
		}

		if ("add".equals(this.votemMainPanel.getAdd_or_update_flag())) {
			this.votemMainPanel.addTheme(dataDto);
		}
		if ("update".equals(this.votemMainPanel.getAdd_or_update_flag())) {
			this.votemMainPanel.updateTheme(dataDto);
		}
		this.dispose();
	}//GEN-LAST:event_finish3ActionPerformed

	/**
	 * 增加表决项是进行处理 ，把增加的表决项放到List中
	 * @param evt 
	 */
	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				VoteDialog_Update dialog = new VoteDialog_Update(
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
	private javax.swing.JPanel bottom;
	private javax.swing.JButton cancel3;
	private javax.swing.JButton finish3;
	private javax.swing.JScrollPane jScrollPane9;
	private javax.swing.ButtonGroup ridioGroup;
	private javax.swing.JLabel theme;
	private javax.swing.JTextField themeField;
	private javax.swing.JTextArea themedescField;
	private javax.swing.JLabel themedescript;
	private javax.swing.JPanel topPanel;
	private javax.swing.JPanel votejPanel;

	// End of variables declaration//GEN-END:variables

	public ButtonGroup getRidioGroup() {
		return ridioGroup;
	}

	public JTextField getThemeField() {
		return themeField;
	}

	public JTextArea getThemedescField() {
		return themedescField;
	}

}
