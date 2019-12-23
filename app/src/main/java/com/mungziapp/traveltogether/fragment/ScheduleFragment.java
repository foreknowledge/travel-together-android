package com.mungziapp.traveltogether.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.activity.AddScheduleActivity;
import com.mungziapp.traveltogether.adapter.ScheduleAdapter;
import com.mungziapp.traveltogether.interfaces.ActivityCallback;
import com.mungziapp.traveltogether.interfaces.OnItemClickListener;

import java.util.Arrays;

public class ScheduleFragment extends Fragment {
	private View rootView;
	private ActivityCallback callback;

	@Override
	public void onAttach(@NonNull Context context) {
		super.onAttach(context);

		if (context instanceof ActivityCallback)
			callback = (ActivityCallback) context;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_schedule, container, false);

		setButtons();
		setRecyclerView();

		return rootView;
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

	private void setRecyclerView() {
		RecyclerView scheduleRecycler = rootView.findViewById(R.id.schedule_recycler);
		scheduleRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

		final ScheduleAdapter scheduleAdapter = new ScheduleAdapter(getContext());
		scheduleAdapter.addDate(Arrays.asList("1", "2020.01.02"));
		scheduleAdapter.addDate(Arrays.asList("2", "2020.01.03"));
		scheduleAdapter.addDate(Arrays.asList("3", "2020.01.04"));
		scheduleAdapter.addDate(Arrays.asList("4", "2020.01.20"));
		scheduleAdapter.addDate(Arrays.asList("5", "2020.01.05"));
		scheduleAdapter.addDate(Arrays.asList("6", "2020.01.06"));
		scheduleAdapter.addDate(Arrays.asList("7", "2020.01.07"));

		scheduleAdapter.setClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
				Intent intent = new Intent(getContext(), AddScheduleActivity.class);
				startActivity(intent);
			}

			@Override
			public Boolean onItemLongClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
				return null;
			}
		});

		scheduleRecycler.setAdapter(scheduleAdapter);
	}
}
