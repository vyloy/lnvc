package com.lorent.lvmc.event;

import java.util.EventObject;

public class BaseEvent extends EventObject {
	
	private static final long serialVersionUID = 1L;
	
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

	public BaseEvent(Object source, int type, Object[] paras){
		super(source);
		this.type = type;
		this.paras = paras;
	}
}
