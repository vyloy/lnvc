package com.lorent.vovo.controller;

import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lorent.common.app.AppContext;
import com.lorent.common.controller.BaseController;
import com.lorent.common.tree.MemberBean;
import com.lorent.util.LCCUtil;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.ui.CallHistoryNodePanel;
import com.lorent.vovo.ui.FriendChatPanel;
import com.lorent.vovo.ui.MessageFrame;
import com.lorent.vovo.ui.PhoneFrame;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.DataUtil;
import com.lorent.vovo.util.MyPlayer;
import com.lorent.vovo.util.CallHistory;
import com.lorent.vovo.util.CallHistory.CallHistoryItem;
import com.lorent.vovo.util.CallHistory.CallInfo;
import com.lorent.vovo.util.TreeUtil;

public abstract class AbstractPhoneController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(AbstractPhoneController.class);
	protected volatile String callingLccNo;
	protected volatile PhoneFrame frame;
	protected volatile boolean preview;
	protected volatile boolean incoming;

	public void registerOK() {

	}

	public void registerFail() {
		// showErrorDialog(getUIString("common.error"),
		// getUIString("phone.registerFail"));
	}

	public void callIncoming(String lccno, int type) throws Exception {
		String lccnostr = getLccNoFromSipStr(lccno);
		final MemberBean memberBean = TreeUtil.getMemberBeanByLccno(lccnostr);
		if (memberBean != null) {
			MessageFrame frame = (MessageFrame) context.getExecuteManager()
					.executeController("chat", "getMessageFrame");
			FriendChatPanel panel = (FriendChatPanel) context
					.getExecuteManager().executeController("chat",
							"getFriendChatPanel", memberBean);
			if (LCCUtil.CALL_TYPE_SOUND == type) {
				panel.showReceiveSoundCall();
			} else if (LCCUtil.CALL_TYPE_SOUND_AND_VIDEO == type
					|| LCCUtil.CALL_TYPE_VIDEO == type) {
				Panel videoPanel = panel.showReceiveVideoCall();
				LCCUtil.getInstance().setVideo(true, videoPanel);
				Thread.sleep(200);
				LCCUtil.getInstance().setPreview(true);
			}
			frame.setVisible(true);
		} else {
			callingLccNo = lccnostr;
			if (LCCUtil.CALL_TYPE_SOUND_AND_VIDEO == type
					|| LCCUtil.CALL_TYPE_VIDEO == type) {
				Panel videoPanel = frame.getScreenPanel();
				LCCUtil.getInstance().setVideo(true, videoPanel);
				Thread.sleep(200);
				LCCUtil.getInstance().setPreview(true);
				preview = true;
			}

			frame.getHandupButton().setEnabled(true);
			frame.getCallButton().setEnabled(false);
			frame.getAcceptButton().setEnabled(true);
			frame.getCameraOpenToggleButton().setEnabled(false);
			frame.getCameraCloseToggleButton().setEnabled(false);
			frame.getMainRightTabbedPane().setSelectedIndex(0);

			String statusInfo = "有电话呼入，呼入号码：" + lccnostr;
			Image image = Toolkit
					.getDefaultToolkit()
					.getImage(
							getClass()
									.getResource(
											"/com/lorent/vovo/resource/images/dial/call_comein.gif"));
			setPhoneFrameState(statusInfo, image);
			frame.setVisible(true);
			incoming=true;
		}
		MyPlayer.play(MyPlayer.TYPE_RING_IN);
	}

	private void setPhoneFrameState(String statusInfo, Image image) {
		JLabel statusInfoLabel = frame.getStatusInfoLabel();
		JLabel statusIconLabel = frame.getStatusIconLabel();
		statusInfoLabel.setText(statusInfo);
		statusIconLabel.setIcon(new ImageIcon(image));
	}

	public void callHangup(String lccno, int type, boolean isConnect)
			throws Exception {
		String lccnostr = getLccNoFromSipStr(lccno);
		final MemberBean memberBean = TreeUtil.getMemberBeanByLccno(lccnostr);
		if (memberBean != null) {
			FriendChatPanel panel = (FriendChatPanel) context
					.getExecuteManager().executeController("chat",
							"getFriendChatPanel", memberBean);
			if (LCCUtil.CALL_TYPE_SOUND == type) {
				if (isConnect) {
					String msg = getUIString("phone.otherHangupSoundCall");
					panel.removeSoundCallPanel(msg, true);
				} else {
					String msg = getUIString("phone.otherCancelSoundCall");
					panel.removeSoundCallPanel(msg, false);
				}
			} else if (LCCUtil.CALL_TYPE_SOUND_AND_VIDEO == type
					|| LCCUtil.CALL_TYPE_VIDEO == type) {
				if (isConnect) {
					String msg = getUIString("phone.otherHangupVideoCall");
					panel.removeVideoCallPanel(msg, true);
				} else {
					String msg = getUIString("phone.otherCancelVideoCall");
					panel.removeVideoCallPanel(msg, false);
				}
				LCCUtil.getInstance().setPreview(false);
			}
			MessageFrame frame = (MessageFrame) context.getExecuteManager()
					.executeController("chat", "getMessageFrame");
			frame.setVisible(true);
		} else {
			if (LCCUtil.CALL_TYPE_SOUND_AND_VIDEO == type
					|| LCCUtil.CALL_TYPE_VIDEO == type) {
				LCCUtil.getInstance().setPreview(false);
				preview = false;
			}
			frame.getHandupButton().setEnabled(false);
			frame.getCallButton().setEnabled(true);
			frame.getAcceptButton().setEnabled(false);
			frame.getCameraOpenToggleButton().setEnabled(true);
			frame.getCameraCloseToggleButton().setEnabled(true);
			String statusInfo = "挂起";
			Image image = Toolkit
					.getDefaultToolkit()
					.getImage(
							getClass()
									.getResource(
											"/com/lorent/vovo/resource/images/dial/call-stop_1.png"));
			setPhoneFrameState(statusInfo, image);
			if(incoming){
				incoming=false;
				addPhoneRecord(new CallHistoryItem(CallInfo.MISSED_CALL.mask, lccnostr, new Date().getTime()));
			}
			frame.setVisible(true);
		}
		MyPlayer.stop();
	}

	public void callConnected(String lccno, int type) {
		String lccnostr = getLccNoFromSipStr(lccno);
		final MemberBean memberBean = TreeUtil.getMemberBeanByLccno(lccnostr);
		if (memberBean != null) {
			FriendChatPanel panel = (FriendChatPanel) context
					.getExecuteManager().executeController("chat",
							"getFriendChatPanel", memberBean);
			if (LCCUtil.CALL_TYPE_SOUND == type) {
				panel.showSoundConnectCall();
			} else if (LCCUtil.CALL_TYPE_SOUND_AND_VIDEO == type
					|| LCCUtil.CALL_TYPE_VIDEO == type) {
				panel.showVideoConnectCall();
				LCCUtil.getInstance().setPreview(false);
			}
			MessageFrame frame = (MessageFrame) context.getExecuteManager()
					.executeController("chat", "getMessageFrame");
			frame.setVisible(true);

		} else {
			if (LCCUtil.CALL_TYPE_SOUND_AND_VIDEO == type
					|| LCCUtil.CALL_TYPE_VIDEO == type) {
				LCCUtil.getInstance().setPreview(false);
				preview = false;
			}
			frame.getHandupButton().setEnabled(true);
			frame.getCallButton().setEnabled(false);
			frame.getAcceptButton().setEnabled(false);
			String statusInfo = "通话中";
			Image image = Toolkit
					.getDefaultToolkit()
					.getImage(
							getClass()
									.getResource(
											"/com/lorent/vovo/resource/images/dial/call-start_1.png"));
			setPhoneFrameState(statusInfo, image);
			if(incoming){
				incoming=false;
				addPhoneRecord(new CallHistoryItem(CallInfo.CALL_IN.mask, lccnostr, new Date().getTime()));
			}
			frame.setVisible(true);
		}
		MyPlayer.stop();
	}

	public void callError(String lccno, int type) throws Exception {
		String lccnostr = getLccNoFromSipStr(lccno);
		final MemberBean memberBean = TreeUtil.getMemberBeanByLccno(lccnostr);
		if (memberBean != null) {
			FriendChatPanel panel = (FriendChatPanel) context
					.getExecuteManager().executeController("chat",
							"getFriendChatPanel", memberBean);
			if (LCCUtil.CALL_TYPE_SOUND == type) {
				String msg = getUIString("phone.callerror");
				panel.removeSoundCallPanel(msg, false);
			} else if (LCCUtil.CALL_TYPE_SOUND_AND_VIDEO == type
					|| LCCUtil.CALL_TYPE_VIDEO == type) {
				String msg = getUIString("phone.callerror");
				panel.removeVideoCallPanel(msg, false);
				LCCUtil.getInstance().setPreview(false);
			}
			MessageFrame frame = (MessageFrame) context.getExecuteManager()
					.executeController("chat", "getMessageFrame");
			frame.setVisible(true);

		} else {
			if (LCCUtil.CALL_TYPE_SOUND_AND_VIDEO == type
					|| LCCUtil.CALL_TYPE_VIDEO == type) {
				LCCUtil.getInstance().setPreview(false);
				preview = false;
			}
			frame.getHandupButton().setEnabled(false);
			frame.getCallButton().setEnabled(true);
			frame.getCameraOpenToggleButton().setEnabled(true);
			frame.getCameraCloseToggleButton().setEnabled(true);
			String statusInfo = "请求失败";
			Image image = Toolkit
					.getDefaultToolkit()
					.getImage(
							getClass()
									.getResource(
											"/com/lorent/vovo/resource/images/dial/call-stop_1.png"));
			setPhoneFrameState(statusInfo, image);
			incoming=false;
		}
		MyPlayer.stop();
	}

	public void makeCallInviteP2P(String sip_lccnostr,String lccno, FriendChatPanel panel,
			boolean soundOnly) throws Exception{
		makeCallInviteDetail(sip_lccnostr,lccno,panel,soundOnly);
	}
	
	private void makeCallInviteDetail(String sip_lccnostr,String lccno, FriendChatPanel panel,
			boolean soundOnly) throws Exception{
		
		if (panel != null) {
			if (soundOnly) {
				panel.showSoundCall();
				LCCUtil.getInstance().setVideo(false, null);
				Thread.sleep(200);
				LCCUtil.getInstance().doCall(sip_lccnostr, LCCUtil.CALL_TYPE_SOUND);
			} else {
				Panel videoPanel = panel.showVideoCall();
				LCCUtil.getInstance().setVideo(true, videoPanel);
				LCCUtil.getInstance().setPreview(true);
				Thread.sleep(200);
				LCCUtil.getInstance().doCall(sip_lccnostr,LCCUtil.CALL_TYPE_SOUND_AND_VIDEO);
			}

		} else {
			if(DataUtil.getUserName().equals(lccno)){
				showMessageDialog(getUIString("info.tip"),
						"不能呼叫自己");
				return;
			}
			final MemberBean memberBean = TreeUtil.getMemberBeanByLccno(lccno);
			if(memberBean!=null){
				MessageFrame _frame = (MessageFrame) context.getExecuteManager()
						.executeController("chat", "getMessageFrame");
				FriendChatPanel fcp = (FriendChatPanel) context
						.getExecuteManager().executeController("chat",
								"getFriendChatPanel", memberBean);
//				makeCallInvite(sip_lccnostr, fcp, soundOnly);////???????????????
				makeCallInviteDetail(sip_lccnostr,lccno,panel,soundOnly);
				_frame.setVisible(true);
				frame.setVisible(false);
				return;
			}
			frame.getHandupButton().setEnabled(true);
			frame.getCallButton().setEnabled(false);
			frame.getCameraOpenToggleButton().setEnabled(false);
			frame.getCameraCloseToggleButton().setEnabled(false);
			String statusInfo = "呼出";
			Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/lorent/vovo/resource/images/dial/call-start_1.png"));
			setPhoneFrameState(statusInfo, image);
			callingLccNo = lccno;
			if (soundOnly) {
				LCCUtil.getInstance().setVideo(false, null);
				Thread.sleep(200);
				LCCUtil.getInstance().doCall(sip_lccnostr, LCCUtil.CALL_TYPE_SOUND);
			} else {
				Panel videoPanel = frame.getScreenPanel();
				LCCUtil.getInstance().setVideo(true, videoPanel);
				LCCUtil.getInstance().setPreview(true);
				Thread.sleep(200);
				LCCUtil.getInstance().doCall(sip_lccnostr,LCCUtil.CALL_TYPE_SOUND_AND_VIDEO);
				preview = true;
			}
			
			addPhoneRecord(new CallHistoryItem(CallInfo.CALL_OUT.mask, lccno, new Date().getTime()));
		}
		MyPlayer.play(MyPlayer.TYPE_RING_OUT);
	}
	
	public void makeCallInvite(String lccno, FriendChatPanel panel,
			boolean soundOnly) throws Exception {
		if (!LCCUtil.canCall()) {
			showMessageDialog(getUIString("info.tip"),
					getUIString("phone.alreadyHadOneCall"));
			return;
		}
		makeCallInviteDetail(lccno,lccno,panel,soundOnly);
	}

	public void addPhoneRecord(CallHistoryItem item){
		CallHistoryNodePanel callHistoryNodePanel = new CallHistoryNodePanel();
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=tempDate.format(new Date(item.getDate()));
        callHistoryNodePanel.setInfo(item.getInfo(), item.getPhone(),date,0);
        JList callHistoryList = frame.getCallHistoryList();
        DefaultListModel model = (DefaultListModel) callHistoryList.getModel();
        model.add(0,callHistoryNodePanel);
        try {
			CallHistory.getInstance().put(item);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	public void cancelCallInvite(String lccno, FriendChatPanel panel,
			boolean soundOnly) throws Exception {
		LCCUtil.getInstance().doHangup(lccno);
		if (soundOnly) {
			String msg = getUIString("phone.youCancelSoundCall");
			panel.removeSoundCallPanel(msg, false);
		} else {
			LCCUtil.getInstance().setPreview(false);
			String msg = getUIString("phone.youCancelVideoCall");
			panel.removeVideoCallPanel(msg, false);
		}
		MyPlayer.stop();
	}

	public void acceptCallInvite(String lccno, FriendChatPanel panel) {
		LCCUtil.getInstance().doAnswer(lccno);
		MyPlayer.stop();
	}

	public void acceptCallInviteByPhone() {
		String lccno = callingLccNo;
		if (lccno == null || lccno.isEmpty())
			return;
		acceptCallInvite(lccno, null);
	}

	public void rejectCallInvite(String lccno, FriendChatPanel panel,
			boolean soundOnly) throws Exception {
		LCCUtil.getInstance().doHangup(lccno);
		if (soundOnly) {
			String msg = getUIString("phone.youRejectSoundCall");
			panel.removeSoundCallPanel(msg, false);
		} else {
			LCCUtil.getInstance().setPreview(false);
			String msg = getUIString("phone.youRejectVideoCall");
			panel.removeVideoCallPanel(msg, false);
		}
		MyPlayer.stop();
	}

	public void hangupCall(String lccno, FriendChatPanel panel,
			boolean soundOnly) throws Exception {
		LCCUtil.getInstance().doHangup(lccno);
		if (soundOnly) {
			String msg = getUIString("phone.youHangupSoundCall");
			panel.removeSoundCallPanel(msg, true);
		} else {
			LCCUtil.getInstance().setPreview(false);
			String msg = getUIString("phone.youHangupVideoCall");
			panel.removeVideoCallPanel(msg, true);
		}
		MyPlayer.stop();
	}

	public void hangupCallByPhone() {
		String lccno = callingLccNo;
		if (lccno == null || lccno.isEmpty())
			return;
		if (preview) {
			LCCUtil.getInstance().setPreview(false);
		}
		LCCUtil.getInstance().doHangup(lccno);
		frame.getHandupButton().setEnabled(false);
		frame.getCallButton().setEnabled(true);
		frame.getAcceptButton().setEnabled(false);
		frame.getCameraOpenToggleButton().setEnabled(true);
		frame.getCameraCloseToggleButton().setEnabled(true);
		String statusInfo = "挂起";
		Image image = Toolkit
				.getDefaultToolkit()
				.getImage(
						getClass()
								.getResource(
										"/com/lorent/vovo/resource/images/dial/call-stop_1.png"));
		setPhoneFrameState(statusInfo, image);
		MyPlayer.stop();
	}

	public int setPlaybackVolume(int level) {
		String lccno = callingLccNo;
		if (lccno == null || lccno.isEmpty())
			return 0;
		return LCCUtil.getInstance().setPlaybackVolume(callingLccNo, level);
	}

	@Override
	public void setContext(AppContext context) {
		super.setContext(context);
		frame = context.getViewManager().getView(
				Constants.ViewKey.PhoneFrame.toString());
	}

	protected String getLccNoFromSipStr(String lccno){
		String lccnostr = lccno;
		if (lccno.indexOf("sip:") != -1 && lccno.indexOf("@") != -1) {
			int begin = lccno.indexOf("sip:")+4;
			int end = lccno.indexOf("@");
			lccnostr = lccno.substring(begin, end);
		}
		return lccnostr;
	}
	
	protected String getSipStrForP2P(String lccno){
		int intProperty = Vovo.getConfigManager().getIntProperty(Constants.ConfigKey.localcsport.toString(), Constants.CONFIG_LOCAL_CS_PORT);
		MemberBean bean = TreeUtil.getMemberBeanByLccno(lccno);
		String sip_lccnostr = "sip:"+bean.getLccAccount()+"@"+bean.getIp()+":"+intProperty;
		if (bean.getIp() == null || bean.getIp().equals("")) {
			sip_lccnostr = lccno;
		}
		return sip_lccnostr;
	}
}