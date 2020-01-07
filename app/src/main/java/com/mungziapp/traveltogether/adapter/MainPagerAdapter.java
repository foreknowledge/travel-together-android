package com.mungziapp.traveltogether.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.mungziapp.traveltogether.fragment.TravelFragment;

import java.util.ArrayList;
import java.util.List;

public class MainPagerAdapter extends FragmentStatePagerAdapter {
	private List<TravelFragment> travelFragments = new ArrayList<>();

	public MainPagerAdapter(FragmentManager fm) {
		super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
	}

	@NonNull
	@Override
	public TravelFragment getItem(int position) {
		return travelFragments.get(position);
	}

	@Override
	public int getCount() {
		return travelFragments.size();
	}

	@Override
	public int getItemPosition(@NonNull Object object) {
		return POSITION_NONE;
	}

	@Nullable
	@Override
	public CharSequence getPageTitle(int position) {
		if (position == 0) return "다가오는 여행";
		else return "지난 여행";
	}

	public void addItem(TravelFragment travelFragment) {
		travelFragments.add(travelFragment);
	}
}

