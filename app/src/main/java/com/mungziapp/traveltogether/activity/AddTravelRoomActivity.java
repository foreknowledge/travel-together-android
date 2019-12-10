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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.mungziapp.traveltogether.OnItemClickListener;
import com.mungziapp.traveltogether.adapter.SearchCountryAdapter;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.item.SearchCountryItem;

import java.util.Calendar;

public class AddTravelRoomActivity extends AppCompatActivity {
    private Button btnStartDate;
    private Button btnEndDate;
    private int flag;

    private final int SET_START_DATE = 1;
    private final int SET_END_DATE = 2;

    private EditText editSearch;
    private ChipGroup chipGroup;
    private RecyclerView countrySearchRecycler;
    private SearchCountryAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel_room);

        setSaveAndCancelButtons();
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

    private void setSearchBar() {
        editSearch = findViewById(R.id.search_country);
        chipGroup = findViewById(R.id.chip_group);

        final Button btnClear = findViewById(R.id.btn_clear);
        final FrameLayout btnClearOut = findViewById(R.id.btn_clear_out);

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btnClear.setVisibility(View.VISIBLE);
                countrySearchRecycler.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editSearch.getText().toString().equals("")) {
                    btnClear.setVisibility(View.INVISIBLE);
                    countrySearchRecycler.setVisibility(View.GONE);
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
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (in != null)
            in.hideSoftInputFromWindow(editSearch.getWindowToken(), 0);
    }

    private void setCountryList() {
        countrySearchRecycler = findViewById(R.id.country_search_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        countrySearchRecycler.setLayoutManager(layoutManager);

        countryAdapter = new SearchCountryAdapter(getApplicationContext());
        countryAdapter.initItem();
        countryAdapter.setClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(SearchCountryAdapter.ViewHolder viewHolder, View view, int position) {
                final SearchCountryItem item = countryAdapter.getSearchItem(position);

                Chip chip = new Chip(AddTravelRoomActivity.this);
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

                chipGroup.addView(chip);

                countryAdapter.selectItem(item);
                countryAdapter.searchItem(editSearch.getText().toString());
            }
        });

        countrySearchRecycler.setAdapter(countryAdapter);
    }

    private void setDateButtons() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int date = Calendar.getInstance().get(Calendar.DATE);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, listener, year, month, date);

        btnStartDate = findViewById(R.id.btn_pick_start_date);
        btnEndDate = findViewById(R.id.btn_pick_end_date);

        btnStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = SET_START_DATE;
                datePickerDialog.show();
            }
        });

        btnEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = SET_END_DATE;
                datePickerDialog.show();
            }
        });
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            String text = i + ". " + i1 + ". " + i2;

            if (flag == SET_START_DATE)
                btnStartDate.setText(text);
            else if (flag == SET_END_DATE)
                btnEndDate.setText(text);
        }
    };

}
