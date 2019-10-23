package com.mungziapp.traveltogether;

public class RoomItem {
    private String roomTitle;
    private String roomDuration;
    private int roomMembers;
    private int imgResId;

    public RoomItem(String roomTitle, String roomDuration, int roomMembers, int imgResId) {
        this.roomTitle = roomTitle;
        this.roomDuration = roomDuration;
        this.roomMembers = roomMembers;
        this.imgResId = imgResId;
    }


    public String getRoomTitle() {
        return roomTitle;
    }

    public String getRoomDuration() {
        return roomDuration;
    }

    public int getRoomMembers() {
        return roomMembers;
    }

    public int getImgResId() {
        return imgResId;
    }
}
