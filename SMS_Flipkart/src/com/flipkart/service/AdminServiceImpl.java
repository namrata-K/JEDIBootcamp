package com.flipkart.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.Exception.UsernameAlreadyExistsException;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Role;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.dao.AdminDao;
import com.flipkart.dao.AdminDaoImpl;

public class AdminServiceImpl implements AdminService {

	// logger object
	private static Logger logger = Logger.getLogger(AdminServiceImpl.class);

	// admin dao object
	AdminDao adminDao = new AdminDaoImpl();

	// Method to add user
	@Override
	public void addUser(User user) throws UsernameAlreadyExistsException {

		if (adminDao.userExists(user.getUsername())) {
			throw new UsernameAlreadyExistsException(user.getUsername());
		} else {
			adminDao.addUser(user);
		}
	}

	// Method to delete user
	@Override
	public void deleteUser(String username) {

		try {
			adminDao.deleteUser(username);
		} catch (Exception e) {
			logger.error("Something went wrong " + e.getMessage());
		}
	}

	// Method to view all users
	@Override
	public void viewAllUser() {

		try {
			List<User> userList = adminDao.getAllUser();
//			String str1 = String.format("|%-10d", "ID");
//			String str2 = String.format("|%-10d", "Name");
//			String str3 = String.format("|%-10d", "Username");
//			String str4 = String.format("|%-10d", "");
			logger.info("Id" +" \t "+"Name"+" \t\t "+" Username"+" \t "+" Role");
			userList.stream().forEach(user -> {
				if (user.getGender().equals("Female"))
					user.setName("Ms " + user.getName());
				else
					user.setName("Mr " + user.getName());
			});
			userList.forEach(user -> logger.info(
					user.getUserId() + " \t " + user.getName() + " \t  " + user.getUsername() + "  \t  " + user.getRole()));
		} catch (Exception e) {
			logger.error("Something went wrong " + e.getMessage());
		}

	}

	// Method to add course to catalog
	@Override
	public void addCourseToCatalog(Course course) {

		try {
			adminDao.addCourseToCatalog(course);
		} catch (Exception e) {
			logger.error("Something went wrong " + e.getMessage());
		}
	}

	// Method to delete course from catalog
	@Override
	public void deleteCourseFromCatalog(int catalogid, int courseId) {

		try {
			adminDao.deleteCourseFromCatalog(catalogid, courseId);
		} catch (Exception e) {
			logger.error("Something went wrong " + e.getMessage());
		}

	}

	// Method to get all roles from catalog
	@Override
	public List<Role> getAllRoles() {

		List<Role> roles = new ArrayList<>();
		try {
			roles = adminDao.getAllRoles();
			logger.info("Role id   Role Name   Description");
			roles.forEach(
					role -> logger.info(role.getRoleid() + "   " + role.getRoleName() + "   " + role.getDescription()));
		} catch (Exception e) {
			logger.error("Something went wrong " + e.getMessage());
		}
		return roles;
	}

	// Method to get userid for a username
	@Override
	public int getUserId(String username) {
		int userid = 0;
		try {
			userid = adminDao.getUserId(username);
		} catch (Exception e) {
			logger.error("Something went wrong " + e.getMessage());
		}
		return userid;
	}

	// Method to add admin
	@Override
	public void addAdmin(Admin admin) {
		try {
			adminDao.addUser(admin);
		} catch (Exception e) {
			logger.error("Something went wrong " + e.getMessage());
		}

	}

	// Method too add Student
	@Override
	public void addStudent(Student student) {
		try {
			adminDao.addStudent(student);
		} catch (Exception e) {
			logger.error("Something went wrong " + e.getMessage());
		}

	}

	// Method to add professor
	@Override
	public void addProfessor(Professor prof) {
		try {
			adminDao.addProfessor(prof);
		} catch (Exception e) {
			logger.error("Something went wrong " + e.getMessage());
		}

	}

	// Method to delete admin
	@Override
	public void deleteAdmin(int userid) {
		try {
			adminDao.deleteAdmin(userid);
		} catch (Exception e) {
			logger.error("Something went wrong " + e.getMessage());
		}

	}

	// Method to delete student
	@Override
	public void deleteStudent(int userid) {
		try {
			adminDao.deleteStudent(userid);
		} catch (Exception e) {
			logger.error("Something went wrong " + e.getMessage());
		}

	}

	// Method to delete professor
	@Override
	public void deleteProfessor(int userid) {
		try {
			adminDao.deleteProfessor(userid);
		} catch (Exception e) {
			logger.error("Something went wrong " + e.getMessage());
		}

	}

	// Method to update course in catalog
	@Override
	public void updateCourse(Course course, int courseid) {

		try {
			adminDao.updateCourse(course, courseid);
		} catch (Exception e) {
			logger.error("Something went wrong " + e.getMessage());
		}

	}

	// Method to update user
	@Override
	public void updateUser(User user, int userid) throws UsernameAlreadyExistsException {
		if (adminDao.userExists(user.getUsername())) {
			throw new UsernameAlreadyExistsException(user.getUsername());
		} else {
			adminDao.updateUser(user, userid);
		}

	}

	// Method to view student details
	@Override
	public void viewDetails(Integer userid) {

		Admin admin = adminDao.getDetails(userid);
		logger.info("Name: " + admin.getName() + "\nGender: " + admin.getGender() + "\nEmail: " + admin.getEmail()
				+ "\nContact No: " + admin.getContactNo());

	}
}
