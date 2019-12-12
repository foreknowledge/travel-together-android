package com.mungziapp.traveltogether.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
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
import com.mungziapp.traveltogether.table.TravelRoomTable;

import java.util.Calendar;

public class EditTravelActivity extends AppCompatActivity {
    private Button btnStartDate;
    private Button btnEndDate;
    private int flag;

    private DatePickerDialog datePickerDialog;
    private static final int SET_START_DATE = 1;
    private static final int SET_END_DATE = 2;

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

        init();
    }

    private void init() {
        Button btnRePickCoverImg = findViewById(R.id.btn_re_pick_cover_img);

        int travelId = getIntent().getIntExtra("travel_id", 0);

        Cursor cursor = DatabaseManager.database.rawQuery(TravelRoomTable.SELECT_QUERY + " WHERE id = " + travelId, null);
        cursor.moveToNext();

        editTitle.setText(cursor.getString(cursor.getColumnIndex("name")));
        btnStartDate.setText(cursor.getString(cursor.getColumnIndex("start_date")));
        btnEndDate.setText(cursor.getString(cursor.getColumnIndex("end_date")));
        String countryCodes = cursor.getString(cursor.getColumnIndex("country_codes"));
        btnRePickCoverImg.setBackgroundResource(cursor.getInt(cursor.getColumnIndex("thumb")));

        for (String countryFlag : countryCodes.split(",")) {
            SearchCountryItem item = countryAdapter.getItem(countryFlag);

            if (item != null) {
                chipGroup.addView(makeChip(item));

                countryAdapter.selectItem(item);
                countryAdapter.searchItem(editSearch.getText().toString());
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
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

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
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int date = Calendar.getInstance().get(Calendar.DATE);
        datePickerDialog = new DatePickerDialog(this, listener, year, month, date);

        btnStartDate = findViewById(R.id.btn_pick_start_date);
        btnEndDate = findViewById(R.id.btn_pick_end_date);

        btnStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickDateButton(SET_START_DATE);
            }
        });

        btnEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickDateButton(SET_END_DATE);
            }
        });
    }

    private void clickDateButton(int flag) {
        editSearch.clearFocus();
        editTitle.clearFocus();
        this.flag = flag;
        datePickerDialog.show();
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            String text = String.valueOf(i).substring(2) + ". " + i1 + ". " + i2;

            if (flag == SET_START_DATE)
                btnStartDate.setText(text);
            else if (flag == SET_END_DATE)
                btnEndDate.setText(text);
        }
    };
}
