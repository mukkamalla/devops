package com.valign.payroll.webservice.model;

import java.util.List;

public class RoleList {
	
	java.util.List<Role> payload ;

	public java.util.List<Role> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<Role> payload) {
		this.payload = payload;
	}

	public RoleList(List<Role> payload) {
		super();
		this.payload = payload;
	}
	
}