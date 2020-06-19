/**
 * 
 */
package com.flipkart.service;

import com.flipkart.Exception.InvalidUserException;

// Interface to verify user credentials 
public interface CheckIdentity {

	// Method to verify username password
	public String verifyIdentity(String username, String password) throws InvalidUserException;

	// Method to get userid for a username
	public int getUserId(String username) throws InvalidUserException;
}
