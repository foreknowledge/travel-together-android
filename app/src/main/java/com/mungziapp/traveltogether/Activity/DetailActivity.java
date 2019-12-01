package com.mungziapp.traveltogether.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.ServiceType;

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
        setButtons();
    }

    private void setRoomInfo() {
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

    private void setButtons() {
        Button btnGoBefore = findViewById(R.id.btn_go_before);
        btnGoBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btnMore = findViewById(R.id.btn_more);
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Button btnNotice = findViewById(R.id.btn_notice);
        btnNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startTravelActivity(ServiceType.NOTICE);
                startTravelActivity(0);
            }
        });

        Button btnSupplies = findViewById(R.id.btn_supplies);
        btnSupplies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startTravelActivity(ServiceType.SUPPLIES);
                startTravelActivity(1);
            }
        });

        Button btnSchedule = findViewById(R.id.btn_schedule);
        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startTravelActivity(ServiceType.SCHEDULE);
                startTravelActivity(2);
            }
        });

        Button btnAccountBook = findViewById(R.id.btn_account_book);
        btnAccountBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startTravelActivity(ServiceType.ACCOUNT_BOOK);
                startTravelActivity(3);
            }
        });

        Button btnDiary = findViewById(R.id.btn_diary);
        btnDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startTravelActivity(ServiceType.DIARY);
                startTravelActivity(4);
            }
        });
    }

    private void startTravelActivity(int type) {
        Intent intent = new Intent(getApplicationContext(), TravelActivity.class);
        intent.putExtra("caller", type);
        startActivity(intent);
    }
}
