package com.flipkart.service;

import java.util.List;

import com.flipkart.Exception.UsernameAlreadyExistsException;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Role;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;

// Interface for admin services
public interface AdminService {

	// Method to add user
	public void addUser(User user) throws UsernameAlreadyExistsException;

	// Method to delete user
	public void deleteUser(String username);

	// Method to view all users
	public void viewAllUser();

	// Method to add course to catalog
	public void addCourseToCatalog(Course course);

	// Method to delete course from catalog
	public void deleteCourseFromCatalog(int catalogid, int courseId);

	// Method to get all roles from catalog
	public List<Role> getAllRoles();

	// Method to get userid for a username
	public int getUserId(String username);

	// Method to add admin
	public void addAdmin(Admin admin);

	// Method too add Student
	public void addStudent(Student student);

	// Method to add professor
	public void addProfessor(Professor prof);

	// Method to delete admin
	public void deleteAdmin(int userid);

	// Method to delete student
	public void deleteStudent(int userid);

	// Method to delete professor
	public void deleteProfessor(int userid);

	// Method to update course in catalog
	public void updateCourse(Course course, int courseid);

	// Method to update user
	public void updateUser(User user, int userid) throws UsernameAlreadyExistsException;

	// Method to view student details
	public void viewDetails(Integer userid);
}
