package ru.maxxlt.bakeme;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import ru.maxxlt.bakeme.data.Baking;
import ru.maxxlt.bakeme.models.BakingListViewModel;
import ru.maxxlt.bakeme.ui.MainFragment;

public class HostActivity extends AppCompatActivity {
    private static final String TAG = HostActivity.class.getSimpleName();
    private static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking//";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        if (findViewById(R.id.main_fragment) != null) {
            MainFragment mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment, mainFragment)
                    .commit();
        }
    }



}
