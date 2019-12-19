package com.mungziapp.traveltogether.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mungziapp.traveltogether.adapter.TravelCountryAdapter;
import com.mungziapp.traveltogether.adapter.TravelMemberAdapter;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.app.DatabaseManager;
import com.mungziapp.traveltogether.data.FragmentType;
import com.mungziapp.traveltogether.interfaces.OnItemClickListener;
import com.mungziapp.traveltogether.table.TravelTable;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mungziapp.traveltogether.data.FragmentType.*;

public class DetailActivity extends AppCompatActivity {
	private int travelId;
	private String travelName;
	private String travelStartDate;
	private String travelEndDate;
	private ArrayList<String> travelCountryCodes = new ArrayList<>();
	private ArrayList<Integer> travelMemberProfiles = new ArrayList<>();
	private int travelCover;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		// Detail Fragment 초기화
		Intent intent = getIntent();
		if (intent != null) {
			setDataFromDB(intent.getIntExtra("id", 0));
			setTravelInfo();
			setButtons();
		}
	}

	private void setDataFromDB(int id) {
		Cursor cursor = DatabaseManager.database.rawQuery(TravelTable.SELECT_QUERY + " WHERE id = " + id, null);
		cursor.moveToNext();

		this.travelId = id;
		this.travelName = cursor.getString(cursor.getColumnIndex("name"));
		this.travelStartDate = cursor.getString(cursor.getColumnIndex("start_date"));
		this.travelEndDate = cursor.getString(cursor.getColumnIndex("end_date"));
		String countryCodes = cursor.getString(cursor.getColumnIndex("country_codes"));
		this.travelCover = cursor.getInt(cursor.getColumnIndex("cover"));
		int numOfMembers = cursor.getInt(cursor.getColumnIndex("members"));

		if (countryCodes != null)
			travelCountryCodes.addAll(Arrays.asList(countryCodes.split(",")));

		for (int j = 0; j < numOfMembers; ++j)
			this.travelMemberProfiles.add(R.drawable.usr_profile_img);

		cursor.close();
	}

	private void setTravelInfo() {
		// 여행 제목 설정
		TextView travelName = findViewById(R.id.travel_name);
		travelName.setText(this.travelName);

		if (travelStartDate != null && travelEndDate != null) {
			// 여행 기간 설정
			TextView travelDuration = findViewById(R.id.travel_duration);
			String strDuration = this.travelStartDate + " ~ " + this.travelEndDate + " (N일간)";
			travelDuration.setText(strDuration);

			// 여행 D-Day 설정
			TextView travelDDay = findViewById(R.id.travel_d_day);
			travelDDay.setText("D - N");
		}

		// 여행지 설정
		RecyclerView countryRecyclerView = findViewById(R.id.country_recycler_view);
		countryRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

		TravelCountryAdapter travelCountryAdapter = new TravelCountryAdapter(getApplicationContext());
		for (String countryCode : travelCountryCodes) travelCountryAdapter.addItem(countryCode);
		countryRecyclerView.setAdapter(travelCountryAdapter);

		// 여행 멤버 설정
		RecyclerView memberRecyclerView = findViewById(R.id.member_recycler_view);
		memberRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

		TravelMemberAdapter travelMemberAdapter = new TravelMemberAdapter(getApplicationContext());
		for (Integer memberProfile : travelMemberProfiles) travelMemberAdapter.addItem(memberProfile);
		travelMemberAdapter.setClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
				startCheckMemberActivity();
			}

			@Override
			public Boolean onItemLongClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
				return null;
			}
		});

		memberRecyclerView.setAdapter(travelMemberAdapter);

		// 여행 커버 이미지 설정
		if (travelCover != 0) {
			FrameLayout travelLayout = findViewById(R.id.travel_layout);
			travelLayout.setBackgroundResource(this.travelCover);
		}
	}

	private void setButtons() {
		Button btnGoBefore = findViewById(R.id.btn_go_before);
		btnGoBefore.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});

		Button btnMore = findViewById(R.id.btn_more);
		btnMore.setOnClickListener(new View.OnClickListener() {
			private String[] options = getResources().getStringArray(R.array.option_travel);

			final AlertDialog deleteDialog = new AlertDialog.Builder(DetailActivity.this)
					.setMessage(getString(R.string.delete_message))
					.setPositiveButton(getString(R.string.btn_ok_text)
							, new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									finish();
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
			public void onClick(View view) {
				new AlertDialog.Builder(DetailActivity.this)
						.setTitle(travelName)
						.setItems(options, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialogInterface, int i) {
								switch (i) {
									case 0:
										Intent intent = new Intent(DetailActivity.this, EditTravelActivity.class);
										intent.putExtra("travel_id", travelId);
										startActivity(intent);
										break;
									case 1:
										deleteDialog.show();
										break;
								}
							}
						}).show();
			}
		});

		LinearLayout notice = findViewById(R.id.btn_notice);
		notice.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startTravelActivity(NOTICE);
			}
		});

		LinearLayout supplies = findViewById(R.id.btn_supplies);
		supplies.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startTravelActivity(SUPPLIES);
			}
		});

		LinearLayout schedule = findViewById(R.id.btn_schedule);
		schedule.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startTravelActivity(SCHEDULE);
			}
		});

		LinearLayout account = findViewById(R.id.btn_account);
		account.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startTravelActivity(ACCOUNT);
			}
		});

		LinearLayout diary = findViewById(R.id.btn_diary);
		diary.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startTravelActivity(DIARY);
			}
		});

		TextView textMember = findViewById(R.id.txt_member);
		textMember.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startCheckMemberActivity();
			}
		});
	}

	private void startTravelActivity(FragmentType type) {
		Intent intent = new Intent(getApplicationContext(), TravelActivity.class);
		intent.putExtra("caller", type);
		startActivity(intent);
	}

	private void startCheckMemberActivity() {
		Intent intent = new Intent(DetailActivity.this, CheckMemberActivity.class);
		intent.putExtra("travel_id", travelId);
		startActivity(intent);
	}
}
