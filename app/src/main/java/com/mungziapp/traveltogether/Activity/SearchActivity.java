package com.mungziapp.traveltogether.Activity;

import androidx.appcompat.app.AppCompatActivity;

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

import com.mungziapp.traveltogether.R;

public class SearchActivity extends AppCompatActivity {
    private EditText editSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setGoBeforeButton();
        setSearchBar();
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
}
