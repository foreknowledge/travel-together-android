package com.mungziapp.traveltogether.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.data.ScheduleData;
import com.mungziapp.traveltogether.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class DetailScheduleAdapter extends RecyclerView.Adapter<DetailScheduleAdapter.ViewHolder> {
	private Context context;
	private List<ScheduleData> scheduleItems = new ArrayList<>();

	private OnItemClickListener listener;

	DetailScheduleAdapter(Context context) {
		this.context = context;
	}

	public void addItem(ScheduleData item) { scheduleItems.add(item); }
	public ScheduleData getItem(int position) { return scheduleItems.get(position); }
	public void setClickListener(OnItemClickListener listener) {
		this.listener = listener;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.item_detail_schedule, parent, false);

		return new ViewHolder(itemView, listener);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.setItem(scheduleItems.get(position));
	}

	@Override
	public int getItemCount() {
		return scheduleItems.size();
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		private View scheduleType;
		private TextView scheduleTime;
		private TextView scheduleTitle;
		private TextView scheduleMemo;

		ViewHolder(@NonNull final View itemView, final OnItemClickListener listener) {
			super(itemView);
			scheduleType = itemView.findViewById(R.id.schedule_type);
			scheduleTime = itemView.findViewById(R.id.schedule_time);
			scheduleTitle = itemView.findViewById(R.id.schedule_title);
			scheduleMemo = itemView.findViewById(R.id.schedule_memo);

			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (listener != null)
						listener.onItemClick(ViewHolder.this, view, getAdapterPosition());
				}
			});

		}

		void setItem(ScheduleData item) {
			scheduleTitle.setText(item.getTitle());

			if (item.getMemo() != null) {
				scheduleMemo.setText(item.getMemo());
				scheduleMemo.setVisibility(View.VISIBLE);
			}

			if (item.getTime() != null)
				scheduleTime.setText(item.getTime());

			if (item.getType() == 1)
				scheduleType.setBackgroundResource(R.drawable.dr_schedule_move);
		}
	}
}
