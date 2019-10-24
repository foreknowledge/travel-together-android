package com.mungziapp.traveltogether.Activity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mungziapp.traveltogether.Fragment.CalendarFragment;
import com.mungziapp.traveltogether.Fragment.MainFragment;
import com.mungziapp.traveltogether.Fragment.SettingsFragment;
import com.mungziapp.traveltogether.Fragment.DetailFragment;
import com.mungziapp.traveltogether.Interface.ActivityCallback;
import com.mungziapp.traveltogether.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements ActivityCallback {
    private FragmentManager fm;
    private Fragment currentFragment;

    private MainFragment mainFragment;
    private CalendarFragment calendarFragment;
    private SettingsFragment settingsFragment;

    private DetailFragment currentDetailFragment;

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

        currentFragment = mainFragment;
        fm.beginTransaction().add(R.id.frame_container, currentFragment).commit();
    }

    @Override
    public void addDetailFragment(DetailFragment detailFragment) {
        currentDetailFragment = detailFragment;
        fm.beginTransaction().add(R.id.frame_container, currentDetailFragment).commit();
    }

    @Override
    public void removeDetailFragment() {
        fm.beginTransaction().remove(currentDetailFragment).commit();
        currentDetailFragment = null;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (!(currentFragment instanceof MainFragment)) {
                        fm.beginTransaction().remove(currentFragment).commit();
                        currentFragment = mainFragment;
                    }
                    return true;

                case R.id.navigation_calendar:
                    if (!(currentFragment instanceof CalendarFragment)){
                        if (currentFragment instanceof SettingsFragment)
                            fm.beginTransaction().remove(currentFragment).commit();
                        currentFragment = calendarFragment;
                        fm.beginTransaction().add(R.id.frame_container, currentFragment).commit();
                    }
                    return true;

                case R.id.navigation_settings:
                    if (!(currentFragment instanceof SettingsFragment)){
                        if (currentFragment instanceof CalendarFragment)
                            fm.beginTransaction().remove(currentFragment).commit();
                        currentFragment = settingsFragment;
                        fm.beginTransaction().add(R.id.frame_container, currentFragment).commit();
                    }
                    return true;
            }
            return false;
        }
    };
}
