package com.valign.payroll.webservice.model;

import java.util.List;

public class RecordStatusList {
	
	java.util.List<RecordStatus> payload ;

	public java.util.List<RecordStatus> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<RecordStatus> payload) {
		this.payload = payload;
	}

	public RecordStatusList(List<RecordStatus> payload) {
		super();
		this.payload = payload;
	}
	
}