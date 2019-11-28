package com.mungziapp.traveltogether.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.Interface.OnItemClickListener;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.TravelItem;

import java.util.ArrayList;

public class TravelsAdapter extends RecyclerView.Adapter<TravelsAdapter.ViewHolder>
                                implements OnItemClickListener{
    private Context context;
    private ArrayList<TravelItem> items = new ArrayList<>();
    private OnItemClickListener listener;

    public TravelsAdapter(Context context) { this.context = context; }
    public void addItem(TravelItem item) { items.add(item); }
    public TravelItem getItem(int position) { return items.get(position); }

    public void setOnClickListener(OnItemClickListener listener) { this.listener = listener; }

    @Override
    public void onItemClick(ViewHolder viewHolder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(viewHolder, view, position);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.travel_item_fragment, parent, false);

        return new ViewHolder(itemView, this);
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
        FrameLayout travelLayout;
        TextView travelTitle;
        TextView travelDuration;
        TextView travelMembers;
        TextView travelDday;

        ViewHolder(@NonNull final View itemView, final OnItemClickListener listener) {
            super(itemView);

            travelLayout = itemView.findViewById(R.id.travel_layout);
            travelTitle = itemView.findViewById(R.id.travel_title);
            travelDuration = itemView.findViewById(R.id.travel_duration);
            travelMembers = itemView.findViewById(R.id.travel_members);
            travelDday = itemView.findViewById(R.id.travel_d_day);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, view, getAdapterPosition());
                    }
                }
            });

        }

        void setItem(TravelItem item) {
            travelLayout.setBackgroundResource(item.getImgResId());
            travelTitle.setText(item.getTravelTitle());

            String strDuration = item.getTravelStartDate() + " ~ " + item.getTravelEndDate() + " (N일간)";
            travelDuration.setText(strDuration);

            String numOfTravelMember = Integer.toString(item.getTravelMembers());
            travelMembers.setText(numOfTravelMember);

            travelDday.setText("D - N");
        }
    }
}
