package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Course;

// Interface for catalog service
public interface CatalogSystem {

	// Method to fetch all courses details
	public List<Course> searchAndFetchCourseDetails();

	// Method to fetch course by courseid
	public Course searchAndFetchCourseById(int courseid);

	// Method to show all courses
	public void showCourses(List<Course> courseList);

}
