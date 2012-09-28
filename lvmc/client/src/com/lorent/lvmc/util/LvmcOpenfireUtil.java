/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.jivesoftware.smack.AbstractConnectionListener;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smackx.muc.DiscussionHistory;

import com.lorent.common.util.OpenfireUtil;
import com.lorent.lvmc.dto.LoginInfo;
import com.lorent.lvmc.dto.MyMultiUserChat;
import com.lorent.lvmc.event.MyEvent;
import com.lorent.lvmc.event.MyVoteListener;
import com.lorent.lvmc.service.VoteIQ;
import com.lorent.lvmc.service.VoteIQProvider;

/**
 *
 * @author jack
 */
public class LvmcOpenfireUtil {

    private static Logger log = Logger.getLogger(LvmcOpenfireUtil.class);

    private static MyChatManagerListener myChatManagerListener;
    private static MyVoteListener myVoteListener;
    private static MyConnectionListener myConnectionListener;
    
    public static void doAddListener() throws Exception{
    	log.info("doAddListener");
    	XMPPConnection conn = OpenfireUtil.getInstance().getConn();
    	ProviderManager.getInstance().addIQProvider("query", Constants.XMLNS, new VoteIQProvider());
    	myChatManagerListener = new MyChatManagerListener();
    	conn.getChatManager().addChatListener(myChatManagerListener);// add private chat listener 
    	myVoteListener = new MyVoteListener();
        conn.addPacketListener(myVoteListener, new PacketFilter() {

            @Override
            public boolean accept(Packet packet) {
                return true;
            }
        });
        myConnectionListener = new MyConnectionListener();
        conn.addConnectionListener(myConnectionListener);
        DataUtil.setValue(DataUtil.Key.Connection, conn);
    }
    
    public static void doRemoveListener() throws Exception{
    	log.info("doRemoveListener");
    	ProviderManager.getInstance().removeIQProvider("query", Constants.XMLNS);
    	XMPPConnection conn = OpenfireUtil.getInstance().getConn();
    	conn.getChatManager().removeChatListener(myChatManagerListener);
    	conn.removePacketListener(myVoteListener);
    	conn.removeConnectionListener(myConnectionListener);
    	DataUtil.setValue(DataUtil.Key.Connection, null);
    }
    
    public static void login(String username, String passwd, String serverIP, int serverPort) throws Exception {
        log.info("建立连接");
        if(OpenfireUtil.getInstance().getConn() == null){
        	OpenfireUtil.getInstance().init(serverIP, serverPort, ConfigUtil.getIntProperty("timeout"));
        }
        
        XMPPConnection conn = OpenfireUtil.getInstance().getConn();
        ProviderManager.getInstance().addIQProvider("query", Constants.XMLNS, new VoteIQProvider());
        conn.getChatManager().addChatListener(new MyChatManagerListener());// add private chat listener 
        conn.addPacketListener(new MyVoteListener(), new PacketFilter() {

            @Override
            public boolean accept(Packet packet) {
                return true;
            }
        });
        try{
        	conn.connect();
        }catch(Exception ex){
        	throw new Exception(StringUtil.getErrorString("error.server.ip"));
        }
        
        log.info("登录");
        try{
        	conn.login(username, passwd);
        }catch(Exception e){
        	throw new Exception(StringUtil.getErrorString("error.username_password.msg"));
        }
        conn.addConnectionListener(new MyConnectionListener());
        DataUtil.setValue(DataUtil.Key.Connection, conn);
    }
    
    //退出登录
    public static void logout(){
    	if(null!=DataUtil.getValue(DataUtil.Key.Connection)){
    		((XMPPConnection)DataUtil.getValue(DataUtil.Key.Connection)).disconnect();
    	}
    }
    
    //监听是否重复登录
    private static class MyConnectionListener extends AbstractConnectionListener{
        public void connectionClosed(){
            log.info("正常退出");
//            MyEvent event = new MyEvent();
//            event.setId("exitApplicationByNotLinkOpenfire");
////            event.setParas(new Object[]{""});
//            MessageUtil.getInstance().sendMessage(event);
        }
        
