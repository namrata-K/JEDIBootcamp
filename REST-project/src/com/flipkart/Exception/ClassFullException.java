package com.flipkart.Exception;

public class ClassFullException extends Exception {

	private int courseid;

	public ClassFullException(int courseid) {
		this.courseid = courseid;
	}

	public int getCourseid() {
		return this.courseid;
	}

}
