package com.flipkart.service;

import java.util.List;

import com.flipkart.Exception.ClassFullException;
import com.flipkart.Exception.CourseAlreadyRegistered;
import com.flipkart.Exception.RegistrationAlreadyDoneException;

// Interface for registration service
public interface RegistrationService {

	// Method to add get registered course ids
	public List<Integer> getRegisteredCourseId(Integer userid);

	// Method to add course
	public void addCourse(Integer userid, int courseid)
			throws ClassFullException, CourseAlreadyRegistered, RegistrationAlreadyDoneException;

	// Method to drop course
	public void deleteCourse(Integer userid, int courseid) throws RegistrationAlreadyDoneException;

	// Method to check if registration is complete
	public void isRegistered(Integer userid) throws RegistrationAlreadyDoneException;

}
