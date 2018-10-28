package com.pbg.tpvbackend.exception.productFamily;

public class ProductFamilyAlreadyExists extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3957117321242970823L;
	
	private String message;
	
	public ProductFamilyAlreadyExists(String message) {
		super(message);
		this.message = message;
	}

	public ProductFamilyAlreadyExists() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
