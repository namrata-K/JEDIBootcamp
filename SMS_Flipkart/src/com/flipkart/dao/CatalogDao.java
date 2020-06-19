package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.User;

// Interface for catalog dao
public interface CatalogDao {

	// Method to get all courses
	public List<Course> getAllCourses();

	// Method to get course by courseid
	public Course getCourseById(int courseid);

}
