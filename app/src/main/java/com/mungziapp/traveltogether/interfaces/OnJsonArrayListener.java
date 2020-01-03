package com.mungziapp.traveltogether.interfaces;

import org.json.JSONArray;

public interface OnJsonArrayListener extends OnResponseListener {
	void onResponse(JSONArray response);
}
