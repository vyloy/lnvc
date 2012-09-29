package com.lorent.lvmc.event;

import java.util.EventObject;

public class JNIEvent extends EventObject {
	
	private static final long serialVersionUID = 1L;

	public static final int TYPE_REGISTER_CB = 10;
	public static final int TYPE_PUBLISHSTATE_CB = 20;
	        
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
}
