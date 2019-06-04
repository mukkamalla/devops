package com.valign.payroll.webservice.model;

import java.util.List;

public class PayrollUserList {
	
	java.util.List<PayrollUser> payload ;

	public java.util.List<PayrollUser> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<PayrollUser> payload) {
		this.payload = payload;
	}

	public PayrollUserList(List<PayrollUser> payload) {
		super();
		this.payload = payload;
	}
	
	

}
