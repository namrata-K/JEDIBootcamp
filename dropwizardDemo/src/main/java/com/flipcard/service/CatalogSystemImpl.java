package com.flipcard.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flipcard.bean.Course;
import com.flipcard.dao.CatalogDaoImpl;

public class CatalogSystemImpl implements CatalogSystem {

	// logger object
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogSystemImpl.class);
	CatalogDaoImpl catalogDao = new CatalogDaoImpl();

	@Override
	public List<Course> searchAndFetchCourseDetails() {

		List<Course> courseList = new ArrayList<Course>();
		try {
			courseList = catalogDao.getAllCourses();
		} catch (Exception e) {
			LOGGER.error("Exception with program " + e.getMessage());
		}
		return courseList;
	}

	@Override
	public Course searchAndFetchCourseById(int courseid) {

		Course course = null;
		try {
			course = catalogDao.getCourseById(courseid);
		} catch (Exception e) {
			LOGGER.error("Exception with program " + e.getMessage());
		}
		return course;
	}

	@Override
	public void showCourses(List<Course> courseList) {

		LOGGER.info("CatalogId CourseId Name Credits Hours Fee Professor");

		courseList.forEach(course -> LOGGER.info(course.getCatalogid() + "   " + course.getCourseId() + "   "
				+ course.getName() + "   " + course.getCredits() + "   " + course.getHours() + "   " + course.getFee()
				+ "   " + course.getProfessor()));
	}

	

}
