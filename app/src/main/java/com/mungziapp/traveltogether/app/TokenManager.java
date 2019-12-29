package com.mungziapp.traveltogether.app;

import android.util.Log;

import java.time.Duration;
import java.time.LocalDateTime;

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
		duration = Duration.between(LocalDateTime.now(), expireTime).getSeconds();
		Log.d(TAG, "duration = " + duration);
		return this;
	}

	public String getAccessToken() { return accessToken; }
	public TokenManager setAccessToken(String accessToken) {
		this.accessToken = accessToken;
		Log.d(TAG, "access token = " + accessToken);
		return this; }
}
