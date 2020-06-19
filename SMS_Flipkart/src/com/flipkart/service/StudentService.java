package com.flipkart.service;

import java.util.List;

import com.flipkart.Exception.RegistrationNotDoneException;
import com.flipkart.bean.Course;


public interface StudentService {

	void viewGrades(Integer userid, List<Course> courses) throws RegistrationNotDoneException;

	void payFee(Integer userid, double amount);

	void viewDetails(Integer userid);

	public double calculateFees(Integer userid, List<Course> courses);


	

}
