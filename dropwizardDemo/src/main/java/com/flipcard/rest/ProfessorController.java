package com.flipcard.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flipcard.Exception.ProfessorAlreadyExistsException;
import com.flipcard.bean.Course;
import com.flipcard.bean.Professor;
import com.flipcard.dao.ProfessorDaoImpl;
import com.flipcard.service.CatalogSystem;
import com.flipcard.service.CatalogSystemImpl;
import com.flipcard.service.ProfessorService;
import com.flipcard.service.ProfessorServiceImpl;

// Professor rest controller
@Path("/professor")
public class ProfessorController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessorDaoImpl.class);
	CatalogSystem catalog = new CatalogSystemImpl();
	ProfessorService professor = new ProfessorServiceImpl();

	// Get call to get professor details
	@GET
	@Path("/viewProfessorDetails/{userid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor viewDetails(@PathParam("userid") int userid) {
		return professor.viewDetails(userid);
	}

	// get call to get all courses
	@GET
	@Path("/viewAllCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> viewCourses() {

		LOGGER.info("View all courses");
		return catalog.searchAndFetchCourseDetails();
	}

	// put call to select a course
	@PUT
	@Path("/selectCourse/{userid}/{username}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response selectCourse(@PathParam("userid") int userid, @PathParam("username") String username,
			int courseid) {
		try {
			professor.selectCourse(userid, courseid, username);
		} catch (ProfessorAlreadyExistsException e) {
			LOGGER.error("Vacancy Full. Try another course ");
		}

		String result = "Track saved : " + courseid;
		return Response.status(201).entity(result).build();
	}

	// put call to drop a course
	@PUT
	@Path("/dropCourse/{userid}/{username}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response dropCourse(@PathParam("userid") int userid, @PathParam("username") String username, int courseid) {
		professor.dropCourse(userid, courseid, username);

		String result = "Track saved : ";
		return Response.status(200).entity(result).build();
	}

	// get call to view selected courses
	@GET
	@Path("/viewSelectedCourses/{userid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> viewSelectedCourses(@PathParam("userid") int userid) {
		return professor.getCourseIds(userid);
	}

	// get call to get registered students
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
		LOGGER.info("Grade Successfully Uploaded");
	}

}
