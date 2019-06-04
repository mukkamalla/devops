package com.valign.payroll.webservice.model;

import java.util.List;

public class LanguageList {
	
	java.util.List<Language> payload ;

	public java.util.List<Language> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<Language> payload) {
		this.payload = payload;
	}

	public LanguageList(List<Language> payload) {
		super();
		this.payload = payload;
	}
	
}