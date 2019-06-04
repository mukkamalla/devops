package com.valign.payroll.webservice.model;

import java.util.List;

public class SalutationList {
	
	java.util.List<Salutation> payload ;

	public java.util.List<Salutation> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<Salutation> payload) {
		this.payload = payload;
	}

	public SalutationList(List<Salutation> payload) {
		super();
		this.payload = payload;
	}
	
}