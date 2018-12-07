package com.pbg.tpvbackend.exception.product;

public class ProductAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1873982791144316381L;
	
	private String message;

	public ProductAlreadyExistsException(String message) {
		super(message);
		this.message = message;
	}

	public ProductAlreadyExistsException() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
