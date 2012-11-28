package com.lorent.vovo.util;

import java.awt.Color;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.RosterPacket;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.GroupChatInvitation;
import org.jivesoftware.smackx.filetransfer.FileTransfer;
import org.jivesoftware.smackx.filetransfer.FileTransferListener;
import org.jivesoftware.smackx.filetransfer.FileTransferManager;
import org.jivesoftware.smackx.filetransfer.FileTransferRequest;
import org.jivesoftware.smackx.filetransfer.IncomingFileTransfer;
import org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer;
import org.jivesoftware.smackx.muc.Affiliate;
import org.jivesoftware.smackx.muc.DiscussionHistory;
import org.jivesoftware.smackx.muc.HostedRoom;
import org.jivesoftware.smackx.muc.InvitationListener;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.packet.MUCUser;

import com.lorent.common.event.MyEvent;
import com.lorent.common.tree.BroadcastEvent;
import com.lorent.common.tree.DepartmentBean;
import com.lorent.common.tree.MemberBean;
import com.lorent.common.util.OpenfireUtil;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.dto.ControllerEvent;
import com.lorent.vovo.dto.FontStyle;
import com.lorent.vovo.dto.GroupInfo;
import com.lorent.vovo.dto.LoginInfo;
import com.lorent.vovo.dto.PrivateData;
import com.lorent.vovo.iq.FetchGroupChatAuthorityBean;
import com.lorent.vovo.iq.FetchGroupChatAuthorityXmlParser;
import com.lorent.vovo.iq.IQXmlParser;
import com.lorent.vovo.iq.VovoIQ;
import com.lorent.vovo.iq.VovoIQListener;
import com.lorent.vovo.iq.VovoIQProvider;

public class MyOpenfireUtil{

	public final static String GROUPCHAT_SPACE_NAME = "jabber:iq:groupchatplugin";
	public final static String MODULE_NAME = "groupchatplugin";
	public final static String PREFIX_NAME = "query";
	private final static String AUTO_JOIN_GROUPCHAT = "autoJoinGroupChat";
	private final static String GROUPCHAT_TOPIC = "groupChatTopic";
	private final static String GROUPCHAT_DESC = "groupChatDesc";
	private final static String GROUPCHAT_JOIN_TYPE = "joinTpye";
	private final static int GROUPCHAT_AGREE_JOIN = 1;
	private final static int GROUPCHAT_REFUSE_JOIN = 2;
	private final static int GROUPCHAT_ACCEPT_INVITATION = 3;
	private final static String GROUP_SERVICE_NAME="groupchat.localhost";
	private static Roster roster;
	private static int state = 1;
	private static Logger log = Logger.getLogger(MyOpenfireUtil.class);
	
	private static List<String> propertyKeys = new ArrayList<String>();
	public static List<String> getPropertyKey(){
		if(propertyKeys.size() == 0){
			propertyKeys.add("sendDate");
			propertyKeys.add("fontName");
			propertyKeys.add("fontSize");
			propertyKeys.add("fontColor");
			propertyKeys.add("isBold");
			propertyKeys.add("isItalic");
			propertyKeys.add("isUnderLine");
			
		}
		return propertyKeys;
	}
	
	static class MyPacketListener implements PacketListener{

		@Override
		public void processPacket(Packet packet) {
//			log.info("接收到服务端信息：" + packet.toXML());
			if (packet instanceof Message){
				Message msg = (Message) packet;
				if(msg.getFrom().split("@")[0].equals("admin")){
					log.info("receive admin msg : " + msg.toXML());
					handleAdminMsg(msg);
				}
			}else if(packet instanceof Presence){
				Presence presence = (Presence)packet;
//				log.info("Presence的内容：" + presence.toXML());
				String from = packet.getFrom();
				if(from.matches(".*?@groupchat.*")){
					return;
				}
				String lccno = presence.getFrom().split("@")[0];
//				System.out.println(packet.toXML());
				int status = convertPresence(presence);
				log.info("get presence{ lccno : " + lccno + " & presence : " + status + "}");
				sendMsgAfterDataReady("userStatusChange", new Object[]{lccno, status});
			}
		}
		
	}
	
	static class MyGroupListener implements PacketListener{

		@Override
		public void processPacket(Packet packet) {
			log.info("MyGroupListener===============" + packet.toXML());
			if (packet instanceof Message){
				Message msg = (Message) packet;
				if(msg.getType().equals(Message.Type.normal)){
					log.info("groupchat join type message:" + msg.toXML());
					String inviterName = (String)msg.getProperty("inviterName");
                    //收到群组邀请
                    GroupChatInvitation extension = (GroupChatInvitation) msg.getExtension(new GroupChatInvitation(null).getNamespace());
                    if (extension != null) {
                    	int joinType = (Integer)msg.getProperty(GROUPCHAT_JOIN_TYPE);
                    	String topic = (String)msg.getProperty(GROUPCHAT_TOPIC);
                    	if(joinType == GROUPCHAT_AGREE_JOIN){
                    		boolean  autoJoin = (Boolean)msg.getProperty(AUTO_JOIN_GROUPCHAT);
                        	
                        	String desc = (String)msg.getProperty(GROUPCHAT_DESC);
                            if(autoJoin){
                            	try {
                            		removeMultiUserChatByRoomJid(extension.getRoomAddress());
                            		MyMultiUserChat chat = new MyMultiUserChat(OpenfireUtil.getInstance().getConn(), extension.getRoomAddress());
                            		DiscussionHistory history = new DiscussionHistory();
                            	    history.setMaxStanzas(0);
                            		chat.join(DataUtil.getUserName(),null,history, SmackConfiguration.getPacketReplyTimeout());
        							DataUtil.addGroupChat(extension.getRoomAddress(), chat);
        						} catch (XMPPException e) {
        							e.printStackTrace();
        						}
                            }
                            Vovo.sendMessage("showJoinGroupChat", new Object[]{extension.getRoomAddress(),topic,desc,(Boolean)msg.getProperty(AUTO_JOIN_GROUPCHAT)});
                    	}else if(joinType == GROUPCHAT_REFUSE_JOIN){
                    		Vovo.sendMessage("recieveRefuseJoinGroupChatMessage", new Object[]{topic,extension.getRoomAddress()});
                    	}else if(joinType == GROUPCHAT_ACCEPT_INVITATION){
                    		Vovo.sendMessage("agreeOneJoinGroupChat", new Object[]{msg.getFrom(),extension.getRoomAddress()});
                    	}
                    }else if(inviterName!=null && inviterName.trim().length()>0){
                    	String roomSubject = (String)msg.getProperty("roomSubject");
                    	String inviter = (String)msg.getProperty("inviter");
                    	String reason = (String)msg.getProperty("reason");
                    	String password = (String)msg.getProperty("password");
                    	String roomJid = (String)msg.getProperty("roomJid");
        				Vovo.sendMessage("recieveInviteMsg", new Object[]{roomJid,inviter,reason,password,roomSubject,inviterName});
                    }
                    
                }
			}else if(packet instanceof Presence){
				String from = packet.getFrom();
				Presence p = (Presence) packet;
				if(p.isAvailable()||!from.matches(".*?@groupchat.*"))
					return;
				MUCUser user = (MUCUser) p.getExtension("http://jabber.org/protocol/muc#user");
				if(user!=null){
					log.info("p.getFrom()=======" + p.getFrom());
					Vovo.sendMessage("dismissGroupChat", new Object[]{user,VovoStringUtil.getHeadString(p.getFrom(), "/")});
				}
			}
		}
		
	}
	
