/*
 * SoundCallPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import java.awt.CardLayout;

import org.apache.log4j.Logger;

import com.lorent.vovo.Vovo;
import com.lorent.vovo.util.CountTime;

/**
 *
 * @author  Jack
 */
public class SoundCallPanel extends javax.swing.JPanel {

	public static final String TYPE_CALLER = "caller";
	public static final String TYPE_CALLEE = "callee";
	public static final String TYPE_INCALL = "incall";
	private Logger log = Logger.getLogger(SoundCallPanel.class);

	/** Creates new form SoundCallPanel */
	public SoundCallPanel() {
		initComponents();
		calleeInfoLbl.setText(Vovo.getViewManager().getUIString(
				"phone.otherSoundInviting"));
		callerInfoLbl.setText(Vovo.getViewManager().getUIString(
				"phone.waitingOtherAcceptSoundInviting"));
		cancelCallInviteBtn.setText(Vovo.getViewManager().getUIString(
				"phone.cancel"));
		acceptCallInviteBtn.setText(Vovo.getViewManager().getUIString(
				"phone.accept"));
		rejectCallInviteBtn.setText(Vovo.getViewManager().getUIString(
				"phone.reject"));
		hangupBtn.setText(Vovo.getViewManager().getUIString("phone.hangup"));
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		jLabel1 = new javax.swing.JLabel();
		centerPanel = new javax.swing.JPanel();
		callerPanel = new javax.swing.JPanel();
		callerInfoLbl = new javax.swing.JLabel();
		cancelCallInviteBtn = new javax.swing.JButton();
		calleePanel = new javax.swing.JPanel();
		calleeInfoLbl = new javax.swing.JLabel();
		acceptCallInviteBtn = new javax.swing.JButton();
		rejectCallInviteBtn = new javax.swing.JButton();
		incallPanel = new javax.swing.JPanel();
		jPanel1 = new javax.swing.JPanel();
		callTimeLbl = new javax.swing.JLabel();
		soundControlBtn = new javax.swing.JButton();
		micControlBtn = new javax.swing.JButton();
		hangupBtn = new javax.swing.JButton();
		jLabel2 = new javax.swing.JLabel();

		setMaximumSize(new java.awt.Dimension(148, 2147483647));
		setOpaque(false);
		setLayout(new java.awt.GridBagLayout());

		jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/sound_bg.png"))); // NOI18N
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		add(jLabel1, gridBagConstraints);

		centerPanel.setOpaque(false);
		centerPanel.setLayout(new java.awt.CardLayout());

		callerPanel.setOpaque(false);
		callerPanel.setLayout(new java.awt.GridBagLayout());

		callerInfoLbl.setText("Calling...");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		callerPanel.add(callerInfoLbl, gridBagConstraints);

		cancelCallInviteBtn.setText("cancel");
		cancelCallInviteBtn
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						cancelCallInviteBtnActionPerformed(evt);
					}
				});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		callerPanel.add(cancelCallInviteBtn, gridBagConstraints);

		centerPanel.add(callerPanel, "caller");

		calleePanel.setOpaque(false);
		calleePanel.setLayout(new java.awt.GridBagLayout());

		calleeInfoLbl.setText("Incoming.......");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		calleePanel.add(calleeInfoLbl, gridBagConstraints);

		acceptCallInviteBtn.setText("accept");
		acceptCallInviteBtn
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						acceptCallInviteBtnActionPerformed(evt);
					}
				});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		calleePanel.add(acceptCallInviteBtn, gridBagConstraints);

		rejectCallInviteBtn.setText("reject");
		rejectCallInviteBtn
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						rejectCallInviteBtnActionPerformed(evt);
					}
				});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		calleePanel.add(rejectCallInviteBtn, gridBagConstraints);

		centerPanel.add(calleePanel, "callee");

		incallPanel.setOpaque(false);
		incallPanel.setLayout(new java.awt.GridBagLayout());

		jPanel1.setOpaque(false);

		callTimeLbl.setText("04:30");
		jPanel1.add(callTimeLbl);

		soundControlBtn.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("/com/lorent/vovo/resource/images/sound.png"))); // NOI18N
		soundControlBtn.setContentAreaFilled(false);
		soundControlBtn.setMaximumSize(new java.awt.Dimension(16, 16));
		soundControlBtn.setMinimumSize(new java.awt.Dimension(16, 16));
		soundControlBtn.setPreferredSize(new java.awt.Dimension(16, 16));
		jPanel1.add(soundControlBtn);

		micControlBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/mic.png"))); // NOI18N
		micControlBtn.setContentAreaFilled(false);
		micControlBtn.setMaximumSize(new java.awt.Dimension(16, 16));
		micControlBtn.setMinimumSize(new java.awt.Dimension(16, 16));
		micControlBtn.setPreferredSize(new java.awt.Dimension(16, 16));
		jPanel1.add(micControlBtn);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		incallPanel.add(jPanel1, gridBagConstraints);

		hangupBtn.setText("cancel");
		hangupBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				hangupBtnActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		incallPanel.add(hangupBtn, gridBagConstraints);

		centerPanel.add(incallPanel, "incall");

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		add(centerPanel, gridBagConstraints);

		jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/sound_bg.png"))); // NOI18N
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		add(jLabel2, gridBagConstraints);
	}// </editor-fold>
	//GEN-END:initComponents

	private void hangupBtnActionPerformed(java.awt.event.ActionEvent evt) {
		if (listener != null) {
			listener.hangupCall();
		}
	}

	private void rejectCallInviteBtnActionPerformed(
			java.awt.event.ActionEvent evt) {
		if (listener != null) {
			listener.rejectCallInvite();
		}
	}

	private void acceptCallInviteBtnActionPerformed(
			java.awt.event.ActionEvent evt) {
		if (listener != null) {
			listener.acceptCallInvite();
		}
	}

	private void cancelCallInviteBtnActionPerformed(
			java.awt.event.ActionEvent evt) {
		if (listener != null) {
			listener.cancelCallInvite();
		}
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton acceptCallInviteBtn;
	private javax.swing.JLabel callTimeLbl;
	private javax.swing.JLabel calleeInfoLbl;
	private javax.swing.JPanel calleePanel;
	private javax.swing.JLabel callerInfoLbl;
	private javax.swing.JPanel callerPanel;
	private javax.swing.JButton cancelCallInviteBtn;
	private javax.swing.JPanel centerPanel;
	private javax.swing.JButton hangupBtn;
	private javax.swing.JPanel incallPanel;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JButton micControlBtn;
	private javax.swing.JButton rejectCallInviteBtn;
	private javax.swing.JButton soundControlBtn;
	// End of variables declaration//GEN-END:variables

	private CountTime countTime;

	public void showPanel(String type) {
		CardLayout layout = (CardLayout) centerPanel.getLayout();
		layout.show(centerPanel, type);
		if (TYPE_INCALL.equals(type)) {
			startCountTime();
		}
	}

	private EventListener listener;

	public interface EventListener {
		void rejectCallInvite();

		void acceptCallInvite();

		void cancelCallInvite();

		void hangupCall();
	}

	public void setEventListener(EventListener listener) {
		this.listener = listener;
	}

	public void startCountTime() {
		countTime = new CountTime(callTimeLbl);
		countTime.start();
	}

	public String stopCountTime() {
		if(countTime != null){
			countTime.cancel();
			return this.callTimeLbl.getText();
		}else{
			return null;
		}
	}
}