package com.mungziapp.traveltogether.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.Interface.OnItemClickListener;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.TravelItem;

import java.util.ArrayList;

public class TravelsRecyclerAdapter extends RecyclerView.Adapter<TravelsRecyclerAdapter.ViewHolder>
                                implements OnItemClickListener{
    private Context context;
    private ArrayList<TravelItem> items = new ArrayList<>();
    private OnItemClickListener listener;

    public TravelsRecyclerAdapter(Context context) { this.context = context; }
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
        View itemView = inflater.inflate(R.layout.fragment_travel_item, parent, false);

        return new ViewHolder(itemView, context, this);
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
        private FrameLayout travelLayout;
        private TextView travelTitle;
        private TextView travelDuration;
        private TextView numOfTravelMembers;
        private TextView travelDday;
        private RecyclerView countryRecyclerView;
        private RecyclerView memberRecyclerView;

        private Context context;

        ViewHolder(@NonNull final View itemView, Context context, final OnItemClickListener listener) {
            super(itemView);

            this.context = context;

            this.travelLayout = itemView.findViewById(R.id.travel_layout);
            this.travelTitle = itemView.findViewById(R.id.travel_title);
            this.travelDuration = itemView.findViewById(R.id.travel_duration);
            this.numOfTravelMembers = itemView.findViewById(R.id.travel_members);
            this.travelDday = itemView.findViewById(R.id.travel_d_day);

            this.countryRecyclerView = itemView.findViewById(R.id.country_recycler_view);
            countryRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

            this.memberRecyclerView = itemView.findViewById(R.id.member_recycler_view);
            memberRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

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
            this.travelLayout.setBackgroundResource(item.getImgResId());
            this.travelTitle.setText(item.getTravelTitle());

            String strDuration = item.getTravelStartDate() + " ~ " + item.getTravelEndDate() + " (N일간)";
            this.travelDuration.setText(strDuration);

            String numOfTravelMembers = Integer.toString(item.getTravelMembers().size());
            this.numOfTravelMembers.setText(numOfTravelMembers);

            travelDday.setText("D - N");

            setRecyclerViews(item);
        }

        private void setRecyclerViews(TravelItem item) {
            ArrayList<String> countries = item.getTravelCountries();
            CountryAdapter countryAdapter = new CountryAdapter(context);
            for (String country: countries) countryAdapter.addItem(country);
            countryRecyclerView.setAdapter(countryAdapter);

            ArrayList<Integer> members = item.getTravelMembers();
            MemberAdapter memberAdapter = new MemberAdapter(context);
            for (int member: members) memberAdapter.addItem(member);
            memberRecyclerView.setAdapter(memberAdapter);
        }
    }
}
