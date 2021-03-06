/*
 * ConferenceCreatePanel.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.painter.ImagePainter;
import org.jdesktop.swingx.painter.ImagePainter.ScaleType;

import com.jhlabs.image.GaussianFilter;
import com.jtattoo.plaf.vovoglass.VoVoTabbedPaneUI;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.util.Constants;

/**
 *
 * @author  __USER__
 */
public class ConferenceCreatePanel extends javax.swing.JPanel {

	private static Logger log = Logger.getLogger(ConferenceCreatePanel.class);

	/** Creates new form ConferenceCreatePanel */
	public ConferenceCreatePanel() {
		initComponents();
		ImagePainter ip;
		try {
			BufferedImage areaimg = Vovo.getMyContext().getDataManager()
					.getValue(Constants.DataKey.BACKGROUND_IMAGE.toString());

//			GaussianFilter gaussianFilter = new GaussianFilter(8.5f);
//			BufferedImage resultImage = gaussianFilter.filter(areaimg, areaimg);

			BufferedImage resultImage = Vovo.getMyContext().getDataManager().getValue(Constants.DataKey.BACKGROUND_GAUSSIAN_IMAGE.toString());
			
			ip = new ImagePainter(resultImage);
			//			ip.setScaleToFit(true);
			ip.setScaleType(ScaleType.Distort);

			backgroundXPanel.setBackgroundPainter(ip);

			BufferedImage whiteimg = ImageIO.read(getClass().getResource(
					"/com/lorent/vovo/resource/images/whitealpha.png"));
			ImagePainter imagePainter = new ImagePainter(whiteimg);
			imagePainter.setScaleType(ScaleType.Distort);
			imagePainter.setScaleToFit(true);
			advanceOptionXPanel.setBackgroundPainter(imagePainter);

			ImagePainter imagePainter2 = new ImagePainter(whiteimg);
			imagePainter2.setScaleType(ScaleType.Distort);
			imagePainter2.setScaleToFit(true);
			baseXPanel.setBackgroundPainter(imagePainter2);

			ImagePainter imagePainter3 = new ImagePainter(whiteimg);
			imagePainter3.setScaleType(ScaleType.Distort);
			imagePainter3.setScaleToFit(true);
			noticeXPanel.setBackgroundPainter(imagePainter3);
		} catch (Exception e) {
			log.error("ConferenceCreatePanel()", e);
			e.printStackTrace();
		}
		mainTabbedPane.setUI(new VoVoTabbedPaneUI());
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
		mainTabbedPane = new javax.swing.JTabbedPane();
		baseXPanel = new org.jdesktop.swingx.JXPanel();
		jLabel1 = new javax.swing.JLabel();
		conferenceNameTextField = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		conferencePasswordField = new javax.swing.JPasswordField();
		jLabel3 = new javax.swing.JLabel();
		confenenceTopicTextField = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		conferenceDescriptionTextArea = new javax.swing.JTextArea();
		noticeXPanel = new org.jdesktop.swingx.JXPanel();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTree1 = new javax.swing.JTree();
		jLabel8 = new javax.swing.JLabel();
		jCheckBox1 = new javax.swing.JCheckBox();
		jCheckBox2 = new javax.swing.JCheckBox();
		jCheckBox3 = new javax.swing.JCheckBox();
		jScrollPane3 = new javax.swing.JScrollPane();
		jTree2 = new javax.swing.JTree();
		advanceOptionXPanel = new org.jdesktop.swingx.JXPanel();
		jLabel5 = new javax.swing.JLabel();
		confTypeComboBox = new javax.swing.JComboBox();
		jLabel6 = new javax.swing.JLabel();
		confRoleComboBox = new javax.swing.JComboBox();
		jLabel7 = new javax.swing.JLabel();
		needApplyComboBox = new javax.swing.JComboBox();
		jPanel4 = new javax.swing.JPanel();
		okButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();

		setOpaque(false);
		setLayout(new javax.swing.BoxLayout(this,
				javax.swing.BoxLayout.LINE_AXIS));

		backgroundXPanel.setLayout(new java.awt.BorderLayout());

		mainTabbedPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(1,
				1, 1, 1));
		mainTabbedPane
				.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

		baseXPanel.setOpaque(false);

		jLabel1.setText("*\u540d\u79f0");

		jLabel2.setText("\u5bc6\u7801");

		jLabel3.setText("\u4e3b\u9898");

		jLabel4.setText("\u63cf\u8ff0");

