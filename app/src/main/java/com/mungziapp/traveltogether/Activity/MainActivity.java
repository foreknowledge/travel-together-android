package com.mungziapp.traveltogether.Activity;

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
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mungziapp.traveltogether.Adapter.TravelsRecyclerAdapter;
import com.mungziapp.traveltogether.Adapter.OuterPagerAdapter;
import com.mungziapp.traveltogether.Fragment.TravelsFragment;
import com.mungziapp.traveltogether.Interface.OnItemClickListener;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.TravelItem;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends BaseActivity {
    private TravelsRecyclerAdapter oncommingAdapter;
    private TravelsRecyclerAdapter lastTravelAdapter;
    private ViewPager outerViewPager;

    private FragmentManager fm;

    private EditText editSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();

        setActionBarSlide();
        setAdapters();
        setTabBar();
        setAddTravelRoomButton();
        setSettingsButton();
        setSearchButton();
    }

    private void setActionBarSlide() {
    }

    private void setAdapters() {
        // oncommingAdapter 세팅
        oncommingAdapter = new TravelsRecyclerAdapter(getApplicationContext());

        ArrayList<String> countries = new ArrayList<>(Arrays.asList("한국", "미국", "대만", "베트남", "부에노스아이레스", "태국", "일본"));
        ArrayList<Integer> members = new ArrayList<>(Arrays.asList(R.drawable.user_img, R.drawable.user_img, R.drawable.user_img,
                R.drawable.user_img, R.drawable.user_img, R.drawable.user_img, R.drawable.user_img, R.drawable.user_img, R.drawable.user_img));

        ArrayList<String> countries2 = new ArrayList<>(Arrays.asList(""));
        ArrayList<Integer> members2 = new ArrayList<>(Arrays.asList(R.drawable.user_img));

        ArrayList<String> countries3 = new ArrayList<>(Arrays.asList("러시아"));
        ArrayList<Integer> members3 = new ArrayList<>(Arrays.asList(R.drawable.user_img, R.drawable.user_img));

        oncommingAdapter.addItem(new TravelItem("엄마와 함께하는 4박 5일 홍콩여행", "19.10.12","19.10.16", countries, members, R.drawable.travel_room_sample_01));
        oncommingAdapter.addItem(new TravelItem("친구들과 처음가는 배낭 여행", "2019.06.09", "19.06.29", countries2, members2, R.drawable.travel_room_sample_02));
        oncommingAdapter.addItem(new TravelItem("마카오로 호캉스~~!!", "19.02.11", "19.02.15", countries3, members3, R.drawable.travel_room_sample_03));
        oncommingAdapter.addItem(new TravelItem("앗싸 퇴직여행 ✈️", "18.08.15", "19.08.16", countries, members, R.drawable.travel_room_sample_04));
        oncommingAdapter.addItem(new TravelItem("혼자가는 러시아 일주 \uD83C\uDFA1", "19.10.12", "19.10.16", countries2, members2, R.drawable.travel_room_sample_01));
        oncommingAdapter.addItem(new TravelItem("찐친들 - 미국 횡단 일주", "19.06.09", "19.06.29", countries3, members3, R.drawable.travel_room_sample_02));

        oncommingAdapter.setOnClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(TravelsRecyclerAdapter.ViewHolder viewHolder, View view, int position) {
                TravelItem item = oncommingAdapter.getItem(position);

                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("travelTitle", item.getTravelTitle());
                intent.putExtra("travelStartDate", item.getTravelStartDate());
                intent.putExtra("travelEndDate", item.getTravelEndDate());
                intent.putExtra("travelCountries", item.getTravelCountries());
                intent.putExtra("travelMembers", item.getTravelMembers());
                intent.putExtra("travelImg", item.getImgResId());

                startActivity(intent);
            }
        });

        // lastTravelAdapter 세팅
        lastTravelAdapter = new TravelsRecyclerAdapter(getApplicationContext());
        lastTravelAdapter.addItem(new TravelItem("가치 같이 여행", "19.10.12", "19.10.16", countries2, members2, R.drawable.travel_room_sample_05));
        lastTravelAdapter.addItem(new TravelItem("일주일 제주 여행", "18.06.09", "19.06.29", countries3, members3, R.drawable.travel_room_sample_06));
        lastTravelAdapter.addItem(new TravelItem("내일로 전국 일주~~", "18.02.11", "18.02.15", countries, members, R.drawable.travel_room_sample_07));
        lastTravelAdapter.addItem(new TravelItem("가자 파리로~!", "18.08.15", "19.08.16", countries2, members2, R.drawable.travel_room_sample_01));
        lastTravelAdapter.addItem(new TravelItem("가치 같이 여행", "19.10.12", "19.10.16", countries3, members3, R.drawable.travel_room_sample_05));
        lastTravelAdapter.addItem(new TravelItem("일주일 제주 여행", "19.06.09", "19.06.29", countries, members, R.drawable.travel_room_sample_06));
        lastTravelAdapter.addItem(new TravelItem("내일로 전국 일주~~", "19.02.11", "19.02.15", countries2, members2, R.drawable.travel_room_sample_07));
        lastTravelAdapter.addItem(new TravelItem("가자 파리로~!", "16.08.19", "16.09.02", countries3, members3, R.drawable.travel_room_sample_01));

        lastTravelAdapter.setOnClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(TravelsRecyclerAdapter.ViewHolder viewHolder, View view, int position) {
                TravelItem item = lastTravelAdapter.getItem(position);

                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("travelTitle", item.getTravelTitle());
                intent.putExtra("travelStartDate", item.getTravelStartDate());
                intent.putExtra("travelEndDate", item.getTravelEndDate());
                intent.putExtra("travelCountries", item.getTravelCountries());
                intent.putExtra("travelMembers", item.getTravelMembers());
                intent.putExtra("travelImg", item.getImgResId());

                startActivity(intent);
            }
        });

        TravelsFragment oncommingTravels = new TravelsFragment(oncommingAdapter);
        TravelsFragment lastTravels = new TravelsFragment(lastTravelAdapter);

        OuterPagerAdapter outerPagerAdapter = new OuterPagerAdapter(fm);
        outerPagerAdapter.addItem(oncommingTravels);
        outerPagerAdapter.addItem(lastTravels);

        outerPagerAdapter.notifyDataSetChanged();

        outerViewPager = findViewById(R.id.outer_view_pager);
        outerViewPager.setOffscreenPageLimit(outerPagerAdapter.getCount());
        outerViewPager.setAdapter(outerPagerAdapter);
    }

    private void setTabBar() {
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(outerViewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                outerViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    private void setAddTravelRoomButton() {
        Button btnAddTravelRoom = findViewById(R.id.btn_add_travel_room);
        btnAddTravelRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddTravelRoomActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setSettingsButton() {
        Button btnGoSettings = findViewById(R.id.btn_go_settings);
        btnGoSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setSearchButton() {
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
        editSearch.clearFocus();
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (in != null)
            in.hideSoftInputFromWindow(editSearch.getWindowToken(), 0);
    }
}
