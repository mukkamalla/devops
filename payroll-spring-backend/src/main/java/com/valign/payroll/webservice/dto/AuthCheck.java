package com.valign.payroll.webservice.dto;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthCheck {
	@JsonProperty
	private final Date createdAt = new Date();
	private final String hash;
	private final String path;
	private final boolean authorized;	
	
	public AuthCheck(@JsonProperty("hash") String hash,@JsonProperty("path") String path, @JsonProperty("authorized") boolean authorized) {
		super();
		this.hash = hash;
		this.path = path;
		this.authorized = authorized;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public String getHash() {
		return hash;
	}

	public String getPath() {
		return path;
	}

	public boolean isAuthorized() {
		return authorized;
	}

	@Override
	public String toString() {
		return "AuthCheck [createdAt=" + createdAt + ", hash=" + hash + ", path=" + path + ", authorized=" + authorized
				+ "]";
	}

}
