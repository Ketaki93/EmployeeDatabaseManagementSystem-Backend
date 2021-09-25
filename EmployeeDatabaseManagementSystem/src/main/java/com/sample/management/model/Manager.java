package com.sample.management.model;

public class Manager extends Employee {
	
	private long managerId;
	
	public Manager() {
	}

	public Manager(String firstname, String lastname, long managerId) {
		super(firstname, lastname);
		this.managerId = managerId;
	}

	public long getManagerId() {
		return managerId;
	}

	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}

}
