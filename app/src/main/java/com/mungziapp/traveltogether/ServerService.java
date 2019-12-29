package com.mungziapp.traveltogether;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

import com.google.gson.Gson;
import com.mungziapp.traveltogether.app.TokenManager;
import com.mungziapp.traveltogether.app.helper.RequestHelper;
import com.mungziapp.traveltogether.interfaces.OnResponseListener;
import com.mungziapp.traveltogether.model.DateObject;
import com.mungziapp.traveltogether.model.response.TokenResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class ServerService extends Service {
	private static final String TAG = "ServerService ::";
	private Gson gson = new Gson();

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "oncreate()");

		TimerTask refreshTask = new TimerTask() {
			@Override
			public void run() {
				final SharedPreferences prefs = getSharedPreferences(TokenManager.prefFileName, MODE_PRIVATE);
				final String refreshToken = prefs.getString(TokenManager.refreshToken, "");

				RequestHelper.getInstance().onSendGetRequest(
						RequestHelper.HOST + "/auth/refresh",
						new OnResponseListener() {
							@Override
							public void onResponse(String response) {
								TokenResponse tokenResponse = gson.fromJson(response, TokenResponse.class);
								String accessToken = tokenResponse.getToken();
								String refreshToken = tokenResponse.getRefreshToken();
								long exp = tokenResponse.getPayload().getExp();

								SharedPreferences.Editor editor = prefs.edit();

								editor.remove(TokenManager.refreshToken);
								editor.putString(TokenManager.refreshToken, refreshToken).apply();
								Log.d(TAG, "refresh token update");

								TokenManager.getInstance()
										.setAccessToken(accessToken)
										.setDuration(DateObject.getLocalDateTime(exp));
							}

							@Override
							public void setParams(Map<String, String> params) { }

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

		Timer refreshTimer = new Timer();
		long duration = TokenManager.getInstance().getDuration() * 1000;
		refreshTimer.schedule(refreshTask, duration, duration);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
