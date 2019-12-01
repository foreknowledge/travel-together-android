package com.mungziapp.traveltogether.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.mungziapp.traveltogether.Adapter.InnerPagerAdapter;
import com.mungziapp.traveltogether.Fragment.AccountFragment;
import com.mungziapp.traveltogether.Fragment.DiaryFragment;
import com.mungziapp.traveltogether.Fragment.NoticeFragment;
import com.mungziapp.traveltogether.Fragment.ScheduleFragment;
import com.mungziapp.traveltogether.Fragment.SuppliesFragment;
import com.mungziapp.traveltogether.Interface.ActivityCallback;
import com.mungziapp.traveltogether.R;

public class TravelActivity extends AppCompatActivity implements ActivityCallback {
    private final String[] titles = {"공지사항", "준비물", "일정", "가계부", "일기"};
    ViewPager innerViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        Intent intent = getIntent();
        setPagerAdapter(intent.getIntExtra("caller", 0));
        setButtons();
    }

    @Override
    public void setFragmentTitle(String title) {
        TextView fragmentTitle = findViewById(R.id.fragment_title);
        fragmentTitle.setText(title);
    }

    private void setPagerAdapter(int type) {
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
        setFragmentTitle(titles[type]);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(innerViewPager);

        for (int i = 0; i < innerPagerAdapter.getCount(); ++i) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) tab.setText(titles[i]);
        }

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

    private void setButtons() {
        Button btnGoBefore = findViewById(R.id.btn_go_before);
        btnGoBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
