package com.mungziapp.traveltogether.Interface;

import android.view.View;

import com.mungziapp.traveltogether.Adapter.OuterTravelsAdapter;

public interface OnItemClickListener {
    void onItemClick(OuterTravelsAdapter.ViewHolder viewHolder, View view, int position);
}
