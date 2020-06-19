package com.flipcard.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.flipcard.bean.Course;
import com.flipcard.bean.Customer;
import com.flipcard.service.CatalogSystem;
import com.flipcard.service.CatalogSystemImpl;

@Path("/customer")
public class CustomerRestController {
	
	CatalogSystem catalog = new CatalogSystemImpl();

	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomerDetails()
	{
		Customer customer = new Customer();
		customer.setId(101);
		customer.setAnme("Fllipkart");
		customer.setAddress("India");
		
		return customer;
	}
	
	@GET
	@Path("/viewAllCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> viewCourses() {
		return catalog.searchAndFetchCourseDetails();
	}
}
