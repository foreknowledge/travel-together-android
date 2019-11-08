package com.mungziapp.traveltogether.Interface;

import java.util.Map;

public interface SetResponseListener {
    void onResponse(String response);
    void setParams(Map<String, String> params);
}
