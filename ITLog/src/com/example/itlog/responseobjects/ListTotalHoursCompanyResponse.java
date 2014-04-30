package com.example.itlog.responseobjects;

import java.util.HashMap;
import java.util.Map;

import android.R.integer;

import com.example.itlog.requestobjects.Company;

public class ListTotalHoursCompanyResponse {
	
	private String username;
	private String day;
	private int hours;
	private Map<Company, integer> companies = new HashMap<Company, integer>();

	public ListTotalHoursCompanyResponse(String username, String day,
			int hours, Map<Company, integer> companies) {
		super();
		this.username = username;
		this.day = day;
		this.hours = hours;
		this.companies = companies;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public Map<Company, integer> getCompanies() {
		return companies;
	}

	public void setCompanies(Map<Company, integer> companies) {
		this.companies = companies;
	}

}
