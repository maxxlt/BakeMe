package ru.maxxlt.bakeme;


import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.maxxlt.bakeme.models.BakingListViewModel;
import ru.maxxlt.bakeme.ui.DetailFragment;
import ru.maxxlt.bakeme.ui.MainFragment;

public class HostActivity extends AppCompatActivity implements MainFragment.ParseData {
    private static final String TAG = HostActivity.class.getSimpleName();
    private boolean twoPane;
    DetailFragment detailFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        if (findViewById(R.id.bake_me_linear_layout) != null){
            twoPane = true;
            //NEEDS EDIT HERE
        }
    }


    @Override
    public void dataParsed(int position) {
        //NEEDS EDIT HERE
        Bundle bundle = new Bundle();
        bundle.putInt("bakeposition",position);
        detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_detail,detailFragment)
                .commit();
    }
}
