package com.mungziapp.traveltogether.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mungziapp.traveltogether.adapter.TravelCountryAdapter;
import com.mungziapp.traveltogether.adapter.TravelMemberAdapter;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.SearchType;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private String travelTitle;
    private String travelStartDate;
    private String travelEndDate;
    private ArrayList<String> travelCountries;
    private ArrayList<Integer> travelMembers;
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
            this.travelCountries = intent.getStringArrayListExtra("travelCountries");
            this.travelMembers = intent.getIntegerArrayListExtra("travelMembers");
            this.travelImg = intent.getIntExtra("travelImg", 0);
        }

        setRoomInfo();
        setButtons();
    }

    private void setRoomInfo() {
        // 여행 제목 설정
        TextView travelTitle = findViewById(R.id.travel_title);
        travelTitle.setText(this.travelTitle);

        // 여행 기간 설정
        TextView travelDuration = findViewById(R.id.travel_duration);
        String strDuration = this.travelStartDate + " ~ " + this.travelEndDate + " (N일간)";
        travelDuration.setText(strDuration);

        // 여행 D-Day 설정
        TextView travelDDay = findViewById(R.id.travel_d_day);
        travelDDay.setText("D - N");

        // 여행지 설정
        if (travelCountries != null) {
            RecyclerView countryRecyclerView = findViewById(R.id.country_recycler_view);
            countryRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

            TravelCountryAdapter travelCountryAdapter = new TravelCountryAdapter(getApplicationContext());
            for (String country : travelCountries) travelCountryAdapter.addItem(country);
            countryRecyclerView.setAdapter(travelCountryAdapter);
        }

        // 여행 멤버 설정
        if (travelMembers != null) {
            RecyclerView memberRecyclerView = findViewById(R.id.member_recycler_view);
            memberRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

            TravelMemberAdapter travelMemberAdapter = new TravelMemberAdapter(getApplicationContext());
            for (Integer member : travelMembers) travelMemberAdapter.addItem(member);
            memberRecyclerView.setAdapter(travelMemberAdapter);
        }

        // 여행 커버 이미지 설정
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

        LinearLayout notice = findViewById(R.id.btn_notice);
        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTravelActivity(SearchType.NOTICE);
            }
        });

        LinearLayout supplies = findViewById(R.id.btn_supplies);
        supplies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTravelActivity(SearchType.SUPPLIES);
            }
        });

        LinearLayout schedule = findViewById(R.id.btn_schedule);
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTravelActivity(SearchType.SCHEDULE);
            }
        });

        LinearLayout account = findViewById(R.id.btn_account);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTravelActivity(SearchType.ACCOUNT);
            }
        });

        LinearLayout diary = findViewById(R.id.btn_diary);
        diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTravelActivity(SearchType.DIARY);
            }
        });
    }

    private void startTravelActivity(int type) {
        Intent intent = new Intent(getApplicationContext(), TravelActivity.class);
        intent.putExtra("caller", type);
        startActivity(intent);
    }
}
