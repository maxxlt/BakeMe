package ru.maxxlt.bakeme.utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import ru.maxxlt.bakeme.data.Baking;

public class networkUtils {
    private static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
    private RetroBake retroBake;

    public networkUtils() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.retroBake = retrofit.create(RetroBake.class);
    }

    public Call<List<Baking>> getCallBake(){
        return retroBake.getData();
    }

    private interface RetroBake {
        @GET("baking.json")
        Call<List<Baking>> getData();
    }
}
