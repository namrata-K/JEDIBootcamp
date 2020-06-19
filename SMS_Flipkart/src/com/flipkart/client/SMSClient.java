/**
 * 
 */
package com.flipkart.client;

import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.Exception.InvalidUserException;
import com.flipkart.service.CheckIdentity;
import com.flipkart.service.CheckIdentityImpl;

// Student Management System Entry Client Class
public class SMSClient {

	// logger object
	private static Logger logger = Logger.getLogger(SMSClient.class);

	// Method to verify login credentials
	public static void login() {

		// scanner object for input
		Scanner scn = new Scanner(System.in);

		// Checking Identity Service object
		CheckIdentity check = new CheckIdentityImpl();

		// Student service object
		StudentClient studentClient = new StudentClient();

		// Professor service object
		ProfessorClient professorClient = new ProfessorClient();

		// Admin service object
		AdminClient adminClient = new AdminClient();

		// Main Login Menu
		try {
			logger.info("---------Welcome to Student Management System-------");

			logger.info("Enter username");
			String username = scn.next();
			logger.info("Enter password");

			String password = scn.next();
			String role = null;
			Integer id = null;

			try {
				role = check.verifyIdentity(username, password);
				id = check.getUserId(username);
			} catch (InvalidUserException e) {
				logger.error(e.getUsername() + " Incorrect Username or Password");
			}

			Date currentDate = new Date();
			logger.info("Logged in on " + currentDate);

			switch (role) {
			case "Admin":
				adminClient.showMenu(username, id);
				break;
			case "Student":
				studentClient.showMenu(username, id);
				break;
			case "Professor":
				professorClient.showMenu(username, id);
				break;
			default:
				logger.info("Session Ended");
				break;
			}
		} catch (Exception e) {
			logger.error("Something went wrong " + e.getMessage());
		} finally {
			login();
		}

		scn.close();
	}

	public static void main(String[] args) throws InvalidUserException {

		login();
	}
}
