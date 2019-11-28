package com.mungziapp.traveltogether;

public class TravelItem {
    private String travelTitle;
    private String travelStartDate;
    private String travelEndDate;
    private int travelMembers;
    private int imgResId;

    public TravelItem(String travelTitle, String travelStartDate, String travelEndDate, int travelMembers, int imgResId) {
        this.travelTitle = travelTitle;
        this.travelStartDate = travelStartDate;
        this.travelEndDate = travelEndDate;
        this.travelMembers = travelMembers;
        this.imgResId = imgResId;
    }


    public String getTravelTitle() {
        return travelTitle;
    }
    public String getTravelStartDate() {
        return travelStartDate;
    }
    public String getTravelEndDate() { return travelEndDate; }
    public int getTravelMembers() {
        return travelMembers;
    }
    public int getImgResId() {
        return imgResId;
    }
}
