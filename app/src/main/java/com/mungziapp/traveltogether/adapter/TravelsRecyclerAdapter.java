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
import com.mungziapp.traveltogether.data.TravelData;

import java.util.ArrayList;
import java.util.Arrays;

public class TravelsRecyclerAdapter extends RecyclerView.Adapter<TravelsRecyclerAdapter.ViewHolder> {
	private Context context;
	private ArrayList<TravelData> items = new ArrayList<>();

	private OnItemClickListener listener;

	public TravelsRecyclerAdapter(Context context) {
		this.context = context;
	}

	public void addItem(TravelData item) { items.add(item); }
	public TravelData getItem(int position) {
		return items.get(position);
	}
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
		holder.setItem(items.get(position));
	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		private View travelLayout;
		private TextView travelName;
		private TextView travelDuration;
		private TextView numOfTravelMembers;
		private TextView travelDday;
		private RecyclerView countryRecyclerView;

		private Context context;

		ViewHolder(@NonNull final View itemView, final OnItemClickListener listener, final Context context) {
			super(itemView);

			this.context = context;

			this.travelLayout = itemView.findViewById(R.id.travel_layout);
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
						listener.onItemClick(TravelsRecyclerAdapter.ViewHolder.this, view, getAdapterPosition());
				}
			});

			itemView.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View view) {
					if (listener != null)
						return listener.onItemLongClick(TravelsRecyclerAdapter.ViewHolder.this, view, getAdapterPosition());

					return false;
				}
			});

		}

		void setItem(TravelData item) {
			this.travelName.setText(item.getName());

			this.travelLayout.setBackgroundResource(item.getCover());

			String numOfTravelMembers = Integer.toString(item.getMembers());
			this.numOfTravelMembers.setText(numOfTravelMembers);

			if (item.getStartDate() != null && item.getEndDate() != null) {
				String strDuration = item.getStartDate() + " ~ " + item.getEndDate() + " (N일간)";
				this.travelDuration.setText(strDuration);

				travelDday.setText("D - N");
			}

			if (item.getCountryCodes() != null) {
				ArrayList<String> countries = new ArrayList<>(Arrays.asList(item.getCountryCodes().split(",")));
				TravelCountryAdapter travelCountryAdapter = new TravelCountryAdapter(context);
				for (String country : countries) travelCountryAdapter.addItem(country);
				countryRecyclerView.setAdapter(travelCountryAdapter);
			}
		}
	}
}
