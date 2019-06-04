package com.valign.payroll.webservice.model;
import java.util.List;

public class CompanyGroupList {

	java.util.List<CompanyGroup> payload;

	public java.util.List<CompanyGroup> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<CompanyGroup> payload) {
		this.payload = payload;
	}

	public CompanyGroupList(List<CompanyGroup> payload) {
		super();
		this.payload = payload;
	}

}