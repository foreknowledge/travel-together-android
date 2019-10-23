package com.mungziapp.traveltogether.Activity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mungziapp.traveltogether.Adapter.PagerAdapter;
import com.mungziapp.traveltogether.Fragment.CalendarFragment;
import com.mungziapp.traveltogether.Fragment.MainFragment;
import com.mungziapp.traveltogether.Fragment.SettingsFragment;
import com.mungziapp.traveltogether.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    FragmentManager fm;
    MainFragment mainFragment;
    CalendarFragment calendarFragment;
    SettingsFragment settingsFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fm.beginTransaction().replace(R.id.frame_container, mainFragment).commit();
                    return true;
                case R.id.navigation_calendar:
                    fm.beginTransaction().replace(R.id.frame_container, calendarFragment).commit();
                    return true;
                case R.id.navigation_settings:
                    fm.beginTransaction().replace(R.id.frame_container, settingsFragment).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.bottom_nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm = getSupportFragmentManager();
        mainFragment = new MainFragment(fm);
        calendarFragment = new CalendarFragment();
        settingsFragment = new SettingsFragment();

        fm.beginTransaction().add(R.id.frame_container, mainFragment).commit();
    }

    private void setViewPager() {
        FragmentManager fm = getSupportFragmentManager();
        PagerAdapter pagerAdapter = new PagerAdapter(fm);

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(0);
        viewPager.setAdapter(pagerAdapter);
    }

}
