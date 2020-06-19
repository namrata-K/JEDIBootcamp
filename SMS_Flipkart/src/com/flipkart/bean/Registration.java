package com.flipkart.bean;

// Registration Bean Class
public class Registration {

	private int registratioid;
	private int studentid;
	private String paymentStatus;
	private double finalAmt;

	/**
	 * @return the registratioid
	 */
	public int getRegistratioid() {
		return registratioid;
	}

	/**
	 * @param registratioid
	 *            the registratioid to set
	 */
	public void setRegistratioid(int registratioid) {
		this.registratioid = registratioid;
	}

	/**
	 * @return the studentid
	 */
	public int getStudentid() {
		return studentid;
	}

	/**
	 * @param studentid
	 *            the studentid to set
	 */
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}

	/**
	 * @return the paymentStatus
	 */
	public String getPaymentStatus() {
		return paymentStatus;
	}

	/**
	 * @param paymentStatus
	 *            the paymentStatus to set
	 */
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	/**
	 * @return the finalAmt
	 */
	public double getFinalAmt() {
		return finalAmt;
	}

	/**
	 * @param finalAmt
	 *            the finalAmt to set
	 */
	public void setFinalAmt(double finalAmt) {
		this.finalAmt = finalAmt;
	}

}
