package com.mungziapp.traveltogether.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.Activity.DetailActivity;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.SearchTravelItem;

import java.util.ArrayList;

public class SearchTravelAdapter extends RecyclerView.Adapter<SearchTravelAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SearchTravelItem> items = new ArrayList<>();

    public SearchTravelAdapter(Context context) { this.context = context; }
    public void addItem(SearchTravelItem item) { items.add(item); }
    public SearchTravelItem getItem(int position) { return items.get(position); }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_search_travel, parent, false);

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
        private ImageView travelThumbnail;
        private TextView travelTitle;
        private TextView travelDuration;

        ViewHolder(@NonNull final View itemView, final ArrayList<SearchTravelItem> items, final Context context) {
            super(itemView);

            this.travelThumbnail = itemView.findViewById(R.id.travel_thumbnail);
            this.travelTitle = itemView.findViewById(R.id.travel_title);
            this.travelDuration = itemView.findViewById(R.id.travel_duration);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SearchTravelItem item = items.get(getAdapterPosition());

                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("travelTitle", item.getTravelTitle());
                    intent.putExtra("travelStartDate", item.getTravelStartDate());
                    intent.putExtra("travelEndDate", item.getTravelEndDate());
                    intent.putExtra("travelImg", item.getImgResId());
                    context.startActivity(intent);
                }
            });

        }

        void setItem(SearchTravelItem item) {
            this.travelThumbnail.setBackgroundResource(item.getImgResId());
            this.travelTitle.setText(item.getTravelTitle());

            String strDuration = "(" + item.getTravelStartDate() + " ~ " + item.getTravelEndDate() + ")";
            this.travelDuration.setText(strDuration);
        }
    }
}
