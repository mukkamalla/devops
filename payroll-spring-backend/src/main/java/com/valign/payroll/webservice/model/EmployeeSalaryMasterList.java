package com.valign.payroll.webservice.model;

import java.util.List;

public class EmployeeSalaryMasterList {

	java.util.List<EmployeeSalaryMaster> payload;

	public java.util.List<EmployeeSalaryMaster> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<EmployeeSalaryMaster> payload) {
		this.payload = payload;
	}

	public EmployeeSalaryMasterList(List<EmployeeSalaryMaster> payload) {
		super();
		this.payload = payload;
	}

}