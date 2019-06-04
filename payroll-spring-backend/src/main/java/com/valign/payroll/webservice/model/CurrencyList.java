package com.valign.payroll.webservice.model;

import java.util.List;

public class CurrencyList {

	java.util.List<Currency> payload;

	public java.util.List<Currency> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<Currency> payload) {
		this.payload = payload;
	}

	public CurrencyList(List<Currency> payload) {
		super();
		this.payload = payload;
	}

}