	private static void handleAdminMsg(Message msg){
		String operate = (String)msg.getProperty("operate");
		if(operate != null && operate.equals(BroadcastEvent.UPDATE_USER) 
				|| operate.equals(BroadcastEvent.ADD_USER) 
				|| operate.equals(BroadcastEvent.DELETE_USER)){
			MemberBean bean = (MemberBean)msg.getProperty("data");
			sendMsgAfterDataReady("memberChange", new Object[]{operate, bean});
		}else if(operate != null && operate.equals(BroadcastEvent.ADD_DEPT) 
				|| operate.equals(BroadcastEvent.UPDATE_DEPT)
				|| operate.equals(BroadcastEvent.DELETE_DEPT)){
			DepartmentBean bean = (DepartmentBean)msg.getProperty("data");
			sendMsgAfterDataReady("deptChange", new Object[]{operate, bean});
		}else if(operate != null && operate.equals(BroadcastEvent.BROADCAST_MY_IPADDRESS)){
			Object property = msg.getProperty("data");
			Object[] pros = (Object[]) property;
			MemberBean bean = (MemberBean) pros[0];
			String from = (String) pros[1];
			String to = (String) pros[2];
			sendMsgAfterDataReady("receiveBroadcastIP", new Object[]{operate, bean,from,to});
		}
		Object property = msg.getProperty("msgType");
		if (property != null && property.equals("ConfNotice")) {
			sendMsgAfterDataReady("showConferenceBubbleDialog", new Object[]{msg.getProperty("confNo"),msg.getBody()});
		}
	}
	
	public static void addListener(){
		OpenfireUtil.getInstance().getConn().addPacketListener(new MyPacketListener(), null);
//		OpenfireUtil.getInstance().getConn().getRoster().addRosterListener(new MyRosterListener());
		OpenfireUtil.getInstance().getConn().getChatManager().addChatListener(new MyChatManagerListener());
		OpenfireUtil.getInstance().getConn().addConnectionListener(new MyConnectionListener());
		FileTransferManager manager = new FileTransferManager(getConn());
		manager.addFileTransferListener(new MyFileTransferListener());
	}
	
	private static class MyConnectionListener implements ConnectionListener{

		@Override
		public void connectionClosed() {
			log.info("connectionClosed");
		}

		@Override
		public void connectionClosedOnError(Exception e) {
			log.info("connectionClosedOnError " + e.getMessage());
            if("stream:error (conflict)".equalsIgnoreCase(e.toString())){
                Vovo.sendMessage("relogin", null);
            }else{
            	Vovo.sendMessage("serverDisconnect", null);
            }
		}

		@Override
		public void reconnectingIn(int s) {
			log.info("reconnectingIn " + s);
		}

		@Override
		public void reconnectionFailed(Exception e) {
			log.info("reconnectionFailed " + e.getMessage());
		}

		@Override
		public void reconnectionSuccessful() {
			log.info("reconnectionSuccessful ");
			Vovo.sendMessage("reconnectionSuccessful", null);
		}
		
	}
	
	
	public static void addInvitationListener(){
		MultiUserChat.addInvitationListener(OpenfireUtil.getInstance().getConn(), new InvitationListener() {
	         
			@Override
			public void invitationReceived(Connection conn, String room,
					String inviter, String reason, String password,
					Message message) {
				log.info("recieve invite groupchat :" + message.toXML());
				String roomSubject = (String)message.getProperty("roomSubject");
				String inviterName = (String)message.getProperty("inviterName");
				Vovo.sendMessage("recieveInviteMsg", new Object[]{room,inviter,reason,password,roomSubject,inviterName});
//				int flag = (Integer)Vovo.exeC("groupChat", "recieveInviteMsg", roomSubject, inviterName);
//				if(flag == JOptionPane.OK_OPTION){
//					try {
//						sendAcceptGroupInvitations(inviter,room);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}else{
//					MultiUserChat.decline(conn, room, inviter, "I'm busy right now");
//				}
			}
	      });
	}
	
	
	public static void refuseGroupInvitation(String inviter,String room){
		MultiUserChat.decline(OpenfireUtil.getInstance().getConn(), room, inviter, "I'm busy right now");
	}
	
	//发送邀请加入群组信息
    public static void sendAcceptGroupInvitations(String targetUserJid,String roomJid) throws Exception{
        
        Message message = new Message(targetUserJid);
        message.addExtension(new GroupChatInvitation(roomJid));
        message.setProperty(GROUPCHAT_JOIN_TYPE, GROUPCHAT_ACCEPT_INVITATION);
        OpenfireUtil.getInstance().getConn().sendPacket(message);
    }
	
	
	private static class MyFileTransferListener implements FileTransferListener{

