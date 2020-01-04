package com.mungziapp.traveltogether.interfaces;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public interface OnResponseListener {
	Map<String, String> getHeaders();
	void onErrorResponse(VolleyError error);

	interface OnGETListener extends OnResponseListener {
		void onResponse(String response);
	}

	interface OnJsonArrayListener extends OnResponseListener {
		void onResponse(JSONArray response);
	}

	interface OnPOSTListener extends OnResponseListener {

		interface OnJsonObjectListener extends OnPOSTListener {
			void onResponse(JSONObject jsonObject);
		}

		interface OnStringListener extends OnPOSTListener {
			void onResponse(String response);
		}
	}
}
