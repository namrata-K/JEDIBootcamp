package com.flipkart.bean;

// Catalog Bean Class
public class Catalog {

	private int catalogid;
	private String name;
	private String description;

	/**
	 * @return the catalogid
	 */
	public int getCatalogid() {
		return catalogid;
	}

	/**
	 * @param catalogid
	 *            the catalogid to set
	 */
	public void setCatalogid(int catalogid) {
		this.catalogid = catalogid;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
