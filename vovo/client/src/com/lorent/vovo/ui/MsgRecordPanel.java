/*
 * MsgRecordPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import java.util.List;
import java.util.Map;

import com.lorent.common.dto.VovoMyInfo;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.dto.ChatRecord;
import com.lorent.vovo.dto.FontStyle;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.DataUtil;
import com.lorent.vovo.util.FontUtil;
import com.lorent.vovo.util.Constants.DataKey;

/**
 *
 * @author  Jack
 */
public class MsgRecordPanel extends javax.swing.JPanel {

	/** Creates new form MsgRecordPanel */
	public MsgRecordPanel() {
		initComponents();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		msgPane = new com.lorent.vovo.ui.MyTextPane();
		jPanel1 = new javax.swing.JPanel();
		firstBtn = new javax.swing.JButton();
		preBtn = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		curPageLbl = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		totalPageLbl = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		nextBtn = new javax.swing.JButton();
		lastBtn = new javax.swing.JButton();

		setMinimumSize(new java.awt.Dimension(300, 58));
		setPreferredSize(new java.awt.Dimension(300, 52));
		setLayout(new java.awt.BorderLayout());

		msgPane.setEditable(false);
		jScrollPane1.setViewportView(msgPane);

		add(jScrollPane1, java.awt.BorderLayout.CENTER);

		jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

		firstBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/arrow-left-double-2.png"))); // NOI18N
		firstBtn.setContentAreaFilled(false);
		firstBtn.setPreferredSize(new java.awt.Dimension(16, 16));
		firstBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				firstBtnActionPerformed(evt);
			}
		});
		jPanel1.add(firstBtn);

		preBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/arrow-left-2.png"))); // NOI18N
		preBtn.setContentAreaFilled(false);
		preBtn.setPreferredSize(new java.awt.Dimension(16, 16));
		preBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				preBtnActionPerformed(evt);
			}
		});
		jPanel1.add(preBtn);

		jLabel1.setText("\u7b2c");
		jPanel1.add(jLabel1);

		curPageLbl.setText("4");
		jPanel1.add(curPageLbl);

		jLabel3.setText("\u9875");
		jPanel1.add(jLabel3);

		jLabel4.setText("/");
		jPanel1.add(jLabel4);

		totalPageLbl.setText("8");
		jPanel1.add(totalPageLbl);

		jLabel6.setText("\u9875");
		jPanel1.add(jLabel6);

		nextBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/arrow-right-2.png"))); // NOI18N
		nextBtn.setContentAreaFilled(false);
		nextBtn.setPreferredSize(new java.awt.Dimension(16, 16));
		nextBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				nextBtnActionPerformed(evt);
			}
		});
		jPanel1.add(nextBtn);

		lastBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/arrow-right-double-2.png"))); // NOI18N
		lastBtn.setContentAreaFilled(false);
		lastBtn.setPreferredSize(new java.awt.Dimension(16, 16));
		lastBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				lastBtnActionPerformed(evt);
			}
		});
		jPanel1.add(lastBtn);

		add(jPanel1, java.awt.BorderLayout.SOUTH);
	}// </editor-fold>
	//GEN-END:initComponents

	private void lastBtnActionPerformed(java.awt.event.ActionEvent evt) {
		curPage = totalPage;
		Vovo.exeC("chat", "changePage", curPage, sessionID, this);
		updateStatus();
	}

	private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {
		curPage++;
		Vovo.exeC("chat", "changePage", curPage, sessionID, this);
		updateStatus();
	}

	private void firstBtnActionPerformed(java.awt.event.ActionEvent evt) {
		curPage = 1;
		Vovo.exeC("chat", "changePage", curPage, sessionID, this);
		updateStatus();
	}

	private void preBtnActionPerformed(java.awt.event.ActionEvent evt) {
		curPage--;
		Vovo.exeC("chat", "changePage", curPage, sessionID, this);
		updateStatus();
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JLabel curPageLbl;
	private javax.swing.JButton firstBtn;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JButton lastBtn;
	private com.lorent.vovo.ui.MyTextPane msgPane;
	private javax.swing.JButton nextBtn;
	private javax.swing.JButton preBtn;
	private javax.swing.JLabel totalPageLbl;
	// End of variables declaration//GEN-END:variables

	private int curPage;
	private int totalPage;
//	private int memberId;
//
//	public void setMemberId(int memberId) {
//		this.memberId = memberId;
//	}
	private String sessionID;
	
	

	public void initInfo(int totalPage, int curPage, List<ChatRecord> records, String sessionID)
			throws Exception {
		this.sessionID = sessionID;
		this.totalPage = totalPage;
		this.curPage = curPage;
		changeData(records);
		updateStatus();
	}

	public void changeData(List<ChatRecord> records) throws Exception {
		VovoMyInfo info = DataUtil.getMyInfo();
		int size = records.size();
		msgPane.setText("");
		for (int i = 0; i < size; i++) {
			ChatRecord record = records.get(i);
			if (record.getSenderId() == info.getId()) {//me
				msgPane.insertMsgAndTitle(record.getSenderName(), FontUtil
						.getMyNameStyle(), record.getDate(), FontUtil
						.getMyDateStyle(), record.getMsg(), (Map) DataUtil
						.getValue(DataKey.SYSTEM_EMOTION), record.getStyle(),null);
			} else {
				msgPane.insertMsgAndTitle(record.getSenderName(), FontUtil
						.getFriendNameStyle(), record.getDate(), FontUtil
						.getFriendDateStyle(), record.getMsg(), (Map) DataUtil
						.getValue(DataKey.SYSTEM_EMOTION), record.getStyle(),null);
			}
		}
	}

	private void updateStatus() {
		this.totalPageLbl.setText(totalPage + "");
		this.curPageLbl.setText(curPage + "");
		if (totalPage == 1) {
			this.lastBtn.setVisible(false);
			this.nextBtn.setVisible(false);
			this.preBtn.setVisible(false);
			this.firstBtn.setVisible(false);
		} else if (curPage == totalPage) {
			this.lastBtn.setVisible(false);
			this.nextBtn.setVisible(false);
			this.preBtn.setVisible(true);
			this.firstBtn.setVisible(true);
		} else if (curPage == 1) {
			this.lastBtn.setVisible(true);
			this.nextBtn.setVisible(true);
			this.preBtn.setVisible(false);
			this.firstBtn.setVisible(false);
		} else {
			this.lastBtn.setVisible(true);
			this.nextBtn.setVisible(true);
			this.preBtn.setVisible(true);
			this.firstBtn.setVisible(true);
		}
	}

}