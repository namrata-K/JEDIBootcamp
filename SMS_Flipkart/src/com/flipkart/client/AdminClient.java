package com.flipkart.client;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.Exception.UsernameAlreadyExistsException;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.service.AdminService;
import com.flipkart.service.AdminServiceImpl;
import com.flipkart.service.CatalogSystem;
import com.flipkart.service.CatalogSystemImpl;
import com.flipkart.service.RegistrationService;
import com.flipkart.service.RegistrationServiceImpl;
import com.flipkart.service.ServiceHelper;

// Client side for admin type users
public class AdminClient implements ServiceHelper {

	// logger object
	private static Logger logger = Logger.getLogger(SMSClient.class);

	// Catalog object
	CatalogSystem catalog = new CatalogSystemImpl();

	// Registration service object
	RegistrationService registration = new RegistrationServiceImpl();

	// Admin service object
	AdminService adminService = new AdminServiceImpl();

	// scanner object for input
	Scanner scn = new Scanner(System.in);

	// Method to view all courses
	public void viewCourses() {
		catalog.showCourses(catalog.searchAndFetchCourseDetails());
	}

	// Method to view student details
	public void viewDetails(Integer userid) {
		adminService.viewDetails(userid);
	}

	// Method to add user
	public void addUser() {

		User user = new User();
		try {
			logger.info("Enter name");
			user.setName(scn.next());
			adminService.getAllRoles();
			logger.info("Enter Role Id and Role Name");
			user.setRoleid(scn.nextInt());
			user.setRole(scn.next());
			logger.info("Enter Gender \n 1 Female \n 2 Male");
			int choice = scn.nextInt();
			switch (choice) {
			case 1:
				user.setGender("Female");
				break;
			case 2:
				user.setGender("Male");
				break;
			default:
				break;
			}
			logger.info("Enter Username");
			user.setUsername(scn.next());
			logger.info("Enter password");
			user.setPassword(scn.next());

			adminService.addUser(user);
			int userid = adminService.getUserId(user.getUsername());
			if (user.getRoleid() == 1) {
				addAdmin(userid, user.getName(), user.getGender());
			} else if (user.getRoleid() == 2) {
				addStudent(userid, user.getName(), user.getGender());
			} else if (user.getRoleid() == 3) {
				addProfessor(userid, user.getName(), user.getGender());
			}
			logger.info("User Successfully Added!");

		} catch (UsernameAlreadyExistsException e) {
			logger.error("Username " + e.getUsername() + " Already Exists. Try Another Username" + e.getMessage());
		}
	}

	// Method to add admin
	public void addAdmin(int userid, String name, String gender) {
		Admin admin = new Admin();
		admin.setAdminid(userid);
		admin.setName(name);
		admin.setGender(gender);
		logger.info("Enter Contact No");
		admin.setContactNo(scn.nextLong());
		logger.info("Enter Email");
		admin.setEmail(scn.next());

		adminService.addAdmin(admin);
	}

	// Method to add student
	public void addStudent(int userid, String name, String gender) {
		Student student = new Student();
		student.setStudentid(userid);
		student.setName(name);
		student.setGender(gender);
		logger.info("Enter Contact No");
		student.setPhoneNo(scn.nextLong());
		logger.info("Enter Email");
		student.setEmail(scn.next());
		logger.info("Enter Scholarship");
		student.setScholarship(scn.nextDouble());

		adminService.addStudent(student);
	}

	// Method to add professor
	public void addProfessor(int userid, String name, String gender) {
		Professor prof = new Professor();
		prof.setProfessorid(userid);
		prof.setName(name);
		prof.setGender(gender);
		logger.info("Enter Contact No");
		prof.setPhoneNo(scn.nextLong());
		logger.info("Enter Email");
		prof.setEmail(scn.next());
		logger.info("Enter Qualification");
		prof.setDesignation(scn.next());

		adminService.addProfessor(prof);
	}

	// Method to delete user
	public void deleteUser() {

		logger.info("Enter Role (1 -> Admin, 2-> Student, 3-> Professor");
		int role = scn.nextInt();
		logger.info("Enter Username");
		String username = scn.next();
		int userid = adminService.getUserId(username);
		if (role == 1) {
			adminService.deleteAdmin(userid);
		} else if (role == 2) {
			adminService.deleteStudent(userid);
		} else if (role == 3) {
			adminService.deleteProfessor(userid);
		}
		adminService.deleteUser(username);
		logger.info("User " + username + " Successfully Deleted!");
	}

