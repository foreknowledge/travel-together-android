package com.mungziapp.traveltogether.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.mungziapp.traveltogether.activity.DetailActivity;
import com.mungziapp.traveltogether.activity.EditTravelActivity;
import com.mungziapp.traveltogether.adapter.TravelRecyclerAdapter;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.app.TokenManager;
import com.mungziapp.traveltogether.app.helper.DatabaseHelper;
import com.mungziapp.traveltogether.app.helper.RequestHelper;
import com.mungziapp.traveltogether.interfaces.ActivityCallback;
import com.mungziapp.traveltogether.interfaces.OnItemClickListener;
import com.mungziapp.traveltogether.interfaces.OnResponseListener;
import com.mungziapp.traveltogether.model.data.TravelData;

import java.util.HashMap;
import java.util.Map;

public class TravelFragment extends Fragment {
	private static final String TAG = "TravelFragment ::";

	private ActivityCallback.MainCallback callback;
	private TravelRecyclerAdapter recyclerAdapter;
	private boolean reverseOrder = false;

	private View rootView;

	public TravelFragment() { }

	@Override
	public void onAttach(@NonNull Context context) {
		super.onAttach(context);

		if (context instanceof ActivityCallback)
			callback = (ActivityCallback.MainCallback)context;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_travels, container, false);

		Bundle bundle = getArguments();
		if (bundle != null)
			reverseOrder = bundle.getBoolean("reverseOrder");

		init();

		return rootView;
	}

	private void init() {
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
		linearLayoutManager.setReverseLayout(reverseOrder);
		linearLayoutManager.setStackFromEnd(reverseOrder);

		recyclerAdapter = new TravelRecyclerAdapter(getContext());
		recyclerAdapter.setClickListener(makeItemClickListener());

		RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(linearLayoutManager);
		recyclerView.setAdapter(recyclerAdapter);
	}

	private void setNoticeTextVisibility() {
		if (recyclerAdapter.getItemCount() != 0) {
			TextView travelNotice = rootView.findViewById(R.id.travel_notice);
			travelNotice.setVisibility(View.INVISIBLE);
		}
	}

	public void addItem(TravelData travelData) {
		recyclerAdapter.addItem(travelData);

		recyclerAdapter.notifyDataSetChanged();
		setNoticeTextVisibility();
	}

	private OnItemClickListener makeItemClickListener() {
		return new OnItemClickListener() {
			private String[] options = getResources().getStringArray(R.array.option_travel);
			private String travelId;

			final AlertDialog deleteDialog = new AlertDialog.Builder(getContext())
					.setMessage(getString(R.string.delete_message))
					.setPositiveButton(getString(R.string.btn_ok_text)
							, new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									RequestHelper.getInstance().onSendPostRequest(RequestHelper.HOST + "/travel-rooms/" + travelId + "/leave",
											new OnResponseListener.OnPOSTListener.OnStringListener() {
												@Override
												public void onResponse(String response) {
													DatabaseHelper.deleteTravelData(travelId);

													recyclerAdapter.clearItems();
													callback.refreshAdapterItems();
												}

												@Override
												public Map<String, String> getHeaders() {
													Map<String, String> headers = new HashMap<>();
													headers.put("Authorization", TokenManager.getInstance().getAuthorization());

													return headers;
												}

												@Override
												public void onErrorResponse(VolleyError error) {
													RequestHelper.processError(error, TAG);
												}
											});
								}
							})
					.setNegativeButton(getString(R.string.btn_cancel_text)
							, new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									dialog.dismiss();
								}
							}).create();

			@Override
			public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
				TravelData item = recyclerAdapter.getItem(position);

				Intent intent = new Intent(getContext(), DetailActivity.class);
				intent.putExtra("id", item.getId());

				startActivity(intent);
			}

			@Override
			public Boolean onItemLongClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
				final TravelData item = recyclerAdapter.getItem(position);
				travelId = item.getId();

				new AlertDialog.Builder(getContext())
						.setTitle(item.getName())
						.setItems(options, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialogInterface, int i) {
								switch (i) {
									case 0:
										Intent intent = new Intent(getContext(), EditTravelActivity.class);
										intent.putExtra("travel_id", travelId);
										startActivity(intent);
										break;
									case 1:
										deleteDialog.show();
										break;
								}
							}
						}).show();

				return true;
			}
		};
	}
}
