package com.example.itlog.responseobjects;

public class GetCalendarResponse {
	
	private String month;
	private int days, holidays;
	
	public GetCalendarResponse(String month, int days, int holidays) {
		super();
		this.month = month;
		this.days = days;
		this.holidays = holidays;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getHolidays() {
		return holidays;
	}

	public void setHolidays(int holidays) {
		this.holidays = holidays;
	}
	
	
	
}
