package com.mungziapp.traveltogether.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;
import com.mungziapp.traveltogether.app.ConnectionStatus;
import com.mungziapp.traveltogether.app.TokenManager;
import com.mungziapp.traveltogether.app.TravelInfoSetter;
import com.mungziapp.traveltogether.app.helper.JsonHelper;
import com.mungziapp.traveltogether.app.helper.RequestHelper;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.interfaces.OnResponseListener;
import com.mungziapp.traveltogether.model.response.NewTravelRoom;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddTravelActivity extends AppCompatActivity {
	private static final String TAG = "AddTravelActivity ::";
	private TravelInfoSetter infoSetter;

	private Button btnStartDate;
	private Button btnEndDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_travel);

		infoSetter = new TravelInfoSetter(AddTravelActivity.this, TAG);
		setViewsAndActions();
	}

	private void setViewsAndActions() {
		// set views
		EditText editTitle = findViewById(R.id.edit_travel_title);
		EditText editSearch = findViewById(R.id.search_country);
		btnStartDate = findViewById(R.id.btn_pick_start_date);
		btnEndDate = findViewById(R.id.btn_pick_end_date);
		ChipGroup chipGroup = findViewById(R.id.chip_group);

		infoSetter.setViews(editTitle, editSearch, btnStartDate, btnEndDate, chipGroup);

		infoSetter.setTitleText();

		RecyclerView countrySearchRecycler = findViewById(R.id.country_search_recycler);
		Button btnClear = findViewById(R.id.btn_clear);
		FrameLayout btnClearOut = findViewById(R.id.btn_clear_out);
		infoSetter.setSearchBar(countrySearchRecycler, btnClear, btnClearOut);

		ScrollView scrollView = findViewById(R.id.scroll_view);
		infoSetter.setCountryList(countrySearchRecycler, scrollView);

		infoSetter.setDateButtons();

		// set save and cancel buttons
		setSaveAndCancelButtons(editTitle);
	}

	private boolean isFilledDates() {
		return (btnStartDate.getText().toString().split("\\.").length == 3)
				&& (btnEndDate.getText().toString().split("\\.").length == 3);
	}

	private void setSaveAndCancelButtons(final EditText editTitle) {
		Button btnSave = findViewById(R.id.btn_save);
		btnSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				requestToServer(editTitle, view);
			}
		});

		Button btnCancel = findViewById(R.id.btn_cancel);
		btnCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}

	private void requestToServer(EditText editTitle, View view) {
		if (ConnectionStatus.getConnected()) {
			if (editTitle.getText().toString().equals(""))
				Snackbar.make(view, "제목은 필수 입력 사항입니다.", Snackbar.LENGTH_SHORT).show();
			else if (!isFilledDates())
				Snackbar.make(view, "여행 기간은 필수 입력 사항입니다.", Snackbar.LENGTH_SHORT).show();
			else
				infoSetter.requestToServer(Request.Method.POST,RequestHelper.HOST + "/travel-rooms",
						new OnResponseListener.OnJsonObjectListener() {
							@Override
							public void onResponse(JSONObject response) {
								NewTravelRoom newTravelRoom = JsonHelper.gson.fromJson(response.toString(), NewTravelRoom.class);
								infoSetter.saveToDatabase(newTravelRoom);

								finish();
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
		} else
			Snackbar.make(view, "네트워크가 연결되어 있지 않습니다.", Snackbar.LENGTH_SHORT).show();
	}
}