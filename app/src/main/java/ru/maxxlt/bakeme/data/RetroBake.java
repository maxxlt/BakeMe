package ru.maxxlt.bakeme.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetroBake {
    @GET("baking.json")
    Call<List<Baking>> getData();
}
