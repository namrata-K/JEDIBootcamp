package com.flipkart.bean;

public class Student {

	private int studentid;
	private String name;
	private String gender;
	private long contactNo;
	private String email;
	private double scholarship;
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the studentid
	 */
	public int getStudentid() {
		return studentid;
	}
	/**
	 * @param studentid the studentid to set
	 */
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the phoneNo
	 */
	public long getPhoneNo() {
		return contactNo;
	}
	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(long phoneNo) {
		this.contactNo = phoneNo;
	}
	/**
	 * @return the scholarship
	 */
	public double getScholarship() {
		return scholarship;
	}
	/**
	 * @param scholarship the scholarship to set
	 */
	public void setScholarship(double scholarship) {
		this.scholarship = scholarship;
	}
	
}
