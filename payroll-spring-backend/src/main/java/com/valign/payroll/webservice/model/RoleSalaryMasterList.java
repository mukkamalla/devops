package com.valign.payroll.webservice.model;

import java.util.List;

public class RoleSalaryMasterList {

	java.util.List<RoleSalaryMaster> payload;

	public java.util.List<RoleSalaryMaster> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<RoleSalaryMaster> payload) {
		this.payload = payload;
	}

	public RoleSalaryMasterList(List<RoleSalaryMaster> payload) {
		super();
		this.payload = payload;
	}

}