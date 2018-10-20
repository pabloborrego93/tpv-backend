package com.pbg.tpvbackend.exception.chain;

public class ChainAlreadyExists extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 17807809274330427L;
	
	private String message;
	
	public ChainAlreadyExists(String message) {
		super(message);
		this.message = message;
	}

	public ChainAlreadyExists() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
