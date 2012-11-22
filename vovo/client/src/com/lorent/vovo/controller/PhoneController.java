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
							int intProperty = Vovo.getConfigManager().getIntProperty(Constants.ConfigKey.localcsport.toString(), Constants.CONFIG_LOCAL_CS_PORT);
							MemberBean bean = TreeUtil.getMemberBeanByLccno(lccno);
							String sip_lccnostr = "sip:"+bean.getLccAccount()+"@"+bean.getIp()+":"+intProperty;
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
		// TODO Auto-generated method stub
		super.cancelCallInvite(lccno, panel, soundOnly);
	}

	@Override
	public void acceptCallInvite(String lccno, FriendChatPanel panel) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		super.rejectCallInvite(lccno, panel, soundOnly);
	}

	@Override
	public void hangupCall(String lccno, FriendChatPanel panel,
			boolean soundOnly) throws Exception {
		// TODO Auto-generated method stub
		super.hangupCall(lccno, panel, soundOnly);
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
