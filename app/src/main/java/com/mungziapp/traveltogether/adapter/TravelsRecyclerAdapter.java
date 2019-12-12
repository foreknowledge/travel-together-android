package com.mungziapp.traveltogether.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.OnItemClickListener;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.item.TravelItem;

import java.util.ArrayList;

public class TravelsRecyclerAdapter extends RecyclerView.Adapter<TravelsRecyclerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<TravelItem> items = new ArrayList<>();

    private OnItemClickListener listener;

    public TravelsRecyclerAdapter(Context context) { this.context = context; }
    public void addItem(TravelItem item) { items.add(item); }
    public TravelItem getItem(int position) { return items.get(position); }

    public void setClickListener(OnItemClickListener listener) { this.listener = listener; }

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
        private TextView travelTitle;
        private TextView travelDuration;
        private TextView numOfTravelMembers;
        private TextView travelDday;
        private RecyclerView countryRecyclerView;

        private Context context;

        ViewHolder(@NonNull final View itemView, final OnItemClickListener listener, final Context context) {
            super(itemView);

            this.context = context;

            this.travelLayout = itemView.findViewById(R.id.travel_layout);
            this.travelTitle = itemView.findViewById(R.id.travel_title);
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

        void setItem(TravelItem item) {
            this.travelLayout.setBackgroundResource(item.getImgResId());
            this.travelTitle.setText(item.getTravelTitle());

            String strDuration = item.getTravelStartDate() + " ~ " + item.getTravelEndDate() + " (N일간)";
            this.travelDuration.setText(strDuration);

            String numOfTravelMembers = Integer.toString(item.getNumOfTravelMembers());
            this.numOfTravelMembers.setText(numOfTravelMembers);

            travelDday.setText("D - N");

            ArrayList<String> countries = item.getTravelCountries();
            TravelCountryAdapter travelCountryAdapter = new TravelCountryAdapter(context);
            for (String country: countries) travelCountryAdapter.addItem(country);
            countryRecyclerView.setAdapter(travelCountryAdapter);
        }
    }
}
