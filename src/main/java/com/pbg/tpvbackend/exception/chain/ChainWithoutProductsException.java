package com.pbg.tpvbackend.exception.chain;

public class ChainWithoutProductsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5462634730335740624L;
	
	private String message;
	
	public ChainWithoutProductsException(String message) {
		super(message);
		this.message = message;
	}

	public ChainWithoutProductsException() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
