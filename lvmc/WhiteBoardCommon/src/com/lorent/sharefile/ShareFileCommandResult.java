package com.lorent.sharefile;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class ShareFileCommandResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Map<String, Object> resultMap = new HashMap<String, Object>();
	
	
	public void setValue(String key,Object value){
		resultMap.put(key, value);
	}
	
	public Object getValue(String key){
		return resultMap.get(key);
	}

}
