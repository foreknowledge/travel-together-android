package com.mungziapp.traveltogether.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class InnerPagerAdapter extends FragmentStatePagerAdapter {
	private ArrayList<Fragment> fragments = new ArrayList<>();

	public InnerPagerAdapter(FragmentManager fm) {
		super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
	}

	@NonNull
	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	@Override
	public int getItemPosition(@NonNull Object object) {
		return POSITION_NONE;
	}

	public void addItem(Fragment fragment) {
		fragments.add(fragment);
	}
}

