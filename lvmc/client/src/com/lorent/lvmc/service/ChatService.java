/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.service;

import com.lorent.lvmc.bean.ChatRecordBean;
import com.lorent.lvmc.util.LvmcOpenfireUtil;

/**
 *
 * @author test
 */
public class ChatService extends BaseService{
    
    public void sendMsgToAll(ChatRecordBean bean) throws Exception {
        LvmcOpenfireUtil.sendMsgToAll(bean.getContent(),bean.getFriendNames().get(0),bean.getSender());
    }

    public void sendMsgToOne(ChatRecordBean bean) throws Exception {
        LvmcOpenfireUtil.sendMsgToOne(bean.getFriendNames().get(0), bean.getContent(),bean.getSender());
    }
}
