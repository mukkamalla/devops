package com.valign.payroll.webservice.model;

import java.util.List;

public class LanguagesSupportedCompanyLevelList {

	java.util.List<LanguagesSupportedCompanyLevel> payload;

	public java.util.List<LanguagesSupportedCompanyLevel> getPayload() {
		return payload;
	}

	public void setPayload(java.util.List<LanguagesSupportedCompanyLevel> payload) {
		this.payload = payload;
	}

	public LanguagesSupportedCompanyLevelList(List<LanguagesSupportedCompanyLevel> payload) {
		super();
		this.payload = payload;
	}

}