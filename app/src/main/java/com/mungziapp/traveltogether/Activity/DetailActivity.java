package com.mungziapp.traveltogether.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mungziapp.traveltogether.R;

public class DetailActivity extends AppCompatActivity {
    private String travelTitle;
    private String travelStartDate;
    private String travelEndDate;
    private int travelImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Detail Fragment 초기화
        Intent intent = getIntent();
        if (intent != null) {
            this.travelTitle = intent.getStringExtra("travelTitle");
            this.travelStartDate = intent.getStringExtra("travelStartDate");
            this.travelEndDate = intent.getStringExtra("travelEndDate");
            this.travelImg = intent.getIntExtra("travelImg", 0);
        }

        setRoomInfo();

        Button btnGoBefore = findViewById(R.id.btn_go_before);
        Button btnMore = findViewById(R.id.btn_more);

        btnGoBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void setRoomInfo() {
        TextView travelTitle = findViewById(R.id.travel_title);
        travelTitle.setText(this.travelTitle);

        TextView travelDuration = findViewById(R.id.travel_duration);
        String strDuration = this.travelStartDate + " ~ " + this.travelEndDate + " (N일간)";
        travelDuration.setText(strDuration);

        TextView travelDDay = findViewById(R.id.travel_d_day);
        travelDDay.setText("D - N");

        FrameLayout travelLayout = findViewById(R.id.travel_layout);
        travelLayout.setBackgroundResource(this.travelImg);
    }
}
