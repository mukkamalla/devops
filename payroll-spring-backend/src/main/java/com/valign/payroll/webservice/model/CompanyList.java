package com.valign.payroll.webservice.model;

import java.util.List;

public class CompanyList {

	java.util.List<Company> payload;

	public java.util.List<Company> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<Company> payload) {
		this.payload = payload;
	}

	public CompanyList(List<Company> payload) {
		super();
		this.payload = payload;
	}

}