package com.mungziapp.traveltogether.interfaces;

public interface ActivityCallback {
	interface MainCallback {
		void refreshAdapterItems();
	}

	interface TravelCallback {
		void finishActivity();
	}
}
