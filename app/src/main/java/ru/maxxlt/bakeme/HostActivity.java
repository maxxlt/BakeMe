package ru.maxxlt.bakeme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.maxxlt.bakeme.ui.MainFragment;

public class HostActivity extends AppCompatActivity {
    private static final String TAG = HostActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        if (findViewById(R.id.main_fragment) != null) {
            MainFragment mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment,mainFragment)
                    .commit();
        }
    }


}
