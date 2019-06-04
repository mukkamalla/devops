package com.valign.payroll.webservice.exception;

public class InvalidJSONFormatException extends Exception {
	
	public InvalidJSONFormatException() {
	}

	public InvalidJSONFormatException(String message) {

		super(message);
	}

	public InvalidJSONFormatException(String message, Throwable cause) {

		super(message, cause);
	}
}
