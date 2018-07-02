package ru.maxxlt.bakeme.ui;

import android.arch.lifecycle.Observer;
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

import java.util.List;

import ru.maxxlt.bakeme.R;
import ru.maxxlt.bakeme.data.Baking;
import ru.maxxlt.bakeme.data.Ingredients;
import ru.maxxlt.bakeme.data.Steps;
import ru.maxxlt.bakeme.models.BakingListViewModel;

public class DetailFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail,container,false);
        final int position = getArguments().getInt("bakeposition");
        Log.v("DetailFragment: ","position " + position);
        RecyclerView ingredientsView = rootView.findViewById(R.id.ingredients_rv);
        RecyclerView stepsView = rootView.findViewById(R.id.steps_rv);

        final IngredientsAdapter ingredientsAdapter = new IngredientsAdapter();
        final StepsAdapter stepsAdapter = new StepsAdapter();

        BakingListViewModel bakingListViewModel = ViewModelProviders.of(getActivity()).get(BakingListViewModel.class);

        bakingListViewModel.getSharingIsCaringBakery().observe(this, new Observer<List<Baking>>() {
            @Override
            public void onChanged(@Nullable List<Baking> bakings) {

                List<Ingredients> ingredientsList = bakings.get(position).getIngredients();
                List<Steps> stepsList = bakings.get(position).getSteps();

                ingredientsAdapter.setIngredientsList(ingredientsList);
                stepsAdapter.setStepsList(stepsList);

                ingredientsAdapter.notifyDataSetChanged();
                stepsAdapter.notifyDataSetChanged();
            }
        });

        ingredientsView.setAdapter(ingredientsAdapter);
        ingredientsView.setLayoutManager(new LinearLayoutManager(getActivity()));
        stepsView.setAdapter(stepsAdapter);
        stepsView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }
}