        public void connectionClosedOnError(Exception e){
            log.info(e.toString());
            if("stream:error (conflict)".equalsIgnoreCase(e.toString())){
                MyEvent event = new MyEvent();
                event.setId("relogin");
//                event.setParas(new Object[]{""});
                MessageUtil.getInstance().sendMessage(event);
            }else{
            	MyEvent event = new MyEvent();
                event.setId("openfireDisconnectByNetwork");
                MessageUtil.getInstance().sendMessage(event);
                log.info("错误退出");
            }
            
        }
        
        public void reconnectionSuccessful(){
        	log.info("reconnectionSuccessful");
        	try{
        		MyEvent event = new MyEvent();
                event.setId("reconnect");
                MessageUtil.getInstance().sendMessage(event);
        	}catch(Exception ex){
        		log.error("reconnectionSuccessful", ex);
        	}
        	
        }
        
        public void reconnectionFailed(Exception e){
        	log.info("reconnectionFailed");
        }
        
        public void reconnectingIn(int seconds){
        	log.info("reconnectingIn " + seconds);
        }
    }
    
    public static void leaveRoom() throws Exception{
    	MyMultiUserChat muc = DataUtil.getValue(DataUtil.Key.Room);
    	DataUtil.setValue(DataUtil.Key.Room, null);
    	DataUtil.setValue(DataUtil.Key.ReadyToLeaveRoom, muc);
    	if (muc != null) {
    		muc.leave();
    		log.info("leaveRoom mcu != null");
		}
    	else{
    		log.info("leaveRoom mcu == null");
    	}
    }
    
    public static void enterRoom(String confno, String username)throws Exception{
       log.info("进入会议室");
       MyMultiUserChat oldMuc = DataUtil.removeElement(DataUtil.Key.Room);
       boolean flag = true;
       if(oldMuc!=null){
    	   flag = false;
    	   oldMuc.removeListeners();
    	   oldMuc.leave();
    	   
    	   Connection conn = DataUtil.removeElement(DataUtil.Key.Connection);
    	   doRemoveListener();
    	   conn.disconnect();
//    	   conn.connect();
//    	   conn.login(DataUtil.getLoginInfo().getUsername(), DataUtil.getLoginInfo().getPassWord());
//    	   DataUtil.setValue(DataUtil.Key.Connection, conn);
			OpenfireUtil.getInstance().init(DataUtil.getLoginInfo().getServerIP(), Integer.parseInt(DataUtil.getLoginInfo().getServerPort()), ConfigUtil.getIntProperty("timeout"));
			doAddListener();
			OpenfireUtil.getInstance().login(DataUtil.getLoginInfo().getUsername(), DataUtil.getLoginInfo().getPassWord(), "vovo");
			DataUtil.setValue(DataUtil.Key.Connection, OpenfireUtil.getInstance().getConn());
    	   
    	   //    	   conn = DataUtil.getValue(DataUtil.Key.Connection);
//           MyMultiUserChat muc = new MyMultiUserChat(conn, confno + "@conference." + conn.getServiceName(),flag);
//           muc.join(username);
//           DataUtil.setValue(DataUtil.Key.Room, muc);
       }
       Connection conn = DataUtil.getValue(DataUtil.Key.Connection);
       MyMultiUserChat muc = new MyMultiUserChat(conn, confno + "@conference." + conn.getServiceName(),true);
       DiscussionHistory history = new DiscussionHistory();
       history.setMaxStanzas(Constants.MAX_HISTORY);
       muc.join(username, null, history, SmackConfiguration.getPacketReplyTimeout());
       
       DataUtil.setValue(DataUtil.Key.Room, muc);
//    	log.info("进入会议室");
//        MyMultiUserChat oldMuc = DataUtil.getValue(DataUtil.Key.Room);
//        boolean flag = true;
//        if(oldMuc!=null){
//     	   flag = false;
//     	   oldMuc.join(DataUtil.getLoginInfo().getUsername());
//        }else{
//           
//        }
        
    }
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    public static void sendMsgToAll(String msg,String toPerson,String sendperson) throws Exception {
        LoginInfo loginInfo=DataUtil.getValue(DataUtil.Key.LoginInfo);
        MyMultiUserChat muc=DataUtil.getValue(DataUtil.Key.Room);
        Message temp = muc.createMessage();
        temp.setBody(msg);
        temp.setProperty("sendDate", dateFormat.format(new Date()));
        temp.setProperty("sender", sendperson);
        temp.setProperty("toperson",toPerson);
        muc.sendMessage(temp);
    }
    
