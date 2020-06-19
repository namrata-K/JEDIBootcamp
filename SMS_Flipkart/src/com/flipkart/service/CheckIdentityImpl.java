package com.flipkart.service;

import com.flipkart.Exception.InvalidUserException;
import com.flipkart.dao.CheckCredentialDaoImpl;

// Class to verify user credentials which implements check identity interface 
public class CheckIdentityImpl implements CheckIdentity {

	// Credential dao object
	CheckCredentialDaoImpl credentialDao = new CheckCredentialDaoImpl();

	// Method to verify username password
	@Override
	public String verifyIdentity(String username, String password) throws InvalidUserException {

		if (credentialDao.verifyIdentity(username, password) != null) {
			return credentialDao.verifyIdentity(username, password);
		} else {
			throw new InvalidUserException(username);
		}

	}

	// Method to get userid for a username
	public int getUserId(String username) throws InvalidUserException {

		if (credentialDao.getUserId(username) != null) {
			return credentialDao.getUserId(username);
		} else {
			throw new InvalidUserException(username);
		}
	}

}
