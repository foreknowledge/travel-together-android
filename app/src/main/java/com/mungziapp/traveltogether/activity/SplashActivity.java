package com.mungziapp.traveltogether.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.mungziapp.traveltogether.app.TokenManager;
import com.mungziapp.traveltogether.app.helper.RequestHelper;
import com.mungziapp.traveltogether.app.helper.TravelHelper;
import com.mungziapp.traveltogether.interfaces.OnResponseListener;
import com.mungziapp.traveltogether.model.DateObject;
import com.mungziapp.traveltogether.model.response.TokenResponse;

import java.util.HashMap;
import java.util.Map;

public class SplashActivity extends AppCompatActivity {
	private static final String TAG = "SplashActivity ::";
	private Gson gson = new Gson();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TravelHelper.init(getApplicationContext());

		final SharedPreferences prefs = getSharedPreferences(TokenManager.prefFileName, MODE_PRIVATE);
		final String refreshToken = prefs.getString(TokenManager.refreshToken, "");
		if (refreshToken.equals("")) {
			startActivity(new Intent(getApplicationContext(), LoginActivity.class));
			finish();
		}
		else {
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
							Log.d(TAG, "refresh token update. new refresh token = " + refreshToken);

							TokenManager.getInstance()
									.setAccessToken(accessToken)
									.setDuration(DateObject.getLocalDateTime(exp));

							startActivity(new Intent(getApplicationContext(), MainActivity.class));
							finish();
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
					}
			);
		}
	}
}
