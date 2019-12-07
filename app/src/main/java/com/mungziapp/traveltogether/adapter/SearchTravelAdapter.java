package com.mungziapp.traveltogether.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.activity.DetailActivity;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.app.DatabaseManager;
import com.mungziapp.traveltogether.item.SearchTravelItem;
import com.mungziapp.traveltogether.table.TravelRoomTable;

import java.util.ArrayList;

public class SearchTravelAdapter extends RecyclerView.Adapter<SearchTravelAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SearchTravelItem> items = new ArrayList<>();
    private ArrayList<SearchTravelItem> filteredItems = new ArrayList<>();

    public SearchTravelAdapter(Context context) { this.context = context; }

    public void initItem() {
        Cursor cursor = DatabaseManager.database.rawQuery(TravelRoomTable.SELECT_QUERY, null);
        int numOfRecords = cursor.getCount();

        for (int i=0; i<numOfRecords; ++i) {
            cursor.moveToNext();

            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("name"));
            String startDate = cursor.getString(cursor.getColumnIndex("start_date"));
            String endDate = cursor.getString(cursor.getColumnIndex("end_date"));
            int thumb = cursor.getInt(cursor.getColumnIndex("thumb"));

            items.add(new SearchTravelItem(id, title, startDate, endDate, thumb));
        }

        cursor.close();

        notifyDataSetChanged();
    }

    public void searchItem(String word) {
        if (word.equals("")) {
            filteredItems.clear();
            notifyDataSetChanged();
            return;
        }

        ArrayList<SearchTravelItem> searchTravelItems = new ArrayList<>();
        for (int i=0; i<items.size(); ++i) {
            SearchTravelItem item = items.get(i);
            if (item.getTravelTitle().contains(word)) {
                searchTravelItems.add(item);
            }
        }

        filteredItems = searchTravelItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_search_travel, parent, false);

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
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("id", item.getId());

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
