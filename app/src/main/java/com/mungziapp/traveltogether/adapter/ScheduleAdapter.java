package com.mungziapp.traveltogether.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
	private Context context;
	private List<List<String>> dates = new ArrayList<>();

	private OnItemClickListener listener;

	public ScheduleAdapter(Context context) {
		this.context = context;
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

		return new ViewHolder(itemView, listener);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.setItem(dates.get(position));
	}

	@Override
	public int getItemCount() {
		return dates.size();
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		private TextView textDayN;
		private TextView textDate;

		ViewHolder(@NonNull final View itemView, final OnItemClickListener listener) {
			super(itemView);

			textDayN = itemView.findViewById(R.id.text_day_n);
			textDate = itemView.findViewById(R.id.text_date);

			TextView addSchedule = itemView.findViewById(R.id.btn_add_schedule);
			addSchedule.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (listener != null) listener.onItemClick(ViewHolder.this, itemView, getAdapterPosition());
				}
			});

		}

		void setItem(List<String> date) {
			String strDayN = "DAY " + date.get(0);
			textDayN.setText(strDayN);
			String strDate = "(" + date.get(1) + ")";
			textDate.setText(strDate);
		}
	}
}
