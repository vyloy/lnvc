/*
 * UploadVideoClipDialog.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import java.awt.Cursor;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.lorent.vovo.Vovo;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.VovoStringUtil;

/**
 *
 * @author  __USER__
 */
public class UploadVideoClipDialog extends javax.swing.JDialog {

	public Boolean uploading = false;
	
	public void setUIEnable(){
		this.getTitleTextField().setEnabled(true);
		this.getDescriptionTextArea().setEnabled(true);
		this.getSelectFileHighButton().setEnabled(true);
		this.getSelectFileStandardButton().setEnabled(true);
		this.getUploadButton().setEnabled(true);
		this.getSelectFileHyperButton().setEnabled(true);
		this.getCategoryComboBox().setEnabled(true);
	}
	
	public void setUIDisable(){
		this.getTitleTextField().setEnabled(false);
		this.getDescriptionTextArea().setEnabled(false);
		this.getSelectFileHighButton().setEnabled(false);
		this.getSelectFileStandardButton().setEnabled(false);
		this.getUploadButton().setEnabled(false);
		this.getSelectFileHyperButton().setEnabled(false);
		this.getCategoryComboBox().setEnabled(false);
	}
	
	private String thumbnailImageFilePath;
	private String selectedHighVideoFilePath;
	private String selectedStandardVideoFilePath;
	private String selectedHyperVideoFilePath;
	private String currentTime;
	private String duration;

	private String newHyperVideoFileName;
	private String newHighVideoFileName;
	private String newStandardVideoFileName;
	
	public String getNewHyperVideoFileName() {
		return newHyperVideoFileName;
	}

	public void setNewHyperVideoFileName(String newHyperVideoFileName) {
		this.newHyperVideoFileName = newHyperVideoFileName;
	}

	public String getNewHighVideoFileName() {
		return newHighVideoFileName;
	}

	public void setNewHighVideoFileName(String newHighVideoFileName) {
		this.newHighVideoFileName = newHighVideoFileName;
	}

	public String getNewStandardVideoFileName() {
		return newStandardVideoFileName;
	}

	public void setNewStandardVideoFileName(String newStandardVideoFileName) {
		this.newStandardVideoFileName = newStandardVideoFileName;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getVideoDescrption() {
		return videoDescrption;
	}

	public void setVideoDescrption(String videoDescrption) {
		this.videoDescrption = videoDescrption;
	}

	private String videoDescrption;

	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

	public String getSelectedStandardVideoFilePath() {
		return selectedStandardVideoFilePath;
	}

	public void setSelectedStandardVideoFilePath(
			String selectedStandardVideoFilePath) {
		this.selectedStandardVideoFilePath = selectedStandardVideoFilePath;
	}

	public String getSelectedHighVideoFilePath() {
		return selectedHighVideoFilePath;
	}

	public void setSelectedHighVideoFilePath(String selectedHighVideoFilePath) {
		this.selectedHighVideoFilePath = selectedHighVideoFilePath;
	}

	public String getSelectedHyperVideoFilePath() {
		return selectedHyperVideoFilePath;
	}

	public void setSelectedHyperVideoFilePath(String selectedHyperVideoFilePath) {
		this.selectedHyperVideoFilePath = selectedHyperVideoFilePath;
	}

	public String getThumbnailImageFilePath() {
		return thumbnailImageFilePath;
	}

	public void setThumbnailImageFilePath(String thumbnailImageFilePath) {
		this.thumbnailImageFilePath = thumbnailImageFilePath;
	}

	/** Creates new form UploadVideoClipDialog */
	public UploadVideoClipDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		resultProgressBar = new javax.swing.JProgressBar();
		jPanel4 = new javax.swing.JPanel();
		selectFileHighButton = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		hightFilePathTextField = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		titleTextField = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		descriptionTextArea = new javax.swing.JTextArea();
		jLabel4 = new javax.swing.JLabel();
		StandardFilePathTextField = new javax.swing.JTextField();
		selectFileStandardButton = new javax.swing.JButton();
		jLabel5 = new javax.swing.JLabel();
		hyperFilePathTextField = new javax.swing.JTextField();
		selectFileHyperButton = new javax.swing.JButton();
		jLabel6 = new javax.swing.JLabel();
		categoryComboBox = new javax.swing.JComboBox();
		jPanel3 = new javax.swing.JPanel();
		thumbnailXPanel1 = new org.jdesktop.swingx.JXPanel();
		durationLabel = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		uploadButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);

