package com.mungziapp.traveltogether.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.RoomItem;

import java.util.ArrayList;

public class TravelRoomAdapter extends RecyclerView.Adapter<TravelRoomAdapter.ViewHolder> {
    private Context context;
    private ArrayList<RoomItem> items = new ArrayList<>();

    public TravelRoomAdapter(Context context) { this.context = context; }
    public void addItem(RoomItem item) { items.add(item); }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.room_item_fragment, parent, false);

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

    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout roomLayout;
        TextView roomTitle;
        TextView roomDuration;
        TextView roomMembers;


        ViewHolder(@NonNull final View itemView, final ArrayList<RoomItem> items, final Context context) {
            super(itemView);

            roomLayout = itemView.findViewById(R.id.room_layout);
            roomTitle = itemView.findViewById(R.id.room_title);
            roomDuration = itemView.findViewById(R.id.room_duration);
            roomMembers = itemView.findViewById(R.id.room_members);

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
