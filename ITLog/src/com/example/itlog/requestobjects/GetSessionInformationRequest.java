package com.example.itlog.requestobjects;

public class GetSessionInformationRequest {
	
	
	//username pq � o login que vem de tras
	private String username;

	public GetSessionInformationRequest(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

}
