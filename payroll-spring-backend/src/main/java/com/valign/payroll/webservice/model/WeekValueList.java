package com.valign.payroll.webservice.model;

import java.util.List;

public class WeekValueList {

	java.util.List<WeekValue> payload;

	public java.util.List<WeekValue> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<WeekValue> payload) {
		this.payload = payload;
	}

	public WeekValueList(List<WeekValue> payload) {
		super();
		this.payload = payload;
	}

}