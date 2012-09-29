package com.lorent.exception;

public class CustomXmlRpcException extends BaseException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CustomXmlRpcException(String key) {
		super(key);
	}
	
	public CustomXmlRpcException(String msg, Throwable cause){
		super(msg, cause);
	}
}
