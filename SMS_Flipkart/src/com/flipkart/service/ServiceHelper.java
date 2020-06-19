package com.flipkart.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import com.flipkart.client.SMSClient;

// Helper Interface to provide default methods
public interface ServiceHelper {

	// logger object
	static Logger logger = Logger.getLogger(ServiceHelper.class);

	// default method to redirect to login
	default public void redirectToLogin() {
		Date currentDate = new Date();
		logger.info("....Session Ended...." + currentDate);
		SMSClient.login();
	}

	// default method to close database connections
	default public void closeConnection(PreparedStatement stmt) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException se2) {
			logger.error(se2.getMessage());
		}
	}

}
