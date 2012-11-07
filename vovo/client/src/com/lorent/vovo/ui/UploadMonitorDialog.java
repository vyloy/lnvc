/*
 * UploadMonitorDialog.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import com.lorent.vovo.Vovo;
import com.lorent.vovo.util.VovoStringUtil;

/**
 *
 * @author  __USER__
 */
public class UploadMonitorDialog extends javax.swing.JDialog {

	private String cacheFileName;
	
	public String getCacheFileName() {
		return cacheFileName;
	}

	public void setCacheFileName(String cacheFileName) {
		this.cacheFileName = cacheFileName;
	}

	/** Creates new form UploadMonitorDialog */
	public UploadMonitorDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel2 = new javax.swing.JPanel();
		uploadButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();
		jPanel1 = new javax.swing.JPanel();
		jPanel3 = new javax.swing.JPanel();
		thumbnailXPanel1 = new org.jdesktop.swingx.JXPanel();
		jPanel4 = new javax.swing.JPanel();
		selectFileHighButton = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		thumbnailTextField = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		titleTextField = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		descriptionTextArea = new javax.swing.JTextArea();
		jLabel4 = new javax.swing.JLabel();
		liveStreamURLTextField = new javax.swing.JTextField();
		resultProgressBar = new javax.swing.JProgressBar();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER,
				50, 5));

		uploadButton.setText(VovoStringUtil
				.getUIString("UploadVideoClipDialog.upload"));
		uploadButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				uploadButtonActionPerformed(evt);
			}
		});
		jPanel2.add(uploadButton);

		cancelButton.setText(VovoStringUtil
				.getUIString("UploadVideoClipDialog.cancel"));
		jPanel2.add(cancelButton);

		getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

		jPanel1.setLayout(new java.awt.BorderLayout());

		thumbnailXPanel1.setBackground(new java.awt.Color(204, 204, 204));
		thumbnailXPanel1.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(0, 0, 0), 1, true));

		javax.swing.GroupLayout thumbnailXPanel1Layout = new javax.swing.GroupLayout(
				thumbnailXPanel1);
		thumbnailXPanel1.setLayout(thumbnailXPanel1Layout);
		thumbnailXPanel1Layout.setHorizontalGroup(thumbnailXPanel1Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 139, Short.MAX_VALUE));
		thumbnailXPanel1Layout.setVerticalGroup(thumbnailXPanel1Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 127, Short.MAX_VALUE));

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout.createSequentialGroup().addContainerGap()
						.addComponent(thumbnailXPanel1,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE).addContainerGap()));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout.createSequentialGroup().addContainerGap()
						.addComponent(thumbnailXPanel1,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(130, Short.MAX_VALUE)));

		jPanel1.add(jPanel3, java.awt.BorderLayout.WEST);

		selectFileHighButton.setText(VovoStringUtil
				.getUIString("UploadVideoClipDialog.browserfile"));
		selectFileHighButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						selectFileHighButtonActionPerformed(evt);
					}
				});

		jLabel1.setText(VovoStringUtil
				.getUIString("UploadMonitorDialog.thumbnail"));

		thumbnailTextField.setEnabled(false);

		jLabel2.setText(VovoStringUtil
				.getUIString("UploadVideoClipDialog.title"));

		jLabel3.setText(VovoStringUtil
				.getUIString("UploadVideoClipDialog.description"));

		descriptionTextArea.setColumns(20);
		descriptionTextArea.setRows(5);
		jScrollPane1.setViewportView(descriptionTextArea);

		jLabel4.setText(VovoStringUtil
				.getUIString("UploadMonitorDialog.liveUrl"));

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(
				jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout
				.setHorizontalGroup(jPanel4Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel4Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel4Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel1)
																		.addGap(
																				12,
																				12,
																				12)
																		.addComponent(
																				thumbnailTextField,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				281,
																				Short.MAX_VALUE)
																		.addGap(
																				7,
																				7,
																				7)
																		.addComponent(
																				selectFileHighButton,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE))
														.addGroup(
																jPanel4Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel4)
																		.addGap(
																				12,
																				12,
																				12)
																		.addComponent(
																				liveStreamURLTextField,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				401,
																				Short.MAX_VALUE))
														.addGroup(
																jPanel4Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel4Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel2)
																						.addComponent(
																								jLabel3))
																		.addGap(
																				12,
																				12,
																				12)
																		.addGroup(
																				jPanel4Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jScrollPane1,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								401,
																								Short.MAX_VALUE)
																						.addComponent(
																								titleTextField,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								401,
																								Short.MAX_VALUE))))
										.addContainerGap()));
		jPanel4Layout
				.setVerticalGroup(jPanel4Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel4Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addGroup(
																jPanel4Layout
																		.createSequentialGroup()
																		.addGap(
																				2,
																				2,
																				2)
																		.addComponent(
																				thumbnailTextField,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanel4Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(
																				jLabel1)
																		.addComponent(
																				selectFileHighButton)))
										.addGap(10, 10, 10)
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																liveStreamURLTextField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel4))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																titleTextField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel2))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jScrollPane1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																127,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel3))
										.addContainerGap(25, Short.MAX_VALUE)));

		jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

		resultProgressBar.setStringPainted(true);
		jPanel1.add(resultProgressBar, java.awt.BorderLayout.SOUTH);

		getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void uploadButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Vovo.exeC("videoclip", "uploadMonitor", this);
	}

	private void selectFileHighButtonActionPerformed(
			java.awt.event.ActionEvent evt) {
		Vovo.exeC("videoclip", "selectedMonitorPictureFile", this);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				UploadMonitorDialog dialog = new UploadMonitorDialog(
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
	private javax.swing.JTextArea descriptionTextArea;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextField liveStreamURLTextField;
	private javax.swing.JProgressBar resultProgressBar;
	private javax.swing.JButton selectFileHighButton;
	private javax.swing.JTextField thumbnailTextField;
	private org.jdesktop.swingx.JXPanel thumbnailXPanel1;
	private javax.swing.JTextField titleTextField;
	private javax.swing.JButton uploadButton;

	// End of variables declaration//GEN-END:variables
	public javax.swing.JTextArea getDescriptionTextArea() {
		return descriptionTextArea;
	}

	public javax.swing.JTextField getLiveStreamURLTextField() {
		return liveStreamURLTextField;
	}

	public javax.swing.JProgressBar getResultProgressBar() {
		return resultProgressBar;
	}

	public javax.swing.JButton getSelectFileHighButton() {
		return selectFileHighButton;
	}

	public javax.swing.JTextField getThumbnailTextField() {
		return thumbnailTextField;
	}

	public org.jdesktop.swingx.JXPanel getThumbnailXPanel1() {
		return thumbnailXPanel1;
	}

	public javax.swing.JTextField getTitleTextField() {
		return titleTextField;
	}

	public javax.swing.JButton getUploadButton() {
		return uploadButton;
	}

}