		@Override
		public void fileTransferRequest(FileTransferRequest req) {
			log.info("receive send file : " + req.getRequestor() + "|" + req.getFileName() + "|" + req.getMimeType() + "|" + req.getStreamID());
			DataUtil.addFileTransferRequest(req.getStreamID(), req);
			String lccno = req.getRequestor().split("@")[0];
			sendMsgAfterDataReady("recSendFileMsg", new Object[]{lccno, req.getFileName(), req.getFileSize(), req.getStreamID()});
//			IncomingFileTransfer transfer = req.accept();
//			transfer.cancel();
		}
		
	}
	
	public static void addGroupChatListener(){
		OpenfireUtil.getInstance().getConn().addPacketListener(new MyGroupListener(), null);
//		addInvitationListener();
	}
	
	private static class MyChatManagerListener implements ChatManagerListener{

		@Override
		public void chatCreated(Chat chat, boolean created) {
			if(!created){
				chat.addMessageListener(new FriendMsgListener());
				DataUtil.addFriendChat(chat.getParticipant().split("@")[0], chat);
			}
		}
		
	}
	
	private static class FriendMsgListener implements MessageListener{
		@Override
		public void processMessage(Chat c, Message msg) {
			if(!msg.getType().equals(Message.Type.normal)){
				receiveFriendMsg(msg);
			}
		}
	}
	
	
	
	private static void receiveFriendMsg(Message msg){
		String lccno = msg.getFrom().split("@")[0];
		if(msg.getProperty("operate")!=null){//命令
			sendMsgAfterDataReady("getOtherMsg", new Object[]{lccno, msg.getProperty("operate"), msg});
		}else{//普通信息
			XMPPError error = msg.getError();
			if(error != null){
				if(error.getCode() == 404 && error.getType().equals(XMPPError.Type.CONTINUE)){
					Vovo.sendMessage("sendOfflineMessageFail", new Object[]{lccno});
					return;
				}
			}
			ControllerEvent event = new ControllerEvent();
			event.setExClass("chat");
			event.setExMethod("getFriendMessage");
			Collection<String> col = msg.getPropertyNames();
			Map<String,String> imgs = new HashMap<String,String>();
			List<String> propsKeys = getPropertyKey();
			for(String str:col){
				if(propsKeys.contains(str)){
					continue;
				}
				imgs.put(str, (String)msg.getProperty(str));
			}
			event.setParas(new Object[]{lccno, msg.getBody(), getFontStyle(msg), new Date((Long)msg.getProperty("sendDate")), imgs});
			sendMsgAfterDataReady("showMessage", new Object[]{event, Constants.MSG_TYPE_FRIEND});
		}

	}
	
	private static class MyRosterListener implements RosterListener{
		@Override
		public void presenceChanged(Presence presence) {
			log.info("MyRosterListener接收到：" + presence.toXML());
			String lccno = presence.getFrom().split("@")[0];
//			System.out.println(packet.toXML());
			int status = convertPresence(presence);
			log.info("get presence{ lccno : " + lccno + " & presence : " + status + "}");
			sendMsgAfterDataReady("userStatusChange", new Object[]{lccno, status});
		}
		
		@Override
		public void entriesUpdated(Collection<String> arg0) {
			//nothing to do
		}
		
		@Override
		public void entriesDeleted(Collection<String> arg0) {
			//nothing to do
		}
		
		@Override
		public void entriesAdded(Collection<String> arg0) {
			//nothing to do
		}
	}
	
	
	public static void addIQProvider(){
		ProviderManager.getInstance().addIQProvider("query", GROUPCHAT_SPACE_NAME, new VovoIQProvider(GROUPCHAT_SPACE_NAME));
		OpenfireUtil.getInstance().getConn().addPacketListener(new VovoIQListener(), new PacketFilter() {
            @Override
            public boolean accept(Packet packet) {
                return true;
            }
        });
	}
	
	public static void notifyGroupChatByKickOne(MultiUserChat muc,String targetUserJid,String roomJid) throws Exception{
		Message temp = muc.createMessage();
        temp.setBody("");
        temp.setProperty(Constants.GROUPCHAT_NOTIFY_TYPE, Constants.DataKey.GROUPCHAT_NOTIFY_TYPE_KICK_ONE.toString());
        temp.setProperty("userJid", targetUserJid);
        temp.setProperty("roomJid", roomJid);
        muc.sendMessage(temp);
	}
	
	 //通知群组有新成员加入
    public static void notifyGroupChatByQuit(MultiUserChat muc,String targetUserJid,String roomJid) throws Exception{
    	Message temp = muc.createMessage();
        temp.setBody("");
        temp.setProperty(Constants.GROUPCHAT_NOTIFY_TYPE, Constants.DataKey.GROUPCHAT_NOTIFY_TYPE_QUIT.toString());
        temp.setProperty("userJid", targetUserJid);
        temp.setProperty("roomJid", roomJid);
        muc.sendMessage(temp);
    }
	
	public static void leftGroupChat(String roomJid) throws Exception{
//		MultiUserChat multiUserChat = DataUtil.getGroupChat(roomJid);
//		multiUserChat.revokeOwnership(DataUtil.getUserName() + "@" + getServiceName());
//		multiUserChat.revokeAdmin(DataUtil.getUserName() + "@" + getServiceName());
//		multiUserChat.revokeMembership(DataUtil.getUserName() + "@" + getServiceName());
		log.info("退出群======"+roomJid);
		MultiUserChat multiUserChat = DataUtil.getGroupChat(roomJid);
		notifyGroupChatByQuit(multiUserChat,DataUtil.getUserName(),roomJid);
		multiUserChat.leave();
	}
	
	public static void leftGroupChatByDestroy(String roomJid){
//		MultiUserChat multiUserChat = DataUtil.getGroupChat(roomJid);
//		multiUserChat.leave();
//		DataUtil.removeGroupChat(roomJid);
		removeMultiUserChatByRoomJid(roomJid);
	}
	
	//查找群组
	public static void searchGroupChat(String groupName){
		StringBuffer sb = new StringBuffer();
		sb.append("<operate name=\""+Constants.SEARCH_GROUPCHAT+"\">");
		sb.append("<groupname>").append(groupName).append("</groupname>");
		sb.append("</operate>");
		sendGroupChatVovoIQ(sb);
	}
	
