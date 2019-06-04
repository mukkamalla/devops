package com.valign.payroll.webservice.model;

import java.util.List;

public class LocaleList {
	
	java.util.List<Locale> payload ;

	public java.util.List<Locale> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<Locale> payload) {
		this.payload = payload;
	}

	public LocaleList(List<Locale> payload) {
		super();
		this.payload = payload;
	}
	
}