package com.valign.payroll.webservice.model;

import java.util.List;

public class LocationList {
	
	java.util.List<Location> payload ;

	public java.util.List<Location> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<Location> payload) {
		this.payload = payload;
	}

	public LocationList(List<Location> payload) {
		super();
		this.payload = payload;
	}
	
}