package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.constant.SQLConstantQueries;
import com.flipkart.service.ServiceHelper;
import com.flipkart.utils.DBUtil;

public class CheckCredentialDaoImpl implements CheckCredentialDao, ServiceHelper {

	// logger object
	private static Logger logger = Logger.getLogger(CheckCredentialDaoImpl.class);

	public String verifyIdentity(String username, String password) {

		// Connection establishment
		Connection connection = DBUtil.getConnection();

		PreparedStatement stmt = null;
		String role = null;
		try {

			stmt = connection.prepareStatement(SQLConstantQueries.GET_ROLE);

			stmt.setString(1, username);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				role = rs.getString("role");
			}

			rs.close();
			stmt.close();

		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}

		return role;
	}

	public Integer getUserId(String username) {

		// Connection establishment
		Connection connection = DBUtil.getConnection();

		PreparedStatement stmt = null;
		Integer userid = null;
		try {

			stmt = connection.prepareStatement(SQLConstantQueries.GET_USER_ID);

			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				userid = rs.getInt("userId");
			}

			rs.close();
			stmt.close();

		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}

		return userid;
	}
}
