package com.valign.payroll.webservice.model;


import java.util.List;

public class ShiftList {

	java.util.List<Shift> payload;

	public java.util.List<Shift> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<Shift> payload) {
		this.payload = payload;
	}

	public ShiftList(List<Shift> payload) {
		super();
		this.payload = payload;
	}

}