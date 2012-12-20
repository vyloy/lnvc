package com.lorent.vovo.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.filetransfer.FileTransferRequest;

import com.lorent.common.controller.BaseController;
import com.lorent.common.dto.VovoMyInfo;
import com.lorent.common.tree.MemberBean;
import com.lorent.common.util.StringUtil;
import com.lorent.util.LCCUtil;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.dto.ChatRecord;
import com.lorent.vovo.dto.ControllerEvent;
import com.lorent.vovo.dto.FontStyle;
import com.lorent.vovo.dto.LoginInfo;
import com.lorent.vovo.ui.FriendChatPanel;
import com.lorent.vovo.ui.MessageFrame;
import com.lorent.vovo.ui.MessageTabPanel;
import com.lorent.vovo.ui.MsgRecordPanel;
import com.lorent.vovo.ui.MyTrayIcon;
import com.lorent.vovo.ui.RightCornerPopMessageManager;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.DataUtil;
import com.lorent.vovo.util.FlashWindowUtil;
import com.lorent.vovo.util.ImageUtil;
import com.lorent.vovo.util.MyOpenfireUtil;
import com.lorent.vovo.util.RecentContactManager;
import com.lorent.vovo.util.TreeUtil;
import com.lorent.vovo.util.UserInfoUtil;
import com.lorent.vovo.util.VovoStringUtil;

public class ChatController extends BaseController {

	private Logger log = Logger.getLogger(ChatController.class);
	
