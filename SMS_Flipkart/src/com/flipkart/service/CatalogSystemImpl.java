package com.flipkart.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.dao.CatalogDaoImpl;

public class CatalogSystemImpl implements CatalogSystem {

	// logger object
	private static Logger logger = Logger.getLogger(CatalogSystemImpl.class);

	// catalog dao object
	CatalogDaoImpl catalogDao = new CatalogDaoImpl();

	// Method to fetch all courses details
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

	// Method to fetch course by courseid
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

	// Method to show all courses
	@Override
	public void showCourses(List<Course> courseList) {

		logger.info("CatalogId  CourseId \t Name \t      Credits  Hours \t   Fee \t   Professor");

		courseList.forEach(course -> logger.info(course.getCatalogid() + "  \t " + course.getCourseId() + " \t\t  "
				+ course.getName() + " \t  " + course.getCredits() + " \t" + course.getHours() + " \t  " + course.getFee()
				+ " \t  " + course.getProfessor()));
	}

}
