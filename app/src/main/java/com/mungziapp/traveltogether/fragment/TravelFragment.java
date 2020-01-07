package com.mungziapp.traveltogether.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.adapter.TravelRecyclerAdapter;
import com.mungziapp.traveltogether.R;

public class TravelFragment extends Fragment {
	private TravelRecyclerAdapter travelRecyclerAdapter;
	private boolean reverseOrder = false;

	private View rootView;

	public TravelFragment() {
	}

	public TravelFragment(TravelRecyclerAdapter travelRecyclerAdapter) {
		this.travelRecyclerAdapter = travelRecyclerAdapter;
	}

	public TravelFragment(TravelRecyclerAdapter travelRecyclerAdapter, boolean reverseOrder) {
		this.travelRecyclerAdapter = travelRecyclerAdapter;
		this.reverseOrder = reverseOrder;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_travels, container, false);

		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
		if (reverseOrder) {
			linearLayoutManager.setReverseLayout(true);
			linearLayoutManager.setStackFromEnd(true);
		}

		RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(linearLayoutManager);
		recyclerView.setAdapter(travelRecyclerAdapter);

		return rootView;
	}

	public void setNoticeTextVisibility() {
		if (travelRecyclerAdapter.getItemCount() != 0) {
			TextView travelNotice = rootView.findViewById(R.id.travel_notice);
			travelNotice.setVisibility(View.INVISIBLE);
		}
	}
}
