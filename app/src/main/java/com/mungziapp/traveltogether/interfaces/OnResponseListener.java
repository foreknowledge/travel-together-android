package com.mungziapp.traveltogether.interfaces;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public interface OnResponseListener {
	Map<String, String> getHeaders();
	void onErrorResponse(VolleyError error);

	interface OnStringListener extends OnResponseListener {
		void onResponse(String response);
	}

	interface OnJsonArrayListener extends OnResponseListener {
		void onResponse(JSONArray response);
	}

	interface OnJsonObjectListener extends OnResponseListener {
		void onResponse(JSONObject jsonObject);
	}
}
