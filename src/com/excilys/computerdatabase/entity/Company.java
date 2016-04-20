package com.excilys.computerdatabase.entity;

/**
 * Company Entity
 * @author excilys
 *
 */
public class Company {

	private Long  id;
	private String name;
	
	/**
	 * Constructor of the class
	 * @param l Id
	 * @param name Name of the company
	 */
	public Company(Long l, String name) {
		this.id = l;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String toString(){
		String res = id+" - ";
		res+=name;
		return res;
	}
}
