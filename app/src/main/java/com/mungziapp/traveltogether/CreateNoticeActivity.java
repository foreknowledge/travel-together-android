package com.mungziapp.traveltogether;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CreateNoticeActivity extends AppCompatActivity {
    final String TAG = "Log/CreateNotice";

    private String title;
    private String contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notice);

        final EditText noticeTitle = findViewById(R.id.notice_title);
        final EditText noticeContents = findViewById(R.id.notice_contents);

        Button save = findViewById(R.id.btn_content_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = noticeTitle.getText().toString();
                contents = noticeContents.getText().toString();
               
                // 서버로 전송
                String url = "http://192.168.0.17:3000/notices";
                makeRequest(url);

                finish();
            }
        });

        Button cancel = findViewById(R.id.btn_content_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void makeRequest(String url) {
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "error message = " + error.toString());
                    }
                }
        ){
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("title", title);
                params.put("content", contents);
                params.put("authorId", "7");

                return params;
            }
        };

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
    }

    private void processResponse(String response) {
        Log.d(TAG, "응답 = " + response);
        Toast.makeText(CreateNoticeActivity.this, "[" + title + " - " + contents + "]가 등록되었습니다.", Toast.LENGTH_SHORT).show();

        finish();
    }
}
