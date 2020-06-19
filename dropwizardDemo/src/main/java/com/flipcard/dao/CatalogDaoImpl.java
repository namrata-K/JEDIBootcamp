package com.flipcard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flipcard.bean.Course;
import com.flipcard.constant.SQLConstantQueries;
import com.flipcard.service.ServiceHelper;
import com.flipcard.utils.DBUtil;;

public class CatalogDaoImpl implements CatalogDao, ServiceHelper {

	// logger object
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogDaoImpl.class);

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
			LOGGER.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}
		return courseList;
	}

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
			LOGGER.error(se.getMessage());
		} finally {
			closeConnection(stmt);
		}
		return course;
	}

	

}
