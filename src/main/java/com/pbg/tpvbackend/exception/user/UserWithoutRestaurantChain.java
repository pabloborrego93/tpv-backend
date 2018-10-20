package com.pbg.tpvbackend.exception.user;

public class UserWithoutRestaurantChain extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1689798852504833835L;
	
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
