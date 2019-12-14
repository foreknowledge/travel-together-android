package com.mungziapp.traveltogether.item;

public class SearchTravelItem {
	private int id;
	private String travelTitle;
	private String travelStartDate;
	private String travelEndDate;
	private int imgResId;

	public SearchTravelItem(int id, String travelTitle, String travelStartDate, String travelEndDate, int imgResId) {
		this.id = id;
		this.travelTitle = travelTitle;
		this.travelStartDate = travelStartDate;
		this.travelEndDate = travelEndDate;
		this.imgResId = imgResId;
	}

	public int getId() {
		return id;
	}

	public String getTravelTitle() {
		return travelTitle;
	}

	public String getTravelStartDate() {
		return travelStartDate;
	}

	public String getTravelEndDate() {
		return travelEndDate;
	}

	public int getImgResId() {
		return imgResId;
	}
}
