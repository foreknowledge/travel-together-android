package com.mungziapp.traveltogether.item;

public class SearchCountryItem {
    private String countryFlag;
    private String countryName;

    public SearchCountryItem(String countryFlag, String countryName) {
        this.countryFlag = countryFlag;
        this.countryName = countryName;
    }

    public String getCountryFlag() { return countryFlag; }
    public String getCountryName() { return countryName; }
}
