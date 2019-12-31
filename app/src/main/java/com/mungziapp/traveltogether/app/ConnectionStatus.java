package com.mungziapp.traveltogether.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.util.Log;
import android.widget.Toast;

public class ConnectionStatus extends BroadcastReceiver {
	private static final String TAG = "ConnectionStatus ::";

	private static boolean connected = false;
	public static boolean getConnected() { return connected; }

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();

		if (action == null) return;

		if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
			if (ConnectionStatus.checkNetwork(context)) {
				ConnectionStatus.connected = true;
				Log.d(TAG, "네트워크 연결됨.");
				Toast.makeText(context, "네트워크 연결됨", Toast.LENGTH_SHORT).show();
			}
			else {
				Log.d(TAG, "네트워크 연결 끊어짐.");
				Toast.makeText(context, "네트워크를 연결해주세요.", Toast.LENGTH_SHORT).show();
				ConnectionStatus.connected = false;
			}
		}
	}

	private static boolean checkNetwork(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivityManager == null) return false;

		Network network = connectivityManager.getActiveNetwork();
		NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);

		if (capabilities != null)
			return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
					capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);

		return false;
	}

	// 네트워크 리시버에 만들어 놓은 ConnectionStatus() 등록하기
	public static void registerNetworkReceiver(Context context) {
		IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
		ConnectionStatus receiver = new ConnectionStatus();
		context.registerReceiver(receiver, filter);
	}
}
