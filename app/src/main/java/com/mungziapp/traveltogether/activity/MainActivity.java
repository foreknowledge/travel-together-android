package com.mungziapp.traveltogether.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.VolleyError;
import com.google.android.material.tabs.TabLayout;
import com.mungziapp.traveltogether.ServerService;
import com.mungziapp.traveltogether.app.ConnectionStatus;
import com.mungziapp.traveltogether.app.DateHelper;
import com.mungziapp.traveltogether.app.TokenManager;
import com.mungziapp.traveltogether.app.helper.JsonHelper;
import com.mungziapp.traveltogether.app.helper.RequestHelper;
import com.mungziapp.traveltogether.interfaces.ActivityCallback;
import com.mungziapp.traveltogether.adapter.MainPagerAdapter;
import com.mungziapp.traveltogether.app.helper.DatabaseHelper;
import com.mungziapp.traveltogether.interfaces.OnResponseListener;
import com.mungziapp.traveltogether.model.data.TravelData;
import com.mungziapp.traveltogether.fragment.TravelFragment;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.model.response.TravelRoom;
import com.mungziapp.traveltogether.model.table.TravelTable;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.DAYS;

public class MainActivity extends BaseActivity implements AutoPermissionsListener, ActivityCallback.MainCallback {
	private static final String TAG = "MainActivity :: ";
	private static final int PERMISSION_CODE = 101;
	private static final int REFRESH_CODE = 102;

	private FragmentManager fm;
	private ViewPager outerViewPager;

	private TravelFragment oncommingTravels;
	private TravelFragment lastTravels;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		startService(new Intent(MainActivity.this, ServerService.class));

		fm = getSupportFragmentManager();

		init();
		refreshAdapterItems();

		AutoPermissions.Companion.loadAllPermissions(this, PERMISSION_CODE);
	}

	private void init() {
		// init View Pager and Adapter
		outerViewPager = findViewById(R.id.outer_view_pager);

		oncommingTravels = new TravelFragment();
		lastTravels = new TravelFragment(true);

		MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(fm);
		mainPagerAdapter.addItem(oncommingTravels);
		mainPagerAdapter.addItem(lastTravels);

		mainPagerAdapter.notifyDataSetChanged();

		outerViewPager.setOffscreenPageLimit(mainPagerAdapter.getCount());
		outerViewPager.setAdapter(mainPagerAdapter);

		// init tab bar
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

		// init buttons
		Button btnSearch = findViewById(R.id.btn_search);
		btnSearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(intent);
			}
		});

		Button btnAddTravel = findViewById(R.id.btn_add_travel);
		btnAddTravel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivityForResult(new Intent(getApplicationContext(), AddTravelActivity.class), REFRESH_CODE);
			}
		});

		Button btnGoSettings = findViewById(R.id.btn_go_settings);
		btnGoSettings.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
			}
		});
	}

	@Override
	public void refreshAdapterItems() {
		if (ConnectionStatus.getConnected()) {
			addItemsInNetwork();
			return;
		}
		addItemsInDatabase();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == REFRESH_CODE) refreshAdapterItems();
	}

	private void addItemsInNetwork() {
		RequestHelper.getInstance().onSendJsonArrayRequest(RequestHelper.HOST + "/travel-rooms",
				new OnResponseListener.OnJsonArrayListener() {
					@Override
					public void onResponse(JSONArray response) {
						List<TravelData> travelData = new ArrayList<>();

						try {
							for (int i = 0; i < response.length(); i++) {
								TravelRoom travelRoom = JsonHelper.gson.fromJson(response.getJSONObject(i).toString(), TravelRoom.class);
								travelData.add(TravelData.toTravelData(travelRoom));
							}

							addTravelItems(travelData);

							for (TravelData data : travelData)
								DatabaseHelper.insertTravelData(data);
							Log.d(TAG, "travelRoom 데이터 업데이트.");

						} catch (JSONException e) { Log.e(TAG, "json exception = " + e.getMessage()); }
					}

					@Override
					public Map<String, String> getHeaders() {
						Map<String, String> headers = new HashMap<>();
						headers.put("Authorization", TokenManager.getInstance().getAuthorization());
						Log.d(TAG, "request headers = " + headers.toString());

						return headers;
					}

					@Override
					public void onErrorResponse(VolleyError error) {
						RequestHelper.processError(error, TAG);
					}
				});
	}

	private void addItemsInDatabase() {
		List<TravelData> travelData = new ArrayList<>();

		Cursor cursor = DatabaseHelper.database.rawQuery(TravelTable.SELECT_QUERY, null);
		int numOfRecords = cursor.getCount();
		Log.d(TAG, "레코드 개수: " + numOfRecords);

		for (int i = 0; i < numOfRecords; ++i) {
			cursor.moveToNext();

			String id = cursor.getString(cursor.getColumnIndex("id"));
			String title = cursor.getString(cursor.getColumnIndex("name"));
			LocalDate startDate = DateHelper.stringISOToLocalDate(cursor.getString(cursor.getColumnIndex("start_date")));
			LocalDate endDate = DateHelper.stringISOToLocalDate(cursor.getString(cursor.getColumnIndex("end_date")));
			String countryCodes = cursor.getString(cursor.getColumnIndex("country_codes"));
			String coverImgPath = cursor.getString(cursor.getColumnIndex("cover_img_path"));
			int numOfMembers = cursor.getInt(cursor.getColumnIndex("members"));

			travelData.add(new TravelData(id, title, startDate, endDate, countryCodes, coverImgPath, numOfMembers));
		}

		cursor.close();
		addTravelItems(travelData);
	}

	private void addTravelItems(List<TravelData> travelData) {
		for (TravelData data : travelData) {
			LocalDate endDate = data.getEndDate();

			if (DAYS.between(LocalDate.now(), endDate) >= 0)
				oncommingTravels.addItem(data);
			else
				lastTravels.addItem(data);
		}
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
