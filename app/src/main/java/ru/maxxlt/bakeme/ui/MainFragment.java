package ru.maxxlt.bakeme.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.maxxlt.bakeme.HostActivity;
import ru.maxxlt.bakeme.R;
import ru.maxxlt.bakeme.data.Bakery;
import ru.maxxlt.bakeme.data.Baking;

public class MainFragment extends Fragment{
    Bakery bakery;
    MainFragmentAdapter mainFragmentAdapter;
    List<Baking> bakingList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main,container,false);
        RecyclerView recyclerView = rootView.findViewById(R.id.main_fragment_recycler);
        bakery = new Bakery();
        bakingList = bakery.getSharingIsCaringBakery().getValue();
        mainFragmentAdapter.setBakings(bakery.getSharingIsCaringBakery().getValue());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mainFragmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        bakery = ViewModelProviders.of(this).get(Bakery.class);
        bakery.getSharingIsCaringBakery().observe(this, new Observer<List<Baking>>() {
            @Override
            public void onChanged(@Nullable List<Baking> bakings) {
                mainFragmentAdapter.setBakings(bakings);
                Log.v("Main Fragment: ","Observer set");
            }
        });

        return rootView;
    }
}
