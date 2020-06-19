package com.flipkart.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.Exception.RegistrationNotDoneException;
import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.dao.RegistrationDaoImpl;
import com.flipkart.dao.StudentDao;
import com.flipkart.dao.StudentDaoImp;

public class StudentServiceImpl implements StudentService {

	private static Logger logger = Logger.getLogger(StudentServiceImpl.class);
	StudentDao studentDao = new StudentDaoImp();
	RegistrationDaoImpl registrationDao = new RegistrationDaoImpl();

	@Override
	public void viewGrades(Integer userid, List<Course> courses) throws RegistrationNotDoneException {

		if (!registrationDao.isRegistered(userid)) {
			throw new RegistrationNotDoneException();
		} else {
			for (Course course : courses) {
				String grade = studentDao.getGrades(userid, course.getCourseId());
				logger.info(course.getName() + "   " + grade);
			}
		}
	}

	@Override
	public void payFee(Integer userid, double amount) {

		try {
			studentDao.payFee(userid, amount);
		} catch (Exception e) {
			logger.error("Something went wrong " + e.getMessage());
		}
	}

	@Override
	public Student viewDetails(Integer userid) {

		Student student = studentDao.getDetails(userid);
		return student;

	}

	@Override
	public double calculateFees(Integer userid, List<Course> courses) {
		
		double fees = 0;
		for (Course course : courses) {
			fees += course.getFee();
		}
		logger.info("Total Fees : " + fees);	
		double scholarship = studentDao.getScholarship(userid);
		logger.info("Scholarship : " + scholarship);	
		double finalAmt = fees-scholarship;
		logger.info("Final Fees : " + finalAmt);
		
		return finalAmt;
		
	}



}
