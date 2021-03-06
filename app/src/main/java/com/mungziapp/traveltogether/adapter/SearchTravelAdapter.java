package com.mungziapp.traveltogether.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.interfaces.OnItemClickListener;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.app.helper.DatabaseHelper;
import com.mungziapp.traveltogether.model.item.SearchTravelItem;
import com.mungziapp.traveltogether.model.table.TravelTable;

import java.util.ArrayList;
import java.util.List;

public class SearchTravelAdapter extends RecyclerView.Adapter<SearchTravelAdapter.ViewHolder> {
	private Context context;
	private List<SearchTravelItem> items = new ArrayList<>();
	private List<SearchTravelItem> filteredItems = new ArrayList<>();

	private OnItemClickListener listener;

	public SearchTravelAdapter(Context context) {
		this.context = context;
	}

	public SearchTravelItem getItem(int position) {
		return filteredItems.get(position);
	}

	public void initItem() {
		Cursor cursor = DatabaseHelper.database.rawQuery(TravelTable.SELECT_QUERY, null);
		int numOfRecords = cursor.getCount();

		for (int i = 0; i < numOfRecords; ++i) {
			cursor.moveToNext();

			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String title = cursor.getString(cursor.getColumnIndex("name"));
			String startDate = cursor.getString(cursor.getColumnIndex("start_date"));
			String endDate = cursor.getString(cursor.getColumnIndex("end_date"));
			String coverImgPath = cursor.getString(cursor.getColumnIndex("cover_img_path"));

			items.add(new SearchTravelItem(id, title, startDate, endDate, coverImgPath));
		}

		cursor.close();

		notifyDataSetChanged();
	}

	public void searchItem(String word) {
		filteredItems.clear();

		if (word.equals("")) {
			notifyDataSetChanged();
			return;
		}

		for (int i = 0; i < items.size(); ++i) {
			SearchTravelItem item = items.get(i);
			if (item.getTravelTitle().replace(" ", "").contains(word)) {
				filteredItems.add(item);
			}
		}

		notifyDataSetChanged();
	}

	public void setClickListener(OnItemClickListener listener) {
		this.listener = listener;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.item_search_travel, parent, false);

		return new ViewHolder(itemView, listener);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.setItem(filteredItems.get(position));
	}

	@Override
	public int getItemCount() {
		return filteredItems.size();
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		//private View travelCover;
		private TextView travelTitle;
		private TextView travelDuration;

		ViewHolder(@NonNull final View itemView, final OnItemClickListener listener) {
			super(itemView);

			//this.travelCover = itemView.findViewById(R.id.travel_cover);
			this.travelTitle = itemView.findViewById(R.id.travel_title);
			this.travelDuration = itemView.findViewById(R.id.travel_duration);

			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (listener != null)
						listener.onItemClick(SearchTravelAdapter.ViewHolder.this, view, getAdapterPosition());
				}
			});

		}

		void setItem(SearchTravelItem item) {
			this.travelTitle.setText(item.getTravelTitle());

			String strDuration = "(" + item.getTravelStartDate() + " ~ " + item.getTravelEndDate() + ")";
			this.travelDuration.setText(strDuration);
		}
	}
}
