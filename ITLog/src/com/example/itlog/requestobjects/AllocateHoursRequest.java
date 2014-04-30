package com.example.itlog.requestobjects;

import java.util.HashMap;
import java.util.Map;

import android.R.integer;

public class AllocateHoursRequest {

	private String username;
	private int day;
	private int[] hours = { 4, 8 };
	private Map<integer, int[]> allocate = new HashMap<integer, int[]>();

	public AllocateHoursRequest(String username, int day, int[] hours,
			Map<integer, int[]> allocate) {
		super();
		this.username = username;
		this.day = day;
		this.hours = hours;
		this.allocate = allocate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int[] getHours() {
		return hours;
	}

	public void setHours(int[] hours) {
		this.hours = hours;
	}

	public Map<integer, int[]> getAllocate() {
		return allocate;
	}

	public void setAllocate(Map<integer, int[]> allocate) {
		this.allocate = allocate;
	}

}
