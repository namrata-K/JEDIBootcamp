package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.User;

// Interface for professor dao
public interface ProfessorDao {
	
	// Method to select Course
	public void selectCourse(int courseid, Integer userid, String username);

	// Method to get Course Ids for a userid
	public List<Course> getCourseIds(Integer userid);

	// Method to get Registered Students
	public List<Integer> getRegisteredStudents(int courseid);

	// Method to get Student
	public User getStudent(Integer studentid);

	// Method to upload Grade Course
	public void uploadGradeCourse(Integer studentid, int courseId, String grade);

	// Method to check if Professor Exist
	public boolean doesProfessorExist(int courseid);

	// Method to drop Course
	public void dropCourse(Integer userid, int courseid, String username);

	// Method to view All Grades
	public String viewAllGrades(Integer userid, int courseid);

	// Method to get Details
	public Professor getDetails(Integer userid);

}