    private static class privateMsgListener implements MessageListener{

		@Override
		public void processMessage(Chat chat, Message msg) {
			 log.debug(msg.getBody());
             MyEvent event = new MyEvent();
             event.setId("getRoomPrivateChatMsg");
             event.setParas(new Object[]{msg, chat});
             MessageUtil.getInstance().sendMessage(event);
		}
    	
    }
    
    private static class MyChatManagerListener implements ChatManagerListener{

        @Override
        public void chatCreated(Chat chat, boolean bln) {
           if (!bln) {
                    log.info("chat not create");
                    chat.addMessageListener(new privateMsgListener());
                    log.info(chat.getParticipant());
                    DataUtil.addPrivateChat(chat.getParticipant().split("/")[1], chat);
           }else{
        	   		log.info("chat is created");
        	   		log.info(chat.getParticipant());
           }
        }
    
    }
    
    public static void sendMsgToOne(String recieverName, String msg,String sendperson) throws Exception{
        Connection conn = DataUtil.getValue(DataUtil.Key.Connection);      
        MyMultiUserChat muc = DataUtil.getValue(DataUtil.Key.Room);
        LoginInfo info = DataUtil.getValue(DataUtil.Key.LoginInfo);
        
        Chat chat = DataUtil.getPrivateChat(recieverName);        
        if(chat == null){
        	chat = muc.createPrivateChat(info.getConfno() + "@conference." + conn.getServiceName() + "/" + recieverName, new privateMsgListener());
        	DataUtil.addPrivateChat(recieverName, chat);
        }
        Message temp = new Message();
        temp.setBody(msg);
        temp.setProperty("sendDate", dateFormat.format(new Date()));
        temp.setProperty("sender", sendperson);
        temp.setProperty("toperson",recieverName);
        chat.sendMessage(temp);
    }
    
    public static List<String> getCurrentMembers()throws Exception{
        MyMultiUserChat muc = DataUtil.getValue(DataUtil.Key.Room);
        Iterator<String> occupants = muc.getOccupants();
        List<String> ret = new ArrayList<String>();
        while(occupants.hasNext()){
        	String name = getNameFromOccupant(occupants.next());
        	log.info("获取会议在线人员：" + name);
            ret.add(name);
//            log.debug(occupants.next());
        }
//        log.debug("获取会议在线人员：" + ret);
        return ret;
    }
    
    public static String getNameFromOccupant(String occupant){
        return occupant.split("/")[1];
    }
   
    public static void sendVoteData(String str){
        
        IQ iq = new VoteIQ(str);
        //iq.setTo("fqq@conference.localhost");
        log.debug("发送数据：" + iq.toXML());
        Connection conn = DataUtil.getValue(DataUtil.Key.Connection);  
        conn.sendPacket(iq);   
    }
    
    //同步发送
    public static Packet sendSynPacket(Packet packet)throws Exception{
    	Connection conn = DataUtil.getValue(DataUtil.Key.Connection);
        PacketCollector collector = conn.createPacketCollector(new PacketIDFilter(packet.getPacketID()));
        conn.sendPacket(packet);
        Packet response = (Packet)collector.nextResult(SmackConfiguration.getPacketReplyTimeout());
        collector.cancel();
        if (response == null) {
            throw new XMPPException("No response from the server.");
        }
        return response;
    }
    
    public static void close(){
        Connection conn = DataUtil.getValue(DataUtil.Key.Connection);
        if(conn!=null){
            conn.disconnect();
        }
        DataUtil.removeElement(DataUtil.Key.Connection);
    }
    
    public static void reAddListeners(){
    	MyMultiUserChat muc = DataUtil.getValue(DataUtil.Key.Room);
    	muc.reAddListeners();
    }
    
    public static String getConfNo(){
    	String confNo = null;
    	MyMultiUserChat muc = DataUtil.getValue(DataUtil.Key.Room);
    	confNo = muc.getRoom().split("@")[0];
    	return confNo;
    }
}
