package com.mungziapp.traveltogether.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mungziapp.traveltogether.OnItemClickListener;
import com.mungziapp.traveltogether.adapter.TravelsRecyclerAdapter;
import com.mungziapp.traveltogether.adapter.OuterPagerAdapter;
import com.mungziapp.traveltogether.app.DatabaseManager;
import com.mungziapp.traveltogether.data.TravelData;
import com.mungziapp.traveltogether.fragment.TravelsFragment;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.table.TravelTable;

public class MainActivity extends BaseActivity {
	private ViewPager outerViewPager;
	private static final String TAG = "MainActivity :: ";

	private FragmentManager fm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		fm = getSupportFragmentManager();

		setAdapters();
		setTabBar();
		setAddTravelRoomButton();
		setSettingsButton();
		setSearchButton();
	}

	private void setAdapters() {
		TravelsRecyclerAdapter oncommingAdapter = new TravelsRecyclerAdapter(getApplicationContext());
		oncommingAdapter.setClickListener(makeItemClickListener(oncommingAdapter));

		TravelsRecyclerAdapter lastTravelAdapter = new TravelsRecyclerAdapter(getApplicationContext());
		lastTravelAdapter.setClickListener(makeItemClickListener(lastTravelAdapter));

		setAdapterItems(oncommingAdapter, lastTravelAdapter);

		TravelsFragment oncommingTravels = new TravelsFragment(oncommingAdapter);
		TravelsFragment lastTravels = new TravelsFragment(lastTravelAdapter);

		OuterPagerAdapter outerPagerAdapter = new OuterPagerAdapter(fm);
		outerPagerAdapter.addItem(oncommingTravels);
		outerPagerAdapter.addItem(lastTravels);

		outerPagerAdapter.notifyDataSetChanged();

		outerViewPager = findViewById(R.id.outer_view_pager);
		outerViewPager.setOffscreenPageLimit(outerPagerAdapter.getCount());
		outerViewPager.setAdapter(outerPagerAdapter);
	}

	private void setTabBar() {
		TabLayout tabLayout = findViewById(R.id.tabLayout);
		tabLayout.setupWithViewPager(outerViewPager);

		tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				outerViewPager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {
			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {
			}
		});
	}

	private void setSearchButton() {
		Button btnSearch = findViewById(R.id.btn_search);
		btnSearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(intent);
			}
		});
	}

	private void setAddTravelRoomButton() {
		Button btnAddTravelRoom = findViewById(R.id.btn_add_travel_room);
		btnAddTravelRoom.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), AddTravelActivity.class);
				startActivity(intent);
			}
		});
	}

	private void setSettingsButton() {
		Button btnGoSettings = findViewById(R.id.btn_go_settings);
		btnGoSettings.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
				startActivity(intent);
			}
		});
	}

	private void setAdapterItems(TravelsRecyclerAdapter oncommingAdapter, TravelsRecyclerAdapter lastTravelAdapter) {
		Cursor cursor = DatabaseManager.database.rawQuery(TravelTable.SELECT_QUERY, null);
		int numOfRecords = cursor.getCount();
		Log.d(TAG, "레코드 개수: " + numOfRecords);

		for (int i = 0; i < numOfRecords; ++i) {
			cursor.moveToNext();

			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String title = cursor.getString(cursor.getColumnIndex("name"));
			String startDate = cursor.getString(cursor.getColumnIndex("start_date"));
			String endDate = cursor.getString(cursor.getColumnIndex("end_date"));
			String countryCodes = cursor.getString(cursor.getColumnIndex("country_codes"));
			int thumb = cursor.getInt(cursor.getColumnIndex("thumb"));
			int numOfMembers = cursor.getInt(cursor.getColumnIndex("members"));

			if (id > 7)
				lastTravelAdapter.addItem(new TravelData(id, title, startDate, endDate, countryCodes, thumb, numOfMembers));
			else
				oncommingAdapter.addItem(new TravelData(id, title, startDate, endDate, countryCodes, thumb, numOfMembers));
		}

		oncommingAdapter.notifyDataSetChanged();
		lastTravelAdapter.notifyDataSetChanged();

		cursor.close();
	}

	private OnItemClickListener makeItemClickListener(final TravelsRecyclerAdapter adapter) {
		return new OnItemClickListener() {
			private String[] options = getResources().getStringArray(R.array.option_travel);

			final AlertDialog deleteDialog = new AlertDialog.Builder(MainActivity.this)
					.setMessage(getString(R.string.delete_message))
					.setPositiveButton(getString(R.string.btn_ok_text)
							, new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									Toast.makeText(MainActivity.this, "여행방 사라짐.", Toast.LENGTH_SHORT).show();
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
				TravelData item = adapter.getItem(position);

				Intent intent = new Intent(MainActivity.this, DetailActivity.class);
				intent.putExtra("id", item.getId());

				startActivity(intent);
			}

			@Override
			public Boolean onItemLongClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
				final TravelData item = adapter.getItem(position);

				AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
						.setTitle(item.getName())
						.setItems(options, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialogInterface, int i) {
								switch (i) {
									case 0:
										Intent intent = new Intent(MainActivity.this, EditTravelActivity.class);
										intent.putExtra("travel_id", item.getId());
										startActivity(intent);
										break;
									case 1:
										deleteDialog.show();
										break;
								}
							}
						}).create();

				dialog.show();

				return true;
			}
		};
	}
}
