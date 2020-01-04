package com.mungziapp.traveltogether.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.app.helper.DatabaseHelper;
import com.mungziapp.traveltogether.model.data.ScheduleData;
import com.mungziapp.traveltogether.interfaces.OnItemClickListener;
import com.mungziapp.traveltogether.model.table.ScheduleTable;

import java.util.ArrayList;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
	private Context context;
	private int travelId;
	private List<List<String>> dates = new ArrayList<>();

	private OnItemClickListener listener;

	public ScheduleAdapter(Context context, int travelId) {
		this.context = context;
		this.travelId = travelId;
	}

	public void addDate(List<String> date) { dates.add(date); }
	public List<String> getDate(int position) {
		return dates.get(position);
	}
	public void setClickListener(OnItemClickListener listener) {
		this.listener = listener;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.item_schedule, parent, false);

		return new ViewHolder(itemView, context, listener);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.setItem(dates.get(position), travelId);
	}

	@Override
	public int getItemCount() {
		return dates.size();
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		private TextView textDayN;
		private TextView textDate;
		private TextView scheduleNotice;

		private Context context;
		private RecyclerView detailRecycler;

		ViewHolder(@NonNull final View itemView, Context context, final OnItemClickListener listener) {
			super(itemView);

			this.context = context;
			textDayN = itemView.findViewById(R.id.text_day_n);
			textDate = itemView.findViewById(R.id.text_date);
			scheduleNotice = itemView.findViewById(R.id.schedule_notice);

			TextView addSchedule = itemView.findViewById(R.id.btn_add_schedule);
			addSchedule.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (listener != null) listener.onItemClick(ViewHolder.this, itemView, getAdapterPosition());
				}
			});

			detailRecycler = itemView.findViewById(R.id.detail_schedule_recycler);
			detailRecycler.setLayoutManager(new LinearLayoutManager(context));
		}

		private void setScheduleData(int travelId, int dayN, DetailScheduleAdapter detailScheduleAdapter) {
			Cursor cursor = DatabaseHelper.database.rawQuery(ScheduleTable.SELECT_QUERY + " WHERE travel_id = '" + travelId + "' AND day_n = " + dayN, null);
			int numOfRecords = cursor.getCount();

			if (numOfRecords != 0) scheduleNotice.setVisibility(View.INVISIBLE);

			for (int i = 0; i < numOfRecords; ++i) {
				cursor.moveToNext();

				int id = cursor.getInt(cursor.getColumnIndex("id"));
				int type = cursor.getInt(cursor.getColumnIndex("type"));
				String title = cursor.getString(cursor.getColumnIndex("title"));
				String time = cursor.getString(cursor.getColumnIndex("time"));
				String place = cursor.getString(cursor.getColumnIndex("place"));
				String memo = cursor.getString(cursor.getColumnIndex("memo"));
				String photos = cursor.getString(cursor.getColumnIndex("photos"));

				detailScheduleAdapter.addItem(new ScheduleData(id, travelId, dayN, type, title, time, place, memo, photos));
			}

			cursor.close();

			detailScheduleAdapter.notifyDataSetChanged();
		}

		void setItem(List<String> date, int travelId) {
			DetailScheduleAdapter detailScheduleAdapter =  new DetailScheduleAdapter(context);

			String strDayN = "DAY " + date.get(0);
			textDayN.setText(strDayN);
			String strDate = "(" + date.get(1) + ")";
			textDate.setText(strDate);

			setScheduleData(travelId, Integer.valueOf(date.get(0)), detailScheduleAdapter);

			detailScheduleAdapter.setClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
					Toast.makeText(context, "item 클릭함.", Toast.LENGTH_SHORT).show();
				}

				@Override
				public Boolean onItemLongClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
					return null;
				}
			});

			detailRecycler.setAdapter(detailScheduleAdapter);
		}
	}
}
