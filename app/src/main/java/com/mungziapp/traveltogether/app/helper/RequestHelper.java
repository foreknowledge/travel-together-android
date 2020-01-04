package com.mungziapp.traveltogether.app.helper;

import android.util.Log;

import com.android.volley.Request;
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
	public static final String HOST = "http://192.168.1.81:3000";

	private static RequestHelper instance = new RequestHelper();
	private static RequestQueue requestQueue;

	private RequestHelper() { }

	public static void setRequestQueue(RequestQueue _requestQueue) {
		requestQueue = _requestQueue;
	}

	public static RequestHelper getInstance() {
		return instance;
	}

	public void onSendGetRequest(String url, final OnResponseListener.OnGETListener listener) {
		StringRequest request = new StringRequest(
				Request.Method.GET,
				url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						Log.d(TAG, "get method response = " + response);
						listener.onResponse(response);
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						processError(error);
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
						Log.d(TAG, "json array response = " + response);
						listener.onResponse(response);
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						processError(error);
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

	public void onSendPostRequest(String url, final OnResponseListener.OnPOSTListener.OnStringListener listener) {
		StringRequest request = new StringRequest(
				Request.Method.POST,
				url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						Log.d(TAG, "get method response = " + response);
						listener.onResponse(response);
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						processError(error);
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

	public void onSendPostRequest(String url, JSONObject jsonObject, final OnResponseListener.OnPOSTListener.OnJsonObjectListener listener) {
		JsonObjectRequest request = new JsonObjectRequest(
				Request.Method.POST,
				url,
				jsonObject,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, "post method response = " + response);
						listener.onResponse(response);
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						processError(error);
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

	private void processError(VolleyError error) {
		String body;
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
