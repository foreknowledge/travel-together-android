package com.mungziapp.traveltogether.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateHelper {
	private LocalDateTime dateTime;

	public static LocalDate stringISOToLocalDate(String strDate) {
		// ISO 시간 형식 기준이라면 'T' 문자 전까지 substring
		int index = strDate.indexOf("T");
		if (index != -1)
			strDate = strDate.substring(0, index);

		String[] strDates = strDate.split("-");

		if (strDates.length == 3) {
			int year = Integer.valueOf(strDates[0]);
			int month = Integer.valueOf(strDates[1]);
			int dayOfMonth = Integer.valueOf(strDates[2]);

			return LocalDate.of(year, month, dayOfMonth);
		}

		return null;
	}

	public static LocalDate stringToLocalDate(CharSequence charDate, String regex) {
		return stringToLocalDate(charDate.toString(), regex);
	}

	public static LocalDate stringToLocalDate(String strDate, String regex) {
		String[] tokens = strDate.split(regex);
		if (tokens.length == 3)
			return LocalDate.of(Integer.valueOf(tokens[0]), Integer.valueOf(tokens[1]), Integer.valueOf(tokens[2]));

		return null;
	}

	public static String toISOFormat(LocalDate localDate) {
		String year = String.valueOf(localDate.getYear());
		String month = String.valueOf(localDate.getMonthValue());
		if (localDate.getMonthValue() < 10) month = "0" + localDate.getMonthValue();
		String day = String.valueOf(localDate.getDayOfMonth());
		if (localDate.getDayOfMonth() < 10) day = "0" + localDate.getDayOfMonth();

		return year + "-" + month + "-" + day + "T00:00:00.000Z";
	}

	public DateHelper() {
		dateTime = LocalDateTime.now();
	}

	public static long getEpochTime(LocalDateTime dateTime) {
		return dateTime.toEpochSecond(ZoneOffset.UTC);
	}

	public static LocalDateTime getLocalDateTime(long seconds) {
		return LocalDateTime.ofEpochSecond(seconds, 0, ZoneOffset.UTC);
	}

	public int getYear() { return dateTime.getYear(); }
	public int getMonth() { return dateTime.getMonth().getValue(); }
	public int getDayOfMonth() { return dateTime.getDayOfMonth(); }
	public int getHour() { return dateTime.getHour(); }
	public int getMinute() { return dateTime.getMinute(); }

	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
}
