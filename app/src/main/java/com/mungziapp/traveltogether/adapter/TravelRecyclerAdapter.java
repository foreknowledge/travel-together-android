package com.mungziapp.traveltogether.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.interfaces.OnItemClickListener;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.model.data.TravelData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class TravelRecyclerAdapter extends RecyclerView.Adapter<TravelRecyclerAdapter.ViewHolder> {
	private Context context;
	private List<TravelData> travelData = new ArrayList<>();

	private OnItemClickListener listener;

	public TravelRecyclerAdapter(Context context) {
		this.context = context;
	}

	public void addItem(TravelData item) {
		travelData.add(item);
		Collections.sort(travelData);
	}

	public TravelData getItem(int position) {
		return travelData.get(position);
	}
	public void clearItems() { travelData.clear(); }
	public void setClickListener(OnItemClickListener listener) {
		this.listener = listener;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.item_travel, parent, false);

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
		//private ImageView travelCover;
		private TextView travelName;
		private TextView travelDuration;
		private TextView numOfTravelMembers;
		private TextView travelDday;
		private RecyclerView countryRecyclerView;

		private Context context;

		ViewHolder(@NonNull final View itemView, final OnItemClickListener listener, final Context context) {
			super(itemView);

			this.context = context;

			//this.travelCover = itemView.findViewById(R.id.travel_cover);
			this.travelName = itemView.findViewById(R.id.travel_name);
			this.travelDuration = itemView.findViewById(R.id.travel_duration);
			this.numOfTravelMembers = itemView.findViewById(R.id.travel_members);
			this.travelDday = itemView.findViewById(R.id.travel_d_day);

			this.countryRecyclerView = itemView.findViewById(R.id.country_recycler_view);
			countryRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (listener != null)
						listener.onItemClick(TravelRecyclerAdapter.ViewHolder.this, view, getAdapterPosition());
				}
			});

			itemView.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View view) {
					if (listener != null)
						return listener.onItemLongClick(TravelRecyclerAdapter.ViewHolder.this, view, getAdapterPosition());

					return false;
				}
			});

		}

		void setItem(TravelData item) {
			this.travelName.setText(item.getName());

			// set cover image

			String numOfTravelMembers = Integer.toString(item.getMembers());
			this.numOfTravelMembers.setText(numOfTravelMembers);

			String strDDay;
			LocalDate startLocalDate = item.getStartDate();
			LocalDate endLocalDate = item.getEndDate();

			long daysBetween = DAYS.between(startLocalDate, endLocalDate) + 1;
			String startDate = item.getStartDate().toString().replace("-", ".");
			String endDate = item.getEndDate().toString().replace("-", ".");

			String strDuration = startDate + " ~ " + endDate + " (" + daysBetween + "일간)";
			this.travelDuration.setText(strDuration);

			LocalDate now = LocalDate.now();
			long dDay = DAYS.between(startLocalDate, now);

			if (dDay > 0) strDDay = "D + " + dDay;
			else if (dDay < 0) strDDay = "D - " + -dDay;
			else strDDay = "D-Day!!";

			travelDday.setText(strDDay);

			if (item.getCountryCodes() != null) {
				ArrayList<String> countryCodes = new ArrayList<>(Arrays.asList(item.getCountryCodes().split(",")));
				TravelCountryAdapter travelCountryAdapter = new TravelCountryAdapter(context);
				for (String countryCode : countryCodes) travelCountryAdapter.addItem(countryCode);
				countryRecyclerView.setAdapter(travelCountryAdapter);
			}
		}
	}
}
