package com.flipkart.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.Exception.ClassFullException;
import com.flipkart.Exception.CourseAlreadyRegistered;
import com.flipkart.Exception.RegistrationAlreadyDoneException;
import com.flipkart.dao.RegistrationDaoImpl;

public class RegistrationServiceImpl implements RegistrationService {

	// logger object
	private static Logger logger = Logger.getLogger(RegistrationServiceImpl.class);
	RegistrationDaoImpl registrationDao = new RegistrationDaoImpl();

	public List<Integer> getRegisteredCourseId(Integer userid) {

		List<Integer> courseIdList = new ArrayList<Integer>();
		courseIdList = registrationDao.getRegisteredCourseId(userid);
		return courseIdList;
	}

	@Override
	public void addCourse(Integer userid, int courseid)
			throws ClassFullException, CourseAlreadyRegistered, RegistrationAlreadyDoneException {

		int count = registrationDao.getEnrolledCount(courseid);
		if (registrationDao.isRegistered(userid)) {
			throw new RegistrationAlreadyDoneException();
		} else if (registrationDao.getRegisteredCourse(userid, courseid)) {
			throw new CourseAlreadyRegistered(courseid);
		} else if (count >= 10) {
			throw new ClassFullException(courseid);
		} else {
			registrationDao.addCourse(courseid, userid);
			logger.info("Course Successfully Added");
		}
	}

	@Override
	public void deleteCourse(Integer userid, int courseid) throws RegistrationAlreadyDoneException {

		if (registrationDao.isRegistered(userid)) {
			throw new RegistrationAlreadyDoneException();
		} else {
			registrationDao.deleteCourse(courseid, userid);
		}

	}

	@Override
	public void isRegistered(Integer userid) throws RegistrationAlreadyDoneException {

		if (registrationDao.isRegistered(userid)) {
			throw new RegistrationAlreadyDoneException();
		}
	}
}
