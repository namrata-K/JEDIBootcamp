package com.flipkart.client;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.Exception.ClassFullException;
import com.flipkart.Exception.CourseAlreadyRegistered;
import com.flipkart.Exception.RegistrationAlreadyDoneException;
import com.flipkart.Exception.RegistrationNotDoneException;
import com.flipkart.bean.Course;
import com.flipkart.service.CatalogSystem;
import com.flipkart.service.CatalogSystemImpl;
import com.flipkart.service.RegistrationService;
import com.flipkart.service.RegistrationServiceImpl;
import com.flipkart.service.ServiceHelper;
import com.flipkart.service.StudentService;
import com.flipkart.service.StudentServiceImpl;

public class StudentClient implements ServiceHelper {

	// logger object
	private static Logger logger = Logger.getLogger(StudentClient.class);

	// Catalog object
	CatalogSystem catalog = new CatalogSystemImpl();

	// Registration service object
	RegistrationService registration = new RegistrationServiceImpl();

	// Student service object
	StudentService studentService = new StudentServiceImpl();

	// scanner object for input
	Scanner scn = new Scanner(System.in);

	// Method to view student details
	public void viewDetails(Integer userid) {
		studentService.viewDetails(userid);
	}

	// Method to view all courses
	public void viewCourses() {
		catalog.showCourses(catalog.searchAndFetchCourseDetails());
	}

	// Method to get registered courses by student
	public List<Course> RegisteredCourses(Integer userid) {

		List<Integer> courseIdList = registration.getRegisteredCourseId(userid);
		List<Course> courseList = new ArrayList<>();
		courseIdList.forEach(courseId -> courseList.add(catalog.searchAndFetchCourseById(courseId)));
		return courseList;
	}

	// Method to view registered courses by student
	public void viewRegisteredCourses(Integer userid) {

		catalog.showCourses(RegisteredCourses(userid));
	}

	// Method to add course
	public void addCourse(Integer userid) {

		try {
			registration.isRegistered(userid);
			logger.info("Enter Course Id");
			int courseid = scn.nextInt();
			registration.addCourse(userid, courseid);
		} catch (ClassFullException e) {
			logger.error("Course" + e.getCourseid() + "full. Try another course ");
		} catch (CourseAlreadyRegistered e) {
			logger.error("Course with id " + e.getCourseid() + "Already Registered");
		} catch (RegistrationAlreadyDoneException e) {
			logger.error("Registration Already Done. Cannot make changes");
		}
	}

	// Method to drop course
	public void dropCourse(Integer userid) {

		try {
			registration.isRegistered(userid);
			logger.info("Enter Course Id");
			int courseid = scn.nextInt();
			registration.deleteCourse(userid, courseid);
			logger.info("Course Successfully Deleted");
		} catch (RegistrationAlreadyDoneException e) {
			logger.error("Registration Already Done. Cannot make changes");
		}
	}

	// Method to register course
	public void registerCourse(String username, Integer userid) {

		try {
			registration.isRegistered(userid);
			logger.info("Courses cannot be cahnged after registeration");
			logger.info("1. Pay fees");
			logger.info("2. Back");
			int choice = scn.nextInt();
			switch (choice) {
			case 1:
				payFees(userid, username);
				break;
			case 2:
				showMenu(username, userid);
				break;
			default:
				break;
			}
		} catch (RegistrationAlreadyDoneException e) {
			logger.error("Registration Already Done. Cannot make changes");
		}
	}

	// Method to view grades
	public void viewGrades(Integer userid) {

		try {
			List<Course> courses = RegisteredCourses(userid);
			studentService.viewGrades(userid, courses);
		} catch (RegistrationNotDoneException e) {
			logger.error("Registration Not Complete. Complete Registration to view grades ");
		}
	}

	// Method to pay fees
	public void payFees(Integer userid, String username) {

		List<Course> courses = RegisteredCourses(userid);
		double finalAmt = studentService.calculateFees(userid, courses);

		logger.info("Pay fee?");
		logger.info("1. Yes");
		logger.info("2. Back");
		int choice = scn.nextInt();
		switch (choice) {
		case 1:
			logger.info("1-> UPI 2-> Bank Tranfer");
			int method = scn.nextInt();
			studentService.payFee(userid, finalAmt);
			logger.error("Payment Successful. Registration Complete.");
			break;
		case 2:
			showMenu(username, userid);
			break;
		default:
			break;
		}

	}

	// Method to show option menu
	public void showMenu(String username, Integer userid) {

		try {
			logger.info("You have successfully signed in as Student");
			logger.info("Welcome " + username);

			logger.info("1. View Details");
			logger.info("2. View Courses");
			logger.info("3. View Registered Courses");
			logger.info("4. Add course");
			logger.info("5. Drop course");
			logger.info("6. Register Course");
			logger.info("7. View Grades");
			logger.info("8. Exit");

			int choice = scn.nextInt();

			switch (choice) {
			case 1:
				viewDetails(userid);
				break;
			case 2:
				viewCourses();
				break;
			case 3:
				viewRegisteredCourses(userid);
				break;
			case 4:
				addCourse(userid);
				break;
			case 5:
				dropCourse(userid);
				break;
			case 6:
				registerCourse(username, userid);
				break;
			case 7:
				viewGrades(userid);
				break;
			case 8:
				redirectToLogin();
				break;
			}
		} catch (InputMismatchException e) {
			logger.error("Wrong input " + e.getMessage());
		} finally {
			showMenu(username, userid);
		}

	}

}
