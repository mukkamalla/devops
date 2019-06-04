package com.valign.payroll.webservice.model;

import java.util.List;

public class WeeklyOffsList {

	java.util.List<WeeklyOffs> payload;

	public java.util.List<WeeklyOffs> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<WeeklyOffs> payload) {
		this.payload = payload;
	}

	public WeeklyOffsList(List<WeeklyOffs> payload) {
		super();
		this.payload = payload;
	}

}