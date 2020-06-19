package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Role;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constant.SQLConstantQueries;
import com.flipkart.service.ServiceHelper;
import com.flipkart.utils.DBUtil;

// Class for Admin dao implementing admin dao Interface
public class AdminDaoImpl implements AdminDao, ServiceHelper {

	// logger object
	private static Logger logger = Logger.getLogger(AdminDaoImpl.class);

	// Method to add user
	@Override
	public void addUser(User user) {

		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.INSERT_USER);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getRole());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getUsername());
			stmt.setString(5, user.getGender());
			stmt.setInt(6, user.getRoleid());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}

	}

	// Method to delete user
	@Override
	public void deleteUser(String username) {

		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.DELETE_USER);
			stmt.setString(1, username);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}
	}

	// Method to get all users
	@Override
	public List<User> getAllUser() {

		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		List<User> userList = new ArrayList<>();
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.GET_ALL_USER);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setUsername(rs.getString("username"));
				user.setName(rs.getString("name"));
				user.setGender(rs.getString("gender"));
				user.setRole(rs.getString("role"));
				userList.add(user);
			}
			rs.close();
			stmt.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}
		return userList;
	}

	// Method to add course to catalog
	@Override
	public void addCourseToCatalog(Course course) {

		// Connection establishment
		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.INSERT_COURSE);
			stmt.setInt(1, course.getCatalogid());
			stmt.setString(2, course.getName());
			stmt.setString(3, course.getType());
			stmt.setInt(4, course.getCredits());
			stmt.setInt(5, course.getHours());
			stmt.setInt(6, course.getFee());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}

	}

	// Method to delete course from catalog
	@Override
	public void deleteCourseFromCatalog(int catalogid, int courseId) {

		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.DELETE_COURSE);
			stmt.setInt(1, catalogid);
			stmt.setInt(2, courseId);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}
	}

	// Method to confirm if userid exists a given username
	@Override
	public boolean userExists(String username) {

		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		int userid = 0;
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.USER_NAME_EXISTS);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				userid = rs.getInt("userId");
			}
			stmt.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}
		if (userid == 0)
			return false;
		else
			return true;
	}

	// Method to get all roles
	@Override
	public List<Role> getAllRoles() {
		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		List<Role> roleList = new ArrayList<>();
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.GET_ALL_ROLES);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Role role = new Role();
				role.setRoleid(rs.getInt("roleid"));
				role.setRoleName(rs.getString("roleName"));
				role.setDescription(rs.getString("description"));
				roleList.add(role);
			}
			rs.close();
			stmt.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}
		return roleList;
	}

	// Method to get userid for a given username
	@Override
	public int getUserId(String username) {

		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		int userid = 0;
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.USER_NAME_EXISTS);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				userid = rs.getInt("userId");
			}
			stmt.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}
		return userid;
	}

	// Method to add user
	@Override
	public void addUser(Admin admin) {

		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.INSERT_ADMIN);
			stmt.setInt(1, admin.getAdminid());
			stmt.setString(2, admin.getName());
			stmt.setString(3, admin.getGender());
			stmt.setLong(4, admin.getContactNo());
			stmt.setString(5, admin.getEmail());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}

	}

	// Method to add student
	@Override
	public void addStudent(Student student) {
		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.INSERT_STUDENT);
			stmt.setInt(1, student.getStudentid());
			stmt.setString(2, student.getName());
			stmt.setString(3, student.getGender());
			stmt.setLong(4, student.getPhoneNo());
			stmt.setString(5, student.getEmail());
			stmt.setDouble(6, student.getScholarship());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}

	}

	// Method to add professor
	@Override
	public void addProfessor(Professor prof) {
		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.INSERT_PROFESSOR);
			stmt.setInt(1, prof.getProfessorid());
			stmt.setString(2, prof.getName());
			stmt.setString(3, prof.getGender());
			stmt.setLong(4, prof.getPhoneNo());
			stmt.setString(5, prof.getEmail());
			stmt.setString(6, prof.getDesignation());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}

	}

	// Method to delete admin
	@Override
	public void deleteAdmin(int userid) {
		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.DELETE_ADMIN);
			stmt.setInt(1, userid);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}

	}

	// Method to delete student
	@Override
	public void deleteStudent(int userid) {
		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.DELETE_STUDENT);
			stmt.setInt(1, userid);
			stmt.executeUpdate();
			stmt = connection.prepareStatement(SQLConstantQueries.DELETE_STUDENT_FROM_COURSE);
			stmt.setInt(1, userid);
			stmt.executeUpdate();
			stmt.close();

		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}

	}

	// Method to delete professor
	@Override
	public void deleteProfessor(int userid) {
		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.DELETE_PROFESSOR);
			stmt.setInt(1, userid);
			stmt.executeUpdate();
			stmt = connection.prepareStatement(SQLConstantQueries.DELETE_PROFESSOR_FROM_COURSE);
			stmt.setInt(1, userid);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}

	}

	// Method to update course in catalog
	@Override
	public void updateCourse(Course course, int courseid) {
		// Connection establishment
		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.UPDATE_COURSE);
			stmt.setInt(1, course.getCatalogid());
			stmt.setString(2, course.getName());
			stmt.setString(3, course.getType());
			stmt.setInt(4, course.getCredits());
			stmt.setInt(5, course.getHours());
			stmt.setInt(6, course.getFee());
			stmt.setInt(7, courseid);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}

	}

	// Method to update user
	@Override
	public void updateUser(User user, int userid) {

		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.UPDATE_USER);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getRole());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getUsername());
			stmt.setString(5, user.getGender());
			stmt.setInt(6, user.getRoleid());
			stmt.setInt(7, userid);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}

	}

	// Method to get admin details
	@Override
	public Admin getDetails(Integer userid) {
		// Connection establishment
		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		Admin admin = new Admin();
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.GET_ADMIN_DETAILS);
			stmt.setInt(1, userid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				admin.setName(rs.getString("name"));
				admin.setEmail(rs.getString("email"));
				admin.setContactNo(rs.getLong("contactNo"));
				admin.setGender(rs.getString("gender"));
			}
			rs.close();
			stmt.close();

		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}
		return admin;
	}
}
