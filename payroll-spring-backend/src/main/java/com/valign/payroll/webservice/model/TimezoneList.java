package com.valign.payroll.webservice.model;

import java.util.List;

public class TimezoneList {
	
	java.util.List<Timezone> payload ;

	public java.util.List<Timezone> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<Timezone> payload) {
		this.payload = payload;
	}

	public TimezoneList(List<Timezone> payload) {
		super();
		this.payload = payload;
	}
	
}