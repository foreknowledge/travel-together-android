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

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.reflect.TypeToken;
import com.mungziapp.traveltogether.ServerService;
import com.mungziapp.traveltogether.app.ConnectionStatus;
import com.mungziapp.traveltogether.app.DateObject;
import com.mungziapp.traveltogether.app.TokenManager;
import com.mungziapp.traveltogether.app.helper.JsonHelper;
import com.mungziapp.traveltogether.app.helper.RequestHelper;
import com.mungziapp.traveltogether.interfaces.OnItemClickListener;
import com.mungziapp.traveltogether.adapter.TravelsRecyclerAdapter;
import com.mungziapp.traveltogether.adapter.MainPagerAdapter;
import com.mungziapp.traveltogether.app.helper.DatabaseHelper;
import com.mungziapp.traveltogether.interfaces.OnResponseListener;
import com.mungziapp.traveltogether.model.data.TravelData;
import com.mungziapp.traveltogether.fragment.TravelsFragment;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.model.response.Country;
import com.mungziapp.traveltogether.model.response.Member;
import com.mungziapp.traveltogether.model.response.TravelRoom;
import com.mungziapp.traveltogether.model.table.TravelTable;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.DAYS;

public class MainActivity extends BaseActivity implements AutoPermissionsListener {
	private ViewPager outerViewPager;
	private static final String TAG = "MainActivity :: ";

	private FragmentManager fm;
	private TravelsRecyclerAdapter oncommingAdapter;
	private TravelsRecyclerAdapter lastTravelAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		startService(new Intent(MainActivity.this, ServerService.class));

		fm = getSupportFragmentManager();

		setAdapters();
		setTabBar();
		setAddTravelButton();
		setSettingsButton();
		setSearchButton();

		AutoPermissions.Companion.loadAllPermissions(this, 101);
	}

	@Override
	protected void onResume() {
		super.onResume();
		setAdapterItems();
	}

	private void setAdapters() {
		oncommingAdapter = new TravelsRecyclerAdapter(getApplicationContext());
		oncommingAdapter.setClickListener(makeItemClickListener(oncommingAdapter));

		lastTravelAdapter = new TravelsRecyclerAdapter(getApplicationContext());
		lastTravelAdapter.setClickListener(makeItemClickListener(lastTravelAdapter));

		setAdapterItems();

		TravelsFragment oncommingTravels = new TravelsFragment(oncommingAdapter);
		TravelsFragment lastTravels = new TravelsFragment(lastTravelAdapter);

		MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(fm);
		mainPagerAdapter.addItem(oncommingTravels);
		mainPagerAdapter.addItem(lastTravels);

		mainPagerAdapter.notifyDataSetChanged();

		outerViewPager = findViewById(R.id.outer_view_pager);
		outerViewPager.setOffscreenPageLimit(mainPagerAdapter.getCount());
		outerViewPager.setAdapter(mainPagerAdapter);
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

	private void setAddTravelButton() {
		Button btnAddTravel = findViewById(R.id.btn_add_travel);
		btnAddTravel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(getApplicationContext(), AddTravelActivity.class));
			}
		});
	}

	private void setSettingsButton() {
		Button btnGoSettings = findViewById(R.id.btn_go_settings);
		btnGoSettings.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
			}
		});
	}

	private void setAdapterItems() {
		oncommingAdapter.clearItems();
		lastTravelAdapter.clearItems();

		if (ConnectionStatus.getConnected())
			addItemsInNetwork();
		else
			addItemsInDatabase();

		oncommingAdapter.notifyDataSetChanged();
		lastTravelAdapter.notifyDataSetChanged();
	}

	private void addItemsInNetwork() {
		RequestHelper.getInstance().onSendGetRequest(RequestHelper.HOST + "/me/travel-rooms",
				new OnResponseListener() {
					@Override
					public void onResponse(String response) {
						try {
							Type listType = new TypeToken<List<TravelRoom>>() {}.getType();
							List<TravelRoom> travelRooms = JsonHelper.gson.fromJson(new JSONObject(response).getJSONArray("result").toString(), listType);

							for (TravelRoom travelRoom : travelRooms) {
								String id = travelRoom.getId();
								String title = travelRoom.getName();
								LocalDate startDate = null, endDate = null;
								if (travelRoom.getStartDate() != null && travelRoom.getEndDate() != null) {
									startDate = DateObject.stringToLocalDate(travelRoom.getStartDate());
									endDate = DateObject.stringToLocalDate(travelRoom.getEndDate());
								}
								List<Country> countries = travelRoom.getCountries();
								List<Member> members = travelRoom.getMembers();
								String coverImgPath = travelRoom.getCoverImagePath();

								StringBuilder countryCodes = new StringBuilder();
								for (Country country: countries) {
									countryCodes.append(country.getCode());
									countryCodes.append(",");
								}

								if (DAYS.between(LocalDate.now(), endDate) < 0)
									lastTravelAdapter.addItem(new TravelData(id, title, startDate, endDate, countryCodes.toString(), coverImgPath, members.size()));
								else
									oncommingAdapter.addItem(new TravelData(id, title, startDate, endDate, countryCodes.toString(), coverImgPath, members.size()));
							}

						} catch (Exception e) { Log.e(TAG, "error message = " + e.getMessage()); }
					}

					@Override
					public void setParams(Map<String, String> params) {

					}

					@Override
					public Map<String, String> getHeaders() {
						Map<String, String> header = new HashMap<>();
						String authorization = TokenManager.TOKEN_TYPE + " " + TokenManager.getInstance().getAccessToken();
						header.put("Authorization", authorization);
						Log.d(TAG, "authorization = " + authorization);

						return header;
					}
				});
	}

	private void addItemsInDatabase() {
		Cursor cursor = DatabaseHelper.database.rawQuery(TravelTable.SELECT_QUERY, null);
		int numOfRecords = cursor.getCount();
		Log.d(TAG, "레코드 개수: " + numOfRecords);

		for (int i = 0; i < numOfRecords; ++i) {
			cursor.moveToNext();

			String id = cursor.getString(cursor.getColumnIndex("id"));
			String title = cursor.getString(cursor.getColumnIndex("name"));
			LocalDate startDate = DateObject.stringToLocalDate(cursor.getString(cursor.getColumnIndex("start_date")));
			LocalDate endDate = DateObject.stringToLocalDate(cursor.getString(cursor.getColumnIndex("end_date")));
			String countryCodes = cursor.getString(cursor.getColumnIndex("country_codes"));
			String coverImgPath = cursor.getString(cursor.getColumnIndex("cover_img_path"));
			int numOfMembers = cursor.getInt(cursor.getColumnIndex("members"));

			if (DAYS.between(LocalDate.now(), endDate) < 0)
				lastTravelAdapter.addItem(new TravelData(id, title, startDate, endDate, countryCodes, coverImgPath, numOfMembers));
			else
				oncommingAdapter.addItem(new TravelData(id, title, startDate, endDate, countryCodes, coverImgPath, numOfMembers));
		}

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

				new AlertDialog.Builder(MainActivity.this)
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
						}).show();

				return true;
			}
		};
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
	}

	// 사용자 응답이 denied 일 때 콜백
	@Override
	public void onDenied(int i, String[] strings) {
		Log.d(TAG, "permission denied : " + strings.length);
	}

	// 사용자 응답이 granted 일 때 콜백
	@Override
	public void onGranted(int i, String[] strings) {
		Log.d(TAG, "permission granted : " + strings.length);
	}
}
