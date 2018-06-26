package ru.maxxlt.bakeme;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.maxxlt.bakeme.data.Baking;
import ru.maxxlt.bakeme.data.Bakings;
import ru.maxxlt.bakeme.data.RetroBake;
import ru.maxxlt.bakeme.ui.MainFragment;

public class HostActivity extends AppCompatActivity {
    public static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
    public ArrayList<String> titleArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        titleArrayList = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetroBake retroBake = retrofit.create(RetroBake.class);
        Call<Bakings> call = retroBake.getData();
        call.enqueue(new Callback<Bakings>() {
            @Override
            public void onResponse(Call<Bakings> call, Response<Bakings> response) {
                Bakings bakings = response.body();
                Toast.makeText(HostActivity.this,"Gottee " + bakings.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Bakings> call, Throwable t) {
                Toast.makeText(HostActivity.this,"Failed :)", Toast.LENGTH_LONG).show();
            }
        });

    }
}
