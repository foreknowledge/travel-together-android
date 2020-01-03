package com.mungziapp.traveltogether.interfaces;

import org.json.JSONObject;

public interface OnPOSTResponseListener extends OnResponseListener {
	void onResponse(JSONObject jsonObject);
}