	public MessageFrame getMessageFrame(){
		try{
			MessageFrame view = Vovo.getViewManager().getView(Constants.ViewKey.MESSAGEFRAME.toString());//
			if (view == null) {
				view = Vovo.getViewManager().createView(MessageFrame.class, Constants.ViewKey.MESSAGEFRAME.toString());
				MessageTabPanel createView = Vovo.getViewManager().createView(MessageTabPanel.class, Constants.ViewKey.MESSAGETABPANEL.toString());
				view.add(createView);
				LoginInfo loginInfo = Vovo.getMyContext().getDataManager().getValue(Constants.DataKey.LOGGININFO.toString());
//				UserInfoUtil.setProperty("", value)
				String tabPlacement = UserInfoUtil.getProperty(loginInfo.getUsername(), "TabPlacement");
				if (tabPlacement == null || tabPlacement.equals("")) {
					tabPlacement = "2";
					UserInfoUtil.setProperty(loginInfo.getUsername()+"_TabPlacement", tabPlacement);
				}
				createView.getMainTabbedPane().setTabPlacement(Integer.parseInt(tabPlacement));
				view.setLocationRelativeTo(null);
			}
			return view;
		}catch(Exception e){
			log.error("ChatController static", e);
			JOptionPane.showMessageDialog(null, "init msg frame error", "error", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
	
	public void showMessageFrame() throws Exception{
		
//		MessageFrame view = context.getViewManager().getView(Constants.ViewKey.MESSAGEFRAME.toString());//
//		if (view == null) {
//			view = context.getViewManager().createView(MessageFrame.class, Constants.ViewKey.MESSAGEFRAME.toString());
//			MessageTabPanel createView = context.getViewManager().createView(MessageTabPanel.class, Constants.ViewKey.MESSAGETABPANEL.toString());
//			view.add(createView);
//		}
//		view.setVisible(true);
		
		//test
		/*
		for (int i = 0; i < 5; i++) {
			ShareFileListPanel shareFileListPanel = new ShareFileListPanel();
			MessageTabPanel messageTabPanel = Vovo.getMyContext().getViewManager().getView(Constants.ViewKey.MESSAGETABPANEL.toString());
			shareFileListPanel.setBackgroundXPanel(messageTabPanel.getBackgroundXPanel());
			shareFileListPanel.setSessionID("session"+i);
			shareFileListPanel.setParentFtpPath("GroupChatFilePath");
			messageTabPanel.addTab(shareFileListPanel, "sharefilelistpanel "+i, "session"+i);
		}
		ShareFileListPanel shareFileListPanel = new ShareFileListPanel();
		MessageTabPanel messageTabPanel = Vovo.getMyContext().getViewManager().getView(Constants.ViewKey.MESSAGETABPANEL.toString());
		shareFileListPanel.setBackgroundXPanel(messageTabPanel.getBackgroundXPanel());
		shareFileListPanel.setSessionID("Files");
		shareFileListPanel.setParentFtpPath("CommonFilePath");
		messageTabPanel.addTab(shareFileListPanel, "sharefilelistpanelCommon", "Files");
		
		
//		ConferencePanel conferencePanel = Vovo.getMyContext().getViewManager().createView(ConferencePanel.class, Constants.ViewKey.CONFERENCEPANEL.toString());
//		ConferencePanel conferencePanel = new ConferencePanel();
//		messageTabPanel.addTab(conferencePanel, "conferencepanel", "conference");
		
		
//		Component[] components = view.getRootPane().getComponents();
		JLayeredPane layeredPane = view.getRootPane().getLayeredPane();
		BaseRootPaneUI ui = (BaseRootPaneUI) view.getRootPane().getUI();
		BaseTitlePane titlePane = ui.getTitlePane();
		
		Robot rbt = new Robot( );
		Toolkit tk = Toolkit.getDefaultToolkit( );
		Dimension dim = tk.getScreenSize( );
		BufferedImage background = rbt.createScreenCapture(
		new Rectangle(0,0,(int)dim.getWidth( ),
                          (int)dim.getHeight( )));

		
//		titlePane.setBackgroundImage(background);
//		System.out.println(ui);
		*/
		
	}
	
	
	public void showFriendChat(MemberBean bean){
		MessageFrame msgFrame = getMessageFrame();
		getFriendChatPanel(bean);
		String key = Constants.FRIEND_CHAT_SESSION_PREFIX + bean.getLccAccount();
		ControllerEvent event = new ControllerEvent();
		event.setExClass("chat");
		event.setExMethod("showNotReadMessage");
		event.setParas(new Object[]{key});
		MyTrayIcon trayIcon = context.getViewManager().getView(Constants.ViewKey.TRAYICON.toString());
		trayIcon.removeEvent(event);
		showNotReadMessage(key);
		msgFrame.setExtendedState(JFrame.NORMAL);
		msgFrame.setVisible(true);
	}
	
	public FriendChatPanel getFriendChatPanel(MemberBean bean){
		String sessionID = getSessionID(bean.getId());
		MessageTabPanel tabPanel = context.getViewManager().getView(Constants.ViewKey.MESSAGETABPANEL.toString());
		FriendChatPanel panel = (FriendChatPanel)tabPanel.getTab(sessionID);
		if(panel == null){
			panel = new FriendChatPanel();
			panel.setInfo(bean);
//			ImageIcon icon = new ImageIcon(getClass().getResource("/com/lorent/vovo/resource/images/systemheads/default40.png"));
			tabPanel.addTab(panel, bean, sessionID);
		}else{
			tabPanel.setNoticeTab(sessionID);
		}
		return panel;
	}
	
	public void sendFriendMsg(String msg, FontStyle fontStyle, String lccno, int memberId, Map<String,String> imgs, long time) throws Exception{
		MyOpenfireUtil.sendFriendMsg(lccno, msg, fontStyle, imgs, time);
		VovoMyInfo info = DataUtil.getMyInfo();
		ChatRecord c = new ChatRecord(info.getId(), info.getRealName(), msg, new Date(time), fontStyle, Constants.FRIEND_CHAT_SESSION_PREFIX + memberId);
		Vovo.exeS("dataStore", "saveChatRecord", c);
	}
	
	public void showMessage(ControllerEvent event, int type)throws Exception{
		//判断msgFrame是否visible
		MessageFrame msgFrame = getMessageFrame();
		if(msgFrame.isVisible()){
			Vovo.exeC(event.getExClass(), event.getExMethod(), event.getParas());
		}else{
			String lccno = (String)event.getParas()[0];
			String key = null;
			if(Constants.MSG_TYPE_FRIEND == type){
				key = Constants.FRIEND_CHAT_SESSION_PREFIX + lccno;
			}else if(Constants.MSG_TYPE_GROUP == type){
				key = Constants.GROUP_CHAT_SESSION_PREFIX + lccno;
			}
			List<Object[]> notReadMsg = DataUtil.getNotReadMsg(key);
			Object[] temp = event.getParas();
			if(notReadMsg == null){//没有未读信息
				MyTrayIcon trayIcon = context.getViewManager().getView(Constants.ViewKey.TRAYICON.toString());
				event.setImage(ImageUtil.getImage(ImageUtil.TRAY_ICON_REC_MSG));
				event.setExClass("chat");
				event.setExMethod("showNotReadMessage");
				event.setParas(new Object[]{key});
				trayIcon.addEvent(event);
			}
			DataUtil.addNotReadMsg(key, temp);
		}
	}
	
	public void showNotReadMessage(String key){
		List<Object[]> notReadMsg = DataUtil.getNotReadMsg(key);
		if(notReadMsg == null){
			return;
		}
		for(Object[] paras : notReadMsg){
			if(key.startsWith(Constants.FRIEND_CHAT_SESSION_PREFIX)){
				Vovo.exeC("chat", "getFriendMessage", paras);			
				try {
					RecentContactManager.getInstance().insertFriendChat((String) paras[0]);
				} catch (IOException e) {
				}
			}else if(key.startsWith(Constants.GROUP_CHAT_SESSION_PREFIX)){
				Vovo.exeC("groupChat", "getGroupChatMessage", paras);
				try {
					RecentContactManager.getInstance().insertGroupChat((String) paras[0]);
				} catch (IOException e) {
				}
			}
		}
		DataUtil.removeNotReadMsg(key);
	}
	
	public void getFriendMessage(String lccno, final String msg, final FontStyle fontStyle, final Date sendDate, final Map<String,String> imgs)throws Exception{
		MessageFrame msgFrame = getMessageFrame();
		final MemberBean memberBean = TreeUtil.getMemberBeanByLccno(lccno);
		if(memberBean==null){
			RightCornerPopMessageManager.showDefaultRightCornerPopMessage(lccno + "用户已被删除，他的离线信息无法显示");
			return;
		}
		final FriendChatPanel panel = getFriendChatPanel(memberBean);
		if (!SwingUtilities.isEventDispatchThread()){
			SwingUtilities.invokeAndWait(new Runnable() {
				
				@Override
				public void run() {
					try{
						handleFriendMsg(panel, memberBean, msg, fontStyle, sendDate, imgs);
					}catch(Exception e){
						log.error("insertFriendMsg", e);
						showErrorDialog("error", e.getMessage());
					}
				}
			});
		}else{
			handleFriendMsg(panel, memberBean, msg, fontStyle, sendDate, imgs);
		}
		if(!msgFrame.isVisible()){
			msgFrame.setVisible(true);
		}
		
	}
	
	private void handleFriendMsg(FriendChatPanel panel, MemberBean memberBean, String msg, FontStyle fontStyle, Date sendDate, Map<String,String> imgs)throws Exception{
		panel.insertFriendMsg(msg, fontStyle, sendDate, imgs);
		FlashWindowUtil.start(getMessageFrame());
		ChatRecord c = new ChatRecord(memberBean.getId(), memberBean.getRealName(), msg, sendDate, fontStyle, getSessionID(memberBean.getId()));
		Vovo.exeS("dataStore", "saveChatRecord", c);
	}
	
	public String sendFile(File file, String lccno)throws Exception{
		return MyOpenfireUtil.sendFile(file, lccno);
	}
	
    public void cancelSendFile(String streamID, String lccno, long time)throws Exception{
    	MyOpenfireUtil.cancelSendFile(streamID, lccno, time);
    }
    
    public void recSendFileMsg(String lccno, String fileName, long fileSize, String streamID){
    	MessageFrame msgFrame = getMessageFrame();
		MemberBean memberBean = TreeUtil.getMemberBeanByLccno(lccno);
		final FriendChatPanel panel = getFriendChatPanel(memberBean);
		panel.addRecvFileItem(fileName, fileSize, streamID);
		msgFrame.setVisible(true);
		msgFrame.setExtendedState(javax.swing.JFrame.NORMAL);
//		FlashWindowUtil.start(msgFrame);
    }
    
    public void getOtherMsg(String lccno, String operate, Message msg)throws Exception{
    	if(Constants.OTHERMSG_OPERATE_CANCELSENDFILE.equals(operate)){
    		String streamID = (String)msg.getProperty("streamID");
    		Date sendDate = new Date((Long)msg.getProperty("sendDate"));
    		FileTransferRequest req = DataUtil.getFileTransferRequest(streamID);
        	MessageFrame msgFrame = getMessageFrame();
    		MemberBean memberBean = TreeUtil.getMemberBeanByLccno(lccno);
    		final FriendChatPanel panel = getFriendChatPanel(memberBean);
    		String info = StringUtil.getFormatString(getUIString("sendFile.otherCancel"), "\"" + req.getFileName() + "\"");
    		panel.otherCancelSendFile(streamID, sendDate, info);
    		msgFrame.setVisible(true);
    	}
    }
    
    public void acceptSendFile(String streamID, String selectPath)throws Exception{
    	log.info("save file " + selectPath);
    	MyOpenfireUtil.acceptSendFile(streamID, selectPath);
    }
    
    public void updateFileProcess(String lccno, String streamID, int percent){
		MemberBean memberBean = TreeUtil.getMemberBeanByLccno(lccno);
		final FriendChatPanel panel = getFriendChatPanel(memberBean);
		panel.updateInfo(streamID, percent);
    }
    
    public void sendFileComplete(String lccno, String streamID, String fileName)throws Exception{
    	MemberBean memberBean = TreeUtil.getMemberBeanByLccno(lccno);
    	final FriendChatPanel panel = getFriendChatPanel(memberBean);
    	panel.sendFileComplete(streamID, fileName);
    	DataUtil.removeFileTransfer(streamID);
    }
    
    public void rejectSendFile(String streamID, String lccno){
    	MyOpenfireUtil.rejectSendFile(streamID);
    }
    
    public void otherRejectSendFile(String lccno, String streamID, String fileName)throws Exception{
    	MemberBean memberBean = TreeUtil.getMemberBeanByLccno(lccno);
    	final FriendChatPanel panel = getFriendChatPanel(memberBean);
    	String info = StringUtil.getFormatString(getUIString("sendFile.reject"), fileName);
    	long systemTime = Vovo.getLcmUtil().getSystemTime();
    	panel.otherCancelSendFile(streamID, new Date(systemTime), info);
    	DataUtil.removeFileTransfer(streamID);
    }
    
    public void cancelSendFileInProcess(String streamID){
    	MyOpenfireUtil.cancelSendFileInProcess(streamID);
    }
    
//    public void transferError(String lccno, String streamID, String fileName, Error error)throws Exception{
//    	MemberBean memberBean = TreeUtil.getMemberBeanByLccno(lccno);
//    	final FriendChatPanel panel = getFriendChatPanel(memberBean);
//    	String info = StringUtil.getFormatString(getUIString("sendFile.otherCancel"), "\"" + fileName + "\"");
//    	panel.otherCancelSendFile(streamID, new Date(), info);
//    	DataUtil.removeFileTransfer(streamID);
//    }
    
    public void transferTimeOut(String lccno, String streamID, String fileName)throws Exception{
    	MemberBean memberBean = TreeUtil.getMemberBeanByLccno(lccno);
    	final FriendChatPanel panel = getFriendChatPanel(memberBean);
    	String info = StringUtil.getFormatString(getUIString("sendFile.timeout"), fileName);
    	long systemTime = Vovo.getLcmUtil().getSystemTime();
    	panel.otherCancelSendFile(streamID, new Date(systemTime), info);
//    	DataUtil.removeFileTransfer(streamID);
    	this.cancelSendFile(streamID, lccno, systemTime);
    }
    
    public void transferInterrupt(String lccno, String streamID, String fileName)throws Exception{
    	MemberBean memberBean = TreeUtil.getMemberBeanByLccno(lccno);
    	final FriendChatPanel panel = getFriendChatPanel(memberBean);
    	String info = StringUtil.getFormatString(getUIString("sendFile.interrupt"), fileName);
    	long systemTime = Vovo.getLcmUtil().getSystemTime();
    	panel.otherCancelSendFile(streamID, new Date(systemTime), info);
    	DataUtil.removeFileTransfer(streamID);
    }
    
    public static String getSessionID(int memberBeanId){
    	return Constants.FRIEND_CHAT_SESSION_PREFIX + memberBeanId;
    }
    
    public void getLatestRecord(int beanId, MsgRecordPanel panel)throws Exception{
    	String sessionID = getSessionID(beanId);
    	int count = (Integer) Vovo.exeS("dataStore", "getRecordNum", sessionID);
    	int totalPage = count / Constants.SHOW_MESSAGE_PER_PAGE + 1;
    	int offset = (totalPage - 1) * Constants.SHOW_MESSAGE_PER_PAGE;
    	List<ChatRecord> records = (List<ChatRecord>) Vovo.exeS("dataStore", "getChatRecord", sessionID, Constants.SHOW_MESSAGE_PER_PAGE, offset);
    	panel.initInfo(totalPage, totalPage, records, sessionID);
    }
    
    public void changePage(int page, String sessionID, MsgRecordPanel panel)throws Exception{
    	int offset = (page - 1) * Constants.SHOW_MESSAGE_PER_PAGE;
    	List<ChatRecord> records = (List<ChatRecord>) Vovo.exeS("dataStore", "getChatRecord", sessionID, Constants.SHOW_MESSAGE_PER_PAGE, offset);
    	panel.changeData(records);
    }
    
    public boolean canRemovePanel(String[] sessionIDs){
    	boolean flag = true;
    	for(String sessionID : sessionIDs){
    		if(!sessionID.startsWith(Constants.FRIEND_CHAT_SESSION_PREFIX)){
    			continue;
    		}
    		int memberId = Integer.parseInt(sessionID.split("_")[1]);
    		String lccno = TreeUtil.getMemberNodeById(memberId).getInfo().getMember().getLccAccount();
    		boolean temp = LCCUtil.getInstance().existCall(lccno);
    		if(temp){
    			this.showMessageDialog(getUIString("info.tip"), getUIString("phone.cannotClosePanel"));
    			flag = false;
    			break;
    		}
    		
    	}
    	return flag;
    }
    
    public boolean canRemoveMsgFrame()throws Exception{
    	MessageTabPanel tab = Vovo.getViewManager().getView(Constants.ViewKey.MESSAGETABPANEL.toString());
    	Set<String> sessionIDs = tab.getAllSessionIDs();
    	return canRemovePanel(StringUtil.parseSetToArray(sessionIDs, String.class));
    }
    
    public void sendOfflineMessageFail(String lccno){
    	final MemberBean memberBean = TreeUtil.getMemberBeanByLccno(lccno);
    	SwingUtilities.invokeLater(new Runnable(){
    		public void run(){
    			ChatController.this.showMessageDialog(null, VovoStringUtil.getFormatString(VovoStringUtil.getUIString("sendofflinemessage.fail.tip"), memberBean.getRealName()));
    		}
    	});
    }

}
