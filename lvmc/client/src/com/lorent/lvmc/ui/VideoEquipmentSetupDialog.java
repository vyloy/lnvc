/*
 * VideoEquipmentSetupDialog.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.lvmc.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lorent.lvmc.controller.ControllerFacade;
import com.lorent.lvmc.controller.ViewManager;
import com.lorent.lvmc.util.ConfigUtil;
import com.lorent.lvmc.util.Constants;
import com.lorent.lvmc.util.DataUtil;
import com.lorent.lvmc.util.StringUtil;
import com.lorent.util.LCCUtil;
import com.lorent.util.LCCUtil.Device;

/**
 *
 * @author  __USER__
 */
public class VideoEquipmentSetupDialog extends javax.swing.JDialog {

	/** Creates new form VideoEquipmentSetupDialog */
	public VideoEquipmentSetupDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		List<Device> cameras = LCCUtil.getInstance().getLocalCameraList();
		this.CameraComboBox.removeAllItems();
		for (Device camera : cameras) {
			this.CameraComboBox.addItem(camera);
			if (ConfigUtil.getIntProperty(Constants.VideoParam.VideoEquipment
					.toString(), 0) == camera.getIndex()) {
				this.CameraComboBox.setSelectedItem(camera);
			}
		}
		this.jPanel4.setVisible(false);
		this.jPanel5.setVisible(false);
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		jTabbedPane1 = new javax.swing.JTabbedPane();
		jPanel2 = new javax.swing.JPanel();
		jPanel23 = new javax.swing.JPanel();
		jPanel22 = new javax.swing.JPanel();
		jPanel37 = new javax.swing.JPanel();
		jPanel3 = new javax.swing.JPanel();
		jPanel7 = new javax.swing.JPanel();
		jPanel9 = new javax.swing.JPanel();
		jPanel28 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		jPanel29 = new javax.swing.JPanel();
		CameraComboBox = new javax.swing.JComboBox();
		jPanel10 = new javax.swing.JPanel();
		jPanel30 = new javax.swing.JPanel();
		jLabel3 = new javax.swing.JLabel();
		jPanel31 = new javax.swing.JPanel();
		jComboBox4 = new javax.swing.JComboBox();
		jPanel25 = new javax.swing.JPanel();
		jPanel4 = new javax.swing.JPanel();
		jPanel11 = new javax.swing.JPanel();
		jPanel13 = new javax.swing.JPanel();
		jPanel32 = new javax.swing.JPanel();
		jLabel5 = new javax.swing.JLabel();
		jPanel33 = new javax.swing.JPanel();
		jComboBox2 = new javax.swing.JComboBox();
		jPanel14 = new javax.swing.JPanel();
		jPanel34 = new javax.swing.JPanel();
		jLabel6 = new javax.swing.JLabel();
		jPanel35 = new javax.swing.JPanel();
		jComboBox5 = new javax.swing.JComboBox();
		jPanel26 = new javax.swing.JPanel();
		jPanel5 = new javax.swing.JPanel();
		jPanel15 = new javax.swing.JPanel();
		jPanel17 = new javax.swing.JPanel();
		jLabel8 = new javax.swing.JLabel();
		jSlider1 = new javax.swing.JSlider();
		jLabel10 = new javax.swing.JLabel();
		jPanel38 = new javax.swing.JPanel();
		jCheckBox3 = new javax.swing.JCheckBox();
		jPanel36 = new javax.swing.JPanel();
		jButton1 = new javax.swing.JButton();
		jPanel6 = new javax.swing.JPanel();
		jPanel8 = new javax.swing.JPanel();
		jPanel12 = new javax.swing.JPanel();
		jLabel9 = new javax.swing.JLabel();
		jPanel24 = new javax.swing.JPanel();
		jPanel1 = new javax.swing.JPanel();
		okButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle(StringUtil
				.getUIString("SystemSetupPopupMenu.videoSetupItem.txt"));
		setResizable(false);

