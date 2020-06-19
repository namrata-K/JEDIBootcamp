package com.flipkart.Exception;

public class UsernameAlreadyExistsException extends Exception {

	private String username;

	public UsernameAlreadyExistsException(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

}
