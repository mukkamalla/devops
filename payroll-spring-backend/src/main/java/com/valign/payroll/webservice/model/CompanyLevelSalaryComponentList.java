package com.valign.payroll.webservice.model;

import java.util.List;

public class CompanyLevelSalaryComponentList {
	
	java.util.List<CompanyLevelSalaryComponent> payload ;

	public java.util.List<CompanyLevelSalaryComponent> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<CompanyLevelSalaryComponent> payload) {
		this.payload = payload;
	}

	public CompanyLevelSalaryComponentList(List<CompanyLevelSalaryComponent> payload) {
		super();
		this.payload = payload;
	}
	
}