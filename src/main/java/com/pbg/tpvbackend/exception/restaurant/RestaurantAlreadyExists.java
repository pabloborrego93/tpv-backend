package com.pbg.tpvbackend.exception.restaurant;

public class RestaurantAlreadyExists extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6692280427904726999L;
	
	private String message;
	
	public RestaurantAlreadyExists(String message) {
		super(message);
		this.message = message;
	}

	public RestaurantAlreadyExists() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
