package com.mungziapp.traveltogether;

public class SearchTravelItem {
    private String travelTitle;
    private String travelStartDate;
    private String travelEndDate;
    private int imgResId;

    public SearchTravelItem(String travelTitle, String travelStartDate, String travelEndDate, int imgResId) {
        this.travelTitle = travelTitle;
        this.travelStartDate = travelStartDate;
        this.travelEndDate = travelEndDate;
        this.imgResId = imgResId;
    }

    public String getTravelTitle() {
        return travelTitle;
    }
    public String getTravelStartDate() {
        return travelStartDate;
    }
    public String getTravelEndDate() { return travelEndDate; }
    public int getImgResId() {
        return imgResId;
    }
}
