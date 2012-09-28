package com.lorent.exception;

public class RpcServerException extends BaseException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RpcServerException(String key){
		super(key);
		this.key = key;
	}
}
