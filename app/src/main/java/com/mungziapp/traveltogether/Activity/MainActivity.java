package com.mungziapp.traveltogether.Activity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mungziapp.traveltogether.Fragment.AlbumFragment;
import com.mungziapp.traveltogether.Fragment.MainFragment;
import com.mungziapp.traveltogether.Fragment.SettingsFragment;
import com.mungziapp.traveltogether.Fragment.DetailFragment;
import com.mungziapp.traveltogether.Interface.ActivityCallback;
import com.mungziapp.traveltogether.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.MenuItem;

public class MainActivity extends BaseActivity
        implements ActivityCallback {
    private FragmentManager fm;
    private Fragment currentFragment;

    private MainFragment mainFragment;
    private AlbumFragment albumFragment;
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
        albumFragment = new AlbumFragment();
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

    @Override
    public void redirectLoginActivityAndFinish() {
        redirectLoginActivity();
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

                case R.id.navigation_album:
                    if (!(currentFragment instanceof AlbumFragment)){
                        if (currentFragment instanceof SettingsFragment)
                            fm.beginTransaction().remove(currentFragment).commit();
                        currentFragment = albumFragment;
                        fm.beginTransaction().add(R.id.frame_container, currentFragment).commit();
                    }
                    return true;

                case R.id.navigation_settings:
                    if (!(currentFragment instanceof SettingsFragment)){
                        if (currentFragment instanceof AlbumFragment)
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
