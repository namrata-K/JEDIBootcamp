package com.flipkart.client;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.Exception.ProfessorAlreadyExistsException;
import com.flipkart.bean.Course;
import com.flipkart.service.CatalogSystem;
import com.flipkart.service.CatalogSystemImpl;
import com.flipkart.service.ProfessorService;
import com.flipkart.service.ProfessorServiceImpl;
import com.flipkart.service.RegistrationService;
import com.flipkart.service.RegistrationServiceImpl;
import com.flipkart.service.ServiceHelper;

public class ProfessorClient implements ServiceHelper {

	// logger object
	private static Logger logger = Logger.getLogger(SMSClient.class);

	// scanner object for input
	Scanner scn = new Scanner(System.in);

	// Catalog object
	CatalogSystem catalog = new CatalogSystemImpl();

	// Registration service object
	RegistrationService registration = new RegistrationServiceImpl();

	// Professor service object
	ProfessorService professor = new ProfessorServiceImpl();

	// Method to view professor details
	public void viewDetails(Integer userid) {
		professor.viewDetails(userid);
	}

	// Method to view all courses
	public void viewCourses() {
		catalog.showCourses(catalog.searchAndFetchCourseDetails());
	}

	// Method to select course to teach
	public void selectCourse(Integer userid, String username) {
		try {
			logger.info("Enter Course Id");
			int courseid = scn.nextInt();
			professor.selectCourse(userid, courseid, username);
		} catch (ProfessorAlreadyExistsException e) {
			logger.error("Vacancy Full. Try another course ");
		}
	}

	// Method to drop course
	public void dropCourse(Integer userid, String username) {
		logger.info("Enter Course Id");
		int courseid = scn.nextInt();
		professor.dropCourse(userid, courseid, username);
	}

	// Method to view selected courses
	public void viewSelectedCourses(Integer userid) {
		catalog.showCourses(professor.getCourseIds(userid));
	}

	// Method to view registered students
	public void viewRegisteredStudents(Integer userid) {
		professor.viewRegisteredStudents(userid);
	}

	// Method to upload grades
	public void uploadGrades(Integer userid) {

		List<Course> courseIds = professor.getCourseIds(userid);
		for (Course course : courseIds) {
			logger.info(".....Students enrolled for " + course.getName() + " " + course.getCourseId() + ".....");
			List<Integer> students = professor.getRegisteredStudentsIds(course.getCourseId());
			for (Integer studentid : students) {
				professor.printStudent(studentid);
				logger.info("Enter grade");
				String grade = scn.next();
				professor.uploadGrade(studentid, course.getCourseId(), grade);
			}
		}
		logger.info("Grades Successfully Uploaded");
	}

	// Method to upload grades by Student id
	public void uploadGradeByStudentId(Integer userid) {

		logger.info("Enter student ID");
		int studentid = scn.nextInt();
		logger.error("Enter Course ID");
		int courseid = scn.nextInt();
		logger.info("Enter Grade");
		String grade = scn.next();
		professor.uploadGrade(studentid, courseid, grade);
		logger.info("Grade Successfully Uploaded");

	}

	// Method to view all grades
	public void viewStudentGrades(Integer userid) {

		logger.info("Enter Course ID");
		int courseid = scn.nextInt();
		List<Integer> students = professor.getRegisteredStudentsIds(courseid);
		for (Integer studentid : students) {
			professor.printStudent(studentid);
			professor.viewAllGrades(studentid, courseid);
		}

	}

	// Method to show option menu
	public void showMenu(String username, Integer userid) {

		try {
			logger.info("You have successfully signed in as Professor");
			logger.info("Welcome " + username);

			logger.info("1. View My Details");
			logger.info("2. View Courses");
			logger.info("3. Select Course to Teach");
			logger.info("4. Drop Course");
			logger.info("5. View Selected Courses");
			logger.info("6. View Registered Students");
			logger.info("7. Upload All Grades");
			logger.info("8. Upload Grade by Student Id");
			logger.info("9. View Student Grades");
			logger.info("10. Exit");

			int choice = scn.nextInt();

			switch (choice) {
			case 1:
				viewDetails(userid);
				break;
			case 2:
				viewCourses();
				break;
			case 3:
				selectCourse(userid, username);
				break;
			case 4:
				dropCourse(userid, username);
				break;
			case 5:
				viewSelectedCourses(userid);
				break;
			case 6:
				viewRegisteredStudents(userid);
				break;
			case 7:
				uploadGrades(userid);
				break;
			case 8:
				uploadGradeByStudentId(userid);
				break;
			case 9:
				viewStudentGrades(userid);
				break;
			case 10:
				redirectToLogin();
				break;
			}
		} catch (Exception e) {
			logger.error("Something went wrong " + e.getMessage());
		} finally {
			showMenu(username, userid);
		}

		scn.close();
	}

}
