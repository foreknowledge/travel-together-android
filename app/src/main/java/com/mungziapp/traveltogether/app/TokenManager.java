package com.mungziapp.traveltogether.app;

import android.content.SharedPreferences;
import android.util.Log;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TokenManager {
	private static final String TAG = "TokenManager ::";

	private static long period;
	private String accessToken;
	public static String prefFileName = "token-storage";
	public static String refreshToken = "refresh-token";
	private static final String TOKEN_TYPE = "Bearer";

	private static TokenManager instance = new TokenManager();
	private TokenManager() {}

	public static TokenManager getInstance() { return instance; }

	public long getPeriod() { return period; }
	public TokenManager setPeriod(LocalDateTime expireTime) {
		LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
		period = Duration.between(now, expireTime).getSeconds();
		period -= 100;
		Log.d(TAG, "period = " + period);
		return this;
	}

	public String getAuthorization() { return TOKEN_TYPE + " " + accessToken; }
	public TokenManager setAccessToken(String accessToken) {
		this.accessToken = accessToken;
		Log.d(TAG, "access token = " + accessToken);
		return this;
	}

	public static boolean isRefreshTokenUpdated(SharedPreferences prefs, String newRefreshToken) {
		return !prefs.getString(refreshToken, "").equals(newRefreshToken);
	}

	public static boolean hasRefreshToken(SharedPreferences prefs) {
		return !prefs.getString(refreshToken, "").equals("");
	}
}
