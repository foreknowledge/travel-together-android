package com.mungziapp.traveltogether.model.table;

import android.provider.BaseColumns;

public class ScheduleTable implements BaseColumns {
	private static String ID = "id";
	private static String TRAVEL_ID = "travel_id";
	private static String DAY_N = "day_n";
	private static String TYPE = "type";
	private static String TITLE = "title";
	private static String TIME = "time";
	private static String PLACE = "place";
	private static String MEMO = "memo";
	private static String PHOTOS = "photos";

	public static String TABLE_NAME = "schedule";

	private ScheduleTable() { }

	public static final String CREATE_QUERY = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
			ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			TRAVEL_ID + " INTEGER NOT NULL, " +
			DAY_N + " INTEGER NOT NULL, " +
			TYPE + " INTEGER NOT NULL, " +
			TITLE + " TEXT NOT NULL, " +
			TIME + " TEXT, " +
			PLACE + " TEXT, " +
			MEMO + " TEXT, " +
			PHOTOS + " TEXT);";

	public static final String DROP_QUERY = "DROP TABLE " + TABLE_NAME;
	public static final String SELECT_QUERY = "SELECT * FROM " + TABLE_NAME;
}
