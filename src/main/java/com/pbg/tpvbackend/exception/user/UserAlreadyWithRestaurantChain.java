package com.pbg.tpvbackend.exception.user;

public class UserAlreadyWithRestaurantChain extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2485494819045995373L;

	private String message;
	
	public UserAlreadyWithRestaurantChain(String message) {
		super(message);
		this.message = message;
	}

	public UserAlreadyWithRestaurantChain() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
