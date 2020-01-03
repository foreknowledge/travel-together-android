package com.mungziapp.traveltogether;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

import com.mungziapp.traveltogether.app.TokenManager;
import com.mungziapp.traveltogether.app.helper.JsonHelper;
import com.mungziapp.traveltogether.app.helper.RequestHelper;
import com.mungziapp.traveltogether.interfaces.OnGETResponseListener;
import com.mungziapp.traveltogether.app.DateHelper;
import com.mungziapp.traveltogether.model.response.TokenResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class ServerService extends Service {
	private static final String TAG = "ServerService ::";

	private Timer refreshTimer;

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "oncreate()");

		refreshTimer = new Timer();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "onStartCommand()");

		TimerTask refreshTask = new TimerTask() {
			@Override
			public void run() {
				final SharedPreferences prefs = getSharedPreferences(TokenManager.prefFileName, MODE_PRIVATE);
				final String refreshToken = prefs.getString(TokenManager.refreshToken, "");

				RequestHelper.getInstance().onSendGetRequest(
						RequestHelper.HOST + "/auth/refresh",
						new OnGETResponseListener() {
							@Override
							public void onResponse(String response) {
								TokenResponse tokenResponse = JsonHelper.gson.fromJson(response, TokenResponse.class);
								String accessToken = tokenResponse.getToken();
								String newRefreshToken = tokenResponse.getRefreshToken();
								long exp = tokenResponse.getPayload().getExp();

								if (TokenManager.isRefreshTokenUpdated(prefs, newRefreshToken)) {
									SharedPreferences.Editor editor = prefs.edit();
									editor.remove(TokenManager.refreshToken);
									editor.putString(TokenManager.refreshToken, refreshToken).apply();
									Log.d(TAG, "refresh token update");
								}

								TokenManager.getInstance()
										.setAccessToken(accessToken)
										.setPeriod(DateHelper.getLocalDateTime(exp));
							}

							@Override
							public Map<String, String> getHeaders() {
								Map<String, String> header = new HashMap<>();
								header.put("x-rt", refreshToken);
								Log.d(TAG, "refreshToken = " + refreshToken);

								return header;
							}
						});
			}
		};

		long duration = TokenManager.getInstance().getPeriod() * 1000;
		refreshTimer.schedule(refreshTask, duration, duration);

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "onDestroy()");
		refreshTimer.cancel();

		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
