package com.mungziapp.traveltogether.model.item;

public class SearchTravelItem {
	private int id;
	private String travelTitle;
	private String travelStartDate;
	private String travelEndDate;
	private String coverImgPath;

	public SearchTravelItem(int id, String travelTitle, String travelStartDate, String travelEndDate, String coverImgPath) {
		this.id = id;
		this.travelTitle = travelTitle;
		this.travelStartDate = travelStartDate;
		this.travelEndDate = travelEndDate;
		this.coverImgPath = coverImgPath;
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

	public String getCoverImgPath() {
		return coverImgPath;
	}
}
