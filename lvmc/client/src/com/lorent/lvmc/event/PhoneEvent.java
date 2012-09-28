/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.event;

/**
 *
 * @author Administrator
 */
public class PhoneEvent extends BaseEvent {

    private static final long serialVersionUID = 1L;
    public static final int TYPE_REGISTER_CB = 10;
    public static final int TYPE_PUBLISHSTATE_CB = 20;
//    public static final int TYPE_REGISTER_SUCCESS = 30;
//    public static final int TYPE_REGISTER_FAIL = 40;
    public static final int TYPE_CALLINCOME = 50;
    public static final int TYPE_CONNECTED = 60;
    public static final int TYPE_HANGUP = 70;
    public static final int TYPE_CALLERROR = 80;

    //电话通话状态
    public static final int PHONE_STATE_CALLOUT=101;//正在拨出电话
    public static final int PHONE_STATE_CALLING=102;//通话中
    public static final int PHONE_STATE_READY=103;//空闲状态
    public static final int PHONE_STATE_CALLIN=104;//有电话呼入中
    //电话注册状态
    public static final int PHONE_STATE_REGISTER_SUCCESS=105;//注册成功
    public static final int PHONE_STATE_REGISTER_FAIL=106;//注册失败
    
    public PhoneEvent(Object source, int type, Object[] paras) {
        super(source, type, paras);
    }
}
