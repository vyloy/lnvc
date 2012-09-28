package com.lorent.exception;

public class ServerException extends BaseException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ServerException(String key) {
		super(key);
	}
	
	public ServerException(String msg, Throwable cause){
		super(msg, cause);
	}
}