		conferenceDescriptionTextArea.setColumns(20);
		conferenceDescriptionTextArea.setRows(5);
		jScrollPane1.setViewportView(conferenceDescriptionTextArea);

		javax.swing.GroupLayout baseXPanelLayout = new javax.swing.GroupLayout(
				baseXPanel);
		baseXPanel.setLayout(baseXPanelLayout);
		baseXPanelLayout
				.setHorizontalGroup(baseXPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								baseXPanelLayout
										.createSequentialGroup()
										.addGap(58, 58, 58)
										.addGroup(
												baseXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(jLabel4)
														.addComponent(jLabel3)
														.addComponent(jLabel2)
														.addComponent(jLabel1))
										.addGap(18, 18, 18)
										.addGroup(
												baseXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																conferencePasswordField,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																388,
																Short.MAX_VALUE)
														.addComponent(
																confenenceTopicTextField,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																388,
																Short.MAX_VALUE)
														.addComponent(
																jScrollPane1,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																388,
																Short.MAX_VALUE)
														.addComponent(
																conferenceNameTextField,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																388,
																Short.MAX_VALUE))
										.addGap(105, 105, 105)));
		baseXPanelLayout
				.setVerticalGroup(baseXPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								baseXPanelLayout
										.createSequentialGroup()
										.addGap(14, 14, 14)
										.addGroup(
												baseXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(
																conferenceNameTextField))
										.addGap(18, 18, 18)
										.addGroup(
												baseXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2)
														.addComponent(
																conferencePasswordField))
										.addGap(18, 18, 18)
										.addGroup(
												baseXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel3)
														.addComponent(
																confenenceTopicTextField))
										.addGap(18, 18, 18)
										.addGroup(
												baseXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel4)
														.addComponent(
																jScrollPane1,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																176,
																Short.MAX_VALUE))
										.addGap(27, 27, 27)));

		mainTabbedPane.addTab("\u57fa\u672c\u4fe1\u606f", baseXPanel);

		noticeXPanel.setOpaque(false);

		jScrollPane2.setViewportView(jTree1);

		jLabel8.setText("\u901a\u77e5\u65b9\u5f0f");

		jCheckBox1.setText("EMail");
		jCheckBox1.setOpaque(false);

		jCheckBox2.setText("\u77ed\u4fe1");
		jCheckBox2.setOpaque(false);

		jCheckBox3.setText("VoVo");
		jCheckBox3.setOpaque(false);

		jScrollPane3.setViewportView(jTree2);

		javax.swing.GroupLayout noticeXPanelLayout = new javax.swing.GroupLayout(
				noticeXPanel);
		noticeXPanel.setLayout(noticeXPanelLayout);
		noticeXPanelLayout
				.setHorizontalGroup(noticeXPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								noticeXPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												noticeXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																noticeXPanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				jScrollPane2,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				266,
																				Short.MAX_VALUE)
																		.addGap(
																				28,
																				28,
																				28)
																		.addComponent(
																				jScrollPane3,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				283,
																				Short.MAX_VALUE))
														.addGroup(
																noticeXPanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel8)
																		.addGap(
																				18,
																				18,
																				18)
																		.addComponent(
																				jCheckBox1)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				jCheckBox2)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				jCheckBox3)))
										.addContainerGap()));
		noticeXPanelLayout
				.setVerticalGroup(noticeXPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								noticeXPanelLayout
										.createSequentialGroup()
										.addGap(34, 34, 34)
										.addGroup(
												noticeXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jScrollPane3,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																250,
																Short.MAX_VALUE)
														.addComponent(
																jScrollPane2,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																250,
																Short.MAX_VALUE))
										.addGap(18, 18, 18)
										.addGroup(
												noticeXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel8)
														.addComponent(
																jCheckBox1)
														.addComponent(
																jCheckBox2)
														.addComponent(
																jCheckBox3))
										.addContainerGap()));

		mainTabbedPane.addTab("\u4f1a\u8bae\u901a\u77e5", noticeXPanel);

		advanceOptionXPanel.setOpaque(false);

		jLabel5.setText("\u4f1a\u8bae\u7c7b\u578b\u540d\u79f0");

		confTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel());

		jLabel6.setText("\u4f1a\u8bae\u9ed8\u8ba4\u89d2\u8272");

		confRoleComboBox.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		jLabel7.setText("\u662f\u5426\u9700\u8981\u7533\u8bf7");

		needApplyComboBox.setModel(new DefaultComboBoxModel(new String[] { "是",
				"否" }));

		javax.swing.GroupLayout advanceOptionXPanelLayout = new javax.swing.GroupLayout(
				advanceOptionXPanel);
		advanceOptionXPanel.setLayout(advanceOptionXPanelLayout);
		advanceOptionXPanelLayout
				.setHorizontalGroup(advanceOptionXPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								advanceOptionXPanelLayout
										.createSequentialGroup()
										.addGap(64, 64, 64)
										.addGroup(
												advanceOptionXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jLabel7,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																66,
																Short.MAX_VALUE)
														.addComponent(
																jLabel6,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																66,
																Short.MAX_VALUE)
														.addComponent(
																jLabel5,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addGap(27, 27, 27)
										.addGroup(
												advanceOptionXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																needApplyComboBox,
																0, 323,
																Short.MAX_VALUE)
														.addComponent(
																confRoleComboBox,
																0, 323,
																Short.MAX_VALUE)
														.addComponent(
																confTypeComboBox,
																0, 323,
																Short.MAX_VALUE))
										.addGap(117, 117, 117)));
		advanceOptionXPanelLayout
				.setVerticalGroup(advanceOptionXPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								advanceOptionXPanelLayout
										.createSequentialGroup()
										.addGap(29, 29, 29)
										.addGroup(
												advanceOptionXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabel5,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																confTypeComboBox))
										.addGap(18, 18, 18)
										.addGroup(
												advanceOptionXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabel6,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																confRoleComboBox))
										.addGap(18, 18, 18)
										.addGroup(
												advanceOptionXPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabel7,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																needApplyComboBox))
										.addGap(207, 207, 207)));

		mainTabbedPane.addTab("\u9ad8\u7ea7\u9009\u9879", advanceOptionXPanel);

		backgroundXPanel.add(mainTabbedPane, java.awt.BorderLayout.CENTER);

		jPanel4.setOpaque(false);
		jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

		okButton.setText("\u786e\u5b9a");
		okButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				okButtonActionPerformed(evt);
			}
		});
		jPanel4.add(okButton);

		cancelButton.setText("\u53d6\u6d88");
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});
		jPanel4.add(cancelButton);

		backgroundXPanel.add(jPanel4, java.awt.BorderLayout.SOUTH);

		add(backgroundXPanel);
	}// </editor-fold>
	//GEN-END:initComponents

	private void confTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Vovo.exeC("conference", "doCreateConference");
	}

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
		JDialog jDialog = Vovo.getMyContext().getViewManager().getView(
				Constants.ViewKey.CONFERENCE_CREATEDIALOG.toString());
		jDialog.dispose();
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private org.jdesktop.swingx.JXPanel advanceOptionXPanel;
	private org.jdesktop.swingx.JXPanel backgroundXPanel;
	private org.jdesktop.swingx.JXPanel baseXPanel;
	private javax.swing.JButton cancelButton;
	private javax.swing.JComboBox confRoleComboBox;
	private javax.swing.JComboBox confTypeComboBox;
	private javax.swing.JTextField confenenceTopicTextField;
	private javax.swing.JTextArea conferenceDescriptionTextArea;
	private javax.swing.JTextField conferenceNameTextField;
	private javax.swing.JPasswordField conferencePasswordField;
	private javax.swing.JCheckBox jCheckBox1;
	private javax.swing.JCheckBox jCheckBox2;
	private javax.swing.JCheckBox jCheckBox3;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JTree jTree1;
	private javax.swing.JTree jTree2;
	private javax.swing.JTabbedPane mainTabbedPane;
	private javax.swing.JComboBox needApplyComboBox;
	private org.jdesktop.swingx.JXPanel noticeXPanel;
	private javax.swing.JButton okButton;

	// End of variables declaration//GEN-END:variables
	public javax.swing.JTextField getConfenenceTopicTextField() {
		return confenenceTopicTextField;
	}

	public javax.swing.JTextArea getConferenceDescriptionTextArea() {
		return conferenceDescriptionTextArea;
	}

	public javax.swing.JTextField getConferenceNameTextField() {
		return conferenceNameTextField;
	}

	public javax.swing.JPasswordField getConferencePasswordField() {
		return conferencePasswordField;
	}

	public javax.swing.JComboBox getConfTypeComboBox() {
		return confTypeComboBox;
	}

	public javax.swing.JComboBox getConfRoleComboBox() {
		return confRoleComboBox;
	}

	public javax.swing.JComboBox getNeedApplyComboBox() {
		return needApplyComboBox;
	}

	public javax.swing.JTabbedPane getMainTabbedPane() {
		return mainTabbedPane;
	}

	
}