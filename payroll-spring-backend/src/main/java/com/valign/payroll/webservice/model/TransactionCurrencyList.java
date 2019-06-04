package com.valign.payroll.webservice.model;

import java.util.List;

public class TransactionCurrencyList {

	java.util.List<TransactionCurrency> payload;

	public java.util.List<TransactionCurrency> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<TransactionCurrency> payload) {
		this.payload = payload;
	}

	public TransactionCurrencyList(List<TransactionCurrency> payload) {
		super();
		this.payload = payload;
	}

}