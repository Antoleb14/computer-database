package com.excilys.computerdatabase.entity;

import java.time.LocalDateTime;

/**
 * Computer Entity
 * @author excilys
 *
 */
public class Computer {

	private Long id;
	private String name;
	private LocalDateTime introduced;
	private LocalDateTime discontinued;
	private Company company;
	
	/**
	 * Constructor of the class
	 */
	public Computer(){
		id = null;
		name = "";
		introduced = null;
		discontinued = null;
		company = null;
	}
	
	/**
	 * Constructor of the class
	 * @param id
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param company
	 */
	public Computer(Long id, String name, LocalDateTime introduced, LocalDateTime discontinued, Company company){
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
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
	public LocalDateTime getIntroduced() {
		return introduced;
	}
	public void setIntroduced(LocalDateTime introduced) {
		this.introduced = introduced;
	}
	public LocalDateTime getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(LocalDateTime discontinued) {
		this.discontinued = discontinued;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public String toString(){
		StringBuilder res = new StringBuilder();
		res.append(id).append(" | ");
		res.append(name).append(" | ");
		res.append(introduced).append(" | ");
		res.append(discontinued).append(" | ");
		res.append((company !=null?company.getName():"No company"));
		return res.toString();
	}
	
	
}
