package com.mungziapp.traveltogether.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.item.SearchCountryItem;

import java.util.ArrayList;

public class SearchCountryAdapter extends RecyclerView.Adapter<SearchCountryAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SearchCountryItem> items = new ArrayList<>();

    public SearchCountryAdapter(Context context) { this.context = context; }
    public void addItem(SearchCountryItem item) { items.add(item); }
    public SearchCountryItem getItem(int position) { return items.get(position); }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_search_country, parent, false);

        return new ViewHolder(itemView, items, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView countryFlag;
        private TextView countryName;

        ViewHolder(@NonNull final View itemView, final ArrayList<SearchCountryItem> items, final Context context) {
            super(itemView);

            this.countryFlag = itemView.findViewById(R.id.country_flag);
            this.countryName = itemView.findViewById(R.id.country_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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
