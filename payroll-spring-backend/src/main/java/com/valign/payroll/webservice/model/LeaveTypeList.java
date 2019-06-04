package com.valign.payroll.webservice.model;


import java.util.List;

public class LeaveTypeList {
	
	java.util.List<LeaveType> payload ;

	public java.util.List<LeaveType> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<LeaveType> payload) {
		this.payload = payload;
	}

	public LeaveTypeList(List<LeaveType> payload) {
		super();
		this.payload = payload;
	}
	
}