	//发送已加入群组的请求
	public static void getGroupChatJid(){
		StringBuffer sb = new StringBuffer();
		sb.append("<operate name=\""+Constants.GET_GROUPCHAT+"\">");
		sb.append("</operate>");
		sendGroupChatVovoIQ(sb);
	}
	
	//申请加入群组发送请求
	public static void applyInGroupChat(String roomjid){
		StringBuffer sb = new StringBuffer();
		sb.append("<operate name=\""+Constants.APPLYIN_GROUPCHAT+"\">");
		sb.append("<roomjid>").append(roomjid).append("</roomjid>");
		sb.append("<applicant>").append(DataUtil.getUserName()).append("</applicant>");
		sb.append("</operate>");
		sendGroupChatVovoIQ(sb);
	}
	
	//获取所在群组权限
	public static FetchGroupChatAuthorityBean fetchGroupChatAuthority(List<String> roomJids){
		StringBuffer sb = new StringBuffer();
		sb.append("<operate name=\""+Constants.FETCH_GROUPCHAT_AUTHORITY+"\">");
		sb.append("<user>").append(DataUtil.getUserName()).append("</user>");
		for(String roomJid:roomJids){
			sb.append("<roomjid>").append(roomJid).append("</roomjid>");
		}
		sb.append("</operate>");
//		sendGroupChatVovoIQ(sb);
		try {
			Packet packet = sendSynPacket(new VovoIQ(sb.toString(),GROUPCHAT_SPACE_NAME));
			IQXmlParser parser = new IQXmlParser(packet.toXML());
			if(parser.getOperateName().equals(Constants.FETCH_GROUPCHAT_AUTHORITY)){
				FetchGroupChatAuthorityBean bean = FetchGroupChatAuthorityXmlParser.parse(parser.getDocument());
				return bean;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//修改某个群组成员权限
	public static void upateGroupChatAuthority(String roomJid,String userJid,List<String> marks){
		StringBuffer sb = new StringBuffer();
		sb.append("<operate name=\""+Constants.UPDATE_GROUPCHAT_AUTHORITY+"\">");
		sb.append("<user>").append(userJid).append("</user>");
		sb.append("<roomjid>").append(userJid).append("</roomjid>");
		for(String mark:marks){
			sb.append("<mark>").append(mark).append("</mark>");
		}
		sb.append("</operate>");
		sendGroupChatVovoIQ(sb);
	}
	
	public static void sendGroupChatVovoIQ(StringBuffer sb){
		IQ iq = new VovoIQ(sb.toString(),GROUPCHAT_SPACE_NAME);
		OpenfireUtil.getInstance().getConn().sendPacket(iq);
	}
	
	private static String getRoomSubject(MultiUserChat multiUserChat)throws Exception{
//		String subject = "";
//		Form configurationForm = multiUserChat.getConfigurationForm();
//        FormField field = configurationForm.getField("muc#roomconfig_roomname");
//        Iterator<String> values = field.getValues();
//        while (values.hasNext()) {
//        	subject = values.next();
//        }
//        return subject;
		return MultiUserChat.getRoomInfo(OpenfireUtil.getInstance().getConn(), multiUserChat.getRoom()).getSubject();
	}
	
	public static GroupInfo getGroupInfo(String roomJid) throws Exception{
		GroupInfo info = new GroupInfo();
		info.setRoomJID(roomJid);
		info.setTopic(getRoomSubject(roomJid));
		info.setDesc(getRoomDesc(roomJid));
		return info;
	} 
	
	public static String getRoomSubject(String roomJid) throws Exception{
		MultiUserChat multiUserChat = DataUtil.getGroupChat(roomJid);
		return getRoomSubject(multiUserChat);
	}
	
	public static String getRoomDesc(String roomJid)throws Exception{
		return MultiUserChat.getRoomInfo(OpenfireUtil.getInstance().getConn(), roomJid).getDescription();
	}
	
	public static String getRealNameByJid(String jid){
		RosterEntry entry = OpenfireUtil.getInstance().getConn().getRoster().getEntry(jid);
		return entry.getName();
	}
	
	public static Collection<HostedRoom> getGroupHostedRooms() throws Exception{
		return MultiUserChat.getHostedRooms(getConn(), GROUP_SERVICE_NAME);
	}
	
	//加入群组
	public static Map<String,String[]> joinGroupChat(List<String> roomJids) throws Exception{
		
		Map<String,String[]> map = new HashMap<String,String[]>();
		for(String roomJid:roomJids){
			MultiUserChat multiUserChat = new MyMultiUserChat(OpenfireUtil.getInstance().getConn(), roomJid);
			DiscussionHistory history = new DiscussionHistory();
			PrivateData pri = DataUtil.getPrivateData();
			if(pri != null){
				if(pri.getLastLoginTime() == null){//第一次登录
					history.setMaxStanzas(Constants.MAX_HISTORY);
				}else if(pri.getLastLogoutTime() == null){//第一次登录后异常退出					
					history.setMaxStanzas(Constants.MAX_HISTORY);
				}else if(pri.getLastLoginTime() > pri.getLastLogoutTime()){//登录后异常退出
					history.setSince(new Date(pri.getLastLoginTime()));
				}else{//正常退出
					history.setSince(new Date(pri.getLastLogoutTime()));
				}
			}
            multiUserChat.join(DataUtil.getUserName(), null, history, SmackConfiguration.getPacketReplyTimeout());
            String topic = getRoomSubject(multiUserChat);
            map.put(roomJid,new String[]{topic,getRoomDesc(roomJid)});
            DataUtil.addGroupChat(roomJid,multiUserChat);
		}
		return map;
	}
	
	//发送设置创建者的群组权限包
	public static void setGroupChatMembersAuthority(String roomjid,List<String> members){
		StringBuffer sb = new StringBuffer();
		sb.append("<operate name=\""+Constants.SET_GROUPCHAT_AUTHORITY+"\">");
		sb.append("<roomjid>").append(roomjid).append("</roomjid>");
		sb.append("<creator>").append(DataUtil.getUserName()).append("</creator>");
		for(String member:members){
			sb.append("<member>").append(member).append("</member>");
		}
		sb.append("</operate>");
		IQ iq = new VovoIQ(sb.toString(),GROUPCHAT_SPACE_NAME);
		OpenfireUtil.getInstance().getConn().sendPacket(iq);
	}
	
	
	//创建群组
	public static String createGroupChat(String topic,String desc ,List<String> members) throws Exception{
		String username = DataUtil.getUserName();
    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssS");
        String sessionId = format.format(new Date());
        String roomJid = DataUtil.getUserName() + "_" + sessionId + "@" + Constants.GROUPCHAT_SERVICE_NAME + "." + OpenfireUtil.getInstance().getConn().getServiceName();
        MultiUserChat multiUserChat = new MyMultiUserChat(OpenfireUtil.getInstance().getConn(), roomJid);
        multiUserChat.create(username);
        Form newConfigForm = multiUserChat.getConfigurationForm().createAnswerForm();

        newConfigForm.setAnswer("muc#roomconfig_publicroom", true);//列出目录中的房间
        newConfigForm.setAnswer("muc#roomconfig_persistentroom", true);//房间是持久的
        System.out.println(StringUtils.escapeForXML(topic));
        newConfigForm.setAnswer("muc#roomconfig_roomname", StringUtils.escapeForXML(topic));
        newConfigForm.setAnswer("muc#roomconfig_roomdesc", StringUtils.escapeForXML(desc));
        List<String> list = new ArrayList<String>();
        list.add("0");
        newConfigForm.setAnswer("muc#roomconfig_maxusers", list);//最大房间占有者人数,0表示无限制
        List<String> newMembers = new ArrayList<String>();
        for (Iterator<String> it = members.iterator(); it.hasNext();) {
            String member = it.next();
            if (member.indexOf("@") == -1) {
                member = member + "@" + OpenfireUtil.getInstance().getConn().getServiceName();
            }
            if (!member.startsWith(username + "@")) {
                newMembers.add(member);
            }
        }
        multiUserChat.sendConfigurationForm(newConfigForm);

        Form otherForm = multiUserChat.getConfigurationForm().createAnswerForm();
//        otherForm.setAnswer("muc#roomconfig_roomadmins", newMembers);
//        otherForm.setAnswer("muc#roomconfig_roomowners", newMembers);
//        List<String> owners = new ArrayList<String>();
        newMembers.add(username + "@" + OpenfireUtil.getInstance().getConn().getServiceName());
        otherForm.setAnswer("muc#roomconfig_roomowners", newMembers);
        multiUserChat.sendConfigurationForm(otherForm);
        multiUserChat.changeSubject(topic);
//        multiUserChat.revokeAdmin(newMembers);
        DataUtil.addGroupChat(roomJid,multiUserChat);
        setGroupChatMembersAuthority(roomJid,members);
        for (Iterator<String> it = newMembers.iterator(); it.hasNext();) {
            String memberJid = it.next();
            if(!memberJid.startsWith(username + "@")){
            	sendGroupInvitations(memberJid, roomJid,topic,desc, true);
            }
        }
        return roomJid;
    }
	
	public static void revokeOwnership(String userJid,String roomJid) throws Exception{
		MultiUserChat multiUserChat = DataUtil.getGroupChat(roomJid);
		if(multiUserChat.getOccupant(roomJid + "/" + userJid) != null){
			multiUserChat.revokeOwnership(userJid + "@" + getServiceName());
			multiUserChat.revokeAdmin(userJid + "@" + getServiceName());
			multiUserChat.revokeMembership(userJid + "@" + getServiceName());
		}
	}
	
	public static void revokeOwnershipByUserDel(String userJid) throws Exception{
		Map<String, MultiUserChat> map = DataUtil.getValue(Constants.DataKey.groupChat);
		if(map != null){
			java.util.Set<String> keys = map.keySet();
			for(String roomJid:keys){
				revokeOwnership(userJid,roomJid);
			}
		}
	}
	
	//退出群组
	public static void quitGroupChat(String roomJid){
		quitGroupChat(roomJid, false);
	}
	//确认退出群组
	public static void quitGroupChat(String roomJid,boolean confirmed){
		StringBuilder sb = new StringBuilder();
		sb.append("<operate name=\""+Constants.QUIT_GROUPCHAT+"\">");
		sb.append("<roomjid>").append(roomJid).append("</roomjid>");
		sb.append("<user>").append(DataUtil.getUserName()).append("</user>");
		if(confirmed){
			sb.append("<confirm/>");
		}
		sb.append("</operate>");
		
		IQ iq = new VovoIQ(sb.toString(),GROUPCHAT_SPACE_NAME);
		if(confirmed){
			try {
				sendSynPacket(iq,1000);
			} catch (XMPPException e) {
			}
			MultiUserChat muc = DataUtil.getGroupChat(roomJid);
			try {
				muc.destroy("群主退出，该群被解散。", DataUtil.getUserName());
			} catch (XMPPException e) {
				e.printStackTrace();
			}
		}else{
			OpenfireUtil.getInstance().getConn().sendPacket(iq);
		}
	}
	
	//获取群组的成员列表
	public static List<String> getGroupChatMembers(String roomJid) throws Exception{
		List<String> list = new ArrayList<String>();
		MultiUserChat muc = DataUtil.getGroupChat(roomJid);
		Collection<Affiliate> col = muc.getOwners();
		for(Affiliate af:col){
			list.add(af.getJid());
		}
		return list;
	}
	
	public static boolean isGroupChatMember(String roomJid,String userAccount) throws Exception{
		boolean flag = false;
		List<String> list = getGroupChatMembers(roomJid);
		for(String str:list){
			str = VovoStringUtil.getHeadString(str, "@" + getConn().getServiceName());
			if(str!=null && str.equals(userAccount)){
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	public static void kickOneFromGroupChat(MemberBean bean,String roomJid) throws Exception{
		MultiUserChat muc = DataUtil.getGroupChat(roomJid);
		muc.revokeOwnership(bean.getLccAccount() + "@" + getServiceName());
		muc.revokeAdmin(bean.getLccAccount() + "@" + getServiceName());
		muc.grantMembership(bean.getLccAccount() + "@" + getServiceName());
//		muc.kickParticipant(bean.getLccAccount(), roomJid);
		muc.banUser(bean.getLccAccount() + "@" + getServiceName(), roomJid);
//		if(getUserState(bean.getLccAccount()) == Constants.STATUS_OFFLINE){
//			notifyGroupChatByKickOne(muc,bean.getLccAccount(),roomJid);
//		}
		notifyGroupChatByKickOne(muc,bean.getLccAccount(),roomJid);
	}
	
	
	public static String getServiceName(){
		return OpenfireUtil.getInstance().getConn().getServiceName();
	}
	
    
	//发送邀请加入群组信息
    public static void sendGroupInvitations(String targetUserJid,String roomJid,String topic,String desc,boolean autoJoin) throws Exception{
        
        Message message = new Message(targetUserJid);
        message.addExtension(new GroupChatInvitation(roomJid));
        message.setProperty(GROUPCHAT_TOPIC, topic);
        message.setProperty(GROUPCHAT_DESC, desc);
        message.setProperty(AUTO_JOIN_GROUPCHAT, autoJoin);
        message.setProperty(GROUPCHAT_JOIN_TYPE, GROUPCHAT_AGREE_JOIN);
        OpenfireUtil.getInstance().getConn().sendPacket(message);
    }
	
    
    public static boolean isjoinGroupChat(String userJid,String roomJid) throws Exception{
    	MultiUserChat multiUserChat = DataUtil.getGroupChat(roomJid);
    	Collection<Affiliate> col = multiUserChat.getOwners();
    	String userName = VovoStringUtil.getHeadString(userJid, "@");
    	for(Affiliate af:col){
    		if(userName.equals(VovoStringUtil.getHeadString(af.getJid(), "@"))){
    			return true;
    		}
    	}
    	return false;
    }
    
    public static void refuseOneJoinGroupChat(String userJid,String roomJid) throws Exception{
    	MultiUserChat multiUserChat = DataUtil.getGroupChat(roomJid);
    	Message message = new Message(userJid);
        message.addExtension(new GroupChatInvitation(roomJid));
        message.setProperty(GROUPCHAT_TOPIC, getRoomSubject(multiUserChat));
        message.setProperty(GROUPCHAT_JOIN_TYPE, GROUPCHAT_REFUSE_JOIN);
        OpenfireUtil.getInstance().getConn().sendPacket(message);
    }
    
    
    //同意某人加入群组
    public static void agreeOneJoinGroupChat(String targetUserJid,String roomJid) throws Exception{
    	MultiUserChat multiUserChat = DataUtil.getGroupChat(roomJid);
    	if(isjoinGroupChat(targetUserJid,roomJid)){
    		return;
    	}
    	Form otherForm = multiUserChat.getConfigurationForm().createAnswerForm();
    	List<String> newMembers = new ArrayList<String>();
    	newMembers.add(targetUserJid);
//    	otherForm.setAnswer("muc#roomconfig_roomowners", newMembers);不用这个属性，是因为会把之前的owner变成普通成员
        otherForm.setAnswer("muc#roomconfig_roomadmins", newMembers);
        multiUserChat.sendConfigurationForm(otherForm);
//        multiUserChat.revokeAdmin(newMembers);
        multiUserChat.grantOwnership(newMembers);
        notifyGroupChatByJoinOne(multiUserChat,targetUserJid,roomJid);
        sendGroupInvitations(targetUserJid,roomJid,getRoomSubject(multiUserChat),getRoomDesc(roomJid),true);
    }
    
    public static void changeGroupChatTopicAndDesc(String roomJid,String topic,String desc) throws Exception{
    	MultiUserChat muc = DataUtil.getGroupChat(roomJid);
    	if(!topic.equals(getRoomSubject(roomJid))){
    		muc.changeSubject(topic);
    	}
    	Form form1 = muc.getConfigurationForm();
        Form submitForm1 = form1.createAnswerForm();
        submitForm1.setAnswer("muc#roomconfig_roomname", StringUtils.escapeForXML(topic));
        submitForm1.setAnswer("muc#roomconfig_roomdesc", StringUtils.escapeForXML(desc));
        muc.sendConfigurationForm(submitForm1);
        notifyGroupChatByChangeTopicDesc(muc,roomJid,topic,desc);
    }
    
    public static void notifyGroupChatByChangeTopicDesc(MultiUserChat muc,String roomJid,String topic,String desc) throws Exception{
    	Message temp = muc.createMessage();
        temp.setBody("");
        temp.setProperty(Constants.GROUPCHAT_NOTIFY_TYPE, Constants.DataKey.GROUPCHAT_NOTIFY_TYPE_CHANGE_TOPIC_DESC.toString());
        temp.setProperty("topic", topic);
        temp.setProperty("desc", desc);
        temp.setProperty("roomJid", roomJid);
        muc.sendMessage(temp);
    }
    
    //通知群组有新成员加入
    public static void notifyGroupChatByJoinOne(MultiUserChat muc,String targetUserJid,String roomJid) throws Exception{
    	Message temp = muc.createMessage();
        temp.setBody("");
        temp.setProperty(Constants.GROUPCHAT_NOTIFY_TYPE, Constants.DataKey.GROUPCHAT_NOTIFY_TYPE_JOIN.toString());
        temp.setProperty("userJid", targetUserJid);
        temp.setProperty("roomJid", roomJid);
        muc.sendMessage(temp);
    }
    
//    private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//    public static void sendGroupChatMsg(MultiUserChat muc,String msg,String toPerson) throws Exception {
//        
//        Message temp = muc.createMessage();
//        temp.setBody(msg);
//        temp.setProperty("sendDate", dateFormat.format(new Date()));
//        muc.sendMessage(temp);
//    }
    
    private static Packet sendSynPacket(Packet packet,int t)throws XMPPException{
    	PacketCollector collector = OpenfireUtil.getInstance().getConn().createPacketCollector(new PacketIDFilter(packet.getPacketID()));
        OpenfireUtil.getInstance().getConn().sendPacket(packet);
        Packet response = (Packet)collector.nextResult(t);
        collector.cancel();
        if (response == null) {
            throw new XMPPException("No response from the server.");
        }
        return response;
    }
    
    private static Packet sendSynPacket(Packet packet)throws XMPPException{
        return sendSynPacket(packet,SmackConfiguration.getPacketReplyTimeout());
    }
    
    public static int getUserState(String jid) throws Exception{
    	int state = 0;
    	if(roster == null){
    		sendSynPacket(new RosterPacket());
    		roster = OpenfireUtil.getInstance().getConn().getRoster();
    	}
    	if(jid != null){
    		if(DataUtil.getUserName().indexOf(jid)>-1){
    			LoginInfo loginInfo = Vovo.getValue(Constants.DataKey.LOGGININFO.toString());
    			return loginInfo.getStatus();
    		}
    		int idx = jid.indexOf("@");
    		if(idx<0){
    			jid = jid + "@" + OpenfireUtil.getInstance().getConn().getServiceName();
    		}
    	}
    	Presence p = roster.getPresence(jid);
        return convertPresence(p);
    }
    
    public static int convertPresence(Presence p){
    	int state = Constants.STATUS_OFFLINE;
    	if(p!=null){
    		Presence.Type type = p.getType();
        	if(type.equals(Presence.Type.available)){
        		 Presence.Mode m = p.getMode();
    	       	 if(m==null){
    	       		 state = Constants.STATUS_ONLINE;
    	       	 }else if(m==Presence.Mode.away){
    	       		 state = Constants.STATUS_AWAY;
    	       	 }else if(m==Presence.Mode.dnd){
    	       		 state = Constants.STATUS_BUSY;
    	       	 }
        	}
    	}
    	return state;
    }
    
    public static void sendMyIpAddress(String to,MemberBean bean) throws Exception{
    	
    }
    
    public static void changeMyPresence(int status){
    	Presence presence = new Presence(Presence.Type.available);
    	if(Constants.STATUS_ONLINE == status){
    		;
    	}else if(Constants.STATUS_AWAY == status){
    		presence.setMode(Presence.Mode.away);
    	}else if(Constants.STATUS_BUSY == status){
    		presence.setMode(Presence.Mode.dnd);
    	}
    	OpenfireUtil.getInstance().getConn().sendPacket(presence);
    }
    
	public static XMPPConnection getConn(){
		return OpenfireUtil.getInstance().getConn();
	}
    
    public static void sendFriendMsg(String to, String context, FontStyle fontStyle, Map<String,String> imgs, long time) throws Exception{
    	Message msg = new Message();
    	msg.setBody(context);
    	setFontStyle(msg, fontStyle);
    	msg.setProperty("sendDate", time);
    	setMsgImgProperty(msg,imgs);
    	getFriendChat(to).sendMessage(msg);
    }
    
    private static void setFontStyle(Message msg, FontStyle fontStyle){
    	msg.setProperty("fontName", fontStyle.getName());
    	msg.setProperty("fontSize", fontStyle.getSize());
    	msg.setProperty("fontColor", fontStyle.getColor().getRGB());
    	msg.setProperty("isBold", fontStyle.getBold());
    	msg.setProperty("isItalic", fontStyle.getItalic());
    	msg.setProperty("isUnderLine", fontStyle.getUnderLine());
    }
    
    public static FontStyle getFontStyle(Message msg){
    	FontStyle style = new FontStyle();
    	style.setName((String)msg.getProperty("fontName"));
    	style.setSize((Integer)msg.getProperty("fontSize"));
    	style.setColor(new Color((Integer)msg.getProperty("fontColor")));
    	style.setItalic((Boolean)msg.getProperty("isItalic"));
    	style.setBold((Boolean)msg.getProperty("isBold"));
    	style.setUnderLine((Boolean)msg.getProperty("isUnderLine"));
    	return style;
    }
    
    
    
    private static void setMsgImgProperty(Message msg,Map<String,String> imgs){
    	Set<String> keys = imgs.keySet();
    	for(String key:keys){
    		msg.setProperty(key, imgs.get(key));
    	}
    }
    
    private static Chat getFriendChat(String lccno){
    	Connection conn = getConn();
    	Chat chat = DataUtil.getFriendChat(lccno);
    	if(chat == null){
    		chat = conn.getChatManager().createChat(lccno + "@" + conn.getServiceName(), new FriendMsgListener());
    		DataUtil.addFriendChat(lccno, chat);
    	}
    	return chat;
    }
    
    private static ConcurrentLinkedQueue<MyEvent> events = new ConcurrentLinkedQueue<MyEvent>();

    public static void sendMsgAfterDataReady(String eventId, Object[] paras){
    	if(!TreeUtil.isDataReady()){
    		MyEvent e = new MyEvent();
    		e.setId(eventId);
    		e.setParas(paras);
    		events.add(e);
    	}else{
    		Vovo.sendMessage(eventId, paras);
    	}
    }
    
    public static void doAfterDataIsReady(){
    	while(!events.isEmpty()){
    		MyEvent event = events.poll();
    		Vovo.sendMessage(event);
    	}
    }
    
    public static void sendGroupChatMsg(String roomJID, String context, FontStyle fontStyle, Map<String,String> imgs, long time)throws Exception{
    	MultiUserChat groupChat = DataUtil.getGroupChat(roomJID);

    	Message msg = groupChat.createMessage();
    	msg.setBody(context);
    	setFontStyle(msg, fontStyle);
    	msg.setProperty("sendDate", time);
    	setMsgImgProperty(msg, imgs);
    	groupChat.sendMessage(msg);
    }
    
    public static String sendFile(final File file, String lccno)throws Exception{
    	XMPPConnection conn = getConn();
    	OutgoingFileTransfer.setResponseTimeout(Constants.SEND_FILE_TIMEOUT);
        FileTransferManager manager = new FileTransferManager(conn);		
        OutgoingFileTransfer transfer = manager.createOutgoingFileTransfer(lccno + "@" + conn.getServiceName() + "/" + Constants.OPENFIRE_RESOURCE);
        transfer.sendFile(file, "");
        String streamId = transfer.getStreamID();
        log.info("send file stream id " + streamId);
        DataUtil.addFileTransfer(streamId, transfer);
        new FileTransferThread(streamId).start();
        return streamId;
    }
    
    public static void cancelSendFile(String streamID, String lccno, long time)throws Exception{
    	FileTransfer transfer = DataUtil.getFileTransfer(streamID);
    	transfer.cancel();
    	//send msg
    	Message msg = new Message();
    	msg.setBody(DataUtil.getUserName() + " cancel send file streamID " + streamID);
    	msg.setProperty("operate", Constants.OTHERMSG_OPERATE_CANCELSENDFILE);
    	msg.setProperty("streamID", streamID);
    	msg.setProperty("sendDate", time);
    	getFriendChat(lccno).sendMessage(msg);
    	DataUtil.removeFileTransfer(streamID);
    }
    
    public static void acceptSendFile(String streamID, String filePath)throws XMPPException{
    	FileTransferRequest req = DataUtil.getFileTransferRequest(streamID);
    	IncomingFileTransfer transfer = req.accept();
    	DataUtil.addFileTransfer(streamID, transfer);
    	transfer.recieveFile(new File(filePath));
    	new FileTransferThread(streamID).start();
    	DataUtil.removeFileTransferRequest(streamID);
    }
    
    public static void rejectSendFile(String streamID){
    	FileTransferRequest req = DataUtil.getFileTransferRequest(streamID);
    	req.reject();
    	DataUtil.removeFileTransferRequest(streamID);
    }
    
    public static void cancelSendFileInProcess(String streamID){
    	FileTransfer transfer = DataUtil.getFileTransfer(streamID);
    	transfer.cancel();
    	DataUtil.removeFileTransfer(streamID);
    }
    
    private static class FileTransferThread extends Thread{
    	
    	private String streamID;
    	private String lccno;
    	
    	
    	public FileTransferThread(String streamID){
    		this.streamID = streamID;
    	}
    	
    	@Override
    	public void run() {
    		FileTransfer transfer = DataUtil.getFileTransfer(streamID);
    		lccno = transfer.getPeer().split("@")[0];
		      while(!transfer.isDone()) {
		            if(transfer.getStatus().equals(FileTransfer.Status.error)) {
		            	break;
		            }else if(transfer.getStatus().equals(FileTransfer.Status.in_progress)){
		            	int percent = (int)(transfer.getProgress() * 100);
		            	Vovo.sendMessage("updateFileProcess", new Object[]{lccno, streamID, percent});
		            }else {
//		            	log.info("FileTransferThread status " + transfer.getStatus());
//		                log.info("FileTransferThread progress " + transfer.getProgress());
		            }
		            try{
		            	sleep(1000);
		            }catch(Exception e){
		            	log.error("FileTransferThread run", e);
		            }
		      }
		      if(transfer.getStatus().equals(FileTransfer.Status.complete)){
		    	  if(transfer.getAmountWritten() != transfer.getFileSize()){
		    		  Vovo.sendMessage("transferInterrupt", new Object[]{lccno, streamID, transfer.getFileName()});
		    	  }else{
		    		  Vovo.sendMessage("sendFileComplete", new Object[]{lccno, streamID, transfer.getFileName()});
		    	  }
		      }else if(transfer.getStatus().equals(FileTransfer.Status.refused)){
		    	  Vovo.sendMessage("otherRejectSendFile", new Object[]{lccno, streamID, transfer.getFileName()});
		      }else if(transfer.getStatus().equals(FileTransfer.Status.error)){
		          try{
		        	  sleep(500);//等待
		          }catch(Exception e){
		        	  log.error("FileTransferThread run", e);
		          }
		    	  log.info("FileTransferThread ERROR!!! " + transfer.getError());
		    	  log.info("FileTransferThread Exception!!! " + transfer.getException());
		    	  if(transfer.getError() == FileTransfer.Error.no_response){		    		  
		    		  Vovo.sendMessage("transferTimeOut", new Object[]{lccno, streamID, transfer.getFileName()});	
		    	  }else{
		    		  Vovo.sendMessage("transferInterrupt", new Object[]{lccno, streamID, transfer.getFileName()});
		    	  }
		      }
		      log.info("end thread status " + transfer.getStatus());
    	}
    }
    
    
    /*public static void inviteOneToGroupChat(String roomJid,String lccAccount) throws Exception{
    	MultiUserChat muc = DataUtil.getGroupChat(roomJid);
    	Message message = new Message();
    	message.setProperty("roomSubject", getRoomSubject(roomJid));
    	message.setProperty("inviterName", DataUtil.getMyInfo().getRealName());
    	muc.invite(message,lccAccount + "@" + getServiceName() + "/" + Constants.OPENFIRE_RESOURCE, DataUtil.getUserName());
    	
    }*/
    
    public static void inviteOneToGroupChat(String roomJid,String lccAccount) throws Exception{
    	Message message = new Message();
    	message.setBody("");
    	message.setProperty("roomJid", roomJid);
    	message.setProperty("roomSubject", getRoomSubject(roomJid));
    	message.setProperty("inviterName", DataUtil.getMyInfo().getRealName());
    	message.setProperty("inviter", DataUtil.getUserName() + "@" + getServiceName());
    	message.setProperty("reason", DataUtil.getUserName());
    	message.setProperty("password", "");
    	message.setTo(lccAccount + "@" + getServiceName() + "/" + Constants.OPENFIRE_RESOURCE);
    	getConn().sendPacket(message);
    }
    
    public static void closeConnection(){
    	getConn().disconnect();
    }
    
    public static void removeMultiUserChatByRoomJid(String roomJid){
    	MyMultiUserChat muc = (MyMultiUserChat)DataUtil.removeGroupChat(roomJid);
    	if(muc == null){
    		return;
    	}
    	muc.leave();
    	muc.removeAllListeners();
    	muc = null;
    	System.gc();
    }
    
    public static void reJoinMultiUserChat() throws Exception{
    	Map<String, MultiUserChat> map = DataUtil.getValue(Constants.DataKey.groupChat);
    	Collection<MultiUserChat> col = map.values();
    	for(MultiUserChat multiUserChat:col){
    		multiUserChat.join(DataUtil.getUserName());
    	}
    }
    
}
