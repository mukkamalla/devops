package com.valign.payroll.webservice.model;

import java.util.List;

public class SupportedLocalesList {

	java.util.List<SupportedLocales> payload;

	public java.util.List<SupportedLocales> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<SupportedLocales> payload) {
		this.payload = payload;
	}

	public SupportedLocalesList(List<SupportedLocales> payload) {
		super();
		this.payload = payload;
	}

}