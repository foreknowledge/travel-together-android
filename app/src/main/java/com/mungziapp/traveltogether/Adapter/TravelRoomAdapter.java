package com.mungziapp.traveltogether.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.Interface.OnItemClickListener;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.RoomItem;

import java.util.ArrayList;

public class TravelRoomAdapter extends RecyclerView.Adapter<TravelRoomAdapter.ViewHolder>
                                implements OnItemClickListener{
    private Context context;
    private ArrayList<RoomItem> items = new ArrayList<>();
    private OnItemClickListener listener;

    public TravelRoomAdapter(Context context) { this.context = context; }
    public void addItem(RoomItem item) { items.add(item); }
    public RoomItem getItem(int position) { return items.get(position); }

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
        View itemView = inflater.inflate(R.layout.room_item_fragment, parent, false);

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
        FrameLayout roomLayout;
        TextView roomTitle;
        TextView roomDuration;
        TextView roomMembers;


        ViewHolder(@NonNull final View itemView, final OnItemClickListener listener) {
            super(itemView);

            roomLayout = itemView.findViewById(R.id.room_layout);
            roomTitle = itemView.findViewById(R.id.room_title);
            roomDuration = itemView.findViewById(R.id.room_duration);
            roomMembers = itemView.findViewById(R.id.room_members);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, view, getAdapterPosition());
                    }
                }
            });

        }

        void setItem(RoomItem item) {
            roomLayout.setBackgroundResource(item.getImgResId());
            roomTitle.setText(item.getRoomTitle());
            roomDuration.setText(item.getRoomDuration());
            String numOfRoomMember = Integer.toString(item.getRoomMembers());
            roomMembers.setText(numOfRoomMember);
        }
    }
}
