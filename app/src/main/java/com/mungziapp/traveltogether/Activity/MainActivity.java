package com.mungziapp.traveltogether.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.FragmentManager;

import com.google.android.material.tabs.TabLayout;
import com.mungziapp.traveltogether.Adapter.TravelsAdapter;
import com.mungziapp.traveltogether.Fragment.TravelsFragment;
import com.mungziapp.traveltogether.Interface.OnItemClickListener;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.TravelItem;

public class MainActivity extends BaseActivity {
    private TravelsAdapter oncommingAdapter;
    private TravelsAdapter lastTravelAdapter;
    private TravelsFragment oncommingTravels;
    private TravelsFragment lastTravels;

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        // create TravelRoomsFragments
        setAdapters();
        oncommingTravels = new TravelsFragment(oncommingAdapter);
        lastTravels = new TravelsFragment(lastTravelAdapter);

        setTabBar();
        setAddTravelRoomButton();
        setSettingsButton();
    }

    private void setAdapters() {
        // oncommingAdapter 세팅
        oncommingAdapter = new TravelsAdapter(getApplicationContext());
        oncommingAdapter.addItem(new TravelItem("엄마와 함께하는 4박 5일 홍콩여행", "19.10.12","19.10.16", 2, R.drawable.travel_room_sample_01));
        oncommingAdapter.addItem(new TravelItem("친구들과 처음가는 배낭 여행", "2019.06.09", "19.06.29", 10, R.drawable.travel_room_sample_02));
        oncommingAdapter.addItem(new TravelItem("마카오로 호캉스~~!!", "19.02.11", "19.02.15", 3, R.drawable.travel_room_sample_03));
        oncommingAdapter.addItem(new TravelItem("앗싸 퇴직여행 ✈️", "18.08.15", "19.08.16", 3, R.drawable.travel_room_sample_04));
        oncommingAdapter.addItem(new TravelItem("혼자가는 러시아 일주 \uD83C\uDFA1", "19.10.12", "19.10.16", 1, R.drawable.travel_room_sample_01));
        oncommingAdapter.addItem(new TravelItem("찐친들 - 미국 횡단 일주", "19.06.09", "19.06.29", 6, R.drawable.travel_room_sample_02));

        oncommingAdapter.setOnClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(TravelsAdapter.ViewHolder viewHolder, View view, int position) {
                TravelItem item = oncommingAdapter.getItem(position);

                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("travelTitle", item.gettravelTitle());
                intent.putExtra("travelStartDate", item.gettravelStartDate());
                intent.putExtra("travelEndDate", item.gettravelEndDate());
                intent.putExtra("travelImg", item.getImgResId());

                startActivity(intent);
            }
        });

        // lastTravelAdapter 세팅
        lastTravelAdapter = new TravelsAdapter(getApplicationContext());
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
            public void onItemClick(TravelsAdapter.ViewHolder viewHolder, View view, int position) {
            }
        });
    }

    private void setTabBar() {
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("다가오는 여행"));
        tabs.addTab(tabs.newTab().setText("지난 여행"));
        fm.beginTransaction().add(R.id.travel_room_container, oncommingTravels).commit();

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                TravelsFragment selected = null;
                switch (position) {
                    case 0:
                        selected = oncommingTravels;
                        break;
                    case 1:
                        selected = lastTravels;
                        break;
                }

                if (selected != null)
                    fm.beginTransaction().replace(R.id.travel_room_container, selected).commit();
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
