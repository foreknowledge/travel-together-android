package com.mungziapp.traveltogether.data;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateObject {
	private LocalDate date;
	private LocalTime time;

	public static LocalDate stringToLocalDate(String strDate) {
		String[] strDates = strDate.split("\\.");

		if (strDates.length == 3) {
			int year = Integer.valueOf(strDates[0]);
			int month = Integer.valueOf(strDates[1]);
			int dayOfMonth = Integer.valueOf(strDates[2]);

			return LocalDate.of(year, month, dayOfMonth);
		}

		return null;
	}

	public DateObject() {
		date = LocalDate.now();
		time = LocalTime.now();
	}

	public int getYear() { return date.getYear(); }
	public int getMonth() { return date.getMonth().getValue(); }
	public int getDayOfMonth() { return date.getDayOfMonth(); }
	public int getHour() { return time.getHour(); }
	public int getMinute() { return time.getMinute(); }

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}
