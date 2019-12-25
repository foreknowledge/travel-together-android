package com.mungziapp.traveltogether.item;

public class DetailScheduleItem {
	private boolean isMove;
	private String title;
	private String time;
	private String memo;
	private String place;
	private String photos;

	public DetailScheduleItem(String title, String time, String memo, String place, String photos) {
		isMove = false;
		this.title = title;
		this.time = time;
		this.memo = memo;
		this.place = place;
		this.photos = photos;
	}

	public DetailScheduleItem(boolean isMove, String title, String time, String memo, String place, String photos) {
		this.isMove = isMove;
		this.title = title;
		this.time = time;
		this.memo = memo;
		this.place = place;
		this.photos = photos;
	}

	public boolean isMove() {
		return isMove;
	}

	public String getTitle() {
		return title;
	}

	public String getTime() {
		return time;
	}

	public String getMemo() {
		return memo;
	}

	public String getPlace() {
		return place;
	}

	public String getPhotos() {
		return photos;
	}
}
