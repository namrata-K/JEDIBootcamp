package com.flipkart.Exception;

// Exception thrown when student capacity for a course is complete
public class ClassFullException extends Exception {

	private int courseid;

	public ClassFullException(int courseid) {
		this.courseid = courseid;
	}

	public int getCourseid() {
		return this.courseid;
	}

}
