package com.mungziapp.traveltogether;

public class RoomItem {
    private String roomTitle;
    private String roomDuration;
    private String roomFlag;
    private int roomMembers;
    private int imgResId;

    public RoomItem(String roomTitle, String roomDuration, String roomFlag, int roomMembers, int imgResId) {
        this.roomTitle = roomTitle;
        this.roomDuration = roomDuration;
        this.roomFlag = roomFlag;
        this.roomMembers = roomMembers;
        this.imgResId = imgResId;
    }


    public String getRoomTitle() {
        return roomTitle;
    }
    public String getRoomDuration() {
        return roomDuration;
    }
    public String getRoomFlag() { return roomFlag; }
    public int getRoomMembers() {
        return roomMembers;
    }
    public int getImgResId() {
        return imgResId;
    }
}
