package com.valign.payroll.webservice.model;

import java.util.List;

public class DayValueList {

	java.util.List<DayValue> payload;

	public java.util.List<DayValue> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<DayValue> payload) {
		this.payload = payload;
	}

	public DayValueList(List<DayValue> payload) {
		super();
		this.payload = payload;
	}

}