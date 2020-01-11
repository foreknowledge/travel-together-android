package com.mungziapp.traveltogether.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mungziapp.traveltogether.model.FragmentType;
import com.mungziapp.traveltogether.fragment.AccountFragment;
import com.mungziapp.traveltogether.fragment.DiaryFragment;
import com.mungziapp.traveltogether.fragment.NoticeFragment;
import com.mungziapp.traveltogether.fragment.ScheduleFragment;
import com.mungziapp.traveltogether.fragment.SuppliesFragment;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.interfaces.ActivityCallback;

import java.util.ArrayList;
import java.util.List;

public class TravelActivity extends AppCompatActivity implements ActivityCallback.ActivityFinishCallback {
	private FragmentManager fragmentManager;

	private List<Fragment> fragments = new ArrayList<>();
	private int currentIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_travel);

		fragmentManager = getSupportFragmentManager();

		Intent intent = getIntent();
		FragmentType type = (FragmentType) intent.getSerializableExtra("caller");
		if (type != null) setPagerAdapter(type, intent.getStringExtra("travel_id"));
	}

	private void setPagerAdapter(final FragmentType type, String travelId) {
		fragments.add(new NoticeFragment());
		fragments.add(new SuppliesFragment());
		fragments.add(new ScheduleFragment());
		fragments.add(new AccountFragment());
		fragments.add(new DiaryFragment());

		Bundle bundle = new Bundle();
		bundle.putString("travel_id", travelId);

		for (Fragment fragment: fragments) {
			fragment.setArguments(bundle);

			fragmentManager.beginTransaction().add(R.id.travel_frame, fragment).commit();
			fragmentManager.beginTransaction().hide(fragment).commit();
		}

		currentIndex = type.getIndex();
		fragmentManager.beginTransaction().show(fragments.get(currentIndex)).commit();

		// bottomNavigationView 설정
		BottomNavigationView bottomNavView = findViewById(R.id.bottom_nav_view);
		bottomNavView.setItemIconTintList(null);
		bottomNavView.setSelectedItemId(type.getMenuItemId());

		bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
				fragmentManager.beginTransaction().hide(fragments.get(currentIndex)).commit();

				switch (menuItem.getItemId()) {
					case R.id.notification: currentIndex = 0; break;
					case R.id.supplies: currentIndex = 1; break;
					case R.id.schedule: currentIndex = 2; break;
					case R.id.account_book: currentIndex = 3; break;
					case R.id.diary: currentIndex = 4; break;
				}
				fragmentManager.beginTransaction().show(fragments.get(currentIndex)).commit();

				return true;
			}
		});
	}

	@Override
	public void finishActivity() {
		finish();
	}
}
