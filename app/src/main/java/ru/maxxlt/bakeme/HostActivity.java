package ru.maxxlt.bakeme;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ru.maxxlt.bakeme.ui.detail.DetailFragment;
import ru.maxxlt.bakeme.ui.main.MainFragment;
import ru.maxxlt.bakeme.ui.step.StepFragment;

public class HostActivity extends AppCompatActivity implements MainFragment.ParseData, DetailFragment.StepParse, StepFragment.OnButtonClickListener {
    private static final String TAG = HostActivity.class.getSimpleName();
    private boolean twoPane;
    DetailFragment detailFragment;
    StepFragment stepFragment;
    int positionMain, positionStep;
    Bundle stepBundle = new Bundle(), detailBundle = new Bundle(), buttonBundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        if (findViewById(R.id.bake_me_linear_layout) != null) {
            twoPane = true;
        }
        if (savedInstanceState == null) {
            if (findViewById(R.id.master_list_fragment) != null) {
                MainFragment mainFragment = new MainFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.master_list_fragment, mainFragment)
                        .commit();
            }
        }
    }


    @Override
    public void dataParsed(int position) {
        positionMain = position;
        if (detailBundle.containsKey("stepposition"))
            detailBundle.remove("stepposition");
        detailBundle.putInt("bakeposition", position);
        detailBundle.putBoolean("bigscreensize", twoPane);
        detailFragment = new DetailFragment();
        detailFragment.setArguments(detailBundle);
        if (twoPane) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_detail, detailFragment)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.master_list_fragment, detailFragment)
                    .commit();
        }

    }

    //https://stackoverflow.com/questions/45002707/back-navigation-in-title-bar-fragment
    @Override
    public void onBackPressed() {
        //check what fragment is current: Detail or Steps
        if (!twoPane) {
            if (detailFragment.isAdded()) {
                MainFragment mainFragment = new MainFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.master_list_fragment, mainFragment)
                        .commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.master_list_fragment, detailFragment)
                        .commit();
            }
        } else {
            if (detailFragment.isAdded()) {
                MainFragment mainFragment = new MainFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_detail, mainFragment)
                        .commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_detail, detailFragment)
                        .commit();
            }
        }
    }

    @Override
    public void stepParsed(int position) {
        positionStep = position;
        if (stepBundle.containsKey("stepposition"))
            stepBundle.remove("stepposition");
        stepBundle.putInt("stepposition", position);
        stepBundle.putInt("bakepostion", positionMain);
        stepFragment = new StepFragment();
        stepFragment.setArguments(stepBundle);
        if (twoPane){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_detail, stepFragment)
                    .commit();
        }
        else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.master_list_fragment, stepFragment)
                    .commit();
        }
    }

    @Override
    public void buttonClicked(int position) {
        positionStep = position;
        if (buttonBundle.containsKey("stepposition"))
            buttonBundle.remove("stepposition");
        buttonBundle.putInt("stepposition", position);
        buttonBundle.putInt("bakepostion", positionMain);
        stepFragment = new StepFragment();
        stepFragment.setArguments(buttonBundle);
        if (twoPane){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_detail, stepFragment)
                    .commit();
        }
        else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.master_list_fragment, stepFragment)
                    .commit();
        }
    }
}
