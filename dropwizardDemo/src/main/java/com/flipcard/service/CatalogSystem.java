package com.flipcard.service;

import java.util.List;

import com.flipcard.bean.Course;

// Interface for catalog system
public interface CatalogSystem {

	// Method to search and fetch all courses
	public List<Course> searchAndFetchCourseDetails();

	// Method to search and fetch course by id
	public Course searchAndFetchCourseById(int courseid);

	// Method to show courses
	public void showCourses(List<Course> courseList);

}
