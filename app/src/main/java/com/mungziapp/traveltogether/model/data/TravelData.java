package com.mungziapp.traveltogether.model.data;

public class TravelData {
	private int id;
	private String name;
	private String startDate;
	private String endDate;
	private String countryCodes;
	private int cover;
	private int members;

	public TravelData(int id, String name, String startDate, String endDate, String countryCodes, int cover, int members) {
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.countryCodes = countryCodes;
		this.cover = cover;
		this.members = members;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getCountryCodes() {
		return countryCodes;
	}

	public int getCover() {
		return cover;
	}

	public int getMembers() {
		return members;
	}
}
