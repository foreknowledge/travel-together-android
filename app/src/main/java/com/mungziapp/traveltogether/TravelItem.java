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


    public String gettravelTitle() {
        return travelTitle;
    }
    public String gettravelStartDate() {
        return travelStartDate;
    }
    public String gettravelEndDate() { return travelEndDate; }
    public int gettravelMembers() {
        return travelMembers;
    }
    public int getImgResId() {
        return imgResId;
    }
}
