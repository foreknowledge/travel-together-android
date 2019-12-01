package com.mungziapp.traveltogether.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.mungziapp.traveltogether.Adapter.InnerPagerAdapter;
import com.mungziapp.traveltogether.Fragment.AccountFragment;
import com.mungziapp.traveltogether.Fragment.DiaryFragment;
import com.mungziapp.traveltogether.Fragment.NoticeFragment;
import com.mungziapp.traveltogether.Fragment.ScheduleFragment;
import com.mungziapp.traveltogether.Fragment.SuppliesFragment;
import com.mungziapp.traveltogether.R;

public class TravelActivity extends AppCompatActivity {
    ViewPager innerViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        Intent intent = getIntent();
        int type = intent.getIntExtra("caller", 0);

        FragmentManager fm = getSupportFragmentManager();

        InnerPagerAdapter innerPagerAdapter = new InnerPagerAdapter(fm);
        innerPagerAdapter.addItem(new NoticeFragment());
        innerPagerAdapter.addItem(new SuppliesFragment());
        innerPagerAdapter.addItem(new ScheduleFragment());
        innerPagerAdapter.addItem(new AccountFragment());
        innerPagerAdapter.addItem(new DiaryFragment());

        innerPagerAdapter.notifyDataSetChanged();

        innerViewPager = findViewById(R.id.travel_view_pager);
        innerViewPager.setOffscreenPageLimit(innerPagerAdapter.getCount());
        innerViewPager.setAdapter(innerPagerAdapter);
        innerViewPager.setCurrentItem(type);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(innerViewPager);

        tabLayout.getTabAt(0).setText("공지사항");
        tabLayout.getTabAt(1).setText("준비물");
        tabLayout.getTabAt(2).setText("일정");
        tabLayout.getTabAt(3).setText("가계부");
        tabLayout.getTabAt(4).setText("일기");

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                innerViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

    }
}
