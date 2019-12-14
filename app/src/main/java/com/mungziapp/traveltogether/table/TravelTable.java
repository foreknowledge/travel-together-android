package com.mungziapp.traveltogether.table;

import android.provider.BaseColumns;

public class TravelTable implements BaseColumns {
	private static String ID = "id";
	private static String NAME = "name";
	private static String START_DATE = "start_date";
	private static String END_DATE = "end_date";
	private static String COUNTRY_CODES = "country_codes";
	private static String COVER = "cover";
	private static String MEMBERS = "members";

	public static String TABLE_NAME = "travel";

	private TravelTable() { }

	public static final String CREATE_QUERY = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
			ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			NAME + " TEXT NOT NULL, " +
			START_DATE + " TEXT, " +
			END_DATE + " TEXT, " +
			COUNTRY_CODES + " TEXT, " +
			COVER + " INTEGER, " +
			MEMBERS + " INTEGER);";

	public static final String DROP_QUERY = "DROP TABLE " + TABLE_NAME;
	public static final String SELECT_QUERY = "SELECT * FROM " + TABLE_NAME;
}
