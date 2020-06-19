package com.flipkart.dao;

// Interface for verifying user identity
public interface CheckCredentialDao {

	// Method to verify Identity by receiving role
	public String verifyIdentity(String username, String password);

	// Method to get userid for a username
	public Integer getUserId(String username);
}
