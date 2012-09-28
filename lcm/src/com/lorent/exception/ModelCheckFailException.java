package com.lorent.exception;

public class ModelCheckFailException extends BaseException {

	public ModelCheckFailException(String key) {
		super(key);
		this.key = key;
	}
	
	public ModelCheckFailException(String key,Throwable throwable) {
		super(key,throwable);
		this.key = key;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
