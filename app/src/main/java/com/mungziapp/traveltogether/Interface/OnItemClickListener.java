package com.mungziapp.traveltogether.Interface;

import android.view.View;

import com.mungziapp.traveltogether.Adapter.TravelsAdapter;

public interface OnItemClickListener {
    void onItemClick(TravelsAdapter.ViewHolder viewHolder, View view, int position);
}
