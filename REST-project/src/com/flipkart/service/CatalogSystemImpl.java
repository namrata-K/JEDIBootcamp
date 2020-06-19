package com.flipkart.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.dao.CatalogDaoImpl;

public class CatalogSystemImpl implements CatalogSystem {

	// logger object
	private static Logger logger = Logger.getLogger(CatalogSystemImpl.class);
	CatalogDaoImpl catalogDao = new CatalogDaoImpl();

	@Override
	public List<Course> searchAndFetchCourseDetails() {

		List<Course> courseList = new ArrayList<Course>();
		try {
			courseList = catalogDao.getAllCourses();
		} catch (Exception e) {
			logger.error("Exception with program " + e.getMessage());
		}
		return courseList;
	}

	@Override
	public Course searchAndFetchCourseById(int courseid) {

		Course course = null;
		try {
			course = catalogDao.getCourseById(courseid);
		} catch (Exception e) {
			logger.error("Exception with program " + e.getMessage());
		}
		return course;
	}

	@Override
	public void showCourses(List<Course> courseList) {

		logger.info("CatalogId CourseId Name Credits Hours Fee Professor");

		courseList.forEach(course -> logger.info(course.getCatalogid() + "   " + course.getCourseId() + "   "
				+ course.getName() + "   " + course.getCredits() + "   " + course.getHours() + "   " + course.getFee()
				+ "   " + course.getProfessor()));
	}

	

}
