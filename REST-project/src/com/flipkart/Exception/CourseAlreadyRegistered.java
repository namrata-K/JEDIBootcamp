package com.flipkart.Exception;

public class CourseAlreadyRegistered extends Exception{
	
	private int courseid;
	public CourseAlreadyRegistered(int courseid)
	{
		this.courseid = courseid;
	}
	
	public int getCourseid() {
		return this.courseid;
	}

}
