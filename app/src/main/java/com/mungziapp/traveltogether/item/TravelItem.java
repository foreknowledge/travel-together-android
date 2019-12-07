package com.mungziapp.traveltogether.item;

import java.util.ArrayList;

public class TravelItem {
    private int id;
    private String travelTitle;
    private String travelStartDate;
    private String travelEndDate;
    private ArrayList<String> travelCountries;
    private ArrayList<Integer> travelMembers;
    private int imgResId;

    public TravelItem(int id, String travelTitle, String travelStartDate, String travelEndDate,
                      ArrayList<String> travelCountries, ArrayList<Integer> travelMembers, int imgResId) {
        this.id = id;
        this.travelTitle = travelTitle;
        this.travelStartDate = travelStartDate;
        this.travelEndDate = travelEndDate;
        this.travelCountries = travelCountries;
        this.travelMembers = travelMembers;
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
    public ArrayList<Integer> getTravelMembers() {
        return travelMembers;
    }
    public int getImgResId() {
        return imgResId;
    }
}
