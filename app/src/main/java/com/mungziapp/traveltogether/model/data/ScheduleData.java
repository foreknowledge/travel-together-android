package com.mungziapp.traveltogether.model.data;

public class ScheduleData {
	private String id;
	private String travelId;
	private int dayN;
	private int type = 0;
	private String title;
	private String time;
	private String place;
	private String memo;
	private String photos;

	public ScheduleData(String id, String travelId, int dayN, int type, String title, String time, String place, String memo, String photos) {
		this.id = id;
		this.travelId = travelId;
		this.dayN = dayN;
		this.type = type;
		this.title = title;
		this.time = time;
		this.place = place;
		this.memo = memo;
		this.photos = photos;
	}

	public ScheduleData(String id, String travelId, int dayN, String title, String time, String place, String memo, String photos) {
		this.id = id;
		this.travelId = travelId;
		this.dayN = dayN;
		this.title = title;
		this.time = time;
		this.place = place;
		this.memo = memo;
		this.photos = photos;
	}

	public String getId() {
		return id;
	}

	public String getTravelId() {
		return travelId;
	}

	public int getDayN() {
		return dayN;
	}

	public int getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public String getTime() {
		return time;
	}

	public String getPlace() {
		return place;
	}

	public String getMemo() {
		return memo;
	}

	public String getPhotos() {
		return photos;
	}
}
