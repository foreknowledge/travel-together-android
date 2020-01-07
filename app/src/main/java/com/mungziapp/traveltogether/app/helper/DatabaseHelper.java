package com.mungziapp.traveltogether.app.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mungziapp.traveltogether.model.data.TravelData;
import com.mungziapp.traveltogether.model.response.TravelRoom;
import com.mungziapp.traveltogether.model.table.ScheduleTable;
import com.mungziapp.traveltogether.model.table.TravelTable;

public class DatabaseHelper {
	private static final String TAG = "Database Manager :: ";

	public static SQLiteDatabase database;
	private static DatabaseHelper instance = new DatabaseHelper();

	private DatabaseHelper() { }

	public static DatabaseHelper getInstance() {
		return instance;
	}

	static void openDatabase(Context context) {
		database = context.openOrCreateDatabase("TRAVEL_TOGETHER.db", Context.MODE_PRIVATE, null);
		Log.d(TAG, "TRAVEL_TOGETHER 데이터베이스 오픈.");
	}

	static void createTables() {
		database.execSQL(TravelTable.CREATE_QUERY);
		Log.d(TAG, "travel 테이블 오픈.");

		database.execSQL(ScheduleTable.CREATE_QUERY);
		Log.d(TAG, "schedule 테이블 오픈.");
	}

	static void dropTables() {
		database.execSQL(TravelTable.DROP_QUERY);
		Log.d(TAG, "travel 테이블 삭제.");

		database.execSQL(ScheduleTable.DROP_QUERY);
		Log.d(TAG, "schedule 테이블 삭제.");
	}

	public static void insertTravelData(TravelData travelData) {
		Cursor cursor = DatabaseHelper.database.rawQuery(TravelTable.SELECT_QUERY + " WHERE id = '" + travelData.getId() + "'", null);
		cursor.moveToNext();
		if (cursor.getCount() != 0) {
			// 테이블에 데이터가 있다면 데이터 지우기
			String sql = "DELETE FROM " + TravelTable.TABLE_NAME + " WHERE id = '" + travelData.getId() + "'";
			DatabaseHelper.database.execSQL(sql);
		}
		String sql = "INSERT INTO " + TravelTable.TABLE_NAME + " VALUES (?, ?, ?, ?, ?, ?, ?)";
		Object[] params = {travelData.getId(), travelData.getName(), travelData.getStartDate(), travelData.getEndDate(), travelData.getCountryCodes(), travelData.getCoverImgPath(), travelData.getMembers()};

		DatabaseHelper.database.execSQL(sql, params);

		cursor.close();
	}

	public static void insertTravelData(TravelRoom travelRoom) {
		insertTravelData(TravelData.toTravelData(travelRoom));
	}

	public static void deleteTravelData(String travelId) {
		Cursor cursor = DatabaseHelper.database.rawQuery(TravelTable.SELECT_QUERY + " WHERE id = '" + travelId + "'", null);
		cursor.moveToNext();
		if (cursor.getCount() != 0) {
			// 테이블에 데이터가 있다면 데이터 지우기
			String sql = "DELETE FROM " + TravelTable.TABLE_NAME + " WHERE id = '" + travelId + "'";
			DatabaseHelper.database.execSQL(sql);
			Log.d(TAG, "travel [" + travelId + "] 데이터 삭제됨.");
		}

		cursor.close();
	}
}
