package com.mungziapp.traveltogether.app;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mungziapp.traveltogether.interfaces.SetResponseListener;

import java.util.HashMap;
import java.util.Map;

public class RequestManager {
	private static final String TAG = "RequestManager :: ";
	public static final String HOST = "http://192.168.1.81:3000";
	private static RequestManager instance = new RequestManager();
	private static RequestQueue requestQueue;

	private RequestManager() {
	}

	static void setRequestQueue(RequestQueue _requestQueue) {
		requestQueue = _requestQueue;
	}

	public static RequestManager getInstance() {
		return instance;
	}

	public void onSendGetRequest(String url, final SetResponseListener listener) {
		StringRequest request = new StringRequest(
				Request.Method.GET,
				url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						listener.onResponse(response);
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.d(TAG, error.toString());
					}
				}
		);
	}

	public void onSendPostRequest(String url, final SetResponseListener listener) {
		StringRequest request = new StringRequest(
				Request.Method.POST,
				url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
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
