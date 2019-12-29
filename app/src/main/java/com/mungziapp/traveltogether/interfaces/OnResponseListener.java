package com.mungziapp.traveltogether.interfaces;

import java.util.Map;

public interface OnResponseListener {
	void onResponse(String response);
	void setParams(Map<String, String> params);
	Map<String, String> getHeaders();
}
