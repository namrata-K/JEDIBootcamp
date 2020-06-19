package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.constant.SQLConstantQueries;
import com.flipkart.service.ServiceHelper;
import com.flipkart.utils.DBUtil;

// Catlag dao class which implements catalog dao Interface
public class CatalogDaoImpl implements CatalogDao, ServiceHelper {

	// logger object
	private static Logger logger = Logger.getLogger(CatalogDaoImpl.class);

	// Method to get all courses
	@Override
	public List<Course> getAllCourses() {

		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		List<Course> courseList = new ArrayList<>();
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.GET_ALL_COURSES);
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

	// Method to get course by courseid
	@Override
	public Course getCourseById(int courseid) {

		// Connection establishment
		Connection connection = DBUtil.getConnection();
		PreparedStatement stmt = null;
		Course course = new Course();
		try {
			stmt = connection.prepareStatement(SQLConstantQueries.GET_COURSE_BY_ID);
			stmt.setInt(1, courseid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				course.setCourseId(rs.getInt("courseId"));
				course.setCatalogid(rs.getInt("catalogid"));
				course.setName(rs.getString("name"));
				course.setType(rs.getString("type"));
				course.setCredits(rs.getInt("credits"));
				course.setHours(rs.getInt("hours"));
				course.setFee(rs.getInt("fee"));
				course.setProfessor(rs.getString("professor"));
				course.setProfessorid(rs.getInt("professorid"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}
		return course;
	}

}
