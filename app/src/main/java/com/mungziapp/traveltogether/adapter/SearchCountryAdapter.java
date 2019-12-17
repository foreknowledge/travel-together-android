package com.mungziapp.traveltogether.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.data.Countries;
import com.mungziapp.traveltogether.interfaces.OnItemClickListener;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.item.SearchCountryItem;

import java.util.ArrayList;
import java.util.List;

public class SearchCountryAdapter extends RecyclerView.Adapter<SearchCountryAdapter.ViewHolder> {
	private Context context;
	private ArrayList<SearchCountryItem> items = new ArrayList<>();
	private ArrayList<SearchCountryItem> searchItems = new ArrayList<>();

	private OnItemClickListener listener;

	public SearchCountryAdapter(Context context) {
		this.context = context;
	}

	public SearchCountryItem getSearchItem(int position) {
		return searchItems.get(position);
	}

	public SearchCountryItem getItem(String countryFlag) {
		for (SearchCountryItem item : items) {
			if (item.getCountryFlag().equals(countryFlag)) return item;
		}

		return null;
	}

	public void selectItem(SearchCountryItem item) {
		item.setIsSelected(true);
		notifyDataSetChanged();
	}

	public void deselectItem(SearchCountryItem item) {
		item.setIsSelected(false);
		notifyDataSetChanged();
	}

	public void initItem() {
		List<Countries.Country> countryList = Countries.getCountryList();

		for (Countries.Country country : countryList)
			items.add(new SearchCountryItem(country.getCountryFlag(), country.getCountryKrName()));

		notifyDataSetChanged();
	}

	public void searchItem(String word) {
		searchItems.clear();

		if (word.equals("")) {
			notifyDataSetChanged();
			return;
		}

		for (int i = 0; i < items.size(); ++i) {
			SearchCountryItem item = items.get(i);
			if (!item.getIsSelected() && item.getCountryName().replace(" ", "").contains(word)) {
				searchItems.add(item);
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
		View itemView = inflater.inflate(R.layout.item_search_country, parent, false);

		return new ViewHolder(itemView, listener);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.setItem(searchItems.get(position));
	}

	@Override
	public int getItemCount() {
		return searchItems.size();
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		private TextView countryFlag;
		private TextView countryName;

		ViewHolder(@NonNull final View itemView, final OnItemClickListener listener) {
			super(itemView);

			this.countryFlag = itemView.findViewById(R.id.country_flag);
			this.countryName = itemView.findViewById(R.id.country_name);

			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (listener != null)
						listener.onItemClick(ViewHolder.this, view, getAdapterPosition());
				}
			});

		}

		void setItem(SearchCountryItem item) {
			this.countryFlag.setText(item.getCountryFlag());
			this.countryName.setText(item.getCountryName());
		}
	}
}
