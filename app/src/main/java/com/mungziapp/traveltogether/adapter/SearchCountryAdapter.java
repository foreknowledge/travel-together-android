package com.mungziapp.traveltogether.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.app.TravelHelper;
import com.mungziapp.traveltogether.item.SearchCountryItem;

import java.util.ArrayList;
import java.util.List;

public class SearchCountryAdapter extends RecyclerView.Adapter<SearchCountryAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SearchCountryItem> items = new ArrayList<>();
    private ArrayList<SearchCountryItem> filteredItems = new ArrayList<>();

    public SearchCountryAdapter(Context context) { this.context = context; }

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
        filteredItems.clear();

        if (word.equals("")) {
            notifyDataSetChanged();
            return;
        }

        for (int i=0; i<items.size(); ++i) {
            SearchCountryItem item = items.get(i);
            if (item.getCountryName().replace(" ", "").contains(word)) {
                filteredItems.add(item);
            }
        }

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_search_country, parent, false);

        return new ViewHolder(itemView, filteredItems, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItem(filteredItems.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView countryFlag;
        private TextView countryName;

        ViewHolder(@NonNull final View itemView, final ArrayList<SearchCountryItem> items, final Context context) {
            super(itemView);

            this.countryFlag = itemView.findViewById(R.id.country_flag);
            this.countryName = itemView.findViewById(R.id.country_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Log", "items size = " + items.size() + ", position = " + getAdapterPosition());
                    SearchCountryItem item = items.get(getAdapterPosition());

                    Toast.makeText(context, "나라 = " + item.getCountryName(), Toast.LENGTH_SHORT).show();
                }
            });

        }

        void setItem(SearchCountryItem item) {
            this.countryFlag.setText(item.getCountryFlag());
            this.countryName.setText(item.getCountryName());
        }
    }
}
