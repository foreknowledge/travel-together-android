package com.mungziapp.traveltogether.table;

import android.provider.BaseColumns;

public class TravelRoomTable implements BaseColumns {
    private static String ID = "id";
    private static String NAME = "name";
    private static String START_DATE = "start_date";
    private static String END_DATE = "end_date";
    private static String COUNTRY_CODES = "country_codes";
    private static String THUMB = "thumb";
    private static String MEMBERS = "members";

    public static String TABLE_NAME = "travelRoom";

    private TravelRoomTable() {}

    public static final String CREATE_QUERY = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT NOT NULL, " +
            START_DATE + " TEXT NULL, " +
            END_DATE + " TEXT NULL, " +
            COUNTRY_CODES + " TEXT NULL, " +
            THUMB + " INTEGER NULL, " +
            MEMBERS + " INTEGER NULL)";

    public static final String DROP_QUERY = "DROP TABLE " + TABLE_NAME;
    public static final String SELECT_QUERY = "SELECT * FROM " + TABLE_NAME;
}
