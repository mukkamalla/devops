package com.valign.payroll.webservice.model;

import java.util.List;

public class EmployeeShiftList {

	java.util.List<EmployeeShift> payload;

	public java.util.List<EmployeeShift> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<EmployeeShift> payload) {
		this.payload = payload;
	}

	public EmployeeShiftList(List<EmployeeShift> payload) {
		super();
		this.payload = payload;
	}

}