package com.flipkart.Exception;

//Exception thrown when course has lready been added for registration
public class CourseAlreadyRegistered extends Exception {

	private int courseid;

	public CourseAlreadyRegistered(int courseid) {
		this.courseid = courseid;
	}

	public int getCourseid() {
		return this.courseid;
	}

}
