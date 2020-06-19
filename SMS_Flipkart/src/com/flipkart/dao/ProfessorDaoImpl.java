package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.User;
import com.flipkart.constant.SQLConstantQueries;
import com.flipkart.service.ServiceHelper;
import com.flipkart.utils.DBUtil;

// Professor Dao class that implements professor dao Interface
public class ProfessorDaoImpl implements ProfessorDao, ServiceHelper {

	// Method to select course to teach
	@Override
	public void selectCourse(int courseid, Integer userid, String username) {

		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.SELECT_COURSE);
			stmt.setInt(1, userid);
			stmt.setString(2, username);
			stmt.setInt(3, courseid);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}
	}

	// Method to get all courses ids selected by professor
	@Override
	public List<Course> getCourseIds(Integer userid) {

		// Connection establishment
		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		List<Course> courseList = new ArrayList<>();
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.GET_PROFESSOR_COURSES);
			stmt.setInt(1, userid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Course course = new Course();
				course.setCatalogid(rs.getInt("catalogid"));
				course.setCourseId(rs.getInt("courseId"));
				course.setName(rs.getString("name"));
				course.setType(rs.getString("type"));
				course.setCredits(rs.getInt("credits"));
				course.setHours(rs.getInt("hours"));
				course.setFee(rs.getInt("fee"));
				course.setProfessor(rs.getString("professor"));
				course.setProfessorid(rs.getInt("professorid"));
				courseList.add(course);
			}
			rs.close();
			stmt.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}
		return courseList;
	}

	// Method to get all registered students for a given course id
	@Override
	public List<Integer> getRegisteredStudents(int courseid) {

		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		List<Integer> studentIds = new ArrayList<>();
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.GET_STUDENT_ID);
			stmt.setInt(1, courseid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				studentIds.add(rs.getInt("studentid"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}
		return studentIds;
	}

	// Method to get user details of a student
	@Override
	public User getStudent(Integer studentid) {

		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		User user = new User();
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.GET_STUDENT_BY_ID);
			stmt.setInt(1, studentid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				user.setUserId(rs.getInt("userId"));
				user.setName(rs.getString("name"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}
		return user;
	}

	// Method to upload grade of all registered students
	@Override
	public void uploadGradeCourse(Integer studentid, int courseId, String grade) {

		// Connection establishment
		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.UPLOAD_GRADES);
			stmt.setString(1, grade);
			stmt.setInt(2, courseId);
			stmt.setInt(3, studentid);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}
	}

	// Method to check if a professor is already available for a course
	@Override
	public boolean doesProfessorExist(int courseid) {

		// Connection establishment
		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		int count = 0;
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.HAS_PROFESSOR);
			stmt.setInt(1, courseid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt("professorid");
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

	// Method to drop course from teaching list
	@Override
	public void dropCourse(Integer userid, int courseid, String username) {

		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.DROP_PROFESSOR_COURSE);
			stmt.setInt(1, courseid);
			stmt.setInt(2, userid);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}

	}

	// Method to view grades of all registered students
	@Override
	public String viewAllGrades(Integer userid, int courseid) {

		// Connection establishment
		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		String grade = "";
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.GET_STUDENT_GRADES);
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

	// Method to show professor details
	@Override
	public Professor getDetails(Integer userid) {
		// Connection establishment
		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		Professor professor = new Professor();
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.GET_PROFESSOR_DETAILS);
			stmt.setInt(1, userid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				professor.setName(rs.getString("name"));
				professor.setEmail(rs.getString("email"));
				professor.setDesignation("qualification");
				;
				professor.setPhoneNo(rs.getLong("contactNo"));
				professor.setGender(rs.getString("gender"));
			}
			rs.close();
			stmt.close();

		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}
		return professor;
	}

}
