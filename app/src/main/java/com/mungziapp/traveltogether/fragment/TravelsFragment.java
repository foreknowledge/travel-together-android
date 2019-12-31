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

import com.mungziapp.traveltogether.adapter.TravelsRecyclerAdapter;
import com.mungziapp.traveltogether.R;

public class TravelsFragment extends Fragment {
	private TravelsRecyclerAdapter travelsRecyclerAdapter;

	public TravelsFragment() {
	}

	public TravelsFragment(TravelsRecyclerAdapter travelsRecyclerAdapter) {
		this.travelsRecyclerAdapter = travelsRecyclerAdapter;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_travels, container, false);

		RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

		recyclerView.setAdapter(travelsRecyclerAdapter);
		if (travelsRecyclerAdapter.getItemCount() != 0) {
			TextView travelNotice = rootView.findViewById(R.id.travel_notice);
			travelNotice.setVisibility(View.INVISIBLE);
		}

		return rootView;
	}
}
