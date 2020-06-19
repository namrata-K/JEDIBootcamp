package com.flipkart.dao;

import java.util.List;

// Interface for registration dao
public interface RegistrationDao {

	// Method to get registered courses for a userid
	public List<Integer> getRegisteredCourseId(Integer userid);

	// Method to get no. of students enrolled
	public int getEnrolledCount(int courseid);

	// Method to add course to registration
	public void addCourse(int courseid, Integer studentid);
}
