package com.mungziapp.traveltogether.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mungziapp.traveltogether.Interface.ActivityCallback;
import com.mungziapp.traveltogether.R;

public class DetailFragment extends Fragment {
    private ActivityCallback callback;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof ActivityCallback)
            callback = (ActivityCallback) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.detail_fragment, container, false);

        // Detail Fragment 초기화

        Button goTravelRooms = rootView.findViewById(R.id.go_travel_rooms);
        Button btnMore = rootView.findViewById(R.id.btn_travel_room_more);

        goTravelRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.removeDetailFragment();
            }
        });

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return rootView;
    }
}
