package ru.maxxlt.bakeme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.maxxlt.bakeme.data.Baking;
import ru.maxxlt.bakeme.models.BakingListViewModel;
import ru.maxxlt.bakeme.ui.MainFragment;
import ru.maxxlt.bakeme.utils.NetworkUtils;

public class HostActivity extends AppCompatActivity {
    private static final String TAG = HostActivity.class.getSimpleName();
    NetworkUtils networkUtils;
    BakingListViewModel bakingListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        if (bakingListViewModel.getSharingIsCaringBakery() == null)
            startDataParsing(networkUtils, bakingListViewModel);

        if (findViewById(R.id.main_fragment) != null) {
            MainFragment mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment,mainFragment)
                    .commit();
        }
    }

    private void startDataParsing(NetworkUtils networkUtils, final BakingListViewModel bakingListViewModel){
        networkUtils.getCallBake().enqueue(new Callback<List<Baking>>() {
            @Override
            public void onResponse(Call<List<Baking>> call, Response<List<Baking>> response) {
                bakingListViewModel.postListToLiveData(response.body());
            }

            @Override
            public void onFailure(Call<List<Baking>> call, Throwable t) {
                Toast.makeText(HostActivity.this,"Failed to parse data",Toast.LENGTH_LONG).show();
            }
        });
    }


}
