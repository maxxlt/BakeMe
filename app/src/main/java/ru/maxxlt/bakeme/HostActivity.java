package ru.maxxlt.bakeme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.maxxlt.bakeme.data.Baking;
import ru.maxxlt.bakeme.ui.MainFragment;
import ru.maxxlt.bakeme.utils.RetroBake;

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
