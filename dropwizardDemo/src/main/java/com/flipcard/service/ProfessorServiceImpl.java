package com.flipcard.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flipcard.Exception.ProfessorAlreadyExistsException;
import com.flipcard.bean.Course;
import com.flipcard.bean.Professor;
import com.flipcard.bean.User;
import com.flipcard.dao.ProfessorDao;
import com.flipcard.dao.ProfessorDaoImpl;

public class ProfessorServiceImpl implements ProfessorService {

	// logger object
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogSystemImpl.class);
	ProfessorDao professorDao = new ProfessorDaoImpl();

	@Override
	public void selectCourse(Integer userid, int courseid, String username) throws ProfessorAlreadyExistsException {

		if (professorDao.doesProfessorExist(courseid)) {
			throw new ProfessorAlreadyExistsException();
		} else {
			professorDao.selectCourse(courseid, userid, username);
			LOGGER.info("Course Successfully Selected");
		}
	}

	@Override
	public List<Course> getCourseIds(Integer userid) {
		List<Course> courseIds = new ArrayList<>();
		try {
			courseIds = professorDao.getCourseIds(userid);
		} catch (Exception e) {
			LOGGER.error("Exception with program " + e.getMessage());
		}
		return courseIds;
	}

	@Override
	public List<Integer> getRegisteredStudentsIds(int courseid) {
		List<Integer> students = new ArrayList<>();
		try {
			students = professorDao.getRegisteredStudents(courseid);
		} catch (Exception e) {
			LOGGER.error("Exception with program " + e.getMessage());
		}
		return students;
	}

	@Override
	public void printStudent(int studentid) {
		try {
			int sno = 1;
			User user = professorDao.getStudent(studentid);
			LOGGER.info(sno + ". " + user.getName() + " " + user.getUserId());
		} catch (Exception e) {
			LOGGER.error("Exception with program " + e.getMessage());
		}
	}

	@Override
	public void viewRegisteredStudents(Integer userid) {

		try {
			List<Course> courseIds = getCourseIds(userid);
			for (Course course : courseIds) {
				LOGGER.info(".....Students enrolled for " + course.getName() + " " + course.getCourseId() + ".....");
				List<Integer> students = getRegisteredStudentsIds(course.getCourseId());
				for (Integer studentid : students) {
					printStudent(studentid);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception with program " + e.getMessage());
		}
	}

	@Override
	public void uploadGrade(Integer studentid, int courseId, String grade) {

		try {
			professorDao.uploadGradeCourse(studentid, courseId, grade);
		} catch (Exception e) {
			LOGGER.error("Exception with program " + e.getMessage());
		}
	}

	@Override
	public void dropCourse(Integer userid, int courseid, String username) {

		professorDao.dropCourse(userid, courseid, username);
		LOGGER.info("Course Successfully Dropped");

	}

	@Override
	public void viewAllGrades(Integer userid, int courseid) {

		String grade = professorDao.viewAllGrades(userid, courseid);
		LOGGER.info("Grade : " + grade);

	}

	@Override
	public Professor viewDetails(Integer userid) {

		return professorDao.getDetails(userid);
		

	}

}
