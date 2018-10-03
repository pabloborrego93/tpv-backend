package com.pbg.tpvbackend.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidJWTException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1498869066393113120L;

	private String message;
	
	public InvalidJWTException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
