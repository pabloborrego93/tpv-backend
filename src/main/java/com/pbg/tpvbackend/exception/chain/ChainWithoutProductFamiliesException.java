package com.pbg.tpvbackend.exception.chain;

public class ChainWithoutProductFamiliesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3156967455251047968L;
	
	private String message;
	
	public ChainWithoutProductFamiliesException(String message) {
		super(message);
		this.message = message;
	}

	public ChainWithoutProductFamiliesException() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
