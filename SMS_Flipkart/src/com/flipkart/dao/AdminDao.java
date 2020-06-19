package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Role;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;

// Interface for admin DAO
public interface AdminDao {

	// Method to add user
	public void addUser(User user);

	// Method to delete user
	public void deleteUser(String username);

	// Method to get all users
	public List<User> getAllUser();

	// Method to add course to catalog
	public void addCourseToCatalog(Course course);

	// Method to delete course from catalog
	public void deleteCourseFromCatalog(int catalogid, int courseId);

	// Method to check if username already exists
	public boolean userExists(String username);

	// Method to get all roles
	public List<Role> getAllRoles();

	// Method to get userid for a given username
	public int getUserId(String username);

	// Method to add user
	public void addUser(Admin admin);

	// Method to add student
	public void addStudent(Student student);

	// Method to add professor
	public void addProfessor(Professor prof);

	// Method to delete admin
	public void deleteAdmin(int userid);

	// Method to delete student
	public void deleteStudent(int userid);

	// Method to delete professor
	public void deleteProfessor(int userid);

	// Method to update course
	public void updateCourse(Course course, int courseid);

	// Method to update user
	public void updateUser(User user, int userid);

	// Method to get admin details
	public Admin getDetails(Integer userid);

}
