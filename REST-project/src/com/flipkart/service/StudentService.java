package com.flipkart.service;

import java.util.List;

import com.flipkart.Exception.RegistrationNotDoneException;
import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

// interface for student service
public interface StudentService {

	// Method to view grades
	void viewGrades(Integer userid, List<Course> courses) throws RegistrationNotDoneException;

	// Method to pay fees
	void payFee(Integer userid, double amount);

	// Method to get view student details
	Student viewDetails(Integer userid);

	// Method to calculate final fees
	public double calculateFees(Integer userid, List<Course> courses);

}
