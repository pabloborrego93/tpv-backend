package com.pbg.tpvbackend.exception;

public class UserWithoutRestaurantChain extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2485494819045995373L;

	private String message;
	
	public UserWithoutRestaurantChain(String message) {
		super(message);
		this.message = message;
	}

	public UserWithoutRestaurantChain() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
