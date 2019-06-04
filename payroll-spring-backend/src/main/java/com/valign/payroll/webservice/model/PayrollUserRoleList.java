package com.valign.payroll.webservice.model;



import java.util.List;

public class PayrollUserRoleList {
	
	java.util.List<PayrollUserRole> payload ;

	public java.util.List<PayrollUserRole> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<PayrollUserRole> payload) {
		this.payload = payload;
	}

	public PayrollUserRoleList(List<PayrollUserRole> payload) {
		super();
		this.payload = payload;
	}
	
	

}
