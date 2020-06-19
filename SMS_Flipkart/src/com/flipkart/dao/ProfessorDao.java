package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.User;

//Interface for professor dao
public interface ProfessorDao {

	// Method to select course to teach
	public void selectCourse(int courseid, Integer userid, String username);

	// Method to get all courses ids selected by professor
	public List<Course> getCourseIds(Integer userid);

	// Method to get all registered students for a given course id
	public List<Integer> getRegisteredStudents(int courseid);

	// Method to get user details of a student
	public User getStudent(Integer studentid);

	// Method to upload grade of all registered students
	public void uploadGradeCourse(Integer studentid, int courseId, String grade);

	// Method to check if a professor is already available for a course
	public boolean doesProfessorExist(int courseid);

	// Method to drop course from teaching list
	public void dropCourse(Integer userid, int courseid, String username);

	// Method to view grades of all registered students
	public String viewAllGrades(Integer userid, int courseid);

	// Method to show professor details
	public Professor getDetails(Integer userid);

}
