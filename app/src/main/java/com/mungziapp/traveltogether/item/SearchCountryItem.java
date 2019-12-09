package com.mungziapp.traveltogether.item;

public class SearchCountryItem {
    private String countryFlag;
    private String countryName;
    private Boolean isSelected;

    public SearchCountryItem(String countryFlag, String countryName) {
        this.countryFlag = countryFlag;
        this.countryName = countryName;
        isSelected = false;
    }

    public String getCountryFlag() { return countryFlag; }
    public String getCountryName() { return countryName; }
    public Boolean getIsSelected() { return isSelected; }

    public void setIsSelected(Boolean bool) { isSelected = bool; }
}
