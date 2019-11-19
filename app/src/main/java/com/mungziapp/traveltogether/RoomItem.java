package com.mungziapp.traveltogether;

public class RoomItem {
    private String roomTitle;
    private String roomStartDate;
    private String roomEndDate;
    private int roomMembers;
    private int imgResId;

    public RoomItem(String roomTitle, String roomStartDate, String roomEndDate, int roomMembers, int imgResId) {
        this.roomTitle = roomTitle;
        this.roomStartDate = roomStartDate;
        this.roomEndDate = roomEndDate;
        this.roomMembers = roomMembers;
        this.imgResId = imgResId;
    }


    public String getRoomTitle() {
        return roomTitle;
    }
    public String getRoomStartDate() {
        return roomStartDate;
    }
    public String getRoomEndDate() { return roomEndDate; }
    public int getRoomMembers() {
        return roomMembers;
    }
    public int getImgResId() {
        return imgResId;
    }
}
