package com.mungziapp.traveltogether.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.activity.AddScheduleActivity;
import com.mungziapp.traveltogether.adapter.ScheduleAdapter;
import com.mungziapp.traveltogether.app.DatabaseManager;
import com.mungziapp.traveltogether.data.DateObject;
import com.mungziapp.traveltogether.interfaces.ActivityCallback;
import com.mungziapp.traveltogether.interfaces.OnItemClickListener;
import com.mungziapp.traveltogether.table.TravelTable;

import java.time.LocalDate;
import java.util.Arrays;

import static java.time.temporal.ChronoUnit.DAYS;

public class ScheduleFragment extends Fragment {
	private View rootView;
	private ActivityCallback callback;

	private String travelStartDate;
	private String travelEndDate;

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

		int travelId = 0;
		Bundle bundle = getArguments();
		if (bundle != null)
			travelId = bundle.getInt("travel_id");

		setDataFromDB(travelId);
		setButtons();
		setRecyclerView();

		return rootView;
	}

	private void setDataFromDB(int id) {
		Cursor cursor = DatabaseManager.database.rawQuery(TravelTable.SELECT_QUERY + " WHERE id = " + id, null);
		cursor.moveToNext();

		this.travelStartDate = cursor.getString(cursor.getColumnIndex("start_date"));
		this.travelEndDate = cursor.getString(cursor.getColumnIndex("end_date"));

		cursor.close();
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
		if (travelStartDate == null || travelEndDate == null) {
			TextView textScheduleNotice = rootView.findViewById(R.id.text_schedule_notice);
			textScheduleNotice.setVisibility(View.VISIBLE);

			return;
		}

		RecyclerView scheduleRecycler = rootView.findViewById(R.id.schedule_recycler);
		scheduleRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

		final ScheduleAdapter scheduleAdapter = new ScheduleAdapter(getContext());

		LocalDate startLocalDate = DateObject.stringToLocalDate(travelStartDate);
		LocalDate endLocalDate = DateObject.stringToLocalDate(travelEndDate);

		if (startLocalDate == null || endLocalDate == null) return;

		long daysBetween = DAYS.between(startLocalDate, endLocalDate);

		LocalDate date = startLocalDate;
		for (int i = 0; i < daysBetween + 1; ++i) {
			String dayN = String.valueOf(i + 1);
			scheduleAdapter.addDate(Arrays.asList(dayN, date.toString()));

			date = date.plusDays(1);
		}

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
