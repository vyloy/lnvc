/*
 * VideoAttributeSetupDialog.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.lvmc.ui;

import com.lorent.lvmc.util.StringUtil;

/**
 *
 * @author  __USER__
 */
public class VideoAttributeSetupDialog extends javax.swing.JDialog {

	/** Creates new form VideoAttributeSetupDialog */
	public VideoAttributeSetupDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		jPanel1 = new javax.swing.JPanel();
		jPanel3 = new javax.swing.JPanel();
		jPanel4 = new javax.swing.JPanel();
		jPanel6 = new javax.swing.JPanel();
		jPanel7 = new javax.swing.JPanel();
		jTabbedPane1 = new javax.swing.JTabbedPane();
		jPanel9 = new javax.swing.JPanel();
		jPanel10 = new javax.swing.JPanel();
		jPanel11 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jPanel12 = new javax.swing.JPanel();
		jSlider1 = new javax.swing.JSlider();
		jLabel2 = new javax.swing.JLabel();
		jPanel13 = new javax.swing.JPanel();
		jPanel14 = new javax.swing.JPanel();
		jLabel3 = new javax.swing.JLabel();
		jPanel15 = new javax.swing.JPanel();
		jSlider2 = new javax.swing.JSlider();
		jLabel4 = new javax.swing.JLabel();
		jPanel16 = new javax.swing.JPanel();
		jPanel17 = new javax.swing.JPanel();
		jLabel5 = new javax.swing.JLabel();
		jPanel18 = new javax.swing.JPanel();
		jSlider3 = new javax.swing.JSlider();
		jLabel6 = new javax.swing.JLabel();
		jPanel19 = new javax.swing.JPanel();
		jPanel20 = new javax.swing.JPanel();
		jLabel7 = new javax.swing.JLabel();
		jPanel21 = new javax.swing.JPanel();
		jSlider4 = new javax.swing.JSlider();
		jLabel8 = new javax.swing.JLabel();
		jPanel22 = new javax.swing.JPanel();
		jPanel23 = new javax.swing.JPanel();
		jLabel9 = new javax.swing.JLabel();
		jPanel24 = new javax.swing.JPanel();
		jSlider5 = new javax.swing.JSlider();
		jLabel10 = new javax.swing.JLabel();
		jPanel25 = new javax.swing.JPanel();
		jPanel26 = new javax.swing.JPanel();
		jLabel11 = new javax.swing.JLabel();
		jPanel27 = new javax.swing.JPanel();
		jSlider6 = new javax.swing.JSlider();
		jLabel12 = new javax.swing.JLabel();
		jPanel28 = new javax.swing.JPanel();
		jPanel29 = new javax.swing.JPanel();
		jLabel13 = new javax.swing.JLabel();
		jPanel30 = new javax.swing.JPanel();
		jSlider7 = new javax.swing.JSlider();
		jLabel14 = new javax.swing.JLabel();
		jPanel31 = new javax.swing.JPanel();
		jPanel32 = new javax.swing.JPanel();
		jLabel15 = new javax.swing.JLabel();
		jPanel33 = new javax.swing.JPanel();
		jSlider8 = new javax.swing.JSlider();
		jLabel16 = new javax.swing.JLabel();
		jPanel34 = new javax.swing.JPanel();
		jPanel35 = new javax.swing.JPanel();
		jLabel17 = new javax.swing.JLabel();
		jPanel36 = new javax.swing.JPanel();
		jSlider9 = new javax.swing.JSlider();
		jLabel18 = new javax.swing.JLabel();
		jPanel8 = new javax.swing.JPanel();
		jPanel5 = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		okButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("\u5c5e\u6027");
		setResizable(false);

		jPanel1.setMinimumSize(new java.awt.Dimension(460, 450));
		jPanel1.setPreferredSize(new java.awt.Dimension(460, 450));
		jPanel1.setLayout(new java.awt.GridBagLayout());

