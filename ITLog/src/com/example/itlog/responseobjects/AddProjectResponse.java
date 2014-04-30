package com.example.itlog.responseobjects;

import java.io.Serializable;

public class AddProjectResponse implements Serializable {
	
	private String status;

	public AddProjectResponse(String status) {
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
