package com.valign.payroll.webservice.model;

import java.util.List;

public class HolidayMasterList {

	java.util.List<HolidayMaster> payload;

	public java.util.List<HolidayMaster> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<HolidayMaster> payload) {
		this.payload = payload;
	}

	public HolidayMasterList(List<HolidayMaster> payload) {
		super();
		this.payload = payload;
	}

}