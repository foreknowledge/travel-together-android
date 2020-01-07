package com.mungziapp.traveltogether.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;
import com.mungziapp.traveltogether.app.ConnectionStatus;
import com.mungziapp.traveltogether.app.DateHelper;
import com.mungziapp.traveltogether.app.TokenManager;
import com.mungziapp.traveltogether.app.helper.DatabaseHelper;
import com.mungziapp.traveltogether.app.helper.JsonHelper;
import com.mungziapp.traveltogether.app.helper.RequestHelper;
import com.mungziapp.traveltogether.interfaces.OnItemClickListener;
import com.mungziapp.traveltogether.adapter.SearchCountryAdapter;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.interfaces.OnResponseListener;
import com.mungziapp.traveltogether.model.data.TravelData;
import com.mungziapp.traveltogether.model.item.CountryItem;
import com.mungziapp.traveltogether.model.response.NewTravelRoom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddTravelActivity extends AppCompatActivity {
	private static final String TAG = "AddTravelActivity ::";
	private Button btnStartDate;
	private Button btnEndDate;

	private EditText editSearch;
	private EditText editTitle;
	private ChipGroup chipGroup;
	private RecyclerView countrySearchRecycler;
	private SearchCountryAdapter countryAdapter;
	private InputMethodManager in;

	private boolean isFilledDates = false;
	private List<String> countryCodes = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_travel);

		in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		setSaveAndCancelButtons();
		setTitleText();
		setDateButtons();
		setSearchBar();
		setCountryList();
	}

	private void setSaveAndCancelButtons() {
		Button btnSave = findViewById(R.id.btn_save);
		btnSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (ConnectionStatus.getConnected()) {
					if (editTitle.getText().toString().equals(""))
						Snackbar.make(view, "제목은 필수 입력 사항입니다.", Snackbar.LENGTH_SHORT).show();
					else if (!isFilledDates)
						Snackbar.make(view, "여행 기간은 필수 입력 사항입니다.", Snackbar.LENGTH_SHORT).show();
					else
						requestToServer();
				} else
					Snackbar.make(view, "네트워크가 연결되어 있지 않습니다.", Snackbar.LENGTH_SHORT).show();
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

	private void requestToServer() {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("name", editTitle.getText().toString());

			LocalDate startDate = DateHelper.stringToLocalDate(btnStartDate.getText(), "\\.");
			LocalDate endDate = DateHelper.stringToLocalDate(btnEndDate.getText(), "\\.");

			jsonObject.put("startDate", DateHelper.toISOFormat(startDate));
			jsonObject.put("endDate", DateHelper.toISOFormat(endDate));

			JSONArray countryJson = new JSONArray();
			for (String countryCode : countryCodes)
				countryJson.put(countryCode);
			jsonObject.put("countries", countryJson);
		} catch (JSONException e) { Log.d(TAG, "error message = " + e.getMessage()); }

		RequestHelper.getInstance().onSendPostRequest(RequestHelper.HOST + "/travel-rooms", jsonObject,
				new OnResponseListener.OnPOSTListener.OnJsonObjectListener() {
					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, "json object response = " + response.toString());

						NewTravelRoom newTravelRoom = JsonHelper.gson.fromJson(response.toString(), NewTravelRoom.class);
						saveToDatabase(newTravelRoom);

						finish();
					}

					@Override
					public Map<String, String> getHeaders() {
						Map<String, String> headers = new HashMap<>();
						headers.put("Authorization", TokenManager.getInstance().getAuthorization());
						Log.d(TAG, "authorization = " + TokenManager.getInstance().getAuthorization());

						return headers;
					}

					@Override
					public void onErrorResponse(VolleyError error) {
						RequestHelper.processError(error, TAG);
					}
				});
	}

	private void saveToDatabase(NewTravelRoom newTravelRoom) {
		LocalDate startDate = DateHelper.stringToLocalDate(btnStartDate.getText(), "\\.");
		LocalDate endDate = DateHelper.stringToLocalDate(btnEndDate.getText(), "\\.");

		StringBuilder selected = new StringBuilder();
		for (int i = 0; i < countryCodes.size(); ++i) {
			selected.append(countryCodes.get(i));
			if (i < countryCodes.size() - 1)
				selected.append(",");
		}

		DatabaseHelper.insertTravelData(new TravelData(newTravelRoom.getId()
				, editTitle.getText().toString()
				, startDate
				, endDate
				, countryCodes.toString()
				, newTravelRoom.getCoverImagePath()
				, 1));
	}

	private void setTitleText() {
		editTitle = findViewById(R.id.edit_travel_title);
		editTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View view, boolean b) {
				if (!b)
					if (in != null)
						in.hideSoftInputFromWindow(editSearch.getWindowToken(), 0);
			}
		});
	}

	private void setSearchBar() {
		editSearch = findViewById(R.id.search_country);

		final Button btnClear = findViewById(R.id.btn_clear);
		final FrameLayout btnClearOut = findViewById(R.id.btn_clear_out);

		editSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View view, boolean b) {
				if (b)
					countrySearchRecycler.setVisibility(View.VISIBLE);
				else {
					countrySearchRecycler.setVisibility(View.GONE);

					if (in != null)
						in.hideSoftInputFromWindow(editSearch.getWindowToken(), 0);
				}
			}
		});

		editSearch.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				btnClear.setVisibility(View.VISIBLE);
			}

			@Override
			public void afterTextChanged(Editable editable) {
				if (editSearch.getText().toString().equals("")) {
					btnClear.setVisibility(View.INVISIBLE);
				}

				countryAdapter.searchItem(editable.toString());
			}
		});

		editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
				if (i == EditorInfo.IME_ACTION_SEARCH) {
					performSearch(textView);
					return true;
				}
				return false;
			}
		});

		btnClear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				editSearch.setText("");
			}
		});
		btnClearOut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				editSearch.setText("");
			}
		});
	}

	private void performSearch(View view) {
		if (editSearch.getText().toString().equals("")) {
			Snackbar.make(view, "검색어를 입력 해 주세요.", Snackbar.LENGTH_SHORT).show();
			return;
		}

		editSearch.clearFocus();
	}

	private void setCountryList() {
		chipGroup = findViewById(R.id.chip_group);

		final ScrollView scrollView = findViewById(R.id.scroll_view);

		countrySearchRecycler = findViewById(R.id.country_search_recycler);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
		countrySearchRecycler.setLayoutManager(layoutManager);

		countryAdapter = new SearchCountryAdapter(getApplicationContext());
		countryAdapter.initItem();
		countryAdapter.setClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
				CountryItem item = countryAdapter.getSearchItem(position);

				chipGroup.addView(makeChip(item));
				countryCodes.add(item.getCountryCode());
				scrollView.post(new Runnable() {
					@Override
					public void run() {
						scrollView.fullScroll(ScrollView.FOCUS_DOWN);
					}
				});

				countryAdapter.selectItem(item);
				countryAdapter.searchItem(editSearch.getText().toString());
			}
			@Override
			public Boolean onItemLongClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
				return null;
			}
		});

		countrySearchRecycler.setAdapter(countryAdapter);
	}

	private Chip makeChip(final CountryItem item) {
		Chip chip = new Chip(AddTravelActivity.this);
		chip.setText(item.getCountryKrName());
		chip.setCloseIconVisible(true);
		chip.setTextAppearance(R.style.chip_text_style);
		ChipDrawable chipDrawable = (ChipDrawable) chip.getChipDrawable();
		chipDrawable.setChipBackgroundColorResource(R.color.color_clip_back);
		chipDrawable.setCloseIconResource(R.drawable.ic_clear_black_24dp);
		chip.setCheckable(false);
		chip.setClickable(false);
		chip.setOnCloseIconClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				chipGroup.removeView(view);
				countryAdapter.deselectItem(item);
				countryCodes.remove(item.getCountryCode());
				countryAdapter.searchItem(editSearch.getText().toString());
			}
		});

		return chip;
	}

	private void setDateButtons() {
		btnStartDate = findViewById(R.id.btn_pick_start_date);
		btnEndDate = findViewById(R.id.btn_pick_end_date);

		btnStartDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				clearFocus();

				int year, month, date;

				String[] startDate = btnStartDate.getText().toString().split("\\.");
				if (startDate.length == 3) {
					year = Integer.valueOf(startDate[0]);
					month = Integer.valueOf(startDate[1]) - 1;
					date = Integer.valueOf(startDate[2]);
				} else {
					DateHelper now = new DateHelper();
					year = now.getYear();
					month = now.getMonth() - 1;
					date = now.getDayOfMonth();
				}
				new DatePickerDialog(AddTravelActivity.this, new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
						String month = String.valueOf(i1 + 1);
						String day = String.valueOf(i2);

						if (i1 < 10) month = "0" + (i1 + 1);
						if (i2 < 10) day = "0" + i2;

						String text = i + "." + month + "." + day;
						btnStartDate.setText(text);
						isFilledDates = true;
					}
				}, year, month, date).show();

			}
		});

		btnEndDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				clearFocus();

				int year, month, date;

				String[] endDate = btnEndDate.getText().toString().split("\\.");
				if (endDate.length == 3) {
					year = Integer.valueOf(endDate[0]);
					month = Integer.valueOf(endDate[1]) - 1;
					date = Integer.valueOf(endDate[2]);
				} else {
					DateHelper now = new DateHelper();
					year = now.getYear();
					month = now.getMonth() - 1;
					date = now.getDayOfMonth();
				}
				new DatePickerDialog(AddTravelActivity.this, new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
						String month = String.valueOf(i1 + 1);
						String day = String.valueOf(i2);

						if (i1 < 10) month = "0" + (i1 + 1);
						if (i2 < 10) day = "0" + i2;

						String text = i + "." + month + "." + day;
						btnEndDate.setText(text);
						isFilledDates = true;
					}
				}, year, month, date).show();
			}
		});
	}

	private void clearFocus() {
		editSearch.clearFocus();
		editTitle.clearFocus();
	}
}