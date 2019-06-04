package com.valign.payroll.webservice.model;

import java.util.List;

public class YesNoList {
	
	java.util.List<YesNo> payload ;

	public java.util.List<YesNo> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<YesNo> payload) {
		this.payload = payload;
	}

	public YesNoList(List<YesNo> payload) {
		super();
		this.payload = payload;
	}
	
}