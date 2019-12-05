package com.mungziapp.traveltogether.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mungziapp.traveltogether.Adapter.SearchRecyclerAdapter;
import com.mungziapp.traveltogether.Adapter.TravelsRecyclerAdapter;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.SearchItem;
import com.mungziapp.traveltogether.TravelItem;

public class SearchActivity extends AppCompatActivity {
    private EditText editSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setGoBeforeButton();
        setSearchBar();
        setRecyclerView();
    }

    private void setGoBeforeButton() {
        Button btnGoBefore = findViewById(R.id.btn_go_before);
        btnGoBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setSearchBar() {
        editSearch = findViewById(R.id.search_travel);
        final Button btnClear = findViewById(R.id.btn_clear);

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btnClear.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editSearch.getText().toString().equals("")) btnClear.setVisibility(View.INVISIBLE);
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

    private void setRecyclerView() {
        RecyclerView searchRecycler = findViewById(R.id.search_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        searchRecycler.setLayoutManager(layoutManager);

        SearchRecyclerAdapter searchAdapter = new SearchRecyclerAdapter(getApplicationContext());
        searchAdapter.addItem(new SearchItem("엄마와 함께하는 4박 5일 홍콩여행", "19.10.12","19.10.16", R.drawable.travel_room_sample_01));
        searchAdapter.addItem(new SearchItem("친구들과 처음가는 배낭 여행", "2019.06.09", "19.06.29", R.drawable.travel_room_sample_02));
        searchAdapter.addItem(new SearchItem("마카오로 호캉스~~!!", "19.02.11", "19.02.15", R.drawable.travel_room_sample_03));
        searchAdapter.addItem(new SearchItem("앗싸 퇴직여행 ✈️", "18.08.15", "19.08.16", R.drawable.travel_room_sample_04));
        searchAdapter.addItem(new SearchItem("혼자가는 러시아 일주 \uD83C\uDFA1", "19.10.12", "19.10.16", R.drawable.travel_room_sample_01));
        searchAdapter.addItem(new SearchItem("찐친들 - 미국 횡단 일주", "19.06.09", "19.06.29", R.drawable.travel_room_sample_02));

        searchAdapter.addItem(new SearchItem("가치 같이 여행", "19.10.12", "19.10.16", R.drawable.travel_room_sample_05));
        searchAdapter.addItem(new SearchItem("일주일 제주 여행", "18.06.09", "19.06.29", R.drawable.travel_room_sample_06));
        searchAdapter.addItem(new SearchItem("내일로 전국 일주~~", "18.02.11", "18.02.15", R.drawable.travel_room_sample_07));
        searchAdapter.addItem(new SearchItem("가자 파리로~!", "18.08.15", "19.08.16", R.drawable.travel_room_sample_01));
        searchAdapter.addItem(new SearchItem("가치 같이 여행", "19.10.12", "19.10.16", R.drawable.travel_room_sample_05));
        searchAdapter.addItem(new SearchItem("일주일 제주 여행", "19.06.09", "19.06.29", R.drawable.travel_room_sample_06));
        searchAdapter.addItem(new SearchItem("내일로 전국 일주~~", "19.02.11", "19.02.15", R.drawable.travel_room_sample_07));
        searchAdapter.addItem(new SearchItem("가자 파리로~!", "16.08.19", "16.09.02", R.drawable.travel_room_sample_01));

        searchRecycler.setAdapter(searchAdapter);
    }
}
