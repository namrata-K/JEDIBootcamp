package com.flipkart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.URIReferenceException;

import org.apache.log4j.Logger;

import com.flipkart.Exception.ClassFullException;
import com.flipkart.Exception.CourseAlreadyRegistered;
import com.flipkart.Exception.RegistrationAlreadyDoneException;
import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.service.CatalogSystem;
import com.flipkart.service.CatalogSystemImpl;
import com.flipkart.service.RegistrationService;
import com.flipkart.service.RegistrationServiceImpl;
import com.flipkart.service.StudentService;
import com.flipkart.service.StudentServiceImpl;

// student controller
@Path("/student")
public class StudentController {

	private static Logger logger = Logger.getLogger(StudentController.class);
	CatalogSystem catalog = new CatalogSystemImpl();
	RegistrationService registration = new RegistrationServiceImpl();
	StudentService studentService = new StudentServiceImpl();


	// get call to view student details
	@GET
	@Path("/viewStudentDetails/{userid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student viewDetails(@PathParam("userid") int userid) {
		
		return studentService.viewDetails(userid);
	}
	
	// get call to view all courses
	@GET
	@Path("/viewAllCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> viewCourses() {

		logger.info("View all courses");
		return catalog.searchAndFetchCourseDetails();

	}

	// get call to view registered courses
	@GET
	@Path("/viewRegisteredCourses/{userid}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> RegisteredCourses(@PathParam("userid") int userid) {

		logger.info("View Registered courses");
		List<Integer> courseIdList = registration.getRegisteredCourseId(userid);
		List<Course> courseList = new ArrayList<>();
		for (Integer courseId : courseIdList) {
			courseList.add(catalog.searchAndFetchCourseById(courseId));
		}
		return courseList;
	}

	// post call to add course
	@POST
	@Path("/addCourse/{userid}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(@PathParam("userid") int userid, int courseid) {

		try {
			registration.isRegistered(userid);
			registration.addCourse(userid, courseid);
		} catch (ClassFullException e) {
			logger.error("Course" + e.getCourseid() + "full. Try another course ");
		} catch (CourseAlreadyRegistered e) {
			logger.error("Course with id " + e.getCourseid() + "Already Registered");
		} catch (RegistrationAlreadyDoneException e) {
			logger.error("Registration Already Done. Cannot make changes");
		}

		String result = "Track saved : " + courseid;

		return Response.status(201).entity(result).build();
	}

	// delete call to delete course
	@DELETE
	@Path("/dropCourse/{userid}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response dropCourse(@PathParam("userid") int userid, int courseid) throws URIReferenceException {

		try {
			registration.isRegistered(userid);
			registration.deleteCourse(userid, courseid);
			logger.info("Course Successfully Deleted");
		} catch (RegistrationAlreadyDoneException e) {
			logger.error("Registration Already Done. Cannot make changes");
		}

		String result = "Track saved : " + courseid;

		return Response.status(200).entity(result).build();
	}
	

}
