package com.mungziapp.traveltogether.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mungziapp.traveltogether.adapter.TravelsRecyclerAdapter;
import com.mungziapp.traveltogether.adapter.OuterPagerAdapter;
import com.mungziapp.traveltogether.app.DatabaseManager;
import com.mungziapp.traveltogether.fragment.TravelsFragment;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.item.TravelItem;
import com.mungziapp.traveltogether.table.TravelRoomTable;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends BaseActivity {
    private ViewPager outerViewPager;
    private final String TAG = "MainActivity";

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();

        setAdapters();
        setTabBar();
        setAddTravelRoomButton();
        setSettingsButton();
        setSearchButton();
    }

    private void setAdapters() {
        TravelsRecyclerAdapter oncommingAdapter = new TravelsRecyclerAdapter(getApplicationContext());
        TravelsRecyclerAdapter lastTravelAdapter = new TravelsRecyclerAdapter(getApplicationContext());

        Cursor cursor = DatabaseManager.database.rawQuery(TravelRoomTable.SELECT_QUERY, null);
        int numOfRecords = cursor.getCount();
        Log.d(TAG, "레코드 개수: " + numOfRecords);

        for (int i=0; i<numOfRecords; ++i) {
            cursor.moveToNext();

            String title = cursor.getString(cursor.getColumnIndex("name"));
            String startDate = cursor.getString(cursor.getColumnIndex("start_date"));
            String endDate = cursor.getString(cursor.getColumnIndex("end_date"));
            String countryCodes = cursor.getString(cursor.getColumnIndex("country_codes"));
            int thumb = cursor.getInt(cursor.getColumnIndex("thumb"));
            int numOfMembers = cursor.getInt(cursor.getColumnIndex("members"));

            ArrayList<String> countries = new ArrayList<>();

            for (String s : countryCodes.split(","))
                countries.add(s);

            ArrayList<Integer> members = new ArrayList<>();
            for (int j = 0; j < numOfMembers; ++j) members.add(R.drawable.user_img);

            if (Integer.valueOf(endDate.substring(0,2)) < 19)
                lastTravelAdapter.addItem(new TravelItem(title, startDate, endDate, countries, members, thumb));
            else
                oncommingAdapter.addItem(new TravelItem(title, startDate, endDate, countries, members, thumb));
        }

        cursor.close();

        TravelsFragment oncommingTravels = new TravelsFragment(oncommingAdapter);
        TravelsFragment lastTravels = new TravelsFragment(lastTravelAdapter);

        OuterPagerAdapter outerPagerAdapter = new OuterPagerAdapter(fm);
        outerPagerAdapter.addItem(oncommingTravels);
        outerPagerAdapter.addItem(lastTravels);

        outerPagerAdapter.notifyDataSetChanged();

        outerViewPager = findViewById(R.id.outer_view_pager);
        outerViewPager.setOffscreenPageLimit(outerPagerAdapter.getCount());
        outerViewPager.setAdapter(outerPagerAdapter);
    }

    private void setTabBar() {
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(outerViewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                outerViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    private void setSearchButton() {
        Button btnSearch = findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setAddTravelRoomButton() {
        Button btnAddTravelRoom = findViewById(R.id.btn_add_travel_room);
        btnAddTravelRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddTravelRoomActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setSettingsButton() {
        Button btnGoSettings = findViewById(R.id.btn_go_settings);
        btnGoSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}
