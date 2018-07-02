package ru.maxxlt.bakeme;


import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.maxxlt.bakeme.models.BakingListViewModel;
import ru.maxxlt.bakeme.ui.MainFragment;

public class HostActivity extends AppCompatActivity implements MainFragment.ParseData {
    private static final String TAG = HostActivity.class.getSimpleName();
    private boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
    }


    @Override
    public void dataParsed(int position) {

    }
}
