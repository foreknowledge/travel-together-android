package com.mungziapp.traveltogether.app.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mungziapp.traveltogether.model.data.ScheduleData;
import com.mungziapp.traveltogether.model.data.TravelData;
import com.mungziapp.traveltogether.model.table.ScheduleTable;
import com.mungziapp.traveltogether.model.table.TravelTable;

import java.util.List;

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

	static void insertDummyData(List<TravelData> travelData) {
		Cursor cursor = DatabaseHelper.database.rawQuery(TravelTable.SELECT_QUERY, null);
		cursor.moveToNext();
		if (cursor.getCount() != 0) return;

		for (TravelData data : travelData) {
			String sql = "INSERT INTO " + TravelTable.TABLE_NAME + " VALUES (?, ?, ?, ?, ?, ?, ?)";
			Object[] params = {data.getId(), data.getName(), data.getStartDate(), data.getEndDate(), data.getCountryCodes(), data.getCoverImgPath(), data.getMembers()};

			DatabaseHelper.database.execSQL(sql, params);
		}
		Log.d(TAG, "travel 데이터 추가됨.");

		cursor.close();
	}

	static void insertScheduleDummyData(List<ScheduleData> scheduleData) {
		Cursor cursor = DatabaseHelper.database.rawQuery(ScheduleTable.SELECT_QUERY, null);
		cursor.moveToNext();
		if (cursor.getCount() != 0) return;

		for (ScheduleData data : scheduleData) {
			String sql = "INSERT INTO " + ScheduleTable.TABLE_NAME + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			Object[] params = {data.getId(), data.getTravelId(), data.getDayN(), data.getType(), data.getTitle(), data.getTime(), data.getPlace(), data.getMemo(), data.getPhotos()};

			DatabaseHelper.database.execSQL(sql, params);
		}
		Log.d(TAG, "schedule 데이터 추가됨.");

		cursor.close();
	}

	public static void insertTravelsData(TravelData travelData) {
		Cursor cursor = DatabaseHelper.database.rawQuery(TravelTable.SELECT_QUERY + " WHERE id = " + travelData.getId(), null);
		cursor.moveToNext();
		if (cursor.getCount() == 0) {
			// 테이블에 데이터가 없다면 데이터 넣기
			String sql = "INSERT INTO movieDetail VALUES (?, ?, ?, ?, ?, ?, ?)";
			Object[] params = {travelData.getId(), travelData.getName(), travelData.getStartDate(), travelData.getEndDate(), travelData.getCountryCodes(), travelData.getCoverImgPath(), travelData.getMembers()};

			DatabaseHelper.database.execSQL(sql, params);
			Log.d(TAG, "movieDetail [" + travelData.getId() + "] 데이터 추가됨.");
		}

		cursor.close();
	}
}
