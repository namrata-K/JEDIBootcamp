package com.flipkart.service;

import java.util.List;

import com.flipkart.Exception.ProfessorAlreadyExistsException;
import com.flipkart.bean.Course;

// Interface for professor service
public interface ProfessorService {

	// Method to select course
	public void selectCourse(Integer userid, int courseid, String username) throws ProfessorAlreadyExistsException;

	// Method to view registered students
	public void viewRegisteredStudents(Integer userid);

	// Method to get selected course ids
	List<Course> getCourseIds(Integer userid);

	// Method to get registered students ids
	List<Integer> getRegisteredStudentsIds(int courseid);

	// Method to print studentid
	void printStudent(int studentid);

	// Method to upload grade
	public void uploadGrade(Integer studentid, int courseId, String grade);

	// Method to drop course
	public void dropCourse(Integer userid, int courseid, String username);

	// Method to view all grades
	public void viewAllGrades(Integer userid, int courseid);

	// Method to view details
	public void viewDetails(Integer userid);
}
