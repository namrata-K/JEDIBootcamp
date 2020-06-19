package com.flipkart.dao;

import com.flipkart.bean.Student;

// Interface for student dao
public interface StudentDao {

	// Method to get grades
	public String getGrades(Integer userid, int courseid);

	// Method to pay fees
	public void payFee(Integer userid, double amount);

	// Method to get student Details
	public Student getDetails(Integer userid);

	// Method to get scholarship amount
	public double getScholarship(Integer userid);

}
