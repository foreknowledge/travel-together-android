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

import com.mungziapp.traveltogether.Adapter.TravelsAdapter;
import com.mungziapp.traveltogether.R;

public class TravelsFragment extends Fragment {
    private TravelsAdapter travelsAdapter;

    public TravelsFragment() {}
    public TravelsFragment(TravelsAdapter travelsAdapter) {
        this.travelsAdapter = travelsAdapter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.travels_fragment, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(travelsAdapter);

        return rootView;
    }
}
