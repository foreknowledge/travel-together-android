package com.mungziapp.traveltogether;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public interface OnItemClickListener {
	void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position);

	Boolean onItemLongClick(RecyclerView.ViewHolder viewHolder, View view, int position);
}