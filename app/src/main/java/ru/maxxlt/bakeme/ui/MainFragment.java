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

import java.util.ArrayList;
import java.util.List;

import ru.maxxlt.bakeme.HostActivity;
import ru.maxxlt.bakeme.R;
import ru.maxxlt.bakeme.data.Baking;
import ru.maxxlt.bakeme.models.BakingListViewModel;
import ru.maxxlt.bakeme.models.MainFragmentSkeleton;

public class MainFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.main_fragment_recycler);
        final BakeryAdapter bakeryAdapter = new BakeryAdapter(R.layout.fragment_material);
        BakingListViewModel bakingListViewModel = ViewModelProviders.of(getActivity()).get(BakingListViewModel.class);
        bakingListViewModel.getSharingIsCaringBakery().observe(this, new Observer<List<Baking>>() {
            @Override
            public void onChanged(@Nullable List<Baking> bakings) {
                List<MainFragmentSkeleton> skeletonList = new ArrayList<>();
                for (int i = 0; i < bakings.size();i++){
                    Baking baking = bakings.get(i);
                    skeletonList.add(new MainFragmentSkeleton(baking));
                }
                bakeryAdapter.setStuff(skeletonList);
                bakeryAdapter.notifyDataSetChanged();
            }
        });
        recyclerView.setAdapter(bakeryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }
}
