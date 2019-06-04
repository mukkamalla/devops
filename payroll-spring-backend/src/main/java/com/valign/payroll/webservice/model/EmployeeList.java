package com.valign.payroll.webservice.model;

import java.util.List;

public class EmployeeList {

	java.util.List<Employee> payload;

	public java.util.List<Employee> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<Employee> payload) {
		this.payload = payload;
	}

	public EmployeeList(List<Employee> payload) {
		super();
		this.payload = payload;
	}

}