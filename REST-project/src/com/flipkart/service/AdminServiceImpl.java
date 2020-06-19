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
	AdminDao adminDao = new AdminDaoImpl();

	@Override
	public void addUser(User user) throws UsernameAlreadyExistsException {

		if (adminDao.userExists(user.getUsername())) {
			throw new UsernameAlreadyExistsException(user.getUsername());
		} else {
			adminDao.addUser(user);
		}

	}

	@Override
	public void deleteUser(String username) {

		try {
			adminDao.deleteUser(username);
		} catch (Exception e) {
			logger.error("Something went wrong " + e.getMessage());
		}
	}

	@Override
	public List<User> viewAllUser() {

		List<User> userList = adminDao.getAllUser();
		return userList;
	}

	@Override
	public void addCourseToCatalog(Course course) {

		try {
			adminDao.addCourseToCatalog(course);
		} catch (Exception e) {
			logger.error("Something went wrong " + e.getMessage());
		}
	}

	@Override
	public void deleteCourseFromCatalog(int catalogid, int courseId) {

		try {
			adminDao.deleteCourseFromCatalog(catalogid, courseId);
		} catch (Exception e) {
			logger.error("Something went wrong " + e.getMessage());
		}

	}

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

	@Override
	public int getUserId(String username) {

		int userid = adminDao.getUserId(username);
		return userid;
	}

	@Override
	public void addAdmin(Admin admin) {
		adminDao.addUser(admin);

	}

	@Override
	public void addStudent(Student student) {
		adminDao.addStudent(student);

	}

	@Override
	public void addProfessor(Professor prof) {
		adminDao.addProfessor(prof);

	}

	@Override
	public void deleteAdmin(int userid) {
		adminDao.deleteAdmin(userid);

	}

	@Override
	public void deleteStudent(int userid) {
		adminDao.deleteStudent(userid);

	}

	@Override
	public void deleteProfessor(int userid) {
		adminDao.deleteProfessor(userid);

	}

	@Override
	public void updateCourse(Course course, int courseid) {

		try {
			adminDao.updateCourse(course, courseid);
		} catch (Exception e) {
			logger.error("Something went wrong " + e.getMessage());
		}

	}

	@Override
	public void updateUser(User user, int userid) throws UsernameAlreadyExistsException {
		if (adminDao.userExists(user.getUsername())) {
			throw new UsernameAlreadyExistsException(user.getUsername());
		} else {
			adminDao.updateUser(user, userid);
		}

	}

	@Override
	public Admin viewDetails(Integer userid) {

		Admin admin = adminDao.getDetails(userid);
		return admin;

	}
}
