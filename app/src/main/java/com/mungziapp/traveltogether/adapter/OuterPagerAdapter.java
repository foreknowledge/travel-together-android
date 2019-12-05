package com.mungziapp.traveltogether.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.mungziapp.traveltogether.fragment.TravelsFragment;

import java.util.ArrayList;

public class OuterPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<TravelsFragment> items = new ArrayList<>();

    public OuterPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public TravelsFragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
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
        items.add(travelsFragment);
    }
}

