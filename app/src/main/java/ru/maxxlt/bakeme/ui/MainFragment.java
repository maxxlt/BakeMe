package ru.maxxlt.bakeme.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.maxxlt.bakeme.R;
import ru.maxxlt.bakeme.data.Baking;
import ru.maxxlt.bakeme.models.BakingListViewModel;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.main_fragment_recycler);
        final MainFragmentAdapter mainFragmentAdapter = new MainFragmentAdapter();
        BakingListViewModel bakingListViewModel = ViewModelProviders.of(getActivity()).get(BakingListViewModel.class);
        bakingListViewModel.getSharingIsCaringBakery().observe(this, new Observer<List<Baking>>() {
            @Override
            public void onChanged(@Nullable List<Baking> bakings) {
                mainFragmentAdapter.setBakingList(bakings);
                mainFragmentAdapter.setOnTitleClickListener(new MainFragmentAdapter.OnTitleClickListener() {
                    @Override
                    public void onTitleSelected(int position) {
                        Toast.makeText(getContext(),"Position: " + position,Toast.LENGTH_LONG).show();
                    }
                });
                mainFragmentAdapter.notifyDataSetChanged();
            }
        });
        recyclerView.setAdapter(mainFragmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }
}
