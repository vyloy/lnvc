/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ChatRecordBean implements Serializable{
    
    private int id;
    private String sender;//发送信息者  (不存用户ID，主要考虑不用在去读服务器数据)
    //private String reciever;//接收信息者  (可以暂时不用)
    private String content;//信息内容
    private Date sendDate;//发送信息时间
    private int chatTitleId;//多人聊天主题ID
    //private String friend_name;//好友的账号
    //private String user_name;//自己的账号
    private int isShow;
    private List<String> friendNames = new ArrayList<String>();
    private String sessionID;
    private boolean showOnly;

    public boolean isShowOnly() {
        return showOnly;
    }

    public void setShowOnly(boolean showOnly) {
        this.showOnly = showOnly;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int is_show) {
        this.isShow = is_show;
    }

    public List<String> getFriendNames() {
        return friendNames;
    }

    public void setFriendNames(List<String> recievers) {
        this.friendNames = recievers;
    }
    
    
    public int getChatTitleId() {
        return chatTitleId;
    }

    public void setChatTitleId(int chatTitleId) {
        this.chatTitleId = chatTitleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
    
    
}
