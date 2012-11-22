package com.lorent.vovo.controller;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lorent.common.app.AppContext;
import com.lorent.common.tree.MemberBean;
import com.lorent.common.util.OpenfireUtil;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.ui.FriendChatPanel;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.TreeUtil;




public class PhoneController extends AbstractPhoneController {

	private static final Logger logger = LoggerFactory.getLogger(PhoneController.class);
	
	@Override
	public void registerOK() {
		super.registerOK();
	}

	@Override
	public void registerFail() {
		super.registerFail();
	}

	@Override
	public void callIncoming(final String lccno, final int type){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					PhoneController.super.callIncoming(lccno, type);
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
			}
		});
	}

	@Override
	public void callHangup(final String lccno, final int type, final boolean isConnect){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					PhoneController.super.callHangup(lccno, type, isConnect);
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
			}
		});
	}

	@Override
	public void callConnected(final String lccno, final int type) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					PhoneController.super.callConnected(lccno, type);
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
			}
		});
		
	}

	@Override
	public void callError(final String lccno, final int type){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					PhoneController.super.callError(lccno, type);
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
			}
		});
	}

	@Override
	public void makeCallInvite(final String lccno, final FriendChatPanel panel,
			final boolean soundOnly) throws Exception {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					//判断是否断线
					synchronized (OpenfireUtil.getInstance().isLogined) {
						if (OpenfireUtil.getInstance().isLogined) {
							PhoneController.super.makeCallInvite(lccno, panel, soundOnly);
						}
						else{
							String sip_lccnostr = getSipStrForP2P(lccno);
							PhoneController.super.makeCallInviteP2P(sip_lccnostr,lccno, panel, soundOnly);
						}
						
					}
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
			}
		});
	}

	@Override
	public void cancelCallInvite(String lccno, FriendChatPanel panel,
			boolean soundOnly) throws Exception {
		synchronized (OpenfireUtil.getInstance().isLogined) {
			if (OpenfireUtil.getInstance().isLogined) {
				super.cancelCallInvite(lccno, panel, soundOnly);
			}
			else{
				super.cancelCallInvite(getSipStrForP2P(lccno), panel, soundOnly);
			}
		}
		
	}

	@Override
	public void acceptCallInvite(String lccno, FriendChatPanel panel) {
//		synchronized (OpenfireUtil.getInstance().isLogined) {
//			if (OpenfireUtil.getInstance().isLogined) {
//				super.acceptCallInvite(lccno, panel);
//			}
//			else{
//				super.acceptCallInvite(lccno, panel);
//			}
//		}
		super.acceptCallInvite(lccno, panel);
	}

	@Override
	public void acceptCallInviteByPhone() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					PhoneController.super.acceptCallInviteByPhone();
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
			}
		});
	}

	@Override
	public void rejectCallInvite(String lccno, FriendChatPanel panel,
			boolean soundOnly) throws Exception {
//		synchronized (OpenfireUtil.getInstance().isLogined) {
//			if (OpenfireUtil.getInstance().isLogined) {
//				super.rejectCallInvite(lccno, panel, soundOnly);
//			}
//			else{
//				super.rejectCallInvite(getSipStrForP2P(lccno), panel, soundOnly);
//			}
//		}
		super.rejectCallInvite(lccno, panel, soundOnly);
	}

	@Override
	public void hangupCall(String lccno, FriendChatPanel panel,
			boolean soundOnly) throws Exception {
		synchronized (OpenfireUtil.getInstance().isLogined) {
			if (OpenfireUtil.getInstance().isLogined) {
				super.hangupCall(lccno, panel, soundOnly);
			}
			else{
				super.hangupCall(getSipStrForP2P(lccno), panel, soundOnly);
				super.hangupCall(lccno, panel, soundOnly);
			}
		}
//		super.hangupCall(lccno, panel, soundOnly);
	}

	@Override
	public void hangupCallByPhone() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					PhoneController.super.hangupCallByPhone();
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
			}
		});
	}

	@Override
	public void setContext(AppContext context) {
		super.setContext(context);
	}
	
	
}
