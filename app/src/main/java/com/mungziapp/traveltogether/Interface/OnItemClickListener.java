package com.mungziapp.traveltogether.Interface;

import android.view.View;

import com.mungziapp.traveltogether.Adapter.TravelsRecyclerAdapter;

public interface OnItemClickListener {
    void onItemClick(TravelsRecyclerAdapter.ViewHolder viewHolder, View view, int position);
}
