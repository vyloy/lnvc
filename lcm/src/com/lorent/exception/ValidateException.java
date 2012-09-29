package com.lorent.exception;

public class ValidateException extends BaseException {

	private static final long serialVersionUID = 1L;

	public ValidateException(String key) {
		super(key);
	}
	
	public ValidateException(String msg,boolean initMsg){
		super(msg,initMsg);
	}
}
