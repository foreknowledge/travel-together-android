package com.mungziapp.traveltogether.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.Adapter.TravelRoomAdapter;
import com.mungziapp.traveltogether.R;

public class TravelRoomsFragment extends Fragment {
    private TravelRoomAdapter travelRoomAdapter;

    public TravelRoomsFragment() {}
    TravelRoomsFragment(TravelRoomAdapter travelRoomAdapter) {
        this.travelRoomAdapter = travelRoomAdapter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.travel_rooms_fragment, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(travelRoomAdapter);

        return rootView;
    }
}
