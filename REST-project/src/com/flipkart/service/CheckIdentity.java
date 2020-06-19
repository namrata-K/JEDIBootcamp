/**
 * 
 */
package com.flipkart.service;

import com.flipkart.Exception.InvalidUserException;

/**
 * @author ishit
 *
 */
// interface for verifying user identity
public interface CheckIdentity {

	// Method to verify identity by getting role
	public String verifyIdentity(String username, String password) throws InvalidUserException;

	// Method to get User id
	public int getUserId(String username) throws InvalidUserException;
}