		jPanel1.setLayout(new java.awt.BorderLayout());

		resultProgressBar.setStringPainted(true);
		jPanel1.add(resultProgressBar, java.awt.BorderLayout.SOUTH);

		selectFileHighButton.setText(VovoStringUtil
				.getUIString("UploadVideoClipDialog.browserfile"));
		selectFileHighButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						selectFileHighButtonActionPerformed(evt);
					}
				});

		jLabel1.setText(VovoStringUtil
				.getUIString("UploadVideoClipDialog.uploadfilehigh"));

		hightFilePathTextField.setEnabled(false);

		jLabel2.setText(VovoStringUtil
				.getUIString("UploadVideoClipDialog.title"));

		jLabel3.setText(VovoStringUtil
				.getUIString("UploadVideoClipDialog.description"));

		descriptionTextArea.setColumns(20);
		descriptionTextArea.setRows(5);
		jScrollPane1.setViewportView(descriptionTextArea);

		jLabel4.setText(VovoStringUtil
				.getUIString("UploadVideoClipDialog.uploadfilestandard"));

		StandardFilePathTextField.setEnabled(false);

		selectFileStandardButton.setText(VovoStringUtil
				.getUIString("UploadVideoClipDialog.browserfile"));
		selectFileStandardButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						selectFileStandardButtonActionPerformed(evt);
					}
				});

		jLabel5.setText(VovoStringUtil
				.getUIString("UploadVideoClipDialog.uploadfilehyper"));

		hyperFilePathTextField.setEnabled(false);

		selectFileHyperButton.setText(VovoStringUtil
				.getUIString("UploadVideoClipDialog.browserfile"));
		selectFileHyperButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						selectFileHyperButtonActionPerformed(evt);
					}
				});

		jLabel6.setText(VovoStringUtil
				.getUIString("UploadVideoClipDialog.duration"));

		categoryComboBox.setModel(new javax.swing.DefaultComboBoxModel(
				Constants.VIDEO_CATEGORY));

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
																				jLabel5)
																		.addGap(
																				12,
																				12,
																				12)
																		.addComponent(
																				hyperFilePathTextField,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				291,
																				Short.MAX_VALUE)
																		.addGap(
																				7,
																				7,
																				7)
																		.addComponent(
																				selectFileHyperButton))
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
																				hightFilePathTextField,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				291,
																				Short.MAX_VALUE)
																		.addGap(
																				7,
																				7,
																				7)
																		.addComponent(
																				selectFileHighButton))
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
																				StandardFilePathTextField,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				291,
																				Short.MAX_VALUE)
																		.addGap(
																				7,
																				7,
																				7)
																		.addComponent(
																				selectFileStandardButton))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel4Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel6)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				categoryComboBox,
																				0,
																				407,
																				Short.MAX_VALUE))
														.addGroup(
																jPanel4Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel2)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				titleTextField,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				407,
																				Short.MAX_VALUE))
														.addGroup(
																jPanel4Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel3)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jScrollPane1,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				407,
																				Short.MAX_VALUE)))
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
																				hyperFilePathTextField,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanel4Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(
																				jLabel5)
																		.addComponent(
																				selectFileHyperButton)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
																				hightFilePathTextField,
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
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
																				StandardFilePathTextField,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanel4Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(
																				jLabel4)
																		.addComponent(
																				selectFileStandardButton)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel6)
														.addComponent(
																categoryComboBox,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2)
														.addComponent(
																titleTextField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel3)
														.addComponent(
																jScrollPane1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																127,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(17, Short.MAX_VALUE)));

		jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

		thumbnailXPanel1.setBackground(new java.awt.Color(204, 204, 204));
		thumbnailXPanel1.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(0, 0, 0), 1, true));
		thumbnailXPanel1.setToolTipText("300 x 420");
		thumbnailXPanel1.setMaximumSize(new java.awt.Dimension(300, 420));
		thumbnailXPanel1.setMinimumSize(new java.awt.Dimension(300, 420));
		thumbnailXPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				thumbnailXPanel1MouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				thumbnailXPanel1MouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				thumbnailXPanel1MouseExited(evt);
			}
		});

		javax.swing.GroupLayout thumbnailXPanel1Layout = new javax.swing.GroupLayout(
				thumbnailXPanel1);
		thumbnailXPanel1.setLayout(thumbnailXPanel1Layout);
		thumbnailXPanel1Layout.setHorizontalGroup(thumbnailXPanel1Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 162, Short.MAX_VALUE));
		thumbnailXPanel1Layout.setVerticalGroup(thumbnailXPanel1Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 186, Short.MAX_VALUE));

		durationLabel.setText("\u65f6\u957f\uff1a");

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout
				.setHorizontalGroup(jPanel3Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																false)
														.addComponent(
																durationLabel,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																thumbnailXPanel1,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																164,
																Short.MAX_VALUE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jPanel3Layout
				.setVerticalGroup(jPanel3Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												thumbnailXPanel1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												188,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(durationLabel)
										.addContainerGap(96, Short.MAX_VALUE)));

		jPanel1.add(jPanel3, java.awt.BorderLayout.WEST);

		getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

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

	private void thumbnailXPanel1MouseClicked(java.awt.event.MouseEvent evt) {
		Vovo.exeC("videoclip", "selectedVideoClipPicture", this);
	}

	private void thumbnailXPanel1MouseExited(java.awt.event.MouseEvent evt) {
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	private void thumbnailXPanel1MouseEntered(java.awt.event.MouseEvent evt) {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	private void selectFileHyperButtonActionPerformed(
			java.awt.event.ActionEvent evt) {
		Vovo.exeC("videoclip", "selectedVideoClipFile", this,
				Constants.VideoDefinition.Hyper);
	}

	private void selectFileStandardButtonActionPerformed(
			java.awt.event.ActionEvent evt) {
		Vovo.exeC("videoclip", "selectedVideoClipFile", this,
				Constants.VideoDefinition.Standard);
	}

	private void selectFileHighButtonActionPerformed(
			java.awt.event.ActionEvent evt) {
		Vovo.exeC("videoclip", "selectedVideoClipFile", this,
				Constants.VideoDefinition.High);
	}

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Vovo.exeC("videoclip", "cancelUpLoadVideoClip", this);
//		this.dispose();
	}

	private void uploadButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Vovo.exeC("videoclip", "uploadVideoClip", this);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				UploadVideoClipDialog dialog = new UploadVideoClipDialog(
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
	private javax.swing.JTextField StandardFilePathTextField;
	private javax.swing.JButton cancelButton;
	private javax.swing.JComboBox categoryComboBox;
	private javax.swing.JTextArea descriptionTextArea;
	private javax.swing.JLabel durationLabel;
	private javax.swing.JTextField hightFilePathTextField;
	private javax.swing.JTextField hyperFilePathTextField;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JProgressBar resultProgressBar;
	private javax.swing.JButton selectFileHighButton;
	private javax.swing.JButton selectFileHyperButton;
	private javax.swing.JButton selectFileStandardButton;
	private org.jdesktop.swingx.JXPanel thumbnailXPanel1;
	private javax.swing.JTextField titleTextField;
	private javax.swing.JButton uploadButton;

	// End of variables declaration//GEN-END:variables

	public org.jdesktop.swingx.JXPanel getThumbnailXPanel1() {
		return thumbnailXPanel1;
	}

	public javax.swing.JTextField getStandardFilePathTextField() {
		return StandardFilePathTextField;
	}

	public javax.swing.JTextField getHightFilePathTextField() {
		return hightFilePathTextField;
	}

	public javax.swing.JProgressBar getResultProgressBar() {
		return resultProgressBar;
	}

	public javax.swing.JTextArea getDescriptionTextArea() {
		return descriptionTextArea;
	}

	public javax.swing.JTextField getTitleTextField() {
		return titleTextField;
	}

	public javax.swing.JButton getSelectFileHighButton() {
		return selectFileHighButton;
	}

	public javax.swing.JButton getSelectFileStandardButton() {
		return selectFileStandardButton;
	}

	public javax.swing.JButton getUploadButton() {
		return uploadButton;
	}

	public javax.swing.JTextField getHyperFilePathTextField() {
		return hyperFilePathTextField;
	}

	public javax.swing.JButton getSelectFileHyperButton() {
		return selectFileHyperButton;
	}

	public javax.swing.JComboBox getCategoryComboBox() {
		return categoryComboBox;
	}

	public javax.swing.JLabel getDurationLabel() {
		return durationLabel;
	}

	public javax.swing.JButton getCancelButton() {
		return cancelButton;
	}

}