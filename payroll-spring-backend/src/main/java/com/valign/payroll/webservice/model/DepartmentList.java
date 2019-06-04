package com.valign.payroll.webservice.model;

import java.util.List;

public class DepartmentList {

	java.util.List<Department> payload;

	public java.util.List<Department> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<Department> payload) {
		this.payload = payload;
	}

	public DepartmentList(List<Department> payload) {
		super();
		this.payload = payload;
	}

}