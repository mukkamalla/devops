package com.valign.payroll.webservice.model;

import java.util.List;

public class CountryList {

	java.util.List<Country> payload;

	public java.util.List<Country> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<Country> payload) {
		this.payload = payload;
	}

	public CountryList(List<Country> payload) {
		super();
		this.payload = payload;
	}

}