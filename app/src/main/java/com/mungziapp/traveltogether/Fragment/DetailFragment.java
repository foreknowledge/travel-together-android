package com.mungziapp.traveltogether.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mungziapp.traveltogether.Interface.ActivityCallback;
import com.mungziapp.traveltogether.R;

public class DetailFragment extends Fragment {
    private ActivityCallback callback;

    private View rootView;
    private String roomTitle;
    private String roomDuration;
    private String roomFlag;
    private int roomImg;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof ActivityCallback)
            callback = (ActivityCallback) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.detail_fragment, container, false);

        // Detail Fragment 초기화
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.roomTitle = arguments.getString("roomTitle");
            this.roomDuration = arguments.getString("roomDuration");
            this.roomFlag = arguments.getString("roomFlag");
            this.roomImg = arguments.getInt("roomImg");
        }

        setRoomInfo();

        Button btnGoBefore = rootView.findViewById(R.id.btn_go_before);
        Button btnMore = rootView.findViewById(R.id.btn_more);

        btnGoBefore.setOnClickListener(new View.OnClickListener() {
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

    private void setRoomInfo() {
        TextView tvRoomTitle = rootView.findViewById(R.id.room_title);
        tvRoomTitle.setText(roomTitle);

        TextView tvRoomDuration = rootView.findViewById(R.id.room_duration);
        tvRoomDuration.setText(roomDuration);

        TextView tvRoomFlag = rootView.findViewById(R.id.room_flag);
        tvRoomFlag.setText(roomFlag);

        FrameLayout frRoomImg = rootView.findViewById(R.id.frame_room_cover_img);
        frRoomImg.setBackgroundResource(roomImg);
    }
}
