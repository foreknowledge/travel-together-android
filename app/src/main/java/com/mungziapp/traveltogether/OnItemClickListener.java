package com.mungziapp.traveltogether;

import android.view.View;

import com.mungziapp.traveltogether.adapter.SearchCountryAdapter;

public interface OnItemClickListener {
    void onItemClick(SearchCountryAdapter.ViewHolder viewHolder, View view, int position);
}