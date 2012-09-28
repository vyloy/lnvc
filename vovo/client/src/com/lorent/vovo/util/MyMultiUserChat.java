/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.vovo.util;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.muc.DefaultUserStatusListener;
import org.jivesoftware.smackx.muc.InvitationRejectionListener;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.ParticipantStatusListener;

import com.lorent.vovo.Vovo;
import com.lorent.vovo.dto.ControllerEvent;

/**
 *
 * @author jack
 */
public class MyMultiUserChat extends MultiUserChat{
    
    private Logger log = Logger.getLogger(MyMultiUserChat.class);
    private MyMessageListener ml;
    private MyParticipantListener pl;
    private MyUserStatusListener ul;
    private InvitationRejectionListener il;
    private MyParticipantStatusListener psl;
    
    public MyMultiUserChat(Connection conn, String room){
        this(conn,room,true);
    }
    
    public void removeAllListeners(){
    	log.info(this.hashCode() + " MyMultiUserChat remove listeners start");
    	this.removeInvitationRejectionListener(il);
    	this.removeMessageListener(ml);
    	this.removeParticipantListener(pl);
    	this.removeParticipantStatusListener(psl);
    	this.removeUserStatusListener(ul);
    	this.removeInvitationRejectionListener(il);
    	log.info(this.hashCode() + "MyMultiUserChat remove listeners end");
    }
    
    public MyMultiUserChat(Connection conn, String room, boolean flag){
    	super(conn, room);
    	if(flag){
    		ml = new MyMessageListener();
    		addMessageListener(ml);
    		pl = new MyParticipantListener();
            addParticipantListener(pl);
            psl = new MyParticipantStatusListener();
            addParticipantStatusListener(psl);
            ul = new MyUserStatusListener();
            addUserStatusListener(ul);//监听自己在会议室的情况
            il = new InvitationRejectionListener() {
                public void invitationDeclined(String invitee, String reason) {
                    Vovo.exeC("groupChat", "invitationDeclined", invitee, reason);
                }
            };
            this.addInvitationRejectionListener(il);
    	}else{
//    		addParticipantStatusListener(new MyParticipantStatusListener());
    	}
    }
    
    
    public void addListeners(){
    	addMessageListener(new MyMessageListener());
        addParticipantListener(new MyParticipantListener());
        addParticipantStatusListener(new MyParticipantStatusListener());
    }

    @Override
    public Chat createPrivateChat(String occupant, MessageListener listener) {
        return super.createPrivateChat(occupant, listener);
    }
    
    private class MyUserStatusListener extends DefaultUserStatusListener{
        public void banned(String actor,String reason){
        	log.info("my god! 我被踢出了(MyUserStatusListener)");
            Vovo.sendMessage("leafGroupChatByKick", new Object[]{reason});
        }
        
        public void kicked(String actor,String reason){
//        	Vovo.sendMessage("leafGroupChatByKick", new Object[]{reason});
        }
    }
    
        
    //=====================Room listener begin==========================
    private class MyMessageListener implements PacketListener{

        @Override
        public void processPacket(Packet packet) {
        	Message msg = (Message)packet;
        	if(!msg.getType().equals(Message.Type.groupchat)){
        		return;
        	}
        	String from = msg.getFrom();
//        	log.info(MyMultiUserChat.this.hashCode() + " get  groupchat msg from : " + from);
        	String notifyTypeName = (String)msg.getProperty(Constants.GROUPCHAT_NOTIFY_TYPE);
        	if(notifyTypeName == null){
//        		log.debug("getGroup msg:" + msg.toXML());
        		if(msg.getFrom().contains("/")){//user msg
        			String lccno = msg.getFrom().split("/")[1];
        			if(!lccno.equals(DataUtil.getUserName())){//not me
        				Collection<String> col = msg.getPropertyNames();
        				Map<String,String> imgs = new HashMap<String,String>();
        				List<String> propsKeys = MyOpenfireUtil.getPropertyKey();
        				for(String str:col){
        					if(propsKeys.contains(str)){
        						continue;
        					}
        					imgs.put(str, (String)msg.getProperty(str));
        				}
	        			ControllerEvent event = new ControllerEvent();
	        			event.setExClass("groupChat");
	        			event.setExMethod("getGroupChatMessage");
	        			event.setParas(new Object[]{ msg.getFrom().split("/")[0], lccno, msg.getBody(), MyOpenfireUtil.getFontStyle(msg), new Date((Long)msg.getProperty("sendDate")),imgs});
	        			MyOpenfireUtil.sendMsgAfterDataReady("showMessage", new Object[]{event, Constants.MSG_TYPE_GROUP});
        			}
        		}
        	}else{
        		if(notifyTypeName.equals(Constants.DataKey.GROUPCHAT_NOTIFY_TYPE_JOIN.toString())){
        			String userJid = (String)msg.getProperty("userJid");
        			String roomJid = (String)msg.getProperty("roomJid");
        			log.warn("有新成员加入了。。。。。。。。。。。。。。。");
        			Vovo.sendMessage("addMemberToGroupChatCallBack", new Object[]{userJid,roomJid});
        			//群组加入新成员的界面显示
        		}else if(notifyTypeName.equals(Constants.DataKey.GROUPCHAT_NOTIFY_TYPE_CHANGE_TOPIC_DESC.toString())){
        			String topic = (String)msg.getProperty("topic");
        			String desc = (String)msg.getProperty("desc");
        			String roomJid = (String)msg.getProperty("roomJid");
        			Vovo.sendMessage("changeGroupChatTopicAndDescCallBack", new Object[]{roomJid,topic,desc});
        		}else if(notifyTypeName.equals(Constants.DataKey.GROUPCHAT_NOTIFY_TYPE_QUIT.toString())){
        			String userJid = (String)msg.getProperty("userJid");
        			String roomJid = (String)msg.getProperty("roomJid");
        			log.warn("有成员退出了。。。。。。。。。。。。。。。" + userJid + ":" + roomJid);
        			Vovo.sendMessage("oneLeftFromGroupChat", new Object[]{userJid,roomJid});
        		}else if(notifyTypeName.equals(Constants.DataKey.GROUPCHAT_NOTIFY_TYPE_KICK_ONE.toString())){
        			String userJid = (String)msg.getProperty("userJid");
        			String roomJid = (String)msg.getProperty("roomJid");
        			log.warn("有成员被踢出群组了。。。。。。。。。。。。。。。" + userJid + ":" + roomJid);
        			Vovo.sendMessage("oneLeftFromGroupChat", new Object[]{userJid,roomJid});
        		}
        	}
        }
        
    }
    
