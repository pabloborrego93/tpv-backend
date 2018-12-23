package com.pbg.tpvbackend.exception.product;

public class ProductUpdateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1179782795312709995L;
	
	private String message;
	
	public ProductUpdateException(String message) {
		super(message);
		this.message = message;
	}

	public ProductUpdateException() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
