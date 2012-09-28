package com.lorent.common.event;

import java.util.EventListener;

public interface JNIEventListener extends EventListener {

    public void registerOkCallBack(JNIEvent event);

    public void registerFailCallBack(JNIEvent event);

    public void connectedCallBack(JNIEvent event);

    public void callerrorCallBack(JNIEvent event);

    public void hangupCallBack(JNIEvent event);

    public void incomingCallBack(JNIEvent event);
    
    public void memberinfoCallBack(JNIEvent event);
    
    public void unRegOKCallBack(JNIEvent event);
    
    public void unInitOKCallBack(JNIEvent event);
    
    public void hangupSuccessCallBack(JNIEvent event);
    
    public void dataMessageCallBack(JNIEvent event);
    
    public void outgoingCallBack(JNIEvent event);
}
