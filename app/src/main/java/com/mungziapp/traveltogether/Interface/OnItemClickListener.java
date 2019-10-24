package com.mungziapp.traveltogether.Interface;

import android.view.View;

import com.mungziapp.traveltogether.Adapter.TravelRoomAdapter;

public interface OnItemClickListener {
    void onItemClick(TravelRoomAdapter.ViewHolder viewHolder, View view, int position);
}
