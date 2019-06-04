package com.valign.payroll.webservice.model;

import java.util.List;

public class ContinentList {
	
	java.util.List<Continent> payload ;

	public java.util.List<Continent> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<Continent> payload) {
		this.payload = payload;
	}

	public ContinentList(List<Continent> payload) {
		super();
		this.payload = payload;
	}
	
	

}