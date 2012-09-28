/*
 * TransferFileListItem.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import java.io.File;

import org.apache.log4j.Logger;

import com.lorent.common.util.FileUtil;
import com.lorent.vovo.Vovo;

/**
 *
 * @author  Jack
 */
public class TransferFileListItem extends javax.swing.JPanel {

	private Logger log = Logger.getLogger(TransferFileListItem.class);

	/** Creates new form TransferFileListItem */
	public TransferFileListItem() {
		initComponents();
		this.monitorLbl.setVisible(false);
		this.acceptLink.setVisible(false);
		this.rejectLink.setVisible(false);
		this.cancelLink.setVisible(false);
		this.cancelInProcessLink.setVisible(false);
		this.monitorLbl.setText("");
		this.progressBar.setMinimum(0);
		this.progressBar.setMaximum(100);
		this.progressBar.setStringPainted(true);
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		fileInfoPanel = new javax.swing.JPanel();
		fileTypeLbl = new javax.swing.JLabel();
		operateLbl = new javax.swing.JLabel();
		filenameLbl = new javax.swing.JLabel();
		progressBar = new javax.swing.JProgressBar();
		transferInfoPanel = new javax.swing.JPanel();
		monitorLbl = new javax.swing.JLabel();
		sizeLbl = new javax.swing.JLabel();
		toolPanel = new javax.swing.JPanel();
		cancelLink = new org.jdesktop.swingx.JXHyperlink();
		acceptLink = new org.jdesktop.swingx.JXHyperlink();
		rejectLink = new org.jdesktop.swingx.JXHyperlink();
		cancelInProcessLink = new org.jdesktop.swingx.JXHyperlink();

		setMaximumSize(new java.awt.Dimension(2147483647, 100));
		setLayout(new java.awt.GridBagLayout());

		fileInfoPanel.setLayout(new java.awt.GridBagLayout());

		fileTypeLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/file.png"))); // NOI18N
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = 2;
		fileInfoPanel.add(fileTypeLbl, gridBagConstraints);

		operateLbl.setText("\u63a5\u6536\u6587\u4ef6\u8bf7\u6c42");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		fileInfoPanel.add(operateLbl, gridBagConstraints);

		filenameLbl.setFont(new java.awt.Font("微软雅黑", 0, 10));
		filenameLbl.setText("text.doc");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		fileInfoPanel.add(filenameLbl, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		add(fileInfoPanel, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		add(progressBar, gridBagConstraints);

		transferInfoPanel.setLayout(new java.awt.GridLayout(1, 0));

		monitorLbl.setText("100k/s");
		transferInfoPanel.add(monitorLbl);

		sizeLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
		sizeLbl.setText("5m");
		transferInfoPanel.add(sizeLbl);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		add(transferInfoPanel, gridBagConstraints);

		toolPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT,
				5, 2));

		cancelLink.setText("\u53d6\u6d88");
		cancelLink.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				cancelLinkMouseClicked(evt);
			}
		});
		toolPanel.add(cancelLink);

		acceptLink.setText("\u63a5\u6536");
		acceptLink.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				acceptLinkMouseClicked(evt);
			}
		});
		toolPanel.add(acceptLink);

		rejectLink.setText("\u62d2\u7edd");
		rejectLink.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				rejectLinkMouseClicked(evt);
			}
		});
		toolPanel.add(rejectLink);

		cancelInProcessLink.setText("\u53d6\u6d88");
		cancelInProcessLink.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				cancelInProcessLinkMouseClicked(evt);
			}
		});
		toolPanel.add(cancelInProcessLink);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		add(toolPanel, gridBagConstraints);
	}// </editor-fold>
	//GEN-END:initComponents

	private void cancelInProcessLinkMouseClicked(java.awt.event.MouseEvent evt) {
		if (listener != null) {
			this.listener.cancelInProcess(streamID, this.filenameLbl.getText());
		}
	}

	private void rejectLinkMouseClicked(java.awt.event.MouseEvent evt) {
		if (listener != null) {
			this.listener.clickReject(streamID, this.filenameLbl.getText());
		}
	}

	private void acceptLinkMouseClicked(java.awt.event.MouseEvent evt) {
		if (listener != null) {
			this.listener.clickAccept(streamID, this.filenameLbl.getText());
		}
	}

	private void cancelLinkMouseClicked(java.awt.event.MouseEvent evt) {

		if (listener != null) {
			this.listener.clickCancel(this);
		}

	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private org.jdesktop.swingx.JXHyperlink acceptLink;
	private org.jdesktop.swingx.JXHyperlink cancelInProcessLink;
	private org.jdesktop.swingx.JXHyperlink cancelLink;
	private javax.swing.JPanel fileInfoPanel;
	private javax.swing.JLabel fileTypeLbl;
	private javax.swing.JLabel filenameLbl;
	private javax.swing.JLabel monitorLbl;
	private javax.swing.JLabel operateLbl;
	private javax.swing.JProgressBar progressBar;
	private org.jdesktop.swingx.JXHyperlink rejectLink;
	private javax.swing.JLabel sizeLbl;
	private javax.swing.JPanel toolPanel;
	private javax.swing.JPanel transferInfoPanel;
	// End of variables declaration//GEN-END:variables

	private String streamID;
	private boolean isSend;
	private String savePath;

	public void setSendFileInfo(String fileName, long fileSize, String streamID) {
		this.streamID = streamID;
		this.operateLbl.setText(Vovo.getViewManager().getUIString(
				"TransferFileList.sendFileRequest"));
		this.filenameLbl.setText(fileName);
		try {
			this.sizeLbl.setText(FileUtil.fileSizeToUserString(fileSize));
		} catch (Exception e) {
			log.error("setSendFileInfo", e);
		}
		this.cancelLink.setVisible(true);
		isSend = true;
	}

	public void setRecvFileInfo(String fileName, long fileSize, String streamID) {
		this.streamID = streamID;
		this.operateLbl.setText(Vovo.getViewManager().getUIString(
				"TransferFileList.receiveFileRequest"));
		this.filenameLbl.setText(fileName);
		try {
			this.sizeLbl.setText(FileUtil.fileSizeToUserString(fileSize));
		} catch (Exception e) {
			log.error("setRecvFileInfo", e);
		}
		this.acceptLink.setVisible(true);
		this.rejectLink.setVisible(true);
		isSend = false;
	}

	private EventListener listener;

	public interface EventListener {
		void clickCancel(TransferFileListItem item);

		void clickAccept(String streamID, String fileName);

		void clickReject(String streamID, String fileName);

		void cancelInProcess(String streamID, String fileName);
	}

	public void setEventListener(EventListener listener) {
		this.listener = listener;
	}

	public String getStreamID() {
		return streamID;
	}

	public void updateInfo(int percent) {
		this.acceptLink.setVisible(false);
		this.rejectLink.setVisible(false);
		this.cancelLink.setVisible(false);
		this.cancelInProcessLink.setVisible(true);
		this.monitorLbl.setVisible(true);
		this.progressBar.setValue(percent);
	}

	public boolean isSend() {
		return isSend;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getFileName() {
		return this.filenameLbl.getText();
	}

}