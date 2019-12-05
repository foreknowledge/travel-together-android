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
import android.widget.TextView;
import android.widget.Toast;

import com.mungziapp.traveltogether.adapter.SearchCountryAdapter;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.item.SearchCountryItem;
import com.mungziapp.traveltogether.app.TravelHelper;

import java.util.Calendar;

public class AddTravelRoomActivity extends AppCompatActivity {
    private Button btnStartDate;
    private Button btnEndDate;
    private Button btnPickCoverImg;
    private Button btnRePickCoverImg;
    private int flag;

    private final int SET_START_DATE = 1;
    private final int SET_END_DATE = 2;

    private EditText editSearch;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel_room);

        setSaveAndCancelButtons();
        setDateButtons();
        setSearchBar();
        setCountryList();
        setCoverImgButtons();
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
        recyclerView = findViewById(R.id.country_search_recycler);

        final Button btnClear = findViewById(R.id.btn_clear);

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btnClear.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editSearch.getText().toString().equals("")) {
                    btnClear.setVisibility(View.INVISIBLE);
                    recyclerView.setVisibility(View.INVISIBLE);
                }
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

        Toast.makeText(this, "검색어 = " + editSearch.getText(), Toast.LENGTH_SHORT).show();
    }

    private void setCountryList() {
        RecyclerView countrySearchRecycler = findViewById(R.id.country_search_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        countrySearchRecycler.setLayoutManager(layoutManager);

        SearchCountryAdapter countryAdapter = new SearchCountryAdapter(getApplicationContext());

        for (String countryName: TravelHelper.countryMap.keySet()) {
            String countryFlag = TravelHelper.countryMap.get(countryName);
            countryAdapter.addItem(new SearchCountryItem(countryFlag, countryName));
        }

        countrySearchRecycler.setAdapter(countryAdapter);
    }

    private void setCoverImgButtons() {
        btnPickCoverImg = findViewById(R.id.btn_pick_cover_img);
        btnPickCoverImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPickCoverImg.setVisibility(View.INVISIBLE);
                btnRePickCoverImg.setVisibility(View.VISIBLE);
                // 갤러리 이미지 선택해서 가져오기
            }
        });

        btnRePickCoverImg = findViewById(R.id.btn_re_pick_cover_img);
        btnRePickCoverImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 갤러리 이미지 선택해서 가져오기
            }
        });
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
