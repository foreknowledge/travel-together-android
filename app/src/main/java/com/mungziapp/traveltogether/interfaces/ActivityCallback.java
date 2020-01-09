package com.mungziapp.traveltogether.interfaces;

public interface ActivityCallback {
	interface MainCallback {
		void refreshAdapterItems(String logMessage);
	}

	interface ActivityFinishCallback {
		void finishActivity();
	}
}
