package com.lorent.exception;

public class CustomSqlException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CustomSqlException(String key) {
		super(key);
	}
	public CustomSqlException(String message,Throwable throwable) {
		super(message,throwable);
	}
}
