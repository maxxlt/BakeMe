package ru.maxxlt.bakeme.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.maxxlt.bakeme.R;
import ru.maxxlt.bakeme.data.BakingViewModel;
import ru.maxxlt.bakeme.data.Baking;

public class MainFragment extends Fragment{
    BakingViewModel bakingViewModel;
    List<Baking> bakingList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main,container,false);
        RecyclerView recyclerView = rootView.findViewById(R.id.main_fragment_recycler);
        final BakeryAdapter bakeryAdapter = new BakeryAdapter(R.layout.fragment_material);
        recyclerView.setAdapter(bakeryAdapter);
        BakingViewModel bakingViewModel = ViewModelProviders.of(this).get(BakingViewModel.class);
        bakingViewModel.getSharingIsCaringBakery().observe(this, new Observer<List<Baking>>() {
            @Override
            public void onChanged(@Nullable List<Baking> bakings) {
                bakeryAdapter.setStuff(bakings);
            }
        });
        return rootView;
    }
}
