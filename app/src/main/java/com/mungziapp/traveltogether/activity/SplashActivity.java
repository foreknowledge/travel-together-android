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
import com.mungziapp.traveltogether.app.DateObject;
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

		SharedPreferences prefs = getSharedPreferences(TokenManager.prefFileName, MODE_PRIVATE);
		String refreshToken = prefs.getString(TokenManager.refreshToken, "");
		if (refreshToken.equals("")) redirectLoginActivity();
		else updateRefreshToken(prefs, refreshToken);
	}

	private void redirectLoginActivity() {
		startActivity(new Intent(getApplicationContext(), LoginActivity.class));
		finish();
	}

	private void updateRefreshToken(final SharedPreferences prefs, final String refreshToken) {
		RequestHelper.getInstance().onSendGetRequest(
				RequestHelper.HOST + "/auth/refresh",
				new OnResponseListener() {
					@Override
					public void onResponse(String response) {
						TokenResponse tokenResponse = gson.fromJson(response, TokenResponse.class);
						String accessToken = tokenResponse.getToken();
						String newRefreshToken = tokenResponse.getRefreshToken();
						long exp = tokenResponse.getPayload().getExp();

						TokenManager.getInstance()
								.setAccessToken(accessToken)
								.setPeriod(DateObject.getLocalDateTime(exp));

						if (TokenManager.isRefreshTokenUpdated(prefs, newRefreshToken)) {
							SharedPreferences.Editor editor = prefs.edit();
							editor.remove(TokenManager.refreshToken);
							editor.putString(TokenManager.refreshToken, refreshToken).apply();
							Log.d(TAG, "refresh token update. new refresh token = " + refreshToken);
						}

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
