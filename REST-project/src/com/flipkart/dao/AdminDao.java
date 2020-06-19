package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Role;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;

// Interface for admin dao
public interface AdminDao {

	// Method to add User
	public void addUser(User user);

	// Method to delete User
	public void deleteUser(String username);

	// Method to get All User
	public List<User> getAllUser();

	// Method to add Course To Catalog
	public void addCourseToCatalog(Course course);

	// Method to delete Course From Catalog
	public void deleteCourseFromCatalog(int catalogid, int courseId);

	// Method to user Exists
	public boolean userExists(String username);

	// Method to get All Roles
	public List<Role> getAllRoles();

	// Method to get UserId
	public int getUserId(String username);

	// Method to add User
	public void addUser(Admin admin);

	// Method to add Student
	public void addStudent(Student student);

	// Method to add Professor
	public void addProfessor(Professor prof);

	// Method to delete Admin
	public void deleteAdmin(int userid);

	// Method to delete Student
	public void deleteStudent(int userid);

	// Method to delete Professor
	public void deleteProfessor(int userid);

	// Method to update Course
	public void updateCourse(Course course, int courseid);

	// Method to update User
	public void updateUser(User user, int userid);

	// Method to get Details
	public Admin getDetails(Integer userid);
}
