package com.mungziapp.traveltogether;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

class AppHelper {
    static RequestQueue requestQueue;

    static void createRequestQueue(Context context) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
    }
}
