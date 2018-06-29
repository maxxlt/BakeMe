package ru.maxxlt.bakeme.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.maxxlt.bakeme.R;

public class MainFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.main_fragment_recycler);
        final BakeryAdapter bakeryAdapter = new BakeryAdapter(R.layout.fragment_material);
        recyclerView.setAdapter(bakeryAdapter);

        return rootView;
    }
}