		jPanel2.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(0, 0, 0)));
		jPanel2.setLayout(new java.awt.GridBagLayout());
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
		gridBagConstraints.weighty = 1.0;
		jPanel2.add(jPanel23, gridBagConstraints);

		jPanel22.setMinimumSize(new java.awt.Dimension(350, 339));
		jPanel22.setPreferredSize(new java.awt.Dimension(350, 339));
		jPanel22.setLayout(new java.awt.GridBagLayout());
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		jPanel22.add(jPanel37, gridBagConstraints);

		jPanel3.setLayout(new java.awt.BorderLayout(5, 5));

		jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(), "\u6355\u6349",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("微软雅黑", 1, 12), new java.awt.Color(0, 0, 0)));
		jPanel7.setMinimumSize(new java.awt.Dimension(169, 90));
		jPanel7.setPreferredSize(new java.awt.Dimension(332, 90));
		jPanel7.setLayout(new java.awt.GridLayout(2, 1));

		jPanel9.setMaximumSize(new java.awt.Dimension(32767, 25));
		jPanel9.setPreferredSize(new java.awt.Dimension(158, 25));
		jPanel9.setLayout(new java.awt.GridBagLayout());

		jPanel28.setMinimumSize(new java.awt.Dimension(90, 25));
		jPanel28.setPreferredSize(new java.awt.Dimension(60, 100));
		jPanel28.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

		jLabel2.setText("\u8bbe\u5907\uff1a");
		jPanel28.add(jLabel2);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
		gridBagConstraints.weighty = 1.0;
		jPanel9.add(jPanel28, gridBagConstraints);

		jPanel29.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		CameraComboBox.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		jPanel29.add(CameraComboBox);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		jPanel9.add(jPanel29, gridBagConstraints);

		jPanel7.add(jPanel9);

		jPanel10.setMinimumSize(new java.awt.Dimension(156, 30));
		jPanel10.setPreferredSize(new java.awt.Dimension(320, 30));
		jPanel10.setLayout(new java.awt.GridBagLayout());

		jPanel30.setMinimumSize(new java.awt.Dimension(90, 25));
		jPanel30.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

		jLabel3.setText("\u89c6\u9891\u7f16\u7801\uff1a");
		jPanel30.add(jLabel3);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
		gridBagConstraints.weighty = 1.0;
		jPanel10.add(jPanel30, gridBagConstraints);

		jPanel31.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "H.264" }));
		jPanel31.add(jComboBox4);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		jPanel10.add(jPanel31, gridBagConstraints);

		jPanel7.add(jPanel10);

		jPanel3.add(jPanel7, java.awt.BorderLayout.CENTER);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		jPanel22.add(jPanel3, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		jPanel22.add(jPanel25, gridBagConstraints);

		jPanel4.setMinimumSize(new java.awt.Dimension(169, 0));
		jPanel4.setPreferredSize(new java.awt.Dimension(332, 0));
		jPanel4.setLayout(new java.awt.BorderLayout(5, 5));

		jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(),
				"\u6444\u50cf\u5934\u8fdc\u7a0b\u63a7\u5236",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("微软雅黑", 1, 12), new java.awt.Color(0, 0, 0)));
		jPanel11.setMinimumSize(new java.awt.Dimension(169, 90));
		jPanel11.setPreferredSize(new java.awt.Dimension(332, 90));
		jPanel11.setLayout(new java.awt.GridLayout(2, 1));

		jPanel13.setPreferredSize(new java.awt.Dimension(200, 30));
		jPanel13.setLayout(new java.awt.GridBagLayout());

		jPanel32.setMinimumSize(new java.awt.Dimension(90, 25));
		jPanel32.setPreferredSize(new java.awt.Dimension(90, 100));
		jPanel32.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

		jLabel5.setText("\u8bbe\u5907\u7c7b\u578b\uff1a");
		jPanel32.add(jLabel5);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
		gridBagConstraints.weighty = 1.0;
		jPanel13.add(jPanel32, gridBagConstraints);

		jPanel33.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4" }));
		jPanel33.add(jComboBox2);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		jPanel13.add(jPanel33, gridBagConstraints);

		jPanel11.add(jPanel13);

		jPanel14.setMinimumSize(new java.awt.Dimension(156, 30));
		jPanel14.setPreferredSize(new java.awt.Dimension(320, 30));
		jPanel14.setLayout(new java.awt.GridBagLayout());

		jPanel34.setMinimumSize(new java.awt.Dimension(90, 25));
		jPanel34.setPreferredSize(new java.awt.Dimension(90, 100));
		jPanel34.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

		jLabel6.setText("\u7aef\u53e3\u53f7\uff1a");
		jPanel34.add(jLabel6);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
		gridBagConstraints.weighty = 1.0;
		jPanel14.add(jPanel34, gridBagConstraints);

		jPanel35.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4" }));
		jPanel35.add(jComboBox5);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		jPanel14.add(jPanel35, gridBagConstraints);

		jPanel11.add(jPanel14);

		jPanel4.add(jPanel11, java.awt.BorderLayout.CENTER);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		jPanel22.add(jPanel4, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		jPanel22.add(jPanel26, gridBagConstraints);

		jPanel5.setMinimumSize(new java.awt.Dimension(269, 0));
		jPanel5.setPreferredSize(new java.awt.Dimension(340, 0));
		jPanel5.setLayout(new java.awt.BorderLayout(5, 5));

		jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(),
				"\u56fe\u50cf\u8d28\u91cf",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("微软雅黑", 1, 12), new java.awt.Color(0, 0, 0)));
		jPanel15.setMinimumSize(new java.awt.Dimension(269, 90));
		jPanel15.setPreferredSize(new java.awt.Dimension(340, 90));
		jPanel15.setLayout(new java.awt.GridLayout(2, 1));

		jPanel17.setMinimumSize(new java.awt.Dimension(164, 30));
		jPanel17.setPreferredSize(new java.awt.Dimension(328, 30));
		jPanel17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,
				20, 3));

		jLabel8.setText("\u66f4\u5feb");
		jPanel17.add(jLabel8);
		jPanel17.add(jSlider1);

		jLabel10.setText("\u66f4\u597d");
		jPanel17.add(jLabel10);

		jPanel15.add(jPanel17);

		jPanel38.setMinimumSize(new java.awt.Dimension(257, 25));
		jPanel38.setPreferredSize(new java.awt.Dimension(100, 25));
		jPanel38.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,
				20, 5));

		jCheckBox3.setSelected(true);
		jCheckBox3
				.setText("\u4f7f\u7528\u65b0\u7684\u89c6\u9891\u663e\u793a\u6280\u672f\uff08\u5efa\u8bae\u4f7f\u7528\uff09");
		jPanel38.add(jCheckBox3);

		jPanel15.add(jPanel38);

		jPanel5.add(jPanel15, java.awt.BorderLayout.CENTER);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		jPanel22.add(jPanel5, gridBagConstraints);

		jPanel36.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

		jButton1.setText("\u66f4\u591a\u5c5e\u6027\u8bbe\u7f6e");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		jPanel36.add(jButton1);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		jPanel22.add(jPanel36, gridBagConstraints);

		jPanel6.setPreferredSize(new java.awt.Dimension(200, 0));
		jPanel6.setLayout(new java.awt.GridBagLayout());

		javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(
				jPanel8);
		jPanel8.setLayout(jPanel8Layout);
		jPanel8Layout.setHorizontalGroup(jPanel8Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 367,
				Short.MAX_VALUE));
		jPanel8Layout.setVerticalGroup(jPanel8Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 168,
				Short.MAX_VALUE));

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		jPanel6.add(jPanel8, gridBagConstraints);

		jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		jLabel9.setFont(new java.awt.Font("微软雅黑", 1, 12));
		jLabel9.setForeground(new java.awt.Color(255, 0, 0));
		jLabel9.setText(StringUtil
				.getUIString("audioAndVideo.setup.restart.tip"));
		jPanel12.add(jLabel9);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		jPanel6.add(jPanel12, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		jPanel22.add(jPanel6, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		jPanel2.add(jPanel22, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
		gridBagConstraints.weighty = 1.0;
		jPanel2.add(jPanel24, gridBagConstraints);

		jTabbedPane1.addTab("\u5e38\u89c4", jPanel2);

		getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

		jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

		okButton.setText(StringUtil
				.getUIString("InviteDialog.inviteButton.txt"));
		okButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				okButtonActionPerformed(evt);
			}
		});
		jPanel1.add(okButton);

		cancelButton.setText(StringUtil
				.getUIString("InviteDialog.cancelButton.txt"));
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});
		jPanel1.add(cancelButton);

		getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
		Device video = (Device) this.CameraComboBox.getSelectedItem();
		Map<String, String> paras = new HashMap<String, String>();
		paras.put(Constants.VideoParam.VideoEquipment.toString(), String
				.valueOf(video.getIndex()));
		ControllerFacade.execute("videoAudioSetupController",
				"setVideoEquimentPara", paras);
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		VideoSetupDialog dialog = new VideoSetupDialog(
				(java.awt.Frame) DataUtil.getTopWindow(), true);
		try {
			ViewManager.setWindowCenterLocation(dialog);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		dialog.setVisible(true);
	}

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				VideoEquipmentSetupDialog dialog = new VideoEquipmentSetupDialog(
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
	private javax.swing.JComboBox CameraComboBox;
	private javax.swing.JButton cancelButton;
	private javax.swing.JButton jButton1;
	private javax.swing.JCheckBox jCheckBox3;
	private javax.swing.JComboBox jComboBox2;
	private javax.swing.JComboBox jComboBox4;
	private javax.swing.JComboBox jComboBox5;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel10;
	private javax.swing.JPanel jPanel11;
	private javax.swing.JPanel jPanel12;
	private javax.swing.JPanel jPanel13;
	private javax.swing.JPanel jPanel14;
	private javax.swing.JPanel jPanel15;
	private javax.swing.JPanel jPanel17;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel22;
	private javax.swing.JPanel jPanel23;
	private javax.swing.JPanel jPanel24;
	private javax.swing.JPanel jPanel25;
	private javax.swing.JPanel jPanel26;
	private javax.swing.JPanel jPanel28;
	private javax.swing.JPanel jPanel29;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel30;
	private javax.swing.JPanel jPanel31;
	private javax.swing.JPanel jPanel32;
	private javax.swing.JPanel jPanel33;
	private javax.swing.JPanel jPanel34;
	private javax.swing.JPanel jPanel35;
	private javax.swing.JPanel jPanel36;
	private javax.swing.JPanel jPanel37;
	private javax.swing.JPanel jPanel38;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JPanel jPanel8;
	private javax.swing.JPanel jPanel9;
	private javax.swing.JSlider jSlider1;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JButton okButton;
	// End of variables declaration//GEN-END:variables

}