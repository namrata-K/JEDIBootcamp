package com.flipkart.dao;

import java.util.List;

//Interface for registration dao
public interface RegistrationDao {

	// Method to return regisetered course ids for a given studentid
	public List<Integer> getRegisteredCourseId(Integer userid);

	// Method to get enrollment count for a course id
	public int getEnrolledCount(int courseid);

	// Method to add course to registration
	public void addCourse(int courseid, Integer studentid);

	// Method to delete course from registration
	public void deleteCourse(int courseid, Integer studentid);

	// Method to get registered course for a given userid and courseid
	public boolean getRegisteredCourse(Integer userid, int courseid);

	// Method to check if the userid is already registered
	public boolean isRegistered(Integer userid);
}
