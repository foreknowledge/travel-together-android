package com.mungziapp.traveltogether.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mungziapp.traveltogether.OnItemClickListener;
import com.mungziapp.traveltogether.adapter.SearchTravelAdapter;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.item.SearchTravelItem;

public class SearchActivity extends AppCompatActivity {
	private EditText editSearch;
	private SearchTravelAdapter searchAdapter;
	private InputMethodManager in;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
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

		editSearch.setFocusableInTouchMode(true);
		editSearch.setFocusable(true);
		editSearch.requestFocus();

		Button btnClear = findViewById(R.id.btn_clear);
		final FrameLayout btnClearOut = findViewById(R.id.btn_clear_out);
		Button btnSearch = findViewById(R.id.btn_search);

		editSearch.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				btnClearOut.setVisibility(View.VISIBLE);
			}

			@Override
			public void afterTextChanged(Editable editable) {
				if (editSearch.getText().toString().equals(""))
					btnClearOut.setVisibility(View.INVISIBLE);

				searchAdapter.searchItem(editable.toString());
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

		btnSearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				performSearch();
			}
		});
	}

	private void performSearch() {
		if (editSearch.getText().toString().equals("")) {
			Toast.makeText(this, "검색어를 입력 해 주세요.", Toast.LENGTH_SHORT).show();
			return;
		}

		editSearch.clearFocus();

		if (in != null)
			in.hideSoftInputFromWindow(editSearch.getWindowToken(), 0);
	}

	private void setRecyclerView() {
		RecyclerView searchRecycler = findViewById(R.id.search_recycler);
		searchRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

		searchAdapter = new SearchTravelAdapter(getApplicationContext());
		searchAdapter.initItem();
		searchAdapter.setClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
				SearchTravelItem item = searchAdapter.getItem(position);

				Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
				intent.putExtra("id", item.getId());

				startActivity(intent);
			}

			@Override
			public Boolean onItemLongClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
				return null;
			}
		});

		searchRecycler.setAdapter(searchAdapter);
	}
}
