package com.mungziapp.traveltogether.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.mungziapp.traveltogether.OnItemClickListener;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.adapter.SearchCountryAdapter;
import com.mungziapp.traveltogether.app.DatabaseManager;
import com.mungziapp.traveltogether.item.SearchCountryItem;
import com.mungziapp.traveltogether.table.TravelTable;

import java.util.Calendar;

public class EditTravelActivity extends AppCompatActivity {
	private Button btnStartDate;
	private Button btnEndDate;
	private Button btnRePickCoverImg;

	private EditText editSearch;
	private EditText editTitle;
	private ChipGroup chipGroup;
	private RecyclerView countrySearchRecycler;
	private SearchCountryAdapter countryAdapter;
	private InputMethodManager in;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_travel);

		in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

		setSaveAndCancelButtons();
		setTitleText();
		setDateButtons();
		setSearchBar();
		setCountryList();
		setCoverImg();

		init();
	}

	private void init() {
		int travelId = getIntent().getIntExtra("travel_id", 0);

		Cursor cursor = DatabaseManager.database.rawQuery(TravelTable.SELECT_QUERY + " WHERE id = " + travelId, null);
		cursor.moveToNext();

		editTitle.setText(cursor.getString(cursor.getColumnIndex("name")));
		if (!cursor.isNull(cursor.getColumnIndex("start_date")))
			btnStartDate.setText(cursor.getString(cursor.getColumnIndex("start_date")));
		if (!cursor.isNull(cursor.getColumnIndex("end_date")))
			btnEndDate.setText(cursor.getString(cursor.getColumnIndex("end_date")));
		String countryCodes = cursor.getString(cursor.getColumnIndex("country_codes"));
		int cover = cursor.getInt(cursor.getColumnIndex("cover"));
		if (cover != 0)
			btnRePickCoverImg.setBackgroundResource(cover);

		if (countryCodes != null) {
			for (String countryFlag : countryCodes.split(",")) {
				SearchCountryItem item = countryAdapter.getItem(countryFlag);

				if (item != null) {
					chipGroup.addView(makeChip(item));

					countryAdapter.selectItem(item);
					countryAdapter.searchItem(editSearch.getText().toString());
				}
			}
		}

		cursor.close();
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
				AlertDialog dialog = new AlertDialog.Builder(EditTravelActivity.this)
						.setMessage(getString(R.string.cancel_message))
						.setPositiveButton(getString(R.string.btn_ok_text), new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialogInterface, int i) {
								finish();
							}
						})
						.setNegativeButton(getString(R.string.btn_cancel_text), new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialogInterface, int i) {
								dialogInterface.dismiss();
							}
						}).create();

				clearFocus();
				dialog.show();
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

		countrySearchRecycler = findViewById(R.id.country_search_recycler);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
		countrySearchRecycler.setLayoutManager(layoutManager);

		countryAdapter = new SearchCountryAdapter(getApplicationContext());
		countryAdapter.initItem();
		countryAdapter.setClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
				SearchCountryItem item = countryAdapter.getSearchItem(position);

				chipGroup.addView(makeChip(item));

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

	private Chip makeChip(final SearchCountryItem item) {
		Chip chip = new Chip(EditTravelActivity.this);
		chip.setText(item.getCountryName());
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
					year = Calendar.getInstance().get(Calendar.YEAR);
					month = Calendar.getInstance().get(Calendar.MONTH);
					date = Calendar.getInstance().get(Calendar.DATE);
				}
				new DatePickerDialog(EditTravelActivity.this, new DatePickerDialog.OnDateSetListener() {
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
					year = Calendar.getInstance().get(Calendar.YEAR);
					month = Calendar.getInstance().get(Calendar.MONTH);
					date = Calendar.getInstance().get(Calendar.DATE);
				}
				new DatePickerDialog(EditTravelActivity.this, new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
						String text = i + "." + (i1 + 1) + "." + i2;
						btnEndDate.setText(text);
					}
				}, year, month, date).show();
			}
		});
	}

	private void setCoverImg() {
		btnRePickCoverImg = findViewById(R.id.btn_re_pick_cover_img);
		btnRePickCoverImg.setOnClickListener(new View.OnClickListener() {
			String[] options = getResources().getStringArray(R.array.option_profile_img);

			@Override
			public void onClick(View view) {
				AlertDialog dialog = new AlertDialog.Builder(EditTravelActivity.this)
						.setTitle(getString(R.string.cover_img))
						.setItems(options, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialogInterface, int i) {
								switch (i) {
									case 0:
										Toast.makeText(EditTravelActivity.this, "기본 이미지로 변경", Toast.LENGTH_SHORT).show();
										break;
									case 1:
										Toast.makeText(EditTravelActivity.this, "갤러리 이미지로 변경", Toast.LENGTH_SHORT).show();
										break;
								}
							}
						}).create();

				clearFocus();
				dialog.show();
			}
		});
	}

	private void clearFocus() {
		editSearch.clearFocus();
		editTitle.clearFocus();
	}
}
