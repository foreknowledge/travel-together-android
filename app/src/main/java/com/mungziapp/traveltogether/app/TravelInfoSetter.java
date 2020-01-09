package com.mungziapp.traveltogether.app;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
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

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.adapter.SearchCountryAdapter;
import com.mungziapp.traveltogether.app.helper.DatabaseHelper;
import com.mungziapp.traveltogether.app.helper.RequestHelper;
import com.mungziapp.traveltogether.interfaces.OnItemClickListener;
import com.mungziapp.traveltogether.interfaces.OnResponseListener;
import com.mungziapp.traveltogether.model.data.TravelData;
import com.mungziapp.traveltogether.model.item.CountryItem;
import com.mungziapp.traveltogether.model.response.NewTravelRoom;
import com.mungziapp.traveltogether.model.table.TravelTable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TravelInfoSetter {
	private String TAG;

	private Context context;
	private InputMethodManager in;

	private SearchCountryAdapter countryAdapter;
	private EditText editTitle;
	private EditText editSearch;
	private Button btnStartDate;
	private Button btnEndDate;
	private ChipGroup chipGroup;

	private List<String> countryCodes = new ArrayList<>();

	public TravelInfoSetter(Context context, String TAG) {
		this.context = context;
		this.TAG = TAG;
		in = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
	}

	public void setViews(EditText editTitle, EditText editSearch, Button btnStartDate, Button btnEndDate, ChipGroup chipGroup) {
		this.editTitle = editTitle;
		this.editSearch = editSearch;

		this.btnStartDate = btnStartDate;
		this.btnEndDate = btnEndDate;

		this.chipGroup = chipGroup;
	}

	public void setDefaultValue(String travelId) {
		Cursor cursor = DatabaseHelper.database.rawQuery(TravelTable.SELECT_QUERY + " WHERE id = '" + travelId + "'", null);
		cursor.moveToNext();

		editTitle.setText(cursor.getString(cursor.getColumnIndex("name")));
		btnStartDate.setText(cursor.getString(cursor.getColumnIndex("start_date")).replace("-", "."));
		btnEndDate.setText(cursor.getString(cursor.getColumnIndex("end_date")).replace("-", "."));
		String countryCodes = cursor.getString(cursor.getColumnIndex("country_codes"));
		//String coverImgPath = cursor.getString(cursor.getColumnIndex("cover_img_path"));

		if (countryCodes != null) {
			for (String countryCode : countryCodes.split(",")) {
				CountryItem item = countryAdapter.getItem(countryCode);

				if (item != null) {
					chipGroup.addView(makeChip(item));

					countryAdapter.selectItem(item);
					countryAdapter.searchItem(editSearch.getText().toString());
				}
			}
		}

		cursor.close();
	}

	public void requestToServer(int method, String url, OnResponseListener.OnJsonObjectListener listener) {
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

		RequestHelper.getInstance().onSendJsonObjectRequest(method, url, jsonObject, listener);
	}

	public void saveToDatabase(NewTravelRoom newTravelRoom) {
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

	public void setTitleText() {
		editTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View view, boolean b) {
				if (!b)
					if (in != null)
						in.hideSoftInputFromWindow(editSearch.getWindowToken(), 0);
			}
		});
	}

	public void setSearchBar(final RecyclerView countrySearchRecycler, final Button btnClear, final FrameLayout btnClearOut) {
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

	public void setCountryList(RecyclerView countrySearchRecycler, final ScrollView scrollView) {
		LinearLayoutManager layoutManager = new LinearLayoutManager(context);
		countrySearchRecycler.setLayoutManager(layoutManager);

		countryAdapter = new SearchCountryAdapter(context);
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
		Chip chip = new Chip(context);
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

	public void setDateButtons() {
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
				new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
						String month = String.valueOf(i1 + 1);
						String day = String.valueOf(i2);

						if (i1 < 10) month = "0" + (i1 + 1);
						if (i2 < 10) day = "0" + i2;

						String text = i + "." + month + "." + day;
						btnStartDate.setText(text);
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
				new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
						String month = String.valueOf(i1 + 1);
						String day = String.valueOf(i2);

						if (i1 < 10) month = "0" + (i1 + 1);
						if (i2 < 10) day = "0" + i2;

						String text = i + "." + month + "." + day;
						btnEndDate.setText(text);
					}
				}, year, month, date).show();
			}
		});
	}

	public void clearFocus() {
		editSearch.clearFocus();
		editTitle.clearFocus();
	}
}
