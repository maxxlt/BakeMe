package ru.maxxlt.bakeme.ui;

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

import ru.maxxlt.bakeme.R;

public class MainFragment extends Fragment{
    ArrayList<String> stringArrayList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main,container,false);
        RecyclerView recyclerView = rootView.findViewById(R.id.main_fragment_recycler);
        stringArrayList = new ArrayList<>();
        stringArrayList.add("Title1");
        stringArrayList.add("Title2");
        stringArrayList.add("Title3");
        stringArrayList.add("Title4");
        MainFragmentAdapter mainFragmentAdapter = new MainFragmentAdapter(stringArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mainFragmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }
}
