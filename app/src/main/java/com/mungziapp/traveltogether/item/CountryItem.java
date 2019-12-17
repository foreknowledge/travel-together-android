package com.mungziapp.traveltogether.item;

import java.util.List;

public class CountryItem {
	private String countryKrName;
	private String countryEnName;
	private String countryCode;
	private String countryFlag;
	private Boolean isSelected;

	public CountryItem(List<String> country) {
		countryKrName = country.get(0);
		countryEnName = country.get(1);
		countryCode = country.get(2);
		countryFlag = country.get(3);
		isSelected = false;
	}

	public String getCountryKrName() {
		return countryKrName;
	}

	public String getCountryEnName() {
		return countryEnName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getCountryFlag() {
		return countryFlag;
	}

	public Boolean getSelected() {
		return isSelected;
	}

	public void setIsSelected(Boolean bool) {
		isSelected = bool;
	}
}
