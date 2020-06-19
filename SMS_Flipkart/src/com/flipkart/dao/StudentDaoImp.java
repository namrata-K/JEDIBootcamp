package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.bean.Student;
import com.flipkart.constant.SQLConstantQueries;
import com.flipkart.service.ServiceHelper;
import com.flipkart.utils.DBUtil;

// Student dao class that implements student Dao Interface  
public class StudentDaoImp implements StudentDao, ServiceHelper {

	private static Logger logger = Logger.getLogger(StudentDaoImp.class);

	// Method to get grade for a given userid and courseid
	@Override
	public String getGrades(Integer userid, int courseid) {

		// Connection establishment
		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		String grade = null;
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.GET_GRADES);
			stmt.setInt(1, userid);
			stmt.setInt(2, courseid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				grade = rs.getString("grade");
			}
			rs.close();
			stmt.close();

		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}
		return grade;
	}

	// Method to pay fees to complete registration
	@Override
	public void payFee(Integer userid, double amount) {

		// Connection establishment
		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.REGISTER);
			stmt.setInt(1, userid);
			stmt.setString(2, "SUCCESS");
			stmt.setDouble(3, amount);
			stmt.executeUpdate();
			stmt.close();

		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}
	}

	// Method to get student details
	@Override
	public Student getDetails(Integer userid) {
		// Connection establishment
		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		Student student = new Student();
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.GET_STUDENT_DETAILS);
			stmt.setInt(1, userid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				student.setName(rs.getString("name"));
				student.setEmail(rs.getString("email"));
				student.setScholarship(rs.getDouble("scholarship"));
				student.setPhoneNo(rs.getLong("contactNo"));
				student.setGender(rs.getString("gender"));
			}
			rs.close();
			stmt.close();

		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}
		return student;
	}

	// Method to get scholarship amount
	@Override
	public double getScholarship(Integer userid) {
		// Connection establishment
		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		double scholarship = 0;
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.GET_SCHOLARSHIP);
			stmt.setInt(1, userid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				scholarship = rs.getDouble("scholarship");
			}
			rs.close();
			stmt.close();

		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}
		return scholarship;
	}

}
