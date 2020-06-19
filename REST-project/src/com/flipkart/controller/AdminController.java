package com.flipkart.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.flipkart.Exception.UsernameAlreadyExistsException;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.service.AdminService;
import com.flipkart.service.AdminServiceImpl;
import com.flipkart.service.CatalogSystem;
import com.flipkart.service.CatalogSystemImpl;
import com.flipkart.service.RegistrationService;
import com.flipkart.service.RegistrationServiceImpl;

// Admin controller
@Path("/admin")
public class AdminController {

	// logger object
	private static Logger logger = Logger.getLogger(AdminController.class);
	CatalogSystem catalog = new CatalogSystemImpl();
	RegistrationService registration = new RegistrationServiceImpl();
	AdminService adminService = new AdminServiceImpl();

	// get call to view all courses
	@GET
	@Path("/viewAllCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> viewCourses() {
		return catalog.searchAndFetchCourseDetails();
	}

	// get call to view admin details
	@GET
	@Path("/viewAdminDetails/{userid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Admin viewDetails(@PathParam("userid") int userid) {
		return adminService.viewDetails(userid);
	}

	// post call to add user
	@POST
	@Path("/addUser")
	@Consumes("application/json")
	public Response addUser(User user) {

		try {
			adminService.addUser(user);

		} catch (UsernameAlreadyExistsException e) {
			logger.error("Username " + e.getUsername() + " Already Exists. Try Another Username" + e.getMessage());
		}
		
		String result = "Track saved : ";
		return Response.status(201).entity(result).build();
	}

	// post call to add admin
	@POST
	@Path("/addAdmin/{userid}")
	@Consumes("application/json")
	public Response addAdmin(@PathParam("userid") int userid, Admin admin) {
		admin.setAdminid(userid);
		adminService.addAdmin(admin);
		
		String result = "Track saved : ";
		return Response.status(201).entity(result).build();
	}

	// post call to add student
	@POST
	@Path("/addStudent/{userid}")
	@Consumes("application/json")
	public Response addStudent(@PathParam("userid") int userid, Student student) {

		student.setStudentid(userid);
		adminService.addStudent(student);
		
		String result = "Track saved : ";
		return Response.status(201).entity(result).build();
	}

	// post call to add professor
	@POST
	@Path("/addProfessor/{userid}")
	@Consumes("application/json")
	public Response addProfessor(@PathParam("userid") int userid, Professor prof) {
		
		prof.setProfessorid(userid);
		adminService.addProfessor(prof);
		
		String result = "Track saved : ";
		return Response.status(201).entity(result).build();
	}

	// get call to view all users
	@GET
	@Path("/viewAllUsers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> viewAllUser() {

		return adminService.viewAllUser();
	}

	// post call to add course
	@POST
	@Path("/addCourse")
	@Consumes("application/json")
	public Response addCourseToCatalog(Course course) {

	
		adminService.addCourseToCatalog(course);
		logger.info("Course Successfully Added!");
		
		String result = "Track saved : ";
		return Response.status(201).entity(result).build();

	}
	
	// delete call to delete course
	@DELETE
	@Path("/deleteCourse/{catalogid}/{courseid}")
	@Consumes("application/json")
	public Response deleteCourseFromCatalog(@PathParam("catalogid") int catalogid, @PathParam("courseid") int courseid) {

		adminService.deleteCourseFromCatalog(catalogid, courseid);
		logger.info("Course Successfully Deleted!");
		
		String result = "Track saved : ";
		return Response.status(201).entity(result).build();
	}

	// put call to update course
	@PUT
	@Path("/updateCourse/{catalogid}/{courseid}")
	@Consumes("application/json")
	public Response updateCourse(@PathParam("catalogid") int catalogid, @PathParam("courseid") int courseid, Course course) {

		adminService.updateCourse(course, courseid);
		logger.info("Course Successfully Updated!");
		
		String result = "Track saved : ";
		return Response.status(201).entity(result).build();
	}

	// put call to update user
	@PUT
	@Path("/updateUser/{userid}")
	@Consumes("application/json")
	public Response updateUser(@PathParam("userid") int userid, User user) {

		try{

			adminService.updateUser(user, userid);
			logger.info("User Successfully Updated!");

		} catch (UsernameAlreadyExistsException e) {
			logger.error("Username " + e.getUsername() + " Already Exists. Try Another Username" + e.getMessage());
		}

		String result = "Track saved : ";
		return Response.status(201).entity(result).build();
	}

}
