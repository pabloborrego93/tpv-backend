package com.pbg.tpvbackend.exception.chain;

public class ChainWithoutUsersException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8028151390137329501L;
	
	private String message;
	
	public ChainWithoutUsersException(String message) {
		super(message);
		this.message = message;
	}

	public ChainWithoutUsersException() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
