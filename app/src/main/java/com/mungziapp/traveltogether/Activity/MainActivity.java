package com.mungziapp.traveltogether.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.FragmentManager;

import com.google.android.material.tabs.TabLayout;
import com.mungziapp.traveltogether.Adapter.TravelRoomAdapter;
import com.mungziapp.traveltogether.Fragment.DetailFragment;
import com.mungziapp.traveltogether.Fragment.TravelRoomsFragment;
import com.mungziapp.traveltogether.Interface.OnItemClickListener;
import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.RoomItem;

public class MainActivity extends BaseActivity {
    private TravelRoomAdapter oncommingAdapter;
    private TravelRoomAdapter lastTravelAdapter;
    private TravelRoomsFragment oncommingTravels;
    private TravelRoomsFragment lastTravels;

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        // create TravelRoomsFragments
        setAdapters();
        oncommingTravels = new TravelRoomsFragment(oncommingAdapter);
        lastTravels = new TravelRoomsFragment(lastTravelAdapter);

        setTabBar();
        setAddTravelRoomButton();
    }

    private void setAdapters() {
        // oncommingAdapter 세팅
        oncommingAdapter = new TravelRoomAdapter(getApplicationContext());
        oncommingAdapter.addItem(new RoomItem("엄마와 함께하는 4박 5일 홍콩여행", "19.10.12 ~ 19.10.16", "\uD83C\uDDED\uD83C\uDDF0", 2, R.drawable.travel_room_sample_01));
        oncommingAdapter.addItem(new RoomItem("친구들과 처음가는 배낭 여행", "2019.06.09 ~ 19.06.29", "\uD83C\uDDF0\uD83C\uDDF7", 10, R.drawable.travel_room_sample_02));
        oncommingAdapter.addItem(new RoomItem("마카오로 호캉스~~!!", "19.02.11 ~ 19.02.15", "\uD83C\uDDF2\uD83C\uDDF4", 3, R.drawable.travel_room_sample_03));
        oncommingAdapter.addItem(new RoomItem("앗싸 퇴직여행 ✈️", "18.08.15 ~ 19.08.16", "\uD83C\uDDEC\uD83C\uDDFA", 3, R.drawable.travel_room_sample_04));
        oncommingAdapter.addItem(new RoomItem("혼자가는 러시아 일주 \uD83C\uDFA1", "19.10.12 ~ 19.10.16", "\uD83C\uDDF7\uD83C\uDDFA", 1, R.drawable.travel_room_sample_01));
        oncommingAdapter.addItem(new RoomItem("찐친들 - 미국 횡단 일주", "19.06.09 ~ 19.06.29", "\uD83C\uDDFA\uD83C\uDDF8", 6, R.drawable.travel_room_sample_02));

        oncommingAdapter.setOnClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(TravelRoomAdapter.ViewHolder viewHolder, View view, int position) {
                RoomItem roomItem = oncommingAdapter.getItem(position);

                DetailFragment detailFragment = new DetailFragment();
                Bundle arguments = new Bundle();
                arguments.putString("roomTitle", roomItem.getRoomTitle());
                arguments.putString("roomDuration", roomItem.getRoomDuration());
                arguments.putString("roomFlag", roomItem.getRoomFlag());
                arguments.putInt("roomImg", roomItem.getImgResId());

                detailFragment.setArguments(arguments);
            }
        });

        // lastTravelAdapter 세팅
        lastTravelAdapter = new TravelRoomAdapter(getApplicationContext());
        lastTravelAdapter.addItem(new RoomItem("가치 같이 여행", "19.10.12 ~ 19.10.16", "\uD83C\uDDFB\uD83C\uDDF3", 7, R.drawable.travel_room_sample_05));
        lastTravelAdapter.addItem(new RoomItem("일주일 제주 여행", "18.06.09 ~ 19.06.29", "\uD83C\uDDF0\uD83C\uDDF7", 2, R.drawable.travel_room_sample_06));
        lastTravelAdapter.addItem(new RoomItem("내일로 전국 일주~~", "18.02.11 ~ 18.02.15", "\uD83C\uDDF0\uD83C\uDDF7", 3, R.drawable.travel_room_sample_07));
        lastTravelAdapter.addItem(new RoomItem("가자 파리로~!", "18.08.15 ~ 19.08.16", "\uD83C\uDDEB\uD83C\uDDF7", 2, R.drawable.travel_room_sample_01));
        lastTravelAdapter.addItem(new RoomItem("가치 같이 여행", "19.10.12 ~ 19.10.16", "\uD83C\uDDFB\uD83C\uDDF3", 7, R.drawable.travel_room_sample_05));
        lastTravelAdapter.addItem(new RoomItem("일주일 제주 여행", "19.06.09 ~ 19.06.29", "\uD83C\uDDF0\uD83C\uDDF7", 2, R.drawable.travel_room_sample_06));
        lastTravelAdapter.addItem(new RoomItem("내일로 전국 일주~~", "19.02.11 ~ 19.02.15", "\uD83C\uDDF0\uD83C\uDDF7", 3, R.drawable.travel_room_sample_07));
        lastTravelAdapter.addItem(new RoomItem("가자 파리로~!", "16.08.19 ~ 16.09.02", "\uD83C\uDDEB\uD83C\uDDF7", 2, R.drawable.travel_room_sample_01));

        lastTravelAdapter.setOnClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(TravelRoomAdapter.ViewHolder viewHolder, View view, int position) {
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

                TravelRoomsFragment selected = null;
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
        Button button = findViewById(R.id.btn_add_travel_room);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddTravelRoomActivity.class);
                startActivity(intent);
            }
        });
    }
}
