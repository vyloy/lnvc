/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.dto;

import org.apache.log4j.Logger;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.ParticipantStatusListener;
import org.jivesoftware.smackx.packet.DelayInformation;

import com.lorent.common.util.ParaUtil;
import com.lorent.lvmc.event.MyEvent;
import com.lorent.lvmc.util.Constants;
import com.lorent.lvmc.util.MessageUtil;
import com.lorent.lvmc.util.LvmcOpenfireUtil;

/**
 *
 * @author jack
 */
public class MyMultiUserChat extends MultiUserChat{
    
    private Logger log = Logger.getLogger(MyMultiUserChat.class);
    
    private MyMessageListener myMessageListener;
    private MyParticipantListener myParticipantListener;
    private MyParticipantStatusListener myParticipantStatusListener;
    
    public MyMultiUserChat(Connection conn, String room){
        this(conn,room,true);
    }
    
    public MyMultiUserChat(Connection conn, String room, boolean flag){
    	super(conn, room);
    	if(flag){
    		addListeners();
    	}else{
//    		addParticipantStatusListener(new MyParticipantStatusListener());
    	}
    }
    
    private void addListeners(){
    	myMessageListener = new MyMessageListener();
		addMessageListener(myMessageListener);
		myParticipantListener = new MyParticipantListener();
        addParticipantListener(myParticipantListener);
        myParticipantStatusListener = new MyParticipantStatusListener();
        addParticipantStatusListener(myParticipantStatusListener);
    }
    
    public void reAddListeners(){
    	removeListeners();
    	addListeners();
    }

    public void removeListeners(){
    	if (myMessageListener != null) {
    		removeMessageListener(myMessageListener);
		}
    	if (myParticipantListener != null) {
    		removeParticipantListener(myParticipantListener);
		}
    	if (myParticipantStatusListener != null) {
    		removeParticipantStatusListener(myParticipantStatusListener);
		}
    }

	@Override
    public Chat createPrivateChat(String occupant, MessageListener listener) {
        return super.createPrivateChat(occupant, listener);
    }
    
    
    
        
    //=====================Room listener begin==========================
    private class MyMessageListener implements PacketListener{

        @Override
        public void processPacket(Packet packet) {
//            log.debug("message:" + packet.toXML());
        	Message msg = (Message)packet;
        	String from = msg.getFrom();
        	log.info("get msg from : " + from);
        	if(from.contains("/" + Constants.SUPPER_USER)){
        		PacketExtension extension = msg.getExtension(new DelayInformation(null).getNamespace());
        		if(extension == null){//不是历史信息
		    		log.info("get admin msg : " + msg.getBody());
		            MyEvent event = new MyEvent();
		            event.setId("getAdminMsg");
		            event.setParas(new Object[]{msg});
		            MessageUtil.getInstance().sendMessage(event);
        		}
    		}else{
	            MyEvent event = new MyEvent();
	            event.setId("getRoomMsg");
	            event.setParas(new Object[]{msg});
	            MessageUtil.getInstance().sendMessage(event);
    		}
        }
        
    }
    
    private class MyParticipantListener implements PacketListener{

        @Override
        public void processPacket(Packet packet) {
//        	Presence presence =  (Presence)packet;
//            log.info("Participant:"+presence+" xml: " + presence.toXML());
//            log.info("from:"+presence.getFrom()+", to:"+presence.getTo()+", available:"+presence.isAvailable()+", away:"+presence.isAway());
            MyEvent event = new MyEvent();
            event.setId("kickByRoom");
            event.setParas(new Object[]{(Presence)packet});
            MessageUtil.getInstance().sendMessage(event);
        }
        
    }
    
            
    private class MyParticipantStatusListener implements ParticipantStatusListener{

        @Override
        public void joined(String participant) {
        	log.info("joined "+participant);
            if(participant.lastIndexOf(Constants.SUPPER_USER) != -1){//admin
                return;
            }
            log.info("会议人员加入:" + participant);
            String member = LvmcOpenfireUtil.getNameFromOccupant(participant);
            ParaUtil paras = ParaUtil.newInstance().setString("member", member).setInt("status", Constants.MEMBER_STATUS_JOIN).setBoolean("isOpenfireUser", true);
            MessageUtil.getInstance().sendMessage("roomMemberChange", new Object[]{paras});
        }

        @Override
        public void left(String participant) {
        	log.info("left "+participant);
            if(participant.lastIndexOf(Constants.SUPPER_USER) != -1){//admin
                return;
            }
            log.info("会议人员离开:" + participant);
            String member = LvmcOpenfireUtil.getNameFromOccupant(participant);
            ParaUtil paras = ParaUtil.newInstance().setString("member", member).setInt("status", Constants.MEMBER_STATUS_LEAVE).setBoolean("isOpenfireUser", true);
            MessageUtil.getInstance().sendMessage("roomMemberChange", new Object[]{paras});
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
