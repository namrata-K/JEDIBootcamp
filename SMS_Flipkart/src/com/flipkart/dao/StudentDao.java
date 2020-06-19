package com.flipkart.dao;

import com.flipkart.bean.Student;

// Interface for student dao
public interface StudentDao {

	// Method to get grade for a given userid and courseid
	public String getGrades(Integer userid, int courseid);

	// Method to pay fees to complete registration
	public void payFee(Integer userid, double amount);

	// Method to get student details
	public Student getDetails(Integer userid);

	// Method to get scholarship amount
	public double getScholarship(Integer userid);

}