		jPanel3.setMinimumSize(new java.awt.Dimension(0, 10));
		jPanel3.setPreferredSize(new java.awt.Dimension(100, 10));

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 460,
				Short.MAX_VALUE));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 10,
				Short.MAX_VALUE));

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		jPanel1.add(jPanel3, gridBagConstraints);

		jPanel4.setLayout(new java.awt.GridBagLayout());

		jPanel6.setMinimumSize(new java.awt.Dimension(10, 0));
		jPanel6.setPreferredSize(new java.awt.Dimension(10, 100));

		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(
				jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 10,
				Short.MAX_VALUE));
		jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 433,
				Short.MAX_VALUE));

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
		gridBagConstraints.weighty = 1.0;
		jPanel4.add(jPanel6, gridBagConstraints);

		jPanel7.setPreferredSize(new java.awt.Dimension(257, 100));
		jPanel7.setLayout(new java.awt.BorderLayout());

		jPanel9.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(204, 204, 204)));
		jPanel9.setPreferredSize(new java.awt.Dimension(252, 100));
		jPanel9.setLayout(new java.awt.GridLayout(9, 1));

		jPanel10.setMaximumSize(new java.awt.Dimension(32767, 30));
		jPanel10.setMinimumSize(new java.awt.Dimension(250, 30));
		jPanel10.setPreferredSize(new java.awt.Dimension(250, 30));
		jPanel10.setLayout(new java.awt.GridBagLayout());

		jPanel11.setPreferredSize(new java.awt.Dimension(100, 35));
		jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT,
				5, 8));

		jLabel1.setText("\u4eae\u5ea6");
		jPanel11.add(jLabel1);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 1.0;
		jPanel10.add(jPanel11, gridBagConstraints);

		jPanel12.setPreferredSize(new java.awt.Dimension(100, 35));
		jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				jSlider1StateChanged(evt);
			}
		});
		jPanel12.add(jSlider1);

		jLabel2.setText("20");
		jLabel2.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(153, 204, 255)));
		jLabel2.setMaximumSize(new java.awt.Dimension(40, 15));
		jLabel2.setMinimumSize(new java.awt.Dimension(40, 15));
		jLabel2.setPreferredSize(new java.awt.Dimension(40, 15));
		jPanel12.add(jLabel2);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 0.7;
		gridBagConstraints.weighty = 1.0;
		jPanel10.add(jPanel12, gridBagConstraints);

		jPanel9.add(jPanel10);

		jPanel13.setMaximumSize(new java.awt.Dimension(32767, 30));
		jPanel13.setMinimumSize(new java.awt.Dimension(250, 30));
		jPanel13.setPreferredSize(new java.awt.Dimension(250, 30));
		jPanel13.setLayout(new java.awt.GridBagLayout());

		jPanel14.setPreferredSize(new java.awt.Dimension(100, 35));
		jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT,
				5, 8));

		jLabel3.setText("\u5bf9\u6bd4\u5ea6");
		jPanel14.add(jLabel3);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 1.0;
		jPanel13.add(jPanel14, gridBagConstraints);

		jPanel15.setPreferredSize(new java.awt.Dimension(100, 35));
		jPanel15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
		jPanel15.add(jSlider2);

		jLabel4.setText("20");
		jLabel4.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(153, 204, 255)));
		jLabel4.setMaximumSize(new java.awt.Dimension(40, 15));
		jLabel4.setMinimumSize(new java.awt.Dimension(40, 15));
		jLabel4.setPreferredSize(new java.awt.Dimension(40, 15));
		jPanel15.add(jLabel4);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 0.7;
		gridBagConstraints.weighty = 1.0;
		jPanel13.add(jPanel15, gridBagConstraints);

		jPanel9.add(jPanel13);

		jPanel16.setMaximumSize(new java.awt.Dimension(32767, 30));
		jPanel16.setMinimumSize(new java.awt.Dimension(250, 30));
		jPanel16.setPreferredSize(new java.awt.Dimension(250, 30));
		jPanel16.setLayout(new java.awt.GridBagLayout());

		jPanel17.setPreferredSize(new java.awt.Dimension(100, 35));
		jPanel17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT,
				5, 8));

		jLabel5.setText("\u8272\u8c03");
		jPanel17.add(jLabel5);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 1.0;
		jPanel16.add(jPanel17, gridBagConstraints);

		jPanel18.setPreferredSize(new java.awt.Dimension(100, 35));
		jPanel18.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
		jPanel18.add(jSlider3);

		jLabel6.setText("20");
		jLabel6.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(153, 204, 255)));
		jLabel6.setMaximumSize(new java.awt.Dimension(40, 15));
		jLabel6.setMinimumSize(new java.awt.Dimension(40, 15));
		jLabel6.setPreferredSize(new java.awt.Dimension(40, 15));
		jPanel18.add(jLabel6);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 0.7;
		gridBagConstraints.weighty = 1.0;
		jPanel16.add(jPanel18, gridBagConstraints);

		jPanel9.add(jPanel16);

		jPanel19.setMaximumSize(new java.awt.Dimension(32767, 30));
		jPanel19.setMinimumSize(new java.awt.Dimension(250, 30));
		jPanel19.setPreferredSize(new java.awt.Dimension(250, 30));
		jPanel19.setLayout(new java.awt.GridBagLayout());

		jPanel20.setPreferredSize(new java.awt.Dimension(100, 35));
		jPanel20.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT,
				5, 8));

		jLabel7.setText("\u9971\u548c\u5ea6");
		jPanel20.add(jLabel7);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 1.0;
		jPanel19.add(jPanel20, gridBagConstraints);

		jPanel21.setPreferredSize(new java.awt.Dimension(100, 35));
		jPanel21.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
		jPanel21.add(jSlider4);

		jLabel8.setText("20");
		jLabel8.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(153, 204, 255)));
		jLabel8.setMaximumSize(new java.awt.Dimension(40, 15));
		jLabel8.setMinimumSize(new java.awt.Dimension(40, 15));
		jLabel8.setPreferredSize(new java.awt.Dimension(40, 15));
		jPanel21.add(jLabel8);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 0.7;
		gridBagConstraints.weighty = 1.0;
		jPanel19.add(jPanel21, gridBagConstraints);

		jPanel9.add(jPanel19);

		jPanel22.setMaximumSize(new java.awt.Dimension(32767, 30));
		jPanel22.setMinimumSize(new java.awt.Dimension(250, 30));
		jPanel22.setPreferredSize(new java.awt.Dimension(250, 30));
		jPanel22.setLayout(new java.awt.GridBagLayout());

		jPanel23.setPreferredSize(new java.awt.Dimension(100, 35));
		jPanel23.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT,
				5, 8));

		jLabel9.setText("\u6e05\u6670\u5ea6");
		jPanel23.add(jLabel9);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 1.0;
		jPanel22.add(jPanel23, gridBagConstraints);

		jPanel24.setPreferredSize(new java.awt.Dimension(100, 35));
		jPanel24.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
		jPanel24.add(jSlider5);

		jLabel10.setText("20");
		jLabel10.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(153, 204, 255)));
		jLabel10.setMaximumSize(new java.awt.Dimension(40, 15));
		jLabel10.setMinimumSize(new java.awt.Dimension(40, 15));
		jLabel10.setPreferredSize(new java.awt.Dimension(40, 15));
		jPanel24.add(jLabel10);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 0.7;
		gridBagConstraints.weighty = 1.0;
		jPanel22.add(jPanel24, gridBagConstraints);

		jPanel9.add(jPanel22);

		jPanel25.setMaximumSize(new java.awt.Dimension(32767, 30));
		jPanel25.setMinimumSize(new java.awt.Dimension(250, 30));
		jPanel25.setPreferredSize(new java.awt.Dimension(250, 30));
		jPanel25.setLayout(new java.awt.GridBagLayout());

		jPanel26.setPreferredSize(new java.awt.Dimension(100, 35));
		jPanel26.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT,
				5, 8));

		jLabel11.setText("\u4f3d\u739b");
		jPanel26.add(jLabel11);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 1.0;
		jPanel25.add(jPanel26, gridBagConstraints);

		jPanel27.setPreferredSize(new java.awt.Dimension(100, 35));
		jPanel27.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
		jPanel27.add(jSlider6);

		jLabel12.setText("20");
		jLabel12.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(153, 204, 255)));
		jLabel12.setMaximumSize(new java.awt.Dimension(40, 15));
		jLabel12.setMinimumSize(new java.awt.Dimension(40, 15));
		jLabel12.setPreferredSize(new java.awt.Dimension(40, 15));
		jPanel27.add(jLabel12);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 0.7;
		gridBagConstraints.weighty = 1.0;
		jPanel25.add(jPanel27, gridBagConstraints);

		jPanel9.add(jPanel25);

		jPanel28.setMaximumSize(new java.awt.Dimension(32767, 30));
		jPanel28.setMinimumSize(new java.awt.Dimension(250, 30));
		jPanel28.setPreferredSize(new java.awt.Dimension(250, 30));
		jPanel28.setLayout(new java.awt.GridBagLayout());

		jPanel29.setPreferredSize(new java.awt.Dimension(100, 35));
		jPanel29.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT,
				5, 8));

		jLabel13.setText("\u767d\u5e73\u8861");
		jPanel29.add(jLabel13);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 1.0;
		jPanel28.add(jPanel29, gridBagConstraints);

		jPanel30.setPreferredSize(new java.awt.Dimension(100, 35));
		jPanel30.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
		jPanel30.add(jSlider7);

		jLabel14.setText("20");
		jLabel14.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(153, 204, 255)));
		jLabel14.setMaximumSize(new java.awt.Dimension(40, 15));
		jLabel14.setMinimumSize(new java.awt.Dimension(40, 15));
		jLabel14.setPreferredSize(new java.awt.Dimension(40, 15));
		jPanel30.add(jLabel14);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 0.7;
		gridBagConstraints.weighty = 1.0;
		jPanel28.add(jPanel30, gridBagConstraints);

		jPanel9.add(jPanel28);

		jPanel31.setMaximumSize(new java.awt.Dimension(32767, 30));
		jPanel31.setMinimumSize(new java.awt.Dimension(250, 30));
		jPanel31.setPreferredSize(new java.awt.Dimension(250, 30));
		jPanel31.setLayout(new java.awt.GridBagLayout());

		jPanel32.setPreferredSize(new java.awt.Dimension(100, 35));
		jPanel32.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT,
				5, 8));

		jLabel15.setText("\u9006\u5149\u5bf9\u6bd4");
		jPanel32.add(jLabel15);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 1.0;
		jPanel31.add(jPanel32, gridBagConstraints);

		jPanel33.setPreferredSize(new java.awt.Dimension(100, 35));
		jPanel33.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
		jPanel33.add(jSlider8);

		jLabel16.setText("20");
		jLabel16.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(153, 204, 255)));
		jLabel16.setMaximumSize(new java.awt.Dimension(40, 15));
		jLabel16.setMinimumSize(new java.awt.Dimension(40, 15));
		jLabel16.setPreferredSize(new java.awt.Dimension(40, 15));
		jPanel33.add(jLabel16);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 0.7;
		gridBagConstraints.weighty = 1.0;
		jPanel31.add(jPanel33, gridBagConstraints);

		jPanel9.add(jPanel31);

		jPanel34.setMaximumSize(new java.awt.Dimension(32767, 30));
		jPanel34.setMinimumSize(new java.awt.Dimension(250, 30));
		jPanel34.setPreferredSize(new java.awt.Dimension(250, 30));
		jPanel34.setLayout(new java.awt.GridBagLayout());

		jPanel35.setPreferredSize(new java.awt.Dimension(100, 35));
		jPanel35.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT,
				5, 8));

		jLabel17.setText("\u8272\u9636");
		jPanel35.add(jLabel17);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 1.0;
		jPanel34.add(jPanel35, gridBagConstraints);

		jPanel36.setPreferredSize(new java.awt.Dimension(100, 35));
		jPanel36.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
		jPanel36.add(jSlider9);

		jLabel18.setText("20");
		jLabel18.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(153, 204, 255)));
		jLabel18.setMaximumSize(new java.awt.Dimension(40, 15));
		jLabel18.setMinimumSize(new java.awt.Dimension(40, 15));
		jLabel18.setPreferredSize(new java.awt.Dimension(40, 15));
		jPanel36.add(jLabel18);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 0.7;
		gridBagConstraints.weighty = 1.0;
		jPanel34.add(jPanel36, gridBagConstraints);

		jPanel9.add(jPanel34);

		jTabbedPane1.addTab("\u89c6\u9891 Proc Amp", jPanel9);

		jPanel7.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		jPanel4.add(jPanel7, gridBagConstraints);

		jPanel8.setMinimumSize(new java.awt.Dimension(10, 0));
		jPanel8.setPreferredSize(new java.awt.Dimension(10, 100));

		javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(
				jPanel8);
		jPanel8.setLayout(jPanel8Layout);
		jPanel8Layout.setHorizontalGroup(jPanel8Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 10,
				Short.MAX_VALUE));
		jPanel8Layout.setVerticalGroup(jPanel8Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 433,
				Short.MAX_VALUE));

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
		gridBagConstraints.weighty = 1.0;
		jPanel4.add(jPanel8, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		jPanel1.add(jPanel4, gridBagConstraints);

		jPanel5.setMinimumSize(new java.awt.Dimension(0, 10));
		jPanel5.setPreferredSize(new java.awt.Dimension(100, 10));

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(
				jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 460,
				Short.MAX_VALUE));
		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 10,
				Short.MAX_VALUE));

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		jPanel1.add(jPanel5, gridBagConstraints);

		getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

		jPanel2.setMinimumSize(new java.awt.Dimension(460, 33));
		jPanel2.setPreferredSize(new java.awt.Dimension(460, 30));
		jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT,
				15, 5));

		okButton.setText(StringUtil
				.getUIString("InviteDialog.inviteButton.txt"));
		jPanel2.add(okButton);

		cancelButton.setText(StringUtil
				.getUIString("InviteDialog.cancelButton.txt"));
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});
		jPanel2.add(cancelButton);

		getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {
		javax.swing.JSlider source = (javax.swing.JSlider)evt.getSource();
		int fps = (int)source.getValue();
        this.jLabel2.setText(String.valueOf(fps));
	}

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {

	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				VideoAttributeSetupDialog dialog = new VideoAttributeSetupDialog(
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
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel16;
	private javax.swing.JLabel jLabel17;
	private javax.swing.JLabel jLabel18;
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
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JPanel jPanel8;
	private javax.swing.JPanel jPanel9;
	private javax.swing.JSlider jSlider1;
	private javax.swing.JSlider jSlider2;
	private javax.swing.JSlider jSlider3;
	private javax.swing.JSlider jSlider4;
	private javax.swing.JSlider jSlider5;
	private javax.swing.JSlider jSlider6;
	private javax.swing.JSlider jSlider7;
	private javax.swing.JSlider jSlider8;
	private javax.swing.JSlider jSlider9;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JButton okButton;
	// End of variables declaration//GEN-END:variables

}