	// Method to view all users
	public void viewAllUser() {

		adminService.viewAllUser();
	}

	// Method to add course to course catalog
	public void addCourseToCatalog() {

		Course course = new Course();

		logger.info("Enter Catalog ID");
		course.setCatalogid(scn.nextInt());
		logger.info("Enter Name");
		course.setName(scn.next());
		logger.info("Enter Type");
		course.setType(scn.next());
		logger.info("Enter Credits");
		course.setCredits(scn.nextInt());
		logger.info("Enter Hours");
		course.setHours(scn.nextInt());
		logger.info("Enter Fee");
		course.setFee(scn.nextInt());

		adminService.addCourseToCatalog(course);
		logger.info("Course Successfully Added!");

	}

	// Method to delete course from course catalog
	public void deleteCourseFromCatalog() {

		logger.info("Enter Catalog Id");
		int catalogid = scn.nextInt();
		logger.info("Enter Course Id");
		int courseId = scn.nextInt();
		adminService.deleteCourseFromCatalog(catalogid, courseId);
		logger.info("Course Successfully Deleted!");
	}

	// Method to update course in course catalog
	public void updateCourse() {

		Course course = new Course();

		logger.info("Enter Course Id");
		int courseId = scn.nextInt();
		logger.info("Enter Catalog ID");
		course.setCatalogid(scn.nextInt());
		logger.info("Enter Name");
		course.setName(scn.next());
		logger.info("Enter Type");
		course.setType(scn.next());
		logger.info("Enter Credits");
		course.setCredits(scn.nextInt());
		logger.info("Enter Hours");
		course.setHours(scn.nextInt());
		logger.info("Enter Fee");
		course.setFee(scn.nextInt());
		adminService.updateCourse(course, courseId);
		logger.info("Course Successfully Updated!");
	}

	// Method to update user
	public void updateUser() {

		User user = new User();
		try {
			logger.info("Enter User ID");
			int userid = scn.nextInt();
			logger.info("Enter name");
			user.setName(scn.next());
			adminService.getAllRoles();
			logger.info("Enter Role Id and Role Name");
			user.setRoleid(scn.nextInt());
			user.setRole(scn.next());
			logger.info("Enter Gender \n 1 Female \n 2 Male");
			int choice = scn.nextInt();
			switch (choice) {
			case 1:
				user.setGender("Female");
				break;
			case 2:
				user.setGender("Male");
				break;
			default:
				break;
			}
			logger.info("Enter Username");
			user.setUsername(scn.next());
			logger.info("Enter password");
			user.setPassword(scn.next());

			adminService.updateUser(user, userid);
			logger.info("User Successfully Updated!");

		} catch (UsernameAlreadyExistsException e) {
			logger.error("Username " + e.getUsername() + " Already Exists. Try Another Username" + e.getMessage());
		}
	}

	// Method to show option menu
	public void showMenu(String username, Integer userid) {

		try {
			logger.info("You have successfully signed in as Admin");
			logger.info("Welcome " + username);

			logger.info("1. View My Details");
			logger.info("2. View Courses");
			logger.info("3. Add Course to Catalog");
			logger.info("4. Delete Course from Catalog");
			logger.info("5. Update Course in Catalog");
			logger.info("6. View All Users");
			logger.info("7. Add User");
			logger.info("8. Delete User");
			logger.info("9. Update User");
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
				addCourseToCatalog();
				break;
			case 4:
				deleteCourseFromCatalog();
				break;
			case 5:
				updateCourse();
				break;
			case 6:
				viewAllUser();
				break;
			case 7:
				addUser();
				break;
			case 8:
				deleteUser();
				break;
			case 9:
				updateUser();
				break;
			case 10:
				redirectToLogin();
				break;
			}
		} catch (InputMismatchException e) {
			logger.error("Wrong input " + e.getMessage());
		} catch (Exception e) {
			logger.error("Something went wrong " + e.getMessage());
		} finally {
			showMenu(username, userid);
		}

		scn.close();
	}

}