    private class MyParticipantListener implements PacketListener{

        @Override
        public void processPacket(Packet packet) {
//        	Presence presence =  (Presence)packet;
//            log.info("Participant:"+presence+" xml: " + presence.toXML());
//            log.info("from:"+presence.getFrom()+", to:"+presence.getTo()+", available:"+presence.isAvailable()+", away:"+presence.isAway());
//            MyEvent event = new MyEvent();
//            event.setId("kickByRoom");
//            event.setParas(new Object[]{(Presence)packet});
//            MessageUtil.getInstance().sendMessage(event);
        }
        
    }
    
            
    private class MyParticipantStatusListener implements ParticipantStatusListener{

        @Override
        public void joined(String participant) {
        	log.info("joined "+participant);
//            if(participant.lastIndexOf(Constants.SUPPER_USER) != -1){//admin
//                return;
//            }
//            log.info("会议人员加入:" + participant);
//            String member = OpenfireUtil.getNameFromOccupant(participant);
//            ParaUtil paras = ParaUtil.newInstance().setString("member", member).setInt("status", Constants.MEMBER_STATUS_JOIN);
//            MessageUtil.getInstance().sendMessage("roomMemberChange", new Object[]{paras});
        }

        @Override
        public void left(String participant) {
        	log.info("left "+participant);
//            if(participant.lastIndexOf(Constants.SUPPER_USER) != -1){//admin
//                return;
//            }
//            log.info("会议人员离开:" + participant);
//            String member = OpenfireUtil.getNameFromOccupant(participant);
//            ParaUtil paras = ParaUtil.newInstance().setString("member", member).setInt("status", Constants.MEMBER_STATUS_LEAVE);
//            MessageUtil.getInstance().sendMessage("roomMemberChange", new Object[]{paras});
        	/*Vovo.sendMessage("oneLeftFromGroupChat", new Object[]{VovoStringUtil.getBottomString(participant, "/"),VovoStringUtil.getHeadString(participant, "/")});*/
        }

        @Override
        public void kicked(String participant, String actor, String reason) {
        	log.info("kicked "+participant+" "+actor+" "+reason);
        }

        @Override
        public void voiceGranted(String participant) {
        	log.info("voiceGranted "+participant);
        }

        @Override
        public void voiceRevoked(String participant) {
        	log.info("voiceRevoked "+participant);
        }

        @Override
        public void banned(String participant, String actor, String reason) {
        	log.info("banned "+participant+" "+actor+" "+reason);
//        	Vovo.sendMessage("ownerKickOneFromGroupChat", new Object[]{VovoStringUtil.getBottomString(participant, "/"),actor,reason});
        }

        @Override
        public void membershipGranted(String participant) {
        	log.info("membershipGranted "+participant);
        }

        @Override
        public void membershipRevoked(String participant) {
        	log.info("membershipRevoked "+participant);
        }

        @Override
        public void moderatorGranted(String participant) {
        	log.info("moderatorGranted "+participant);
        }

        @Override
        public void moderatorRevoked(String participant) {
        	log.info("moderatorRevoked "+participant);
        }

        @Override
        public void ownershipGranted(String participant) {
        	log.info("ownershipGranted "+participant);
        }

        @Override
        public void ownershipRevoked(String participant) {
        	log.info("ownershipRevoked "+participant);
        }

        @Override
        public void adminGranted(String participant) {
        	log.info("adminGranted "+participant);
        }

        @Override
        public void adminRevoked(String participant) {
        	log.info("adminRevoked "+participant);
        }

        @Override
        public void nicknameChanged(String participant, String newNickname) {
        	log.info("nicknameChanged "+participant+" "+newNickname);
        }

        
    }        
    //=====================Room listener end==========================
    
    
}
