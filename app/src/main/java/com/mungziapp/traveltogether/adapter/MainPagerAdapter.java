package com.mungziapp.traveltogether.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.mungziapp.traveltogether.fragment.TravelsFragment;

import java.util.ArrayList;

public class MainPagerAdapter extends FragmentStatePagerAdapter {
	private ArrayList<TravelsFragment> travelsFragments = new ArrayList<>();

	public MainPagerAdapter(FragmentManager fm) {
		super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
	}

	@NonNull
	@Override
	public TravelsFragment getItem(int position) {
		return travelsFragments.get(position);
	}

	@Override
	public int getCount() {
		return travelsFragments.size();
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

	public void addItem(TravelsFragment travelsFragment) {
		travelsFragments.add(travelsFragment);
	}
}

