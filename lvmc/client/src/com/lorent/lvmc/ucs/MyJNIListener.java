package com.lorent.lvmc.ucs;

import org.apache.log4j.Logger;

import com.lorent.common.event.JNIEvent;
import com.lorent.common.event.JNIEventAdapter;
import com.lorent.util.LCCUtil;

public class MyJNIListener extends JNIEventAdapter{

	private Logger log = Logger.getLogger(MyJNIListener.class);
	
    @Override
    public void registerOkCallBack(JNIEvent event) {
        UCSClient.sendRegisterOK();
    }

    @Override
    public void registerFailCallBack(JNIEvent event) {
    	UCSClient.sendRegisterFail();
    }

    @Override
    public void connectedCallBack(JNIEvent event) {
    	UCSClient.sendConnected((String)event.getParas()[0]);
    }

    @Override
    public void callerrorCallBack(JNIEvent event) {
        
    }

    @Override
    public void hangupCallBack(JNIEvent event) {
    	UCSClient.sendOtherHangup((String)event.getParas()[0]);
    }

    @Override
    public void incomingCallBack(JNIEvent event) {
    	UCSClient.sendIncoming((String)event.getParas()[0]);
    }

	@Override
	public void memberinfoCallBack(JNIEvent event) {

	}
	
	@Override
	public void unRegOKCallBack(JNIEvent event) {
		UCSClient.sendUnRegisterOK();
	}
	
	@Override
	public void unInitOKCallBack(JNIEvent evnet) {
		log.info("uninit ok lvmc exit");
		LCCUtil.getInstance().doUninit();
		System.exit(0);
	}
	
	@Override
	public void hangupSuccessCallBack(JNIEvent event) {
		UCSClient.sendHangup((String)event.getParas()[0]);
	}
	
	@Override
	public void dataMessageCallBack(JNIEvent event) {
		UCSClient.sendDataMessage((String)event.getParas()[0], (String)event.getParas()[1], (Integer)event.getParas()[2], (Integer)event.getParas()[3]);
	}
	
	@Override
	public void outgoingCallBack(JNIEvent event) {
		UCSClient.sendOutgoing((String)event.getParas()[0]);
	}
}
