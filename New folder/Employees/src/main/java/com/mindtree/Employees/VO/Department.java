package com.mindtree.Employees.VO;

public class Department {
	private long id;
	private String name;
	private int noOfEmployees;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNoOfEmployees() {
		return noOfEmployees;
	}
	public void setNoOfEmployees(int noOfEmployees) {
		this.noOfEmployees = noOfEmployees;
	}
	public Department(long id, String name, int noOfEmployees) {
		super();
		this.id = id;
		this.name = name;
		this.noOfEmployees = noOfEmployees;
	}
	public Department()
	{
		super();
	}
	

}
