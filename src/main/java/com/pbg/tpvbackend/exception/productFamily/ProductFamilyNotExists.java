package com.pbg.tpvbackend.exception.productFamily;

public class ProductFamilyNotExists extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9205962973518375708L;
	
	private String message;
	
	public ProductFamilyNotExists(String message) {
		super(message);
		this.message = message;
	}

	public ProductFamilyNotExists() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
