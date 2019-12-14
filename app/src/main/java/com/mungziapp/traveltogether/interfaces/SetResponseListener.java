package com.mungziapp.traveltogether.interfaces;

import java.util.Map;

public interface SetResponseListener {
	void onResponse(String response);

	void setParams(Map<String, String> params);
}
