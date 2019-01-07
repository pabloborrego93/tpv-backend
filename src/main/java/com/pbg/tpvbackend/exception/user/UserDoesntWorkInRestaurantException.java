package com.pbg.tpvbackend.exception.user;

public class UserDoesntWorkInRestaurantException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3968684921685358083L;
	
	private String message;

	public UserDoesntWorkInRestaurantException(String message) {
		super(message);
		this.message = message;
	}

	public UserDoesntWorkInRestaurantException() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
