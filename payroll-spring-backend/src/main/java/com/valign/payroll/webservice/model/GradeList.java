package com.valign.payroll.webservice.model;

import java.util.List;

public class GradeList {

	java.util.List<Grade> payload;

	public java.util.List<Grade> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<Grade> payload) {
		this.payload = payload;
	}

	public GradeList(List<Grade> payload) {
		super();
		this.payload = payload;
	}

}