package com.pbg.tpvbackend.exception.product;

public class ProductNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3981934054927956005L;

	private String message;

	public ProductNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	public ProductNotFoundException() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
