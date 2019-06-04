package com.valign.payroll.webservice.model;

import java.util.List;

public class DesignationList {

	java.util.List<Designation> payload;

	public java.util.List<Designation> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<Designation> payload) {
		this.payload = payload;
	}

	public DesignationList(List<Designation> payload) {
		super();
		this.payload = payload;
	}

}