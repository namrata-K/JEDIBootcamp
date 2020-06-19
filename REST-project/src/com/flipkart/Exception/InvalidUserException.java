package com.flipkart.Exception;

public class InvalidUserException extends Exception {

	private String username;



	public InvalidUserException(String username) {
		// TODO Auto-generated constructor stub
		 this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

}
