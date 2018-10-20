package com.pbg.tpvbackend.exception.user;

public class UserWithoutRestaurants extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1044271127678616492L;
	
	private String message;
	
	public UserWithoutRestaurants(String message) {
		super(message);
		this.message = message;
	}

	public UserWithoutRestaurants() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
