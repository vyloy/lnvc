package com.lorent.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.FormField;
import org.jivesoftware.smackx.muc.HostedRoom;
import org.jivesoftware.smackx.muc.MultiUserChat;

import com.lorent.common.tree.MemberBean;
import com.lorent.model.ConferenceNewBean;
import com.lorent.model.UserBean;

public class OpenfireUtil {

	private Logger log = Logger.getLogger(OpenfireUtil.class);
	
	private static OpenfireUtil instance = new OpenfireUtil();
	
	private OpenfireUtil() {
    }

    public static OpenfireUtil getInstance() {
        return instance;
    }
	
	public synchronized static XMPPConnection initXMPPConnection() throws XMPPException {
        ConnectionConfiguration config = new ConnectionConfiguration(PropertiesUtil.getConstant("openfire.serverIP"), Integer.parseInt(PropertiesUtil.getConstant("openfire.serverPort")));
        config.setSASLAuthenticationEnabled(false);
//        config.setCompressionEnabled(true);
        config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
        SmackConfiguration.setPacketReplyTimeout(Integer.valueOf(PropertiesUtil.getConstant("openfire.timeout")));
        XMPPConnection conn = null;
        if (conn == null) {
            conn = new XMPPConnection(config);
        }
        try {
            if (!conn.isConnected()) {
                conn.connect();
            }
        } catch (XMPPException ex) {
            conn = null;//连接不到服务器时，conn设置为空，
            throw ex;
            //Logger.getLogger(OpenfireUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //DataUtil.setValue("serverIP", serverIP);
        return conn;
    }
	
	
	public void login(String username, String passwd) throws Exception {
        XMPPConnection conn = initXMPPConnection();
        conn.login(username, passwd);
    }
	
	private String getConferenceServiceName(XMPPConnection conn) throws Exception {
        
        Collection<String> serviceNames = MultiUserChat.getServiceNames(conn);
        for (Iterator<String> it = serviceNames.iterator(); it.hasNext();) {
            String servicename = it.next();
            if (servicename.indexOf("conference.") != -1) {
                return servicename;
            }
        }
        return null;
    }
	
	
	public String getHostedRoomJid(String topic,XMPPConnection conn) throws Exception {
        String roomJid = null;
        String servicename = getConferenceServiceName(conn);
        Collection<HostedRoom> hostedRooms = MultiUserChat.getHostedRooms(conn, servicename);
        for (Iterator<HostedRoom> it = hostedRooms.iterator(); it.hasNext();) {
            HostedRoom hostedRoom = it.next();
//            System.out.println("hostedRoom.getJid():" + hostedRoom.getJid() + "  hostedRoom.getName():" + hostedRoom.getName());
            if (hostedRoom.getName().equals(topic)) {
                roomJid = hostedRoom.getJid();
//                System.out.println("break");
                break;
            }
        }
        return roomJid;
    }
	
	public void removeConferenceRoom(List<String> confNos) throws Exception{
		XMPPConnection conn = null;
		String roomJid = null;
		MultiUserChat multiUserChat = null;
		try{
			conn = initXMPPConnection();
	        conn.login(PropertiesUtil.getConstant("initdata.admin.name"), PropertiesUtil.getConstant("initdata.admin.password"));//PropertiesUtil.getConstant("initdata.admin.password")
	        
	        String conferenceService = getConferenceServiceName(conn);
	        if (conferenceService == null) {
	            return ;
	        }
	        
	        for(String confNo:confNos){
	        	roomJid = confNo + "@" + conferenceService;
	        	multiUserChat = new MultiUserChat(conn, roomJid);
	        	multiUserChat.join(PropertiesUtil.getConstant("initdata.admin.name"));
	        	multiUserChat.destroy(null, null);
	        	multiUserChat.leave();
	        	multiUserChat = null;
	        }
		}catch(Exception ex){
			throw ex;
		}finally{
			if(conn !=null){
				conn.disconnect();
			}
		}
	}
	
	public void updateConferenceRoom(ConferenceNewBean conferenceNew, List<String> members,UserBean user,List<UserBean> oldUsers) throws Exception{
		XMPPConnection conn = null;
		try{
			conn = initXMPPConnection();
	        conn.login(PropertiesUtil.getConstant("initdata.admin.name"), PropertiesUtil.getConstant("initdata.admin.password"));//PropertiesUtil.getConstant("initdata.admin.password")
	        String topic = conferenceNew.getConferenceName();
	        String conferenceService = getConferenceServiceName(conn);
	        if (conferenceService == null) {
	            return ;
	        }
	        String roomJid = conferenceNew.getConfNo() + "@" + conferenceService;
	        MultiUserChat multiUserChat = new MultiUserChat(conn, roomJid);
	        multiUserChat.join(PropertiesUtil.getConstant("initdata.admin.name"));
	        Form newConfigForm = multiUserChat.getConfigurationForm().createAnswerForm();
	        newConfigForm.setAnswer("muc#roomconfig_roomname", topic);
	        newConfigForm.setAnswer("muc#roomconfig_roomdesc", topic);
	        //newConfigForm.setAnswer("muc#roomconfig_roomowners", new ArrayList());
//	        List<String> newMembers = new ArrayList<String>();
//	        newConfigForm.setAnswer("muc#roomconfig_roomowners", newMembers);
//	        List<String> oldMembers = new ArrayList<String>();
//	        if(oldUsers!=null && oldUsers.size()>0){
//	        	for(UserBean ub: oldUsers){
//	        		if(members.contains(ub.getLccAccount()) || ub.getLccAccount().equals(PropertiesUtil.getConstant("initdata.admin.name"))){
//	        			continue;
//	        		}
//	        		multiUserChat.revokeOwnership(ub.getLccAccount() + "@" + conn.getServiceName());
//	        		oldMembers.add(ub.getLccAccount() + "@" + conn.getServiceName());
//	        	}
//	        }
//	        if(oldMembers.size()>0){
//	        	multiUserChat.banUsers(oldMembers);
//	        }
	        
	        multiUserChat.sendConfigurationForm(newConfigForm);
	        
//	        List<String> newMembers = new ArrayList<String>();
//            for (Iterator<String> it = members.iterator(); it.hasNext();) {
//                String member = it.next();
//                newMembers.add(member + "@" + conn.getServiceName());
//            }
//	        Form otherForm = multiUserChat.getConfigurationForm().createAnswerForm();
//            otherForm.setAnswer("muc#roomconfig_roomowners", newMembers);
//            multiUserChat.sendConfigurationForm(otherForm);
            try{
            	multiUserChat.changeSubject(topic);
            }catch(Exception e1){
            	String message = e1.getMessage();
            }
            multiUserChat.sendMessage("conf.change");
	        multiUserChat.leave();
		}catch(Exception ex){
			throw ex;
		}finally{
			if(conn !=null){
				conn.disconnect();
			}
		}
		
	}
	
	public String createConferenceRoom(ConferenceNewBean conferenceNew, List<String> members,boolean invitation,UserBean user) throws Exception {
		XMPPConnection conn = null;
		try{
			conn = initXMPPConnection();
	        conn.login(PropertiesUtil.getConstant("initdata.admin.name"), PropertiesUtil.getConstant("initdata.admin.password"));//PropertiesUtil.getConstant("initdata.admin.password")
	        String topic = conferenceNew.getConferenceName();
//	        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssS");
//	        String sessionId = format.format(new Date());
	        String conferenceService = getConferenceServiceName(conn);
	        if (conferenceService == null) {
	            return null;
	        }
	        String roomJid = conferenceNew.getConfNo() + "@" + conferenceService;
	        //查找是否已存在该主题的聊天室
//	        String getRoomJid = getHostedRoomJid(topic);
//	        if (getRoomJid != null) {
//	            roomJid = getRoomJid;
//	        } else {
	            //创建聊天室
	            MultiUserChat multiUserChat = new MultiUserChat(conn, roomJid);
	            multiUserChat.create(user.getLccAccount());
	            Form form = multiUserChat.getConfigurationForm();
	            Form newConfigForm = form.createAnswerForm();
	            for (Iterator fields = form.getFields(); fields.hasNext();) {
	                FormField field = (FormField) fields.next();
	                if (!FormField.TYPE_HIDDEN.equals(field.getType()) && field.getVariable() != null) {
	                    // Sets the default value as the answer
//	                	System.out.println("default answer " + field.getVariable());
	                	newConfigForm.setDefaultAnswer(field.getVariable());
	                }
	            }
	            
	            newConfigForm.setAnswer("muc#roomconfig_publicroom", true);//列出目录中的房间
	            newConfigForm.setAnswer("muc#roomconfig_persistentroom", true);//房间是持久的
	            newConfigForm.setAnswer("muc#roomconfig_roomname", topic);
	            newConfigForm.setAnswer("muc#roomconfig_roomdesc", topic);
	            List<String> list = new ArrayList<String>();
	            list.add("0");
	            newConfigForm.setAnswer("muc#roomconfig_maxusers", list);//最大房间占有者人数,0表示无限制
	            newConfigForm.setAnswer("muc#roomconfig_enablelogging", true);//记录日志
//	            List<String> newMembers = new ArrayList<String>();
//	            for (Iterator<String> it = members.iterator(); it.hasNext();) {
//	                String member = it.next();
//	                if (member.indexOf("@") == -1) {
//	                    member = member + "@" + conn.getServiceName();
//	                }
//	                if (!member.startsWith(user.getLccAccount() + "@")) {
//	                    newMembers.add(member);
//	                }
//	            }
//	            
//	            newMembers.add(user.getLccAccount() + "@" + conn.getServiceName());
//	            newConfigForm.setAnswer("muc#roomconfig_roomowners", newMembers);
//	            newConfigForm.setAnswer("muc#roomconfig_roomadmins", newMembers);
	            multiUserChat.sendConfigurationForm(newConfigForm);

//	            Form otherForm = multiUserChat.getConfigurationForm().createAnswerForm();
//	            otherForm.setAnswer("muc#roomconfig_roomowners", newMembers);
//	            multiUserChat.sendConfigurationForm(otherForm);
//	            if (invitation) {
//	                for (Iterator<String> it = newMembers.iterator(); it.hasNext();) {
//	                    String memberJid = it.next();
//	                    sendGroupInvitations(memberJid, roomJid);
//	                }
//	            }
			//} 
	            conn.disconnect();
	            return roomJid;
		}catch(Exception ex){
			throw ex;
		}finally{
			if(conn !=null){
				conn.disconnect();
			}
		}
    }
	
	public boolean existGroup(String groupName)throws Exception{
		String url = getUserServiceUrl() + "&type=existGroup&username=admin&groups=" + groupName;
		String ret = HttpClientUtil.executeGet(url);
		if(ret.contains("true")){
			return true;
		}else{
			return false;
		}
	}
	
	public void createShareGroup(String groupName)throws Exception{
		String url = getUserServiceUrl() + "&type=createShareGroup&username=admin&groups=" + groupName;
		HttpClientUtil.executeGet(url);
	}
	
	public void removeShareGroup(String groupName)throws Exception{
		String url = getUserServiceUrl() + "&type=removeShareGroup&username=admin&groups=" + groupName;
		HttpClientUtil.executeGet(url);
	}
	
	public void addGroupUser(String groupName, String username)throws Exception{
		boolean exist = existGroup(PropertiesUtil.getConstant("openfire.systemGroup"));
		if(!exist){
			createShareGroup(PropertiesUtil.getConstant("openfire.systemGroup"));
		}
		String url = getUserServiceUrl() + "&type=addGroupUser&username=" + username + "&groups=" + groupName;
		String res = HttpClientUtil.executeGet(url);
		log.info("res============" + res);
		if(res == null || !res.contains("ok")){
			throw new Exception("openfire addGroupUser error");
		}
	}
	
	public void removeGroupUser(String groupName, String username)throws Exception{
		String url = getUserServiceUrl() + "&type=removeGroupUser&username=" + username + "&groups=" + groupName;
		String res = HttpClientUtil.executeGet(url);
		if(res == null || !res.contains("ok")){
			throw new Exception("openfire removeGroupUser error");
		}
	}
	
	public void updateGroupUser(String groupName, String username,String password,String email,String name) throws Exception{
		String url = getUserServiceUrl() + "&type=update&username=" + username + "&groups=" + groupName + "&password=" + password + "&email=" + email + "&name=" + name;
		String res = HttpClientUtil.executeGet(url);
		if(res == null || !res.contains("ok")){
			throw new Exception("openfire updateGroupUser error");
		}
	}
	
	private String getUserServiceUrl(){
		String url = "http://" + PropertiesUtil.getConstant("openfire.serverIP") + ":" + 
			PropertiesUtil.getConstant("openfire.httpPort") + "/plugins/userService/userservice?secret=" + 
			PropertiesUtil.getConstant("openfire.httpSecret");
		return url;
	}
	
	private Message getAdminGroupMsg(XMPPConnection conn){
		Message msg = new Message();
		msg.setFrom(PropertiesUtil.getConstant("initdata.admin.name") + "@" + conn.getServiceName());
		msg.setTo(PropertiesUtil.getConstant("openfire.systemGroup") + "@broadcast." + conn.getServiceName());
		return msg;
	}
	
	public void sendGroupBroadcast(String operate, Object obj)throws Exception{
		XMPPConnection conn = initXMPPConnection();
		conn.login(PropertiesUtil.getConstant("initdata.admin.name"), PropertiesUtil.getConstant("initdata.admin.password"));
		Message msg = getAdminGroupMsg(conn);
		msg.setProperty("operate", operate);
		msg.setProperty("data", obj);
		conn.sendPacket(msg);
		conn.disconnect();
	}
	
	private Message getDeletedUserMsg(XMPPConnection conn,MemberBean user){
		Message msg = new Message();
		msg.setFrom(PropertiesUtil.getConstant("initdata.admin.name") + "@" + conn.getServiceName());
		msg.setTo(user.getLccAccount() + "@" + conn.getServiceName());
		return msg;
	}
	
	public void sendMsgToDeletedUser(String operate, Object obj)throws Exception{
		XMPPConnection conn = initXMPPConnection();
		conn.login(PropertiesUtil.getConstant("initdata.admin.name"), PropertiesUtil.getConstant("initdata.admin.password"));
		Message msg = getDeletedUserMsg(conn,(MemberBean)obj);
		msg.setProperty("operate", operate);
		msg.setProperty("data", obj);
		conn.sendPacket(msg);
		conn.disconnect();
	}
	
	
	
	public void sendConfNotice(String confNo,String[] members,String msgText) throws Exception{
		XMPPConnection conn = initXMPPConnection();
		conn.login(PropertiesUtil.getConstant("initdata.admin.name"), PropertiesUtil.getConstant("initdata.admin.password"));
		Message msg = getAdminGroupMsg(conn);
//		msg.setProperty("msgText", msgText);
		msg.setProperty("confNo", confNo);
		msg.setProperty("operate", "CONF_NOTICE");
		msg.setProperty("usernameList", members);
		msg.setProperty("msgType", "ConfNotice");
		msg.setBody(msgText);
		conn.sendPacket(msg);
		conn.disconnect();
	}
}
