package com.flipcard.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Interface for default methods
public interface ServiceHelper {

	// logger object
	 static final Logger LOGGER = LoggerFactory.getLogger(ServiceHelper.class);

	//default method to redirect to login
	default public void redirectToLogin() {
		Date currentDate = new Date();
		LOGGER.info("....Session Ended...." + currentDate);
	//	SMSClient.login();
	}

	//default method to close database connections
	default public void closeConnection(PreparedStatement stmt) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException se2) {
			LOGGER.error(se2.getMessage());
		}
	}

}
