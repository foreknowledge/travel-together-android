package com.mungziapp.traveltogether.model.data;

import java.time.LocalDate;

public class TravelData {
	private String id;
	private String name;
	private LocalDate startDate;
	private LocalDate endDate;
	private String countryCodes;
	private String coverImgPath;
	private int members;

	public TravelData(String id, String name, LocalDate startDate, LocalDate endDate, String countryCodes, String coverImgPath, int members) {
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.countryCodes = countryCodes;
		this.coverImgPath = coverImgPath;
		this.members = members;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public String getCountryCodes() {
		return countryCodes;
	}

	public String getCoverImgPath() {
		return coverImgPath;
	}

	public int getMembers() {
		return members;
	}
}
