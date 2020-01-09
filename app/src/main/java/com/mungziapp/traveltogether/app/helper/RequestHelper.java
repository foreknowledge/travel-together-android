package com.mungziapp.traveltogether.app.helper;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.mungziapp.traveltogether.interfaces.OnResponseListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RequestHelper {
	private static final String TAG = "RequestHelper :: ";
	public static final String HOST = "http://192.168.200.164:3000";

	private static RequestHelper instance = new RequestHelper();
	private static RequestQueue requestQueue;

	private RequestHelper() { }

	public static void setRequestQueue(RequestQueue _requestQueue) {
		requestQueue = _requestQueue;
	}

	public static RequestHelper getInstance() {
		return instance;
	}

	public void onSendStringRequest(int method, String url, final OnResponseListener.OnStringListener listener) {
		StringRequest request = new StringRequest(
				method,
				url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						Log.d(TAG, "string response");
						listener.onResponse(response);
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						listener.onErrorResponse(error);
					}
				}
		) {
			@Override
			public Map<String, String> getHeaders() {
				return listener.getHeaders();
			}
		};

		request.setShouldCache(false);
		requestQueue.add(request);
	}

	public void onSendJsonArrayRequest(String url, final OnResponseListener.OnJsonArrayListener listener) {
		JsonArrayRequest request = new JsonArrayRequest(
				url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, "json array response");
						listener.onResponse(response);
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						listener.onErrorResponse(error);
					}
				}
		) {
			@Override
			public Map<String, String> getHeaders() {
				return listener.getHeaders();
			}
		};

		request.setShouldCache(false);
		requestQueue.add(request);
	}

	public void onSendJsonObjectRequest(int method, String url, JSONObject jsonObject, final OnResponseListener.OnJsonObjectListener listener) {
		JsonObjectRequest request = new JsonObjectRequest(
				method,
				url,
				jsonObject,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, "json object response");
						listener.onResponse(response);
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						listener.onErrorResponse(error);
					}
				}
		){
			@Override
			public Map<String, String> getHeaders() {
				Map<String, String> headers = listener.getHeaders();
				if (headers == null) headers = new HashMap<>();
				headers.put("Content-Type", "application/json");
				return headers;
			}
		};

		request.setShouldCache(false);
		requestQueue.add(request);
	}

	public static void processError(VolleyError error, String TAG) {
		String body;
		if (error == null || error.networkResponse == null) {
			Log.d(TAG, "network is not connected");
			return;
		}

		if (error.networkResponse.data != null) {
			try {
				body = new String(error.networkResponse.data, StandardCharsets.UTF_8);

				Log.d(TAG, "network error message = " + body);
			} catch (Exception e) {
				Log.d(TAG, "exception error message = " + e.getMessage());
			}
		}
	}
}
