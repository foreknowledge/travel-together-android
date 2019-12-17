package com.mungziapp.traveltogether.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.data.Countries;
import com.mungziapp.traveltogether.item.CountryItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TravelCountryAdapter extends RecyclerView.Adapter<TravelCountryAdapter.ViewHolder> {
	private Context context;
	private ArrayList<String> countryFlags = new ArrayList<>();

	public TravelCountryAdapter(Context context) {
		this.context = context;
	}

	public void addItem(String countryCode) {
		HashMap<String, CountryItem> countryMap = Countries.getCountryMap();

		CountryItem item = countryMap.get(countryCode);
		if (item != null) countryFlags.add(item.getCountryFlag());
	}

	@Override   // 아이템의 개수 리턴
	public int getItemCount() {
		return countryFlags.size();
	}

	@NonNull
	@Override   // 뷰홀더가 만들어지는 시점에 호출되는 메서드
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.item_country, parent, false);

		return new ViewHolder(itemView);
	}

	@Override   // 데이터와 뷰가 결합되는 시점에 호출되는 메서드
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.setItem(countryFlags.get(position));
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		TextView country;

		// 각각의 item에 대한 뷰가 뷰홀더의 파라미터로 전달 됨.
		ViewHolder(@NonNull final View itemView) {
			super(itemView);

			country = itemView.findViewById(R.id.country_textView);
		}

		void setItem(String item) {
			country.setText(item);
		}
	}
}
