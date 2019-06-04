package com.valign.payroll.webservice.model;

import java.util.List;

public class EmploymentStatusList {

	java.util.List<EmploymentStatus> payload;

	public java.util.List<EmploymentStatus> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<EmploymentStatus> payload) {
		this.payload = payload;
	}

	public EmploymentStatusList(List<EmploymentStatus> payload) {
		super();
		this.payload = payload;
	}

}