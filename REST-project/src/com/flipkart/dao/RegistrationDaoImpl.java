package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.constant.SQLConstantQueries;
import com.flipkart.service.ServiceHelper;
import com.flipkart.utils.DBUtil;

public class RegistrationDaoImpl implements RegistrationDao, ServiceHelper {
	// logger object
	private static Logger logger = Logger.getLogger(RegistrationDaoImpl.class);

	public List<Integer> getRegisteredCourseId(Integer userid) {

		// Connection establishment
		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		List<Integer> courseIdList = new ArrayList<>();
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.GET_COURSE_ID);
			stmt.setInt(1, userid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				courseIdList.add(rs.getInt("courseid"));
			}
			rs.close();
			stmt.close();

		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}
		return courseIdList;
	}

	public int getEnrolledCount(int courseid) {

		// Connection establishment
		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		int count = 0;
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.GET_COURSE_COUNT);
			stmt.setInt(1, courseid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			stmt.close();

		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}
		return count;
	}

	public void addCourse(int courseid, Integer studentid) {

		// Connection establishment
		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.ADD_COURSE_REGISTRATION);
			stmt.setInt(1, studentid);
			stmt.setInt(2, courseid);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}
	}

	public void deleteCourse(int courseid, Integer studentid) {

		// Connection establishment
		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.DROP_COURSE_REGISTRATION);
			stmt.setInt(1, studentid);
			stmt.setInt(2, courseid);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}
	}

	public boolean getRegisteredCourse(Integer userid, int courseid) {

		// Connection establishment
		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		int count = 0;
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.GET_REGISTERED_COURSE);
			stmt.setInt(1, courseid);
			stmt.setInt(2, userid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt("courseid");
			}
			rs.close();
			stmt.close();

		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}
		if (count > 0)
			return true;
		else
			return false;
	}

	public boolean isRegistered(Integer userid) {

		// Connection establishment
		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		int count = 0;
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.IS_REGISTERED);
			stmt.setInt(1, userid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt("registrationid");
			}
			rs.close();
			stmt.close();

		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}
		if (count > 0)
			return true;
		else
			return false;
	}
}
