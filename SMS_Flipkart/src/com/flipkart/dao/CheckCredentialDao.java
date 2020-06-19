package com.flipkart.dao;

// Interface for checking credential dao
public interface CheckCredentialDao {

	// Method to verify username password
	public String verifyIdentity(String username, String password);

	// Method to get userid for a given username
	public Integer getUserId(String username);
}
