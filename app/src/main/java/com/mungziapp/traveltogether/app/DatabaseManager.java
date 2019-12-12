package com.mungziapp.traveltogether.app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mungziapp.traveltogether.table.TravelRoomTable;
import com.mungziapp.traveltogether.data.TravelRoom;

import java.util.List;

public class DatabaseManager {
    private static final String TAG = "Database Manager :: ";

    public static SQLiteDatabase database;
    private static DatabaseManager instance = new DatabaseManager();

    private DatabaseManager() {}
    public static DatabaseManager getInstance() { return instance; }

    static void openDatabase(Context context) {
        database = context.openOrCreateDatabase("TRAVEL_TOGETHER.db", Context.MODE_PRIVATE, null);
        Log.d(TAG, "TRAVEL_TOGETHER 데이터베이스 오픈.");
    }

    static void createTables() {
        database.execSQL(TravelRoomTable.CREATE_QUERY);
        Log.d(TAG, "travelRoom 테이블 오픈.");
    }

    static void dropTables() {
        database.execSQL(TravelRoomTable.DROP_QUERY);
        Log.d(TAG, "travelRoom 테이블 삭제.");
    }

    static void insertDummyData(List<TravelRoom> travelRooms) {
        Cursor cursor = DatabaseManager.database.rawQuery(TravelRoomTable.SELECT_QUERY, null);
        cursor.moveToNext();
        if (cursor.getCount() != 0) return;

        for (TravelRoom travelRoom : travelRooms) {
            String sql = "INSERT INTO " + TravelRoomTable.TABLE_NAME + " VALUES (?, ?, ?, ?, ?, ?, ?)";
            Object[] params = {travelRoom.getId(), travelRoom.getName(), travelRoom.getStart_date(), travelRoom.getEnd_date(), travelRoom.getCountry_codes(), travelRoom.getThumb(), travelRoom.getMembers()};

            DatabaseManager.database.execSQL(sql, params);
        }
        Log.d(TAG, "travelRoom 데이터 추가됨.");

        cursor.close();
    }

    static void insertTravelRoomData(TravelRoom travelRoom) {
        Cursor cursor = DatabaseManager.database.rawQuery(TravelRoomTable.SELECT_QUERY + " WHERE id = " + travelRoom.getId(), null);
        cursor.moveToNext();
        if (cursor.getCount() == 0) {
            // 테이블에 데이터가 없다면 데이터 넣기
            String sql = "INSERT INTO movieDetail VALUES (?, ?, ?, ?, ?, ?, ?)";
            Object[] params = {travelRoom.getId(), travelRoom.getName(), travelRoom.getStart_date(), travelRoom.getEnd_date(), travelRoom.getCountry_codes(), travelRoom.getThumb(), travelRoom.getMembers()};

            DatabaseManager.database.execSQL(sql, params);
            Log.d(TAG, "movieDetail [" + travelRoom.getId() + "] 데이터 추가됨.");
        }

        cursor.close();
    }
}
