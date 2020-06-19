package com.flipkart.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.flipkart.Exception.ProfessorAlreadyExistsException;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.service.CatalogSystem;
import com.flipkart.service.CatalogSystemImpl;
import com.flipkart.service.ProfessorService;
import com.flipkart.service.ProfessorServiceImpl;
import com.flipkart.service.RegistrationService;
import com.flipkart.service.RegistrationServiceImpl;

// professor controller
@Path("/professor")
public class ProfessorController {

	private static Logger logger = Logger.getLogger(ProfessorController.class);
	CatalogSystem catalog = new CatalogSystemImpl();
	RegistrationService registration = new RegistrationServiceImpl();
	ProfessorService professor = new ProfessorServiceImpl();

	// get call to view professor details
	@GET
	@Path("/viewProfessorDetails/{userid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor viewDetails(@PathParam("userid") int userid) {
		return professor.viewDetails(userid);
	}

	// get call to view all courses
	@GET
	@Path("/viewAllCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> viewCourses() {

		logger.info("View all courses");
		return catalog.searchAndFetchCourseDetails();
	}

	// put call to select course
	@PUT
	@Path("/selectCourse/{userid}/{username}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response selectCourse(@PathParam("userid") int userid, @PathParam("username") String username,
			int courseid) {
		try {
			professor.selectCourse(userid, courseid, username);
		} catch (ProfessorAlreadyExistsException e) {
			logger.error("Vacancy Full. Try another course ");
		}

		String result = "Track saved : " + courseid;
		return Response.status(201).entity(result).build();
	}

	// put call to drop course
	@PUT
	@Path("/dropCourse/{userid}/{username}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response dropCourse(@PathParam("userid") int userid, @PathParam("username") String username, int courseid) {
		professor.dropCourse(userid, courseid, username);

		String result = "Track saved : ";
		return Response.status(200).entity(result).build();
	}

	// get call to view selected course
	@GET
	@Path("/viewSelectedCourses/{userid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> viewSelectedCourses(@PathParam("userid") int userid) {
		return professor.getCourseIds(userid);
	}

	// get call to view registered students
	@GET
	@Path("/getRegisteredStudents")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Integer> viewRegisteredStudents(int courseid) {
		return professor.getRegisteredStudentsIds(courseid);
	}

	// put call to upload grades
	@PUT
	@Path("/uploadGrade/{studentid}/{courseid}")
	@Consumes("application/json")
	public void uploadGradeByStudentId(@PathParam("studentid") int studentid, @PathParam("courseid") int courseid,
			String grade) {

		professor.uploadGrade(studentid, courseid, grade);
		logger.info("Grade Successfully Uploaded");
	}

}
