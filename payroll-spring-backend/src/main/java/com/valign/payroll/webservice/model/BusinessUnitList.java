package com.valign.payroll.webservice.model;

import java.util.List;

public class BusinessUnitList {

	java.util.List<BusinessUnit> payload;

	public java.util.List<BusinessUnit> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<BusinessUnit> payload) {
		this.payload = payload;
	}

	public BusinessUnitList(List<BusinessUnit> payload) {
		super();
		this.payload = payload;
	}

}