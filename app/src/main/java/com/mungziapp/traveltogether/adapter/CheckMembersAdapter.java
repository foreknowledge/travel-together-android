package com.mungziapp.traveltogether.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.R;

import java.util.ArrayList;

public class CheckMembersAdapter extends RecyclerView.Adapter<CheckMembersAdapter.ViewHolder> {
	private Context context;
	private ArrayList<Integer> items = new ArrayList<>();
	private View.OnClickListener listener;

	public CheckMembersAdapter(Context context) {
		this.context = context;
	}

	public void initItem(int numOfMembers) {
		for (int i=0; i<numOfMembers; ++i)
			items.add(R.drawable.usr_profile_img);
	}

	public void setOnImageClickListener(View.OnClickListener listener) { this.listener = listener; }

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.item_travel_member, parent, false);

		return new ViewHolder(itemView, listener);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.setItem(items.get(position));
	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		private ImageView profileImage;
		private TextView profileName;
		private TextView profileMessage;

		ViewHolder(@NonNull final View itemView, View.OnClickListener listener) {
			super(itemView);

			this.profileImage = itemView.findViewById(R.id.profile_img);
			this.profileName = itemView.findViewById(R.id.profile_name);
			this.profileMessage = itemView.findViewById(R.id.profile_message);

			profileImage.setOnClickListener(listener);
		}

		void setItem(int image) {
			this.profileImage.setImageResource(image);
			this.profileName.setText("김예지");
			this.profileMessage.setText("(상태 메시지)");
		}
	}
}
