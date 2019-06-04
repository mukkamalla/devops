package com.valign.payroll.webservice.model;

import java.util.List;

public class EmployeeSalaryComponentList {

	java.util.List<EmployeeSalaryComponent> payload;

	public java.util.List<EmployeeSalaryComponent> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<EmployeeSalaryComponent> payload) {
		this.payload = payload;
	}

	public EmployeeSalaryComponentList(List<EmployeeSalaryComponent> payload) {
		super();
		this.payload = payload;
	}

}