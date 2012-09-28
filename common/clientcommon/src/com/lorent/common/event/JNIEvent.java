package com.lorent.common.event;

import java.util.EventObject;

public class JNIEvent extends EventObject {
	
	private static final long serialVersionUID = 1L;

    public static final int REGISTER_OK = 1;
    public static final int REGISTER_FAIL = 2;
    public static final int CONNECTED = 3;
    public static final int INCOMING_CB = 4;//  incomingcb
    public static final int HANDUP_CB = 5;
	public static final int CALLERROR_CB = 6;        
	public static final int MEMBERINFO_CB = 7;
	public static final int UNREGISTER_OK = 8;
	public static final int UNINIT_OK = 9;
	public static final int HANDUP_SUCCESS_CB = 10;
	public static final int DATA_MSG_CB = 11;
	public static final int OUTGOING_CB = 12;
	
        
	private int type;
	private Object[] paras;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Object[] getParas() {
		return paras;
	}

	public void setParas(Object[] paras) {
		this.paras = paras;
	}

	public JNIEvent(Object source, int type, Object[] paras){
		super(source);
		this.type = type;
		this.paras = paras;
	}
        
        public JNIEvent(int type, Object[] paras){
		super(null);
		this.type = type;
		this.paras = paras;
	}
}
