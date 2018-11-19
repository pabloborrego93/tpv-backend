package com.pbg.tpvbackend.exception.product;

public class InvalidProductTypeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 73170045390260022L;
	
	private String message;
	
	public InvalidProductTypeException(String message) {
		super(message);
		this.message = message;
	}

	public InvalidProductTypeException() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
