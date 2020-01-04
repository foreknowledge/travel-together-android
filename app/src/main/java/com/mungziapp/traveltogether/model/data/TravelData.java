package com.mungziapp.traveltogether.model.data;

import com.mungziapp.traveltogether.app.DateHelper;
import com.mungziapp.traveltogether.model.response.Country;
import com.mungziapp.traveltogether.model.response.Member;
import com.mungziapp.traveltogether.model.response.TravelRoom;

import java.time.LocalDate;
import java.util.List;

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

	public static TravelData toTravelData(TravelRoom travelRoom) {
		String id = travelRoom.getId();
		String title = travelRoom.getName();
		LocalDate startDate = null, endDate = null;
		if (travelRoom.getStartDate() != null && travelRoom.getEndDate() != null) {
			startDate = DateHelper.stringISOToLocalDate(travelRoom.getStartDate());
			endDate = DateHelper.stringISOToLocalDate(travelRoom.getEndDate());
		}
		List<Country> countries = travelRoom.getCountries();
		List<Member> members = travelRoom.getMembers();
		String coverImgPath = travelRoom.getCoverImagePath();

		StringBuilder countryCodes = new StringBuilder();
		for (Country country: countries) {
			countryCodes.append(country.getCode());
			countryCodes.append(",");
		}

		return new TravelData(id, title, startDate, endDate, countryCodes.toString(), coverImgPath, members.size());
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
