package com.flipkart.service;

import com.flipkart.Exception.InvalidUserException;
import com.flipkart.dao.CheckCredentialDaoImpl;

public class CheckIdentityImpl implements CheckIdentity {

	CheckCredentialDaoImpl credentialDao = new CheckCredentialDaoImpl();

	@Override
	public String verifyIdentity(String username, String password) throws InvalidUserException {

		if (credentialDao.verifyIdentity(username, password) != null) {
			return credentialDao.verifyIdentity(username, password);
		} else {
			throw new InvalidUserException(username);
		}

	}

	public int getUserId(String username) throws InvalidUserException {

		if (credentialDao.getUserId(username) != null) {
			return credentialDao.getUserId(username);
		} else {
			throw new InvalidUserException(username);
		}
	}

}
