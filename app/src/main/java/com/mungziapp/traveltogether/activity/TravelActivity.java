package com.mungziapp.traveltogether.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.mungziapp.traveltogether.adapter.InnerPagerAdapter;
import com.mungziapp.traveltogether.fragment.AccountFragment;
import com.mungziapp.traveltogether.fragment.DiaryFragment;
import com.mungziapp.traveltogether.fragment.NoticeFragment;
import com.mungziapp.traveltogether.fragment.ScheduleFragment;
import com.mungziapp.traveltogether.fragment.SuppliesFragment;
import com.mungziapp.traveltogether.ActivityCallback;
import com.mungziapp.traveltogether.R;

public class TravelActivity extends AppCompatActivity implements ActivityCallback {
    private static final String[] titles = {"공지사항", "준비물", "일정", "가계부", "일기"};
    private static final int[] icons = {R.drawable.ic_notice, R.drawable.ic_supplies, R.drawable.ic_schedule, R.drawable.ic_account, R.drawable.ic_diary};
    private static final int[] icons_selected = {R.drawable.ic_notice_selected, R.drawable.ic_supplies_selected, R.drawable.ic_schedule_selected,
            R.drawable.ic_account_selected, R.drawable.ic_diary_selected};

    private ViewPager innerViewPager;
    private InnerPagerAdapter innerPagerAdapter;
    private TextView fragmentTitle;
    private TabLayout tabLayout;

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
        if (fragmentTitle == null) fragmentTitle = findViewById(R.id.fragment_title);
        fragmentTitle.setText(title);
    }

    private void setPagerAdapter(int type) {
        FragmentManager fm = getSupportFragmentManager();

        // innerPagerAdapter 설정
        innerPagerAdapter = new InnerPagerAdapter(fm);
        innerPagerAdapter.addItem(new NoticeFragment());
        innerPagerAdapter.addItem(new SuppliesFragment());
        innerPagerAdapter.addItem(new ScheduleFragment());
        innerPagerAdapter.addItem(new AccountFragment());
        innerPagerAdapter.addItem(new DiaryFragment());

        innerPagerAdapter.notifyDataSetChanged();

        // innerViewPager 설정
        innerViewPager = findViewById(R.id.travel_view_pager);
        innerViewPager.setOffscreenPageLimit(innerPagerAdapter.getCount());
        innerViewPager.setAdapter(innerPagerAdapter);
        innerViewPager.setCurrentItem(type);

        innerViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) {
                setFragmentTitle(titles[position]);

                for (int i = 0; i < innerPagerAdapter.getCount(); ++i) {
                    TabLayout.Tab tab = tabLayout.getTabAt(i);

                    if (tab != null) {
                        if (i == position) tab.setIcon(icons_selected[i]);
                        else tab.setIcon(icons[i]);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });

        setFragmentTitle(titles[type]);

        // tabLayout 설정
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(innerViewPager);

        for (int i = 0; i < innerPagerAdapter.getCount(); ++i) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);

            if (tab != null) {
                if (i == type) tab.setIcon(icons_selected[i]);
                else tab.setIcon(icons[i]);
            }
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
