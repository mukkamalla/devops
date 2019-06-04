package com.valign.payroll.webservice.exception;


public class InvalidJWTToken extends Exception {
	
	public InvalidJWTToken() {
	}

	public InvalidJWTToken(String message) {

		super(message);
	}

	public InvalidJWTToken(String message, Throwable cause) {

		super(message, cause);
	}
}
