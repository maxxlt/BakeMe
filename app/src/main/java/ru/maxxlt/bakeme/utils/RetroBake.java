package ru.maxxlt.bakeme.utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.maxxlt.bakeme.data.Baking;

public interface RetroBake {
    @GET("baking.json")
    Call<List<Baking>> getData();
}
