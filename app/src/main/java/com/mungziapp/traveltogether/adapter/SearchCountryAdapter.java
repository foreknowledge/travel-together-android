package com.mungziapp.traveltogether.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.OnItemClickListener;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.app.TravelHelper;
import com.mungziapp.traveltogether.item.SearchCountryItem;

import java.util.ArrayList;
import java.util.List;

public class SearchCountryAdapter extends RecyclerView.Adapter<SearchCountryAdapter.ViewHolder>
        implements OnItemClickListener {
    private Context context;
    private ArrayList<SearchCountryItem> items = new ArrayList<>();
    private ArrayList<SearchCountryItem> searchItems = new ArrayList<>();

    private OnItemClickListener listener;

    public SearchCountryAdapter(Context context) { this.context = context; }

    public SearchCountryItem getSearchItem(int position) { return searchItems.get(position); }

    public void selectItem(SearchCountryItem item) {
        item.setIsSelected(true);
        notifyDataSetChanged();
    }

    public void deselectItem(SearchCountryItem item) {
        item.setIsSelected(false);
        notifyDataSetChanged();
    }

    public void initItem() {
        for (String countryName: TravelHelper.countryMap.keySet()) {
            List<String> countryInfo = TravelHelper.countryMap.get(countryName);
            String countryFlag = countryInfo.get(0);
            //String countryCode = countryInfo.get(1);
            items.add(new SearchCountryItem(countryFlag, countryName));
        }
        notifyDataSetChanged();
    }

    public void searchItem(String word) {
        searchItems.clear();

        if (word.equals("")) {
            notifyDataSetChanged();
            return;
        }

        for (int i=0; i<items.size(); ++i) {
            SearchCountryItem item = items.get(i);
            if (!item.getIsSelected() && item.getCountryName().replace(" ", "").contains(word)) {
                searchItems.add(item);
            }
        }

        notifyDataSetChanged();
    }

    public void setClickListener(OnItemClickListener listener) { this.listener = listener; }

    @Override
    public void onItemClick(ViewHolder viewHolder, View view, int position) {
        if (listener != null)
            listener.onItemClick(viewHolder, view, position);
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

    public static class ViewHolder extends RecyclerView.ViewHolder{
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
