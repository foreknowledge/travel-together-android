package com.mungziapp.traveltogether.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.mungziapp.traveltogether.app.DateObject;
import com.mungziapp.traveltogether.interfaces.OnItemClickListener;
import com.mungziapp.traveltogether.adapter.SearchCountryAdapter;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.model.item.CountryItem;

public class AddTravelActivity extends AppCompatActivity {
	private Button btnStartDate;
	private Button btnEndDate;

	private EditText editSearch;
	private EditText editTitle;
	private ChipGroup chipGroup;
	private RecyclerView countrySearchRecycler;
	private SearchCountryAdapter countryAdapter;
	private InputMethodManager in;

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
				finish();
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
					performSearch();
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

	private void performSearch() {
		if (editSearch.getText().toString().equals("")) {
			Toast.makeText(this, "검색어를 입력 해 주세요.", Toast.LENGTH_SHORT).show();
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
					DateObject now = new DateObject();
					year = now.getYear();
					month = now.getMonth();
					date = now.getDayOfMonth();
				}
				new DatePickerDialog(AddTravelActivity.this, new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
						String text = i + "." + (i1 + 1) + "." + i2;
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
					DateObject now = new DateObject();
					year = now.getYear();
					month = now.getMonth();
					date = now.getDayOfMonth();
				}
				new DatePickerDialog(AddTravelActivity.this, new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
						String text = i + "." + (i1 + 1) + "." + i2;
						btnEndDate.setText(text);
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
