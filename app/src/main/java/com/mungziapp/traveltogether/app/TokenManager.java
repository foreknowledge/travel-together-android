package com.mungziapp.traveltogether.app;

import android.util.Log;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TokenManager {
	private static final String TAG = "TokenManager ::";

	private static long duration;
	private String accessToken;
	public static String prefFileName = "token-storage";
	public static String refreshToken = "refresh-token";

	private static TokenManager instance = new TokenManager();
	private TokenManager() {}

	public static TokenManager getInstance() { return instance; }

	public long getDuration() { return duration; }
	public TokenManager setDuration(LocalDateTime expireTime) {
		LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
		duration = Duration.between(now, expireTime).getSeconds();
		duration -= 100;
		Log.d(TAG, "duration = " + duration);
		return this;
	}

	public String getAccessToken() { return accessToken; }
	public TokenManager setAccessToken(String accessToken) {
		this.accessToken = accessToken;
		Log.d(TAG, "access token = " + accessToken);
		return this; }
}
