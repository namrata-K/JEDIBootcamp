package com.flipkart.service;

import java.util.List;

import com.flipkart.Exception.ProfessorAlreadyExistsException;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;

// Interface for professor services
public interface ProfessorService {

	// Method to select Course
	public void selectCourse(Integer userid, int courseid, String username) throws ProfessorAlreadyExistsException;

	// Method to view Registered Students
	public void viewRegisteredStudents(Integer userid);

	// Method to get Course Ids
	List<Course> getCourseIds(Integer userid);

	// Method to get Registered Students Ids
	List<Integer> getRegisteredStudentsIds(int courseid);

	// Method to print Student
	void printStudent(int studentid);

	// Method to upload Grade
	public void uploadGrade(Integer studentid, int courseId, String grade);

	// Method to drop Course
	public void dropCourse(Integer userid, int courseid, String username);

	// Method to view All Grades
	public void viewAllGrades(Integer userid, int courseid);

	// Method to view Details
	public Professor viewDetails(Integer userid);
}
