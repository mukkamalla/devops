package com.valign.payroll.webservice.model;

import java.util.List;

public class SalaryCycleList {
	
	java.util.List<SalaryCycle> payload ;

	public java.util.List<SalaryCycle> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<SalaryCycle> payload) {
		this.payload = payload;
	}

	public SalaryCycleList(List<SalaryCycle> payload) {
		super();
		this.payload = payload;
	}
	
}