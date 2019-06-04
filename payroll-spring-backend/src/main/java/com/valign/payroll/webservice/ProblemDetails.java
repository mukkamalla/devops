package com.valign.payroll.webservice;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import lombok.*;

@Data
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.CUSTOM, property = "error", visible = true)
@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
public class ProblemDetails {

	private int status;

	private String detail;

	public ProblemDetails() {

	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public ProblemDetails(int status) {
		this();
		this.status = status;
	}

	ProblemDetails(int status, Throwable ex) {
		this();
		this.status = status;

		this.detail = ex.getLocalizedMessage();
	}

}
