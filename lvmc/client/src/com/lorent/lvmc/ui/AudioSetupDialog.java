/*
 * AudioSetupDialog.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.lvmc.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JSlider;

import com.lorent.lvmc.controller.ControllerFacade;
import com.lorent.lvmc.util.ConfigUtil;
import com.lorent.lvmc.util.Constants;
import com.lorent.lvmc.util.StringUtil;
import com.lorent.util.LCCUtil;
import com.lorent.util.LCCUtil.Device;

/**
 *
 * @author  __USER__
 */
public class AudioSetupDialog extends javax.swing.JDialog {

	private static boolean isShowedDialog = false;
	
	/** Creates new form AudioSetupDialog */
	public AudioSetupDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		this.jTabbedPane1.remove(jPanel28);
		this.jPanel28.setVisible(false);
		initData();
		this.jPanel6.setVisible(false);
	}

	public void initData() {
		List<Device> mics = LCCUtil.getInstance().getLocalMicList();
		this.micComboBox.removeAllItems();
		for (Device mic : mics) {
			this.micComboBox.addItem(mic);
			if (ConfigUtil.getIntProperty(Constants.AudioParam.MicEquipment
					.toString(), 0) == mic.getIndex()) {
				this.micComboBox.setSelectedItem(mic);
			}
		}
		this.narratorComboBox.removeAllItems();
		List<Device> narrators = LCCUtil.getInstance().getLocalPlayBackList();
		for (Device narrator : narrators) {
			this.narratorComboBox.addItem(narrator);
			if (ConfigUtil.getIntProperty(
					Constants.AudioParam.NarratorEquipment.toString(), 0) == narrator
					.getIndex()) {
				this.narratorComboBox.setSelectedItem(narrator);
			}
		}
		//		int micVolume = ConfigUtil.getIntProperty(
		//				Constants.AudioParam.MicVolume.toString(), 50);
		int micVolume = LCCUtil.getInstance().getMicVolume();
		this.micSlider.setValue(micVolume);
		if(isShowedDialog){
			int narratorVolume = ConfigUtil.getIntProperty(
					Constants.AudioParam.NarratorVolume.toString(), 50);
			this.narratorSlider.setValue(narratorVolume);
		}else{
			isShowedDialog = true;
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
		java.awt.GridBagConstraints gridBagConstraints;

		jTabbedPane1 = new javax.swing.JTabbedPane();
		jPanel2 = new javax.swing.JPanel();
		jPanel23 = new javax.swing.JPanel();
		jPanel22 = new javax.swing.JPanel();
		jPanel37 = new javax.swing.JPanel();
		jPanel3 = new javax.swing.JPanel();
		jPanel7 = new javax.swing.JPanel();
		jPanel8 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jPanel9 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		micComboBox = new javax.swing.JComboBox();
		jPanel10 = new javax.swing.JPanel();
		jLabel3 = new javax.swing.JLabel();
		jPanel39 = new javax.swing.JPanel();
		jLabel10 = new javax.swing.JLabel();
		micSlider = new javax.swing.JSlider();
		jLabel11 = new javax.swing.JLabel();
		jPanel25 = new javax.swing.JPanel();
		jPanel4 = new javax.swing.JPanel();
		jPanel11 = new javax.swing.JPanel();
		jPanel12 = new javax.swing.JPanel();
		jLabel4 = new javax.swing.JLabel();
		jPanel13 = new javax.swing.JPanel();
		jLabel5 = new javax.swing.JLabel();
		narratorComboBox = new javax.swing.JComboBox();
		jPanel14 = new javax.swing.JPanel();
		jLabel6 = new javax.swing.JLabel();
		jPanel40 = new javax.swing.JPanel();
		jLabel12 = new javax.swing.JLabel();
		narratorSlider = new javax.swing.JSlider();
		jLabel13 = new javax.swing.JLabel();
		jPanel26 = new javax.swing.JPanel();
		jPanel5 = new javax.swing.JPanel();
		jPanel15 = new javax.swing.JPanel();
		jPanel16 = new javax.swing.JPanel();
		jLabel7 = new javax.swing.JLabel();
		jPanel17 = new javax.swing.JPanel();
		jLabel8 = new javax.swing.JLabel();
		jComboBox3 = new javax.swing.JComboBox();
		jPanel27 = new javax.swing.JPanel();
		jPanel6 = new javax.swing.JPanel();
		jPanel18 = new javax.swing.JPanel();
		jPanel19 = new javax.swing.JPanel();
		jLabel9 = new javax.swing.JLabel();
		jPanel20 = new javax.swing.JPanel();
		jCheckBox1 = new javax.swing.JCheckBox();
		jPanel21 = new javax.swing.JPanel();
		jCheckBox2 = new javax.swing.JCheckBox();
		jPanel36 = new javax.swing.JPanel();
		jPanel24 = new javax.swing.JPanel();
		jPanel28 = new javax.swing.JPanel();
		jPanel29 = new javax.swing.JPanel();
		jPanel30 = new javax.swing.JPanel();
		jPanel38 = new javax.swing.JPanel();
		jPanel32 = new javax.swing.JPanel();
		jPanel33 = new javax.swing.JPanel();
		jLabel14 = new javax.swing.JLabel();
		jPanel34 = new javax.swing.JPanel();
		jLabel15 = new javax.swing.JLabel();
		jComboBox4 = new javax.swing.JComboBox();
		jPanel35 = new javax.swing.JPanel();
		jPanel31 = new javax.swing.JPanel();
		jPanel1 = new javax.swing.JPanel();
		okButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle(StringUtil
				.getUIString("SystemSetupPopupMenu.audioSetupItem.txt"));
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

		jPanel22.setLayout(new java.awt.GridBagLayout());
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		jPanel22.add(jPanel37, gridBagConstraints);

		jPanel3.setLayout(new java.awt.BorderLayout(5, 5));

		jPanel7.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(0, 0, 0)));
		jPanel7.setLayout(new java.awt.GridLayout(3, 1));

		jPanel8.setMaximumSize(new java.awt.Dimension(32767, 25));
		jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		jLabel1.setFont(new java.awt.Font("微软雅黑", 1, 12));
		jLabel1.setText("\u9ea6\u514b\u98ce");
		jPanel8.add(jLabel1);

		jPanel7.add(jPanel8);

		jPanel9.setMaximumSize(new java.awt.Dimension(32767, 25));
		jPanel9.setPreferredSize(new java.awt.Dimension(158, 25));
		jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20,
				0));

		jLabel2.setText("\u8bbe\u5907\uff1a");
		jPanel9.add(jLabel2);

		micComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4" }));
		jPanel9.add(micComboBox);

		jPanel7.add(jPanel9);

		jPanel10.setMinimumSize(new java.awt.Dimension(156, 30));
		jPanel10.setPreferredSize(new java.awt.Dimension(320, 30));
		jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,
				20, 5));

		jLabel3.setText("\u97f3\u91cf\uff1a");
		jPanel10.add(jLabel3);

		jPanel39.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0,
				0));

		jLabel10.setText("\u5c0f");
		jPanel39.add(jLabel10);

		micSlider.setMajorTickSpacing(100);
		micSlider.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				micSliderStateChanged(evt);
			}
		});
		jPanel39.add(micSlider);

		jLabel11.setText("\u5927");
		jPanel39.add(jLabel11);

		jPanel10.add(jPanel39);

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

		jPanel4.setLayout(new java.awt.BorderLayout(5, 5));

		jPanel11.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(0, 0, 0)));
		jPanel11.setLayout(new java.awt.GridLayout(3, 1));

		jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		jLabel4.setFont(new java.awt.Font("微软雅黑", 1, 12));
		jLabel4.setText("\u626c\u58f0\u5668");
		jPanel12.add(jLabel4);

		jPanel11.add(jPanel12);

		jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,
				20, 0));

		jLabel5.setText("\u8bbe\u5907\uff1a");
		jPanel13.add(jLabel5);

		narratorComboBox.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		jPanel13.add(narratorComboBox);

		jPanel11.add(jPanel13);

		jPanel14.setMinimumSize(new java.awt.Dimension(156, 30));
		jPanel14.setPreferredSize(new java.awt.Dimension(320, 30));
		jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,
				20, 0));

		jLabel6.setText("\u97f3\u91cf\uff1a");
		jPanel14.add(jLabel6);

		jPanel40.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER,
				0, 5));

		jLabel12.setText("\u5c0f");
		jPanel40.add(jLabel12);

		narratorSlider.setValue(100);
		narratorSlider
				.addChangeListener(new javax.swing.event.ChangeListener() {
					public void stateChanged(javax.swing.event.ChangeEvent evt) {
						narratorSliderStateChanged(evt);
					}
				});
		jPanel40.add(narratorSlider);

		jLabel13.setText("\u5927");
		jPanel40.add(jLabel13);

		jPanel14.add(jPanel40);

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

		jPanel5.setLayout(new java.awt.BorderLayout(5, 5));

		jPanel15.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(0, 0, 0)));
		jPanel15.setLayout(new java.awt.GridLayout(2, 1));

		jPanel16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		jLabel7.setFont(new java.awt.Font("微软雅黑", 1, 12));
		jLabel7.setText("\u7f16\u7801\u7c7b\u578b");
		jPanel16.add(jLabel7);

		jPanel15.add(jPanel16);

		jPanel17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,
				20, 3));

		jLabel8.setText("\u8bf7\u9009\u62e9\u7f16\u7801\u7c7b\u578b\uff1a");
		jPanel17.add(jLabel8);

		jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4" }));
		jPanel17.add(jComboBox3);

		jPanel15.add(jPanel17);

		jPanel5.add(jPanel15, java.awt.BorderLayout.CENTER);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		jPanel22.add(jPanel5, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		jPanel22.add(jPanel27, gridBagConstraints);

		jPanel6.setLayout(new java.awt.BorderLayout(5, 5));

		jPanel18.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(0, 0, 0)));
		jPanel18.setLayout(new java.awt.GridLayout(3, 1));

		jPanel19.setMaximumSize(new java.awt.Dimension(32767, 27));
		jPanel19.setMinimumSize(new java.awt.Dimension(36, 27));
		jPanel19.setPreferredSize(new java.awt.Dimension(36, 27));
		jPanel19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		jLabel9.setFont(new java.awt.Font("微软雅黑", 1, 12));
		jLabel9.setText("\u8bbe\u7f6e");
		jPanel19.add(jLabel9);

		jPanel18.add(jPanel19);

		jPanel20.setMaximumSize(new java.awt.Dimension(32767, 27));
		jPanel20.setMinimumSize(new java.awt.Dimension(113, 27));
		jPanel20.setPreferredSize(new java.awt.Dimension(113, 27));
		jPanel20.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,
				20, 0));

		jCheckBox1.setText("\u566a\u58f0\u964d\u4f4e");
		jPanel20.add(jCheckBox1);

		jPanel18.add(jPanel20);

		jPanel21.setMaximumSize(new java.awt.Dimension(32767, 27));
		jPanel21.setMinimumSize(new java.awt.Dimension(113, 27));
		jPanel21.setPreferredSize(new java.awt.Dimension(113, 27));
		jPanel21.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,
				20, 0));

		jCheckBox2.setText("\u5507\u97f3\u540c\u6b65");
		jPanel21.add(jCheckBox2);

		jPanel18.add(jPanel21);

		jPanel6.add(jPanel18, java.awt.BorderLayout.CENTER);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		jPanel22.add(jPanel6, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 8;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		jPanel22.add(jPanel36, gridBagConstraints);

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

		jTabbedPane1.addTab(StringUtil.getUIString("Convention.txt"), jPanel2);

		jPanel28.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(0, 0, 0)));
		jPanel28.setLayout(new java.awt.GridBagLayout());
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
		gridBagConstraints.weighty = 1.0;
		jPanel28.add(jPanel29, gridBagConstraints);

		jPanel30.setLayout(new java.awt.GridBagLayout());
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		jPanel30.add(jPanel38, gridBagConstraints);

		jPanel32.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(0, 0, 0)));
		jPanel32.setLayout(new java.awt.GridLayout(2, 1));

		jPanel33.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		jLabel14.setFont(new java.awt.Font("宋体", 1, 12));
		jLabel14.setText(StringUtil.getUIString("sample_frequency.txt"));
		jPanel33.add(jLabel14);

		jPanel32.add(jPanel33);

		jPanel34.setPreferredSize(new java.awt.Dimension(188, 29));
		jPanel34.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,
				20, 0));

		jLabel15.setText(StringUtil.getUIString("select.sample_frequency.txt"));
		jPanel34.add(jLabel15);

		jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4" }));
		jPanel34.add(jComboBox4);

		jPanel32.add(jPanel34);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		jPanel30.add(jPanel32, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		jPanel30.add(jPanel35, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		jPanel28.add(jPanel30, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
		gridBagConstraints.weighty = 1.0;
		jPanel28.add(jPanel31, gridBagConstraints);

		jTabbedPane1.addTab("<User Code>", jPanel28);

		getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);
		jTabbedPane1.getAccessibleContext().setAccessibleName(
				StringUtil.getUIString("advanced.txt"));

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

	private void narratorSliderStateChanged(javax.swing.event.ChangeEvent evt) {
		JSlider source = (JSlider) evt.getSource();
		if (!source.getValueIsAdjusting()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put(Constants.AudioParam.NarratorVolume.toString(), String
					.valueOf(source.getValue()));
			ControllerFacade.execute("videoAudioSetupController",
					"setAudioParas", map);
		}
	}

	private void micSliderStateChanged(javax.swing.event.ChangeEvent evt) {
		JSlider source = (JSlider) evt.getSource();
		if (!source.getValueIsAdjusting()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put(Constants.AudioParam.MicVolume.toString(), String
					.valueOf(source.getValue()));
			ControllerFacade.execute("videoAudioSetupController",
					"setAudioParas", map);
		}
	}

	private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
		Map<String, String> map = new HashMap<String, String>();
		Device mic = (Device) this.micComboBox.getSelectedItem();
		Device narrator = (Device) this.narratorComboBox.getSelectedItem();
		int micVolume = this.micSlider.getValue();
		int narratorVolume = this.narratorSlider.getValue();
		map.put(Constants.AudioParam.MicEquipment.toString(), String
				.valueOf(mic.getIndex()));
		map.put(Constants.AudioParam.NarratorEquipment.toString(), String
				.valueOf(narrator.getIndex()));
		map.put(Constants.AudioParam.MicVolume.toString(), String
				.valueOf(micVolume));
		map.put(Constants.AudioParam.NarratorVolume.toString(), String
				.valueOf(narratorVolume));
		ControllerFacade.execute("videoAudioSetupController", "setAudioParas",
				map);
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
				AudioSetupDialog dialog = new AudioSetupDialog(
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
	private javax.swing.JButton cancelButton;
	private javax.swing.JCheckBox jCheckBox1;
	private javax.swing.JCheckBox jCheckBox2;
	private javax.swing.JComboBox jComboBox3;
	private javax.swing.JComboBox jComboBox4;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel10;
	private javax.swing.JPanel jPanel11;
	private javax.swing.JPanel jPanel12;
	private javax.swing.JPanel jPanel13;
	private javax.swing.JPanel jPanel14;
	private javax.swing.JPanel jPanel15;
	private javax.swing.JPanel jPanel16;
	private javax.swing.JPanel jPanel17;
	private javax.swing.JPanel jPanel18;
	private javax.swing.JPanel jPanel19;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel20;
	private javax.swing.JPanel jPanel21;
	private javax.swing.JPanel jPanel22;
	private javax.swing.JPanel jPanel23;
	private javax.swing.JPanel jPanel24;
	private javax.swing.JPanel jPanel25;
	private javax.swing.JPanel jPanel26;
	private javax.swing.JPanel jPanel27;
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
	private javax.swing.JPanel jPanel39;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel40;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JPanel jPanel8;
	private javax.swing.JPanel jPanel9;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JComboBox micComboBox;
	private javax.swing.JSlider micSlider;
	private javax.swing.JComboBox narratorComboBox;
	private javax.swing.JSlider narratorSlider;
	private javax.swing.JButton okButton;
	// End of variables declaration//GEN-END:variables

}