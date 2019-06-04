package com.valign.payroll.webservice.model;

public class EmployeeCount {

	Integer payload;

	public Integer getPayload() {
		return payload;
	}

	public void setPayload(Integer payload) {
		this.payload = payload;
	}

	public EmployeeCount(Integer payload) {
		super();
		this.payload = payload;
	}

}