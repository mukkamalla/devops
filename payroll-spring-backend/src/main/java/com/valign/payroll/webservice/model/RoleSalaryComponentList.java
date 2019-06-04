package com.valign.payroll.webservice.model;


import java.util.List;

public class RoleSalaryComponentList {
	
	java.util.List<RoleSalaryComponent> payload ;

	public java.util.List<RoleSalaryComponent> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<RoleSalaryComponent> payload) {
		this.payload = payload;
	}

	public RoleSalaryComponentList(List<RoleSalaryComponent> payload) {
		super();
		this.payload = payload;
	}
	
}