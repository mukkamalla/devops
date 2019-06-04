package com.valign.payroll.webservice.model;

import java.util.List;

public class AttendanceList {

	java.util.List<Attendance> payload;

	public java.util.List<Attendance> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<Attendance> payload) {
		this.payload = payload;
	}

	public AttendanceList(List<Attendance> payload) {
		super();
		this.payload = payload;
	}

}