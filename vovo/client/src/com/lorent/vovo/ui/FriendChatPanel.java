/*
 * FriendChatPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import java.awt.Component;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Date;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.jivesoftware.smack.packet.Message;

import com.lorent.common.tree.MemberBean;
import com.lorent.common.util.StringUtil;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.dto.FontStyle;
import com.lorent.vovo.ui.InputArea.EventListener;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.DataUtil;
import com.lorent.vovo.util.FontUtil;
import com.lorent.vovo.util.ImageUtil;
import com.lorent.vovo.util.VovoStringUtil;
import com.lorent.vovo.util.Constants.DataKey;

/**
 *
 * @author  Jack
 */
public class FriendChatPanel extends javax.swing.JPanel {

	private Logger log = Logger.getLogger(FriendChatPanel.class);

	/** Creates new form FriendChatPanel */
	public FriendChatPanel() {
		initComponents();
		inputArea = new InputArea();
		jPanel3.add(inputArea);
		inputArea.setEventListener(new EventListener() {

			@Override
			public void sendMsg(String msg, FontStyle style,
					Map<String, String> imgs) {
				try {
					long time = Vovo.getLcmUtil().getSystemTime();
					Vovo.exeC("chat", "sendFriendMsg", msg, style, bean
							.getLccAccount(), bean.getId(), imgs, time);
					insertMyMsg(msg, style, new Date(time), imgs);
				} catch (Exception e) {
					log.error("insert my msg error", e);
					JOptionPane.showMessageDialog(FriendChatPanel.this,
							"insert my msg error", "error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		//============ init
		//		videoBtn.setVisible(false);
		//		soundBtn.setVisible(false);

		msgRecordPanel = new MsgRecordPanel();

		transferFileList = new TransferFileList();
		transferFileList.setEventListener(new TransferEventListener());

		soundCallPanel = new SoundCallPanel();
		soundCallPanel.setEventListener(new MySoundCallEventListener());

		videoCallPanel = new VideoCallPanel();
		videoCallPanel.setEventListener(new MyVideoCallEventListener());

		jPanel6.setVisible(false);

		showPane.setEventListener(new MyTextPane.EventListener() {

			@Override
			public void enterType(KeyEvent e) {

			}

			@Override
			public void dropFile(File file) {
				if (file.isDirectory()) {
					JOptionPane.showMessageDialog(FriendChatPanel.this, Vovo
							.getViewManager().getUIString("sendFile.fileOnly"));
					return;
				}
				doSendFile(file);
			}
		});
	}

	private class TransferEventListener implements
			TransferFileList.EventListener {

		@Override
		public void clickCancel(String streamID, String fileName) {
			String text = StringUtil.getFormatString(Vovo.getViewManager()
					.getUIString("sendFile.meCancel"), "\"" + fileName + "\"");
			try {
				long time = Vovo.getLcmUtil().getSystemTime();
				showPane.insertNotice(MyTextPane.NOTICE_TYPE_ERROR, new Date(
						time), text, FontUtil.getNoticeStyle());
				Vovo.exeC("chat", "cancelSendFile", streamID, bean
						.getLccAccount(), time);
			} catch (Exception e) {
				log.error("clickCancel", e);
			}
		}

		@Override
		public void listIsEmpty() {
			removeOperateTab(transferFileList);
		}

		@Override
		public void clickAccept(String streamID, String fileName) {
			JFileChooser fileChooser = new JFileChooser();
			//			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fileChooser.setCurrentDirectory(new File(Constants.USER_HOME));
			fileChooser.setSelectedFile(new File(fileName));
			int ret = fileChooser.showDialog(null, Vovo.getViewManager()
					.getUIString("common.save"));
			if (ret == JFileChooser.APPROVE_OPTION) {
				String path = fileChooser.getSelectedFile().getPath();
				boolean fileExist = new File(path).exists();
				if (fileExist) {
					int ret2 = JOptionPane.showConfirmDialog(
							FriendChatPanel.this, Vovo.getViewManager()
									.getUIString("sendFile.fileExist"), "",
							JOptionPane.YES_NO_OPTION);
					if (ret2 != JOptionPane.OK_OPTION) {
						return;
					}
				}
				transferFileList.setSavePath(streamID, path);
				Vovo.exeC("chat", "acceptSendFile", streamID, path);
			}
		}

		@Override
		public void clickReject(String streamID, String fileName) {
			String text = StringUtil.getFormatString(Vovo.getViewManager()
					.getUIString("receiveFile.reject"), fileName);
			try {
				long time = Vovo.getLcmUtil().getSystemTime();
				showPane.insertNotice(MyTextPane.NOTICE_TYPE_ERROR, new Date(
						time), text, FontUtil.getNoticeStyle());
				Vovo.exeC("chat", "rejectSendFile", streamID, bean
						.getLccAccount());
			} catch (Exception e) {
				log.error("clickReject", e);
			}
		}

		@Override
		public void cancelInProcess(String streamID, String fileName) {
			String text = StringUtil.getFormatString(Vovo.getViewManager()
					.getUIString("sendFile.meCancel"), "\"" + fileName + "\"");
			try {
				long time = Vovo.getLcmUtil().getSystemTime();
				showPane.insertNotice(MyTextPane.NOTICE_TYPE_ERROR, new Date(
						time), text, FontUtil.getNoticeStyle());
				Vovo.exeC("chat", "cancelSendFileInProcess", streamID);
			} catch (Exception e) {
				log.error("cancelInProcess", e);
			}
		}

	}

	private class MySoundCallEventListener implements
			com.lorent.vovo.ui.SoundCallPanel.EventListener {

		@Override
		public void acceptCallInvite() {
			Vovo.exeC("phone", "acceptCallInvite", bean.getLccAccount(),
					FriendChatPanel.this);
		}

		@Override
		public void cancelCallInvite() {
			Vovo.exeC("phone", "cancelCallInvite", bean.getLccAccount(),
					FriendChatPanel.this, true);
		}

		@Override
		public void hangupCall() {
			Vovo.exeC("phone", "hangupCall", bean.getLccAccount(),
					FriendChatPanel.this, true);
		}

		@Override
		public void rejectCallInvite() {
			Vovo.exeC("phone", "rejectCallInvite", bean.getLccAccount(),
					FriendChatPanel.this, true);
		}

	}

	private class MyVideoCallEventListener implements
			com.lorent.vovo.ui.VideoCallPanel.EventListener {
		@Override
		public void acceptCallInvite() {
			Vovo.exeC("phone", "acceptCallInvite", bean.getLccAccount(),
					FriendChatPanel.this);
		}

		@Override
		public void cancelCallInvite() {
			Vovo.exeC("phone", "cancelCallInvite", bean.getLccAccount(),
					FriendChatPanel.this, false);
		}

		@Override
		public void hangupCall() {
			Vovo.exeC("phone", "hangupCall", bean.getLccAccount(),
					FriendChatPanel.this, false);
		}

		@Override
		public void rejectCallInvite() {
			Vovo.exeC("phone", "rejectCallInvite", bean.getLccAccount(),
					FriendChatPanel.this, false);
		}
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		jPanel1 = new javax.swing.JPanel();
		jPanel7 = new javax.swing.JPanel();
		headLbl = new javax.swing.JLabel();
		nameLbl = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		videoBtn = new javax.swing.JButton();
		soundBtn = new javax.swing.JButton();
		sendFileBtn = new javax.swing.JButton();
		showMsgBtn = new javax.swing.JButton();
		jButton1 = new javax.swing.JButton();
		jPanel4 = new javax.swing.JPanel();
		jPanel5 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		showPane = new com.lorent.vovo.ui.MyTextPane();
		jPanel3 = new javax.swing.JPanel();
		jPanel6 = new javax.swing.JPanel();
		operateTab = new javax.swing.JTabbedPane();

		setBorder(javax.swing.BorderFactory.createEtchedBorder());
		setOpaque(false);
		setLayout(new java.awt.GridBagLayout());

		jPanel1.setOpaque(false);
		jPanel1.setLayout(new java.awt.GridBagLayout());

		jPanel7.setLayout(new java.awt.BorderLayout());

		headLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/systemheads/default60.png"))); // NOI18N
		jPanel7.add(headLbl, java.awt.BorderLayout.CENTER);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = 2;
		jPanel1.add(jPanel7, gridBagConstraints);

		nameLbl.setText("Jack");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
		jPanel1.add(nameLbl, gridBagConstraints);

		jPanel2.setOpaque(false);
		jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		videoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/camera.png"))); // NOI18N
		videoBtn.setToolTipText(VovoStringUtil
				.getUIString("chatwindow.videobtn.tip"));
		videoBtn.setContentAreaFilled(false);
		videoBtn.setPreferredSize(new java.awt.Dimension(32, 32));
		videoBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				videoBtnActionPerformed(evt);
			}
		});
		jPanel2.add(videoBtn);

		soundBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/microphone.png"))); // NOI18N
		soundBtn.setToolTipText(VovoStringUtil
				.getUIString("chatwindow.audiobtn.tip"));
		soundBtn.setContentAreaFilled(false);
		soundBtn.setPreferredSize(new java.awt.Dimension(32, 32));
		soundBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				soundBtnActionPerformed(evt);
			}
		});
		jPanel2.add(soundBtn);

		sendFileBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/sendfile.png"))); // NOI18N
		sendFileBtn.setToolTipText(VovoStringUtil
				.getUIString("chatwindow.uploadfilebtn.tip"));
		sendFileBtn.setContentAreaFilled(false);
		sendFileBtn.setPreferredSize(new java.awt.Dimension(32, 32));
		sendFileBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				sendFileBtnActionPerformed(evt);
			}
		});
		jPanel2.add(sendFileBtn);

		showMsgBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/show_msg.png"))); // NOI18N
		showMsgBtn.setToolTipText(VovoStringUtil
				.getUIString("chatwindow.chatrecordbtn.tip"));
		showMsgBtn.setContentAreaFilled(false);
		showMsgBtn.setPreferredSize(new java.awt.Dimension(32, 32));
		showMsgBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				showMsgBtnActionPerformed(evt);
			}
		});
		jPanel2.add(showMsgBtn);

		jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/group_add.png"))); // NOI18N
		jButton1.setToolTipText("\u521b\u5efa\u7fa4\u7ec4");
		jButton1.setContentAreaFilled(false);
		jButton1.setPreferredSize(new java.awt.Dimension(32, 32));
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton1);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
		jPanel1.add(jPanel2, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		add(jPanel1, gridBagConstraints);

		jPanel4.setLayout(new java.awt.GridBagLayout());

		jPanel5.setMinimumSize(new java.awt.Dimension(350, 10));
		jPanel5.setPreferredSize(new java.awt.Dimension(350, 25));
		jPanel5.setLayout(new java.awt.GridBagLayout());

		showPane.setEditable(false);
		jScrollPane1.setViewportView(showPane);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		jPanel5.add(jScrollPane1, gridBagConstraints);

		jPanel3.setLayout(new java.awt.BorderLayout());
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		jPanel5.add(jPanel3, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		jPanel4.add(jPanel5, gridBagConstraints);

		jPanel6.setMinimumSize(new java.awt.Dimension(220, 5));
		jPanel6.setPreferredSize(new java.awt.Dimension(220, 10));
		jPanel6.setLayout(new java.awt.BorderLayout());
		jPanel6.add(operateTab, java.awt.BorderLayout.CENTER);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		jPanel4.add(jPanel6, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		add(jPanel4, gridBagConstraints);
	}// </editor-fold>
	//GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		GroupChatCreateDialog groupChatCreateDialog = Vovo.getViewManager()
				.getView(Constants.ViewKey.groupChatCreateDialog.toString());
		if (groupChatCreateDialog == null || !groupChatCreateDialog.isVisible()) {
			try {
				groupChatCreateDialog = Vovo.getViewManager().createView(
						GroupChatCreateDialog.class,
						new Class[] { java.awt.Frame.class, boolean.class },
						new Object[] { null, true },
						Constants.ViewKey.groupChatCreateDialog.toString());
				groupChatCreateDialog.hiddenTopicAndDesc();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			Vovo.getViewManager()
					.setWindowCenterLocation(groupChatCreateDialog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		groupChatCreateDialog.setVisible(true);
	}

	private void videoBtnActionPerformed(java.awt.event.ActionEvent evt) {
		Vovo.exeC("phone", "makeCallInvite", bean.getLccAccount(), this, false);
	}

	private void soundBtnActionPerformed(java.awt.event.ActionEvent evt) {
		Vovo.exeC("phone", "makeCallInvite", bean.getLccAccount(), this, true);
	}

	private void showMsgBtnActionPerformed(java.awt.event.ActionEvent evt) {
		if (existTab(msgRecordPanel)) {
			removeOperateTab(msgRecordPanel);
		} else {
			addOperateTab(Vovo.getViewManager().getUIString(
					"operateTab.msgrecord"), msgRecordPanel);
			Vovo.exeC("chat", "getLatestRecord", bean.getId(), msgRecordPanel);
		}
	}

	private void sendFileBtnActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int ret = fileChooser.showOpenDialog(null);
		if (ret == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			doSendFile(selectedFile);
		}
	}

	private void doSendFile(File file) {
		String streamID = (String) Vovo.exeC("chat", "sendFile", file, bean
				.getLccAccount());
		transferFileList.addSendFileItem(file, streamID);
		addOperateTab(Vovo.getViewManager().getUIString(
				"operateTab.filetransfer"), transferFileList);
	}

	private void updateJpanel6() {
		if (this.operateTab.getTabCount() == 0) {
			this.jPanel6.setVisible(false);
		} else {
			this.jPanel6.setVisible(true);
		}

	}

	private void addOperateTab(String title, JPanel panel) {
		boolean exist = existTab(panel);
		if (!exist) {
			operateTab.addTab(title, panel);
		}
		updateJpanel6();
	}

	private void removeOperateTab(JPanel panel) {
		operateTab.remove(panel);
		updateJpanel6();
	}

	private boolean existTab(JPanel panel) {
		Component[] components = operateTab.getComponents();
		boolean exist = false;
		for (Component component : components) {
			if (component == panel) {
				exist = true;
				break;
			}
		}
		return exist;
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JLabel headLbl;
	private javax.swing.JButton jButton1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel nameLbl;
	private javax.swing.JTabbedPane operateTab;
	private javax.swing.JButton sendFileBtn;
	private javax.swing.JButton showMsgBtn;
	private com.lorent.vovo.ui.MyTextPane showPane;
	private javax.swing.JButton soundBtn;
	private javax.swing.JButton videoBtn;
	// End of variables declaration//GEN-END:variables

	private InputArea inputArea;
	private MemberBean bean;
	private TransferFileList transferFileList;
	private MsgRecordPanel msgRecordPanel;
	private SoundCallPanel soundCallPanel;
	private VideoCallPanel videoCallPanel;

	public void setInfo(MemberBean bean) {
		this.bean = bean;
		this.nameLbl.setText(bean.getRealName());
		this.jPanel7.removeAll();
		this.jPanel7.add(ImageUtil.generateHeadImagePanel(bean, 60, 60), java.awt.BorderLayout.CENTER);
//		this.jPanel7.add(new HeadImagePanel(bean.getDefaultImg(), bean
//				.getState(), 60, 60), java.awt.BorderLayout.CENTER);
		//		this.msgRecordPanel.setMemberId(bean.getId());
	}

	public void insertFriendMsg(String msg, FontStyle fontStyle, Date sendDate,
			Map<String, String> imgs) throws Exception {
		showPane.insertMsgAndTitle(bean.getRealName(), FontUtil
				.getFriendNameStyle(), sendDate, FontUtil.getFriendDateStyle(),
				msg, (Map) DataUtil.getValue(DataKey.SYSTEM_EMOTION),
				fontStyle, imgs);
	}

	public void insertMyMsg(String msg, FontStyle fontStyle, Date sendDate,
			Map<String, String> imgs) throws Exception {
		showPane.insertMsgAndTitle(DataUtil.getMyInfo().getRealName(), FontUtil
				.getMyNameStyle(), sendDate, FontUtil.getMyDateStyle(), msg,
				(Map) DataUtil.getValue(DataKey.SYSTEM_EMOTION), fontStyle,
				imgs);
	}

	public void addRecvFileItem(String fileName, long fileSize, String streamID) {
		transferFileList.addRecvFileItem(fileName, fileSize, streamID);
		addOperateTab(Vovo.getViewManager().getUIString(
				"operateTab.filetransfer"), transferFileList);
	}

	public void otherCancelSendFile(String streamID, Date date, String text)
			throws Exception {
		transferFileList.removeItem(streamID);
		showPane.insertNotice(MyTextPane.NOTICE_TYPE_ERROR, date, text,
				FontUtil.getNoticeStyle());
	}

	public void updateInfo(String streamID, int percent) {
		transferFileList.updateInfo(streamID, percent);
	}

	public void sendFileComplete(String streamID, String fileName)
			throws Exception {
		boolean isSend = transferFileList.isSend(streamID);
		long time = Vovo.getLcmUtil().getSystemTime();
		if (isSend) {
			String text = Vovo.getViewManager().getUIString("sendFile.success")
					+ "\"" + fileName + "\"";
			showPane.insertNotice(MyTextPane.NOTICE_TYPE_OK, new Date(time),
					text, FontUtil.getNoticeStyle());
		} else {
			String text = StringUtil.getFormatString(Vovo.getViewManager()
					.getUIString("receiveFile.success"), fileName,
					transferFileList.getSavePath(streamID));
			showPane.insertNotice(MyTextPane.NOTICE_TYPE_OK, new Date(time),
					text, FontUtil.getNoticeStyle());
		}
		transferFileList.removeItem(streamID);
	}

	public void showSoundCall() {
		soundCallPanel.showPanel(SoundCallPanel.TYPE_CALLER);
		addOperateTab(Vovo.getViewManager().getUIString("phone.sound"),
				soundCallPanel);
		this.soundBtn.setEnabled(false);
		this.videoBtn.setEnabled(false);
	}

	public void showReceiveSoundCall() {
		soundCallPanel.showPanel(SoundCallPanel.TYPE_CALLEE);
		addOperateTab(Vovo.getViewManager().getUIString("phone.sound"),
				soundCallPanel);
		this.soundBtn.setEnabled(false);
		this.videoBtn.setEnabled(false);
	}

	public void removeSoundCallPanel(String msg, boolean flag) throws Exception {
		removeOperateTab(soundCallPanel);
		String time = soundCallPanel.stopCountTime();
		if (flag) {
			msg = msg + time;
		}
		this.showPane.insertNotice(MyTextPane.NOTICE_TYPE_OK, new Date(), msg,
				FontUtil.getNoticeStyle());
		this.soundBtn.setEnabled(true);
		this.videoBtn.setEnabled(true);
	}

	public void showSoundConnectCall() {
		soundCallPanel.showPanel(SoundCallPanel.TYPE_INCALL);
	}

	public Panel showVideoCall() {
		videoCallPanel.showPanel(SoundCallPanel.TYPE_CALLER);
		addOperateTab(Vovo.getViewManager().getUIString("phone.video"),
				videoCallPanel);
		this.soundBtn.setEnabled(false);
		this.videoBtn.setEnabled(false);
		return videoCallPanel.getVideoPanel();
	}

	public Panel showReceiveVideoCall() {
		videoCallPanel.showPanel(SoundCallPanel.TYPE_CALLEE);
		addOperateTab(Vovo.getViewManager().getUIString("phone.video"),
				videoCallPanel);
		this.soundBtn.setEnabled(false);
		this.videoBtn.setEnabled(false);
		return videoCallPanel.getVideoPanel();
	}

	public void removeVideoCallPanel(String msg, boolean flag) throws Exception {
		removeOperateTab(videoCallPanel);
		String time = videoCallPanel.stopCountTime();
		if (flag) {
			msg = msg + time;
		}
		this.showPane.insertNotice(MyTextPane.NOTICE_TYPE_OK, new Date(), msg,
				FontUtil.getNoticeStyle());
		this.soundBtn.setEnabled(true);
		this.videoBtn.setEnabled(true);
	}

	public void showVideoConnectCall() {
		videoCallPanel.showPanel(SoundCallPanel.TYPE_INCALL);
	}

}