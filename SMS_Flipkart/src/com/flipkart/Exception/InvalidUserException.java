package com.flipkart.Exception;

//Exception thrown when user does not exist
public class InvalidUserException extends Exception {

	private String username;

	public InvalidUserException(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

}
