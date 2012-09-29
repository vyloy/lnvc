package com.lorent.exception;


public class ArgsException extends BaseException {

	public ArgsException(String key) {
		super(key);
	}
	
	public ArgsException(String message,Throwable throwable) {
		super(message,throwable);
	}
	
	public ArgsException(String msg,boolean initMsg) {
		super(msg,initMsg);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
