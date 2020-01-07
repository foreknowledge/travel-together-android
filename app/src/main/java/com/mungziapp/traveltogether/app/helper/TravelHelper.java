package com.mungziapp.traveltogether.app.helper;

import android.content.Context;

import com.mungziapp.traveltogether.app.ConnectionStatus;
import com.mungziapp.traveltogether.model.Countries;

public class TravelHelper {
	private static TravelHelper instance = new TravelHelper();

	public static TravelHelper getInstance() { return instance; }
	private TravelHelper() { Countries.setCountryListAndHash(); }

	public static void init(Context context) {
		ConnectionStatus.registerNetworkReceiver(context);
		DatabaseHelper.openDatabase(context);
		//DatabaseHelper.dropTables();
		DatabaseHelper.createTables();
	}
}
