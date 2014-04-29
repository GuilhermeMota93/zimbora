package com.example.itlog.responseobjects;

import java.io.Serializable;

public class LoginResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3197789451495373620L;
	private String status;

	public LoginResponse(String status) {
		super();
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
