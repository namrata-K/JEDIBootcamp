package com.flipkart.service;

import java.util.List;

import com.flipkart.Exception.ProfessorAlreadyExistsException;
import com.flipkart.bean.Course;

// interface for catalog system
public interface CatalogSystem {

	// Method to fetch all course details
	public List<Course> searchAndFetchCourseDetails();

	// Method to fetch course by id
	public Course searchAndFetchCourseById(int courseid);

	// Method to show courses
	public void showCourses(List<Course> courseList);

}
