package com.mungziapp.traveltogether.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.data.TravelData;
import com.mungziapp.traveltogether.interfaces.OnItemClickListener;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
	private Context context;
	private ArrayList<TravelData> travelData = new ArrayList<>();

	private OnItemClickListener listener;

	public ScheduleAdapter(Context context) {
		this.context = context;
	}

	public void addItem(TravelData item) { travelData.add(item); }
	public TravelData getItem(int position) {
		return travelData.get(position);
	}
	public void setClickListener(OnItemClickListener listener) {
		this.listener = listener;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.item_schedule, parent, false);

		return new ViewHolder(itemView, listener, context);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.setItem(travelData.get(position));
	}

	@Override
	public int getItemCount() {
		return travelData.size();
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		private Context context;

		ViewHolder(@NonNull final View itemView, final OnItemClickListener listener, final Context context) {
			super(itemView);

			this.context = context;

		}

		void setItem(TravelData item) {

		}
	}
}
