package com.lorent.exception;

import org.springframework.security.AuthenticationException;

import com.lorent.util.PropertiesUtil;

public class CustomAuthenticationException extends AuthenticationException {
	private String messageKey = "";
	private final String FILE_NAME = "exceptionResource";
	public CustomAuthenticationException(String messageKey) {
		super(messageKey);
		this.messageKey = messageKey;
	}
	
	@Override
	public String getMessage() {
		return PropertiesUtil.getProperty(FILE_NAME, messageKey,true);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
