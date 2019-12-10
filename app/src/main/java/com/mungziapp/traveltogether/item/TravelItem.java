package com.mungziapp.traveltogether.item;

import java.util.ArrayList;

public class TravelItem {
    private int id;
    private String travelTitle;
    private String travelStartDate;
    private String travelEndDate;
    private ArrayList<String> travelCountries;
    private int numOfTravelMembers;
    private int imgResId;

    public TravelItem(int id, String travelTitle, String travelStartDate, String travelEndDate,
                      ArrayList<String> travelCountries, int numOfTravelMembers, int imgResId) {
        this.id = id;
        this.travelTitle = travelTitle;
        this.travelStartDate = travelStartDate;
        this.travelEndDate = travelEndDate;
        this.travelCountries = travelCountries;
        this.numOfTravelMembers = numOfTravelMembers;
        this.imgResId = imgResId;
    }

    public int getId() { return id; }
    public String getTravelTitle() {
        return travelTitle;
    }
    public String getTravelStartDate() {
        return travelStartDate;
    }
    public String getTravelEndDate() { return travelEndDate; }
    public ArrayList<String> getTravelCountries() {
        return travelCountries;
    }
    public int getNumOfTravelMembers() { return numOfTravelMembers; }
    public int getImgResId() {
        return imgResId;
    }
}
