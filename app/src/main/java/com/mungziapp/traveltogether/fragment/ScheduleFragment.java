package com.mungziapp.traveltogether.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.activity.AddScheduleActivity;
import com.mungziapp.traveltogether.adapter.ScheduleAdapter;
import com.mungziapp.traveltogether.app.helper.DatabaseHelper;
import com.mungziapp.traveltogether.app.helper.DateHelper;
import com.mungziapp.traveltogether.interfaces.ActivityCallback;
import com.mungziapp.traveltogether.interfaces.OnItemClickListener;
import com.mungziapp.traveltogether.model.table.TravelTable;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class ScheduleFragment extends Fragment {
	private View rootView;
	private ActivityCallback.ActivityFinishCallback callback;

	private String travelId;
	private String travelStartDate;
	private String travelEndDate;

	@Override
	public void onAttach(@NonNull Context context) {
		super.onAttach(context);

		if (context instanceof ActivityCallback.ActivityFinishCallback)
			callback = (ActivityCallback.ActivityFinishCallback) context;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_schedule, container, false);

		Bundle bundle = getArguments();
		if (bundle != null)
			travelId = bundle.getString("travel_id");

		setDataFromDB(travelId);
		setButtons();
		setRecyclerView();

		return rootView;
	}

	private void setDataFromDB(String travelId) {
		Cursor cursor = DatabaseHelper.database.rawQuery(TravelTable.SELECT_QUERY + " WHERE id = '" + travelId + "'", null);
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
		RecyclerView scheduleRecycler = rootView.findViewById(R.id.schedule_recycler);
		scheduleRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

		final ScheduleAdapter scheduleAdapter = new ScheduleAdapter(getContext(), travelId);

		LocalDate startLocalDate = DateHelper.stringToLocalDate(travelStartDate, "-");
		LocalDate endLocalDate = DateHelper.stringToLocalDate(travelEndDate, "-");

		if (startLocalDate == null || endLocalDate == null) return;

		long daysBetween = DAYS.between(startLocalDate, endLocalDate) + 1;

		LocalDate date = startLocalDate;
		for (int i = 0; i < daysBetween; ++i) {
			String dayN = String.valueOf(i + 1);
			scheduleAdapter.addDate(Arrays.asList(dayN, date.toString()));

			date = date.plusDays(1);
		}

		scheduleAdapter.setClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
				List<String> date = scheduleAdapter.getDate(position);

				Intent intent = new Intent(getContext(), AddScheduleActivity.class);
				intent.putExtra("day_n", date.get(0));
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
