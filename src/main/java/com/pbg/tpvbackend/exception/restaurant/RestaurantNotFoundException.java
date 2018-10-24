package com.pbg.tpvbackend.exception.restaurant;

public class RestaurantNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6403717222093947299L;
	
	private String message;
	
	public RestaurantNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	public RestaurantNotFoundException() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
