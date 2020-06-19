package com.flipcard.dao;

import java.util.List;

import com.flipcard.bean.Course;

// Interface for catalog dao
public interface CatalogDao {

	// Method to get all courses
	public List<Course> getAllCourses();

	// Method to get course by id
	public Course getCourseById(int courseid);

}
