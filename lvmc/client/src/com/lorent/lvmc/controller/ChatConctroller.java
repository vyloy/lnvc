/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.controller;

import com.lorent.common.tree.BroadcastEvent;
import com.lorent.common.util.ParaUtil;
import com.lorent.lvmc.Launcher;
import com.lorent.lvmc.bean.ChatRecordBean;
import com.lorent.lvmc.bean.PicInfo;
import com.lorent.lvmc.service.ChatService;
import com.lorent.lvmc.ui.ChatMainPanel;
import com.lorent.lvmc.dto.LoginInfo;
import com.lorent.lvmc.dto.MemberDto;
import com.lorent.lvmc.service.ConfService;
import com.lorent.lvmc.util.Constants;
import com.lorent.lvmc.util.DataUtil;
import com.lorent.lvmc.util.StringUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.packet.Message;

/**
 *
 * @author test
 */
public class ChatConctroller extends BaseController{
    private static Logger log = Logger.getLogger(ChatConctroller.class);

//    
    public void recievePrivateMessage(Message msg,Chat chat) throws Exception {
        String[] msgbody = new String[2];
        if (msg.getProperty("sender") == null || msg.getProperty("sendDate") == null || msg.getBody() == null || msg.getBody().equals("")) {
        	log.info("recievePrivateMessage msg.getbody,sender,sendDate == null");
			return;
		}
        if (msg.getBody().indexOf("-^*$+%") > 0) {
            msgbody[0] = msg.getBody().substring(0, msg.getBody().indexOf("-^*$+%"));
            msgbody[1] = msg.getBody().substring(msg.getBody().indexOf("-^*$+%") + 6);
        } else {
            msgbody = msg.getBody().split("[*]");
        }
        LoginInfo loginInfo=DataUtil.getValue(DataUtil.Key.LoginInfo);
        List<PicInfo> myPicInfo = new LinkedList<PicInfo>();
        if (null != msgbody && msgbody.length > 1 && null != msgbody[1] && msgbody[1].length() > 0) {
            PicInfo picInfo = null;
            String[] picstr = msgbody[1].split("[+]");
            for (int i = 0; i < picstr.length; i++) {
                if (null != picstr[i] && picstr[i].length() > 0) {
                    String[] tem = picstr[i].split("[&]");
                    picInfo = new PicInfo(Integer.parseInt(tem[0]), tem[1]);
                    myPicInfo.add(picInfo);
                }
            }
        }
//        ViewManager.getComponent(ChatMainPanel.class).addMeg(msg.getProperty("toperson").toString()+"[私聊]",msgbody[0],myPicInfo,msg.getProperty("sender").toString(),msg.getProperty("sendDate").toString());
        ViewManager.getComponent(ChatMainPanel.class).addMeg("[From] ",msgbody[0],myPicInfo,msg.getProperty("sender").toString(),msg.getProperty("sendDate").toString());
    }
    public void recieveMessage(Message msg) throws Exception {
    	this.msg=msg;
    	SwingUtilities.invokeAndWait(new Runnable() {
			
			@Override
			public void run() {
			   addMsgTopanel();	
			}
		});
    }
    private Message msg;
    
    public void addMsgTopanel(){

        String[] msgbody = new String[2];
        if (msg.getBody().indexOf("-^*$+%") > 0) {
            msgbody[0] = msg.getBody().substring(0, msg.getBody().indexOf("-^*$+%"));
            msgbody[1] = msg.getBody().substring(msg.getBody().indexOf("-^*$+%") + 6);
        } else {
            msgbody = msg.getBody().split("[*]");
        }
        List<PicInfo> myPicInfo = new LinkedList<PicInfo>();
        if(null!=msgbody&&msgbody.length>1&&null!=msgbody[1]&&msgbody[1].length()>0){
            PicInfo picInfo=null;
            String[] picstr=msgbody[1].split("[+]");
            for(int i=0;i<picstr.length;i++){
                if(null!=picstr[i]&&picstr[i].length()>0){
                    String[] tem=picstr[i].split("[&]");
                    try{
                    	picInfo=new PicInfo(Integer.parseInt(tem[0]), tem[1]);
                        myPicInfo.add(picInfo);
                    }catch(Exception ex){
                    	
                    }
                }
            }
        }
        if(null!=msgbody&&msgbody.length>0){
        if (Message.Type.chat.equals(msg.getType())) {
//            ViewManager.getComponent(ChatMainPanel.class).addMeg(msg.getProperty("sender").toString(),msgbody[0]);
//            System.out.print(msg.getBody() + "\n chat" );
        } else if (Message.Type.groupchat.equals(msg.getType())) {
            if (null != msg.getProperty("sender"))
				try {
					ViewManager.getComponent(ChatMainPanel.class).addMeg(msg.getProperty("toperson").toString(),msgbody[0],myPicInfo,msg.getProperty("sender").toString(),msg.getProperty("sendDate").toString());
				} catch (Exception e) {
					log.error("addMsgTopanel", e);
					e.printStackTrace();
				}
        }
        }
    
    }
    public boolean sendMsg(ParaUtil paras) throws Exception{
        boolean flag=true;
        ViewManager.getComponent(ChatMainPanel.class);
        String msg=paras.getValue("msg");
        String toperson=paras.getValue("toPerson");
        ChatService chatService=new ChatService();
        ChatRecordBean bean=new ChatRecordBean();
        bean.setContent(msg);
        bean.getFriendNames().add(toperson);//对某个人说
        bean.setSender(paras.getValue("sendperson").toString());
        String chatType=paras.getValue("isprivateChat");
        try{
        	if("1"==chatType){
        		chatService.sendMsgToOne(bean);
            }else{
            	chatService.sendMsgToAll(bean);
            }
        }catch(Exception ex){
        	log.error("sendMsg", ex);
        	this.showErrorDialog(StringUtil.getErrorString("error.title"), StringUtil.getErrorString("MainController.sendMsg.openfireDisconnect"));//
        	flag = false;
        }
        
        return flag;
    }
    public static String getNickNameByName(String name){
        String nickName=null;
        try {
            ConfService confService=new ConfService();
            MemberDto memberDto = confService.getMemberDtoByName(name);
            nickName = memberDto.getNickname();
        } catch (Exception ex) {
        	log.error("getNickNameByName", ex);
        }
        return nickName;
    }
    
    public void recieveAdminMessage(Message msg){
    	String body = msg.getBody();
    	if("conf.change".equals(body)){
    		if(Launcher.isStartedFromOutSide){
    			this.showMessageDialog(null, StringUtil.getUIString("info.confischange"));
    			ControllerFacade.execute("mainController", "exitApplication");
    		}else{
	    		String reason = StringUtil.getUIString("info.confischange");
	    		ControllerFacade.execute("mainController", "exitApplicationWithoutConfirm", true, reason);
    		}
    	}else if(BroadcastEvent.REVOKE_CONF_AUTHORITY.equals(body)){
    		ControllerFacade.execute("authorityController", "updateAuthorityByRevokeAuthority",msg);
    	}else if(BroadcastEvent.GRANT_CONF_AUTHORITY.equals(body)){
    		ControllerFacade.execute("authorityController", "updateAuthorityByGrantAuthority",msg);
    	}
    }
}
