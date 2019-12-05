package com.mungziapp.traveltogether.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.Activity.DetailActivity;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.SearchItem;

import java.util.ArrayList;

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SearchItem> items = new ArrayList<>();

    public SearchRecyclerAdapter(Context context) { this.context = context; }
    public void addItem(SearchItem item) { items.add(item); }
    public SearchItem getItem(int position) { return items.get(position); }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.fragment_search_item, parent, false);

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

        ViewHolder(@NonNull final View itemView, final ArrayList<SearchItem> items, final Context context) {
            super(itemView);

            this.travelThumbnail = itemView.findViewById(R.id.travel_thumbnail);
            this.travelTitle = itemView.findViewById(R.id.travel_title);
            this.travelDuration = itemView.findViewById(R.id.travel_duration);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SearchItem item = items.get(getAdapterPosition());

                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("travelTitle", item.getTravelTitle());
                    intent.putExtra("travelStartDate", item.getTravelStartDate());
                    intent.putExtra("travelEndDate", item.getTravelEndDate());
                    intent.putExtra("travelImg", item.getImgResId());
                    context.startActivity(intent);
                }
            });

        }

        void setItem(SearchItem item) {
            this.travelThumbnail.setBackgroundResource(item.getImgResId());
            this.travelTitle.setText(item.getTravelTitle());

            String strDuration = "(" + item.getTravelStartDate() + " ~ " + item.getTravelEndDate() + ")";
            this.travelDuration.setText(strDuration);
        }
    }
}
