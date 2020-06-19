package com.flipkart.service;

import java.util.List;

import com.flipkart.Exception.ClassFullException;
import com.flipkart.Exception.CourseAlreadyRegistered;
import com.flipkart.Exception.RegistrationAlreadyDoneException;

// Interface for Registration service
public interface RegistrationService {

	// Method to get registeres course ids by userid
	public List<Integer> getRegisteredCourseId(Integer userid);

	// Method to add course to reistration
	public void addCourse(Integer userid, int courseid)
			throws ClassFullException, CourseAlreadyRegistered, RegistrationAlreadyDoneException;

	// Method to delete course from registration
	public void deleteCourse(Integer userid, int courseid) throws RegistrationAlreadyDoneException;

	// Method to check if registration is done
	public void isRegistered(Integer userid) throws RegistrationAlreadyDoneException;

}
