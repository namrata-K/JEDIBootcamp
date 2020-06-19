package com.flipcard.constant;

// SQL Constant Queries 
public class SQLConstantQueries {

	// query to get all courses
	public static final String GET_ALL_COURSES = "select * from course";

	// query to get course by id
	public static final String GET_COURSE_BY_ID = "select * from course where courseId = ?";

	// query to get user role
	public static final String GET_ROLE = "select role from user where username = ? and password = ?";

	// query to get userid
	public static final String GET_USER_ID = "select userId from user where username = ?";

	// query to get course id
	public static final String GET_COURSE_ID = "select courseid from registeredcourse where studentid = ?";

	// query to insert user to user table
	public static final String INSERT_USER = "insert into user values(null,?,?,?,?,?,?)";

	// query to insert admin to admin table
	public static final String INSERT_ADMIN = "insert into admin values(?,?,?,?,?)";

	// query to insert student in student table
	public static final String INSERT_STUDENT = "insert into student values(?,?,?,?,?,?)";

	// query to add professor in professor table
	public static final String INSERT_PROFESSOR = "insert into professor values(?,?,?,?,?,?)";

	// query to update user
	public static final String UPDATE_USER = "update user set name = ?, role = ?, password = ?, username = ?, gender = ?, roleid = ? where userid = ?";

	// query to delete user from user table
	public static final String DELETE_USER = "delete from user where username = ?";

	// query to delete admin from admin table
	public static final String DELETE_ADMIN = "delete from admin where adminid = ?";

	// query to delete student from student table
	public static final String DELETE_STUDENT = "delete from student where studentid = ?";

	// query to delete student from course
	public static final String DELETE_STUDENT_FROM_COURSE = "delete from registeredcourse where studentid = ?";

	// query to delete professor from professor table
	public static final String DELETE_PROFESSOR = "delete from professor where professorid = ?";

	// query to delete professor from course
	public static final String DELETE_PROFESSOR_FROM_COURSE = "update course set professorid = null, professor = null where professorid = ?";

	// query to get all users
	public static final String GET_ALL_USER = "select * from user order by role";

	// query to insert course in course table
	public static final String INSERT_COURSE = "insert into course(courseId, catalogid, name, type, credits, hours, fee) values(null,?,?,?,?,?,?)";

	// query to update course
	public static final String UPDATE_COURSE = "update course set catalogid = ?, name = ?, type = ?, credits = ?, hours = ?, fee = ? where courseid = ?";

	// query to delete course from course table
	public static final String DELETE_COURSE = "delete from course where catalogid = ? and courseId = ?";

	// query to get count of students enrolled in a course
	public static final String GET_COURSE_COUNT = "select count(*) from registeredcourse where courseid = ?";

	// query to add course registration
	public static final String ADD_COURSE_REGISTRATION = "insert into registeredcourse(studentid, courseid, timestamp) values(?,?, CURRENT_TIMESTAMP)";

	// query to drop course registration
	public static final String DROP_COURSE_REGISTRATION = "delete from registeredcourse where studentid = ? and courseid = ?";

	// query to select course to teach
	public static final String SELECT_COURSE = "update course set professorid = ?, professor = ? where courseid = ?";

	// query to drop course from teaching
	public static final String DROP_PROFESSOR_COURSE = "update course set professor = null, professorid = null where courseid = ? and professorid = ?";

	// query to get professor courses
	public static final String GET_PROFESSOR_COURSES = "select * from course where professorid = ?";

	// query to get student id
	public static final String GET_STUDENT_ID = "select studentid from registeredcourse where courseid = ?";

	// query to get student by id
	public static final String GET_STUDENT_BY_ID = "select * from user where userId = ?";

	// query to upload grades
	public static final String UPLOAD_GRADES = "update registeredCourse set grade = ? where courseid = ? and studentid = ?";

	// query to get grades
	public static final String GET_GRADES = "select grade from registeredCourse where studentid = ? and courseid = ?";

	// query to get userid for a username
	public static final String USER_NAME_EXISTS = "select userid from user where username = ?";

	// query to register 
	public static final String REGISTER = "insert into registration values(null,?,CURRENT_TIMESTAMP,?,?)";

	// query to get all roles
	public static final String GET_ALL_ROLES = "select * from role";

	// query to get registered course
	public static final String GET_REGISTERED_COURSE = "select courseid from registeredcourse where courseid = ? and studentid = ?";

	// query to get registration id
	public static final String IS_REGISTERED = "select registrationid from registration where studentid = ?";

	// query to get professor id for a courseid
	public static final String HAS_PROFESSOR = "select professorid from course where courseid = ?";

	// query to get student details
	public static final String GET_STUDENT_DETAILS = "select * from student where studentid = ?";

	// query to get admin details
	public static final String GET_ADMIN_DETAILS = "select * from admin where adminid = ?";

	// query to get professor details
	public static final String GET_PROFESSOR_DETAILS = "select * from professor where professorid = ?";

	// query to get scholarship amount
	public static final String GET_SCHOLARSHIP = "select scholarship from student where studentid = ?";
	
	// query to get student grades
	public static final String GET_STUDENT_GRADES = "select grade from registeredCourse where studentid = ? and courseid = ?";

}
