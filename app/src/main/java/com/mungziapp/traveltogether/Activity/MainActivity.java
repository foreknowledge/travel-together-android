package com.mungziapp.traveltogether.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mungziapp.traveltogether.Adapter.OuterTravelsAdapter;
import com.mungziapp.traveltogether.Adapter.OuterPagerAdapter;
import com.mungziapp.traveltogether.Fragment.TravelsFragment;
import com.mungziapp.traveltogether.Interface.OnItemClickListener;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.TravelItem;

public class MainActivity extends BaseActivity {
    private OuterTravelsAdapter oncommingAdapter;
    private OuterTravelsAdapter lastTravelAdapter;
    private ViewPager outerViewPager;

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();

        setAdapters();
        setTabBar();
        setAddTravelRoomButton();
        setSettingsButton();
    }

    private void setAdapters() {
        // oncommingAdapter 세팅
        oncommingAdapter = new OuterTravelsAdapter(getApplicationContext());
        oncommingAdapter.addItem(new TravelItem("엄마와 함께하는 4박 5일 홍콩여행", "19.10.12","19.10.16", 2, R.drawable.travel_room_sample_01));
        oncommingAdapter.addItem(new TravelItem("친구들과 처음가는 배낭 여행", "2019.06.09", "19.06.29", 10, R.drawable.travel_room_sample_02));
        oncommingAdapter.addItem(new TravelItem("마카오로 호캉스~~!!", "19.02.11", "19.02.15", 3, R.drawable.travel_room_sample_03));
        oncommingAdapter.addItem(new TravelItem("앗싸 퇴직여행 ✈️", "18.08.15", "19.08.16", 3, R.drawable.travel_room_sample_04));
        oncommingAdapter.addItem(new TravelItem("혼자가는 러시아 일주 \uD83C\uDFA1", "19.10.12", "19.10.16", 1, R.drawable.travel_room_sample_01));
        oncommingAdapter.addItem(new TravelItem("찐친들 - 미국 횡단 일주", "19.06.09", "19.06.29", 6, R.drawable.travel_room_sample_02));

        oncommingAdapter.setOnClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(OuterTravelsAdapter.ViewHolder viewHolder, View view, int position) {
                TravelItem item = oncommingAdapter.getItem(position);

                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("travelTitle", item.getTravelTitle());
                intent.putExtra("travelStartDate", item.getTravelStartDate());
                intent.putExtra("travelEndDate", item.getTravelEndDate());
                intent.putExtra("travelImg", item.getImgResId());

                startActivity(intent);
            }
        });

        // lastTravelAdapter 세팅
        lastTravelAdapter = new OuterTravelsAdapter(getApplicationContext());
        lastTravelAdapter.addItem(new TravelItem("가치 같이 여행", "19.10.12", "19.10.16", 7, R.drawable.travel_room_sample_05));
        lastTravelAdapter.addItem(new TravelItem("일주일 제주 여행", "18.06.09", "19.06.29", 2, R.drawable.travel_room_sample_06));
        lastTravelAdapter.addItem(new TravelItem("내일로 전국 일주~~", "18.02.11", "18.02.15", 3, R.drawable.travel_room_sample_07));
        lastTravelAdapter.addItem(new TravelItem("가자 파리로~!", "18.08.15", "19.08.16", 2, R.drawable.travel_room_sample_01));
        lastTravelAdapter.addItem(new TravelItem("가치 같이 여행", "19.10.12", "19.10.16", 7, R.drawable.travel_room_sample_05));
        lastTravelAdapter.addItem(new TravelItem("일주일 제주 여행", "19.06.09", "19.06.29", 2, R.drawable.travel_room_sample_06));
        lastTravelAdapter.addItem(new TravelItem("내일로 전국 일주~~", "19.02.11", "19.02.15", 3, R.drawable.travel_room_sample_07));
        lastTravelAdapter.addItem(new TravelItem("가자 파리로~!", "16.08.19", "16.09.02", 2, R.drawable.travel_room_sample_01));

        lastTravelAdapter.setOnClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(OuterTravelsAdapter.ViewHolder viewHolder, View view, int position) {
                TravelItem item = lastTravelAdapter.getItem(position);

                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("travelTitle", item.getTravelTitle());
                intent.putExtra("travelStartDate", item.getTravelStartDate());
                intent.putExtra("travelEndDate", item.getTravelEndDate());
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
}
