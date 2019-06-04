package com.valign.payroll.webservice.model;

import java.util.List;

public class GenderList {

	java.util.List<Gender> payload;

	public java.util.List<Gender> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<Gender> payload) {
		this.payload = payload;
	}

	public GenderList(List<Gender> payload) {
		super();
		this.payload = payload;
	}

}