package com.mungziapp.traveltogether.app.helper;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mungziapp.traveltogether.interfaces.OnResponseListener;

import java.util.HashMap;
import java.util.Map;

public class RequestHelper {
	private static final String TAG = "RequestHelper :: ";
	public static final String HOST = "http://192.168.0.44:3000";

	private static RequestHelper instance = new RequestHelper();
	private static RequestQueue requestQueue;

	private RequestHelper() { }

	public static void setRequestQueue(RequestQueue _requestQueue) {
		requestQueue = _requestQueue;
	}

	public static RequestHelper getInstance() {
		return instance;
	}

	public void onSendGetRequest(String url, final OnResponseListener listener) {
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
						Log.d(TAG, error.toString());
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

	public void onSendPostRequest(String url, final OnResponseListener listener) {
		StringRequest request = new StringRequest(
				Request.Method.POST,
				url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						Log.d(TAG, "post method response = " + response);
						listener.onResponse(response);
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.d(TAG, "error message = " + error.toString());
					}
				}
		) {
			@Override
			public String getBodyContentType() {
				return "application/x-www-form-urlencoded; charset=UTF-8";
			}

			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<>();
				listener.setParams(params);

				return params;
			}
		};

		request.setShouldCache(false);
		requestQueue.add(request);
	}
}
