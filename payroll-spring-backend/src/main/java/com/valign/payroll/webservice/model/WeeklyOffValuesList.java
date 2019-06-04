package com.valign.payroll.webservice.model;

import java.util.List;

public class WeeklyOffValuesList {

	java.util.List<WeeklyOffValues> payload;

	public java.util.List<WeeklyOffValues> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<WeeklyOffValues> payload) {
		this.payload = payload;
	}

	public WeeklyOffValuesList(List<WeeklyOffValues> payload) {
		super();
		this.payload = payload;
	}

}