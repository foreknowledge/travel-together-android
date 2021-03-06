package com.mungziapp.traveltogether.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mungziapp.traveltogether.adapter.SuppliesPagerAdapter;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.interfaces.ActivityCallback;

public class SuppliesFragment extends Fragment {
	private View rootView;
	private ActivityCallback.ActivityFinishCallback callback;

	private ViewPager suppliesViewPager;
	private FragmentManager fm;

	@Override
	public void onAttach(@NonNull Context context) {
		super.onAttach(context);

		if (context instanceof ActivityCallback.ActivityFinishCallback)
			callback = (ActivityCallback.ActivityFinishCallback) context;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_supplies, container, false);

		fm = getFragmentManager();

		setAdapters();
		setTabBar();
		setButtons();

		return rootView;
	}

	private void setAdapters() {
		SuppliesAllFragment allFragment = new SuppliesAllFragment();
		SuppliesMyFragment myFragment = new SuppliesMyFragment();

		SuppliesPagerAdapter suppliesPagerAdapter = new SuppliesPagerAdapter(fm);
		suppliesPagerAdapter.addItem(allFragment);
		suppliesPagerAdapter.addItem(myFragment);
		suppliesPagerAdapter.notifyDataSetChanged();

		suppliesViewPager = rootView.findViewById(R.id.supply_viewPager);
		suppliesViewPager.setOffscreenPageLimit(suppliesPagerAdapter.getCount());
		suppliesViewPager.setAdapter(suppliesPagerAdapter);
	}

	private void setTabBar() {
		TabLayout tabLayout = rootView.findViewById(R.id.supply_tabLayout);
		tabLayout.setupWithViewPager(suppliesViewPager);

		tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				suppliesViewPager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {
			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {
			}
		});
	}

	private void setButtons() {
		Button btnGoBefore = rootView.findViewById(R.id.btn_go_before);
		btnGoBefore.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				callback.finishActivity();
			}
		});
	}
}
