package ru.maxxlt.bakeme.models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.maxxlt.bakeme.data.Baking;
import ru.maxxlt.bakeme.utils.networkUtils;

public class BakingViewModel extends ViewModel {
    private MutableLiveData<List<Baking>> sharingIsCaringBakery;

    //getter
    public LiveData<List<Baking>> getSharingIsCaringBakery() {
        if (sharingIsCaringBakery == null){
            //pulling out data using Retrofit
            networkUtils networkUtils = new networkUtils();
            networkUtils.getCallBake().enqueue(new Callback<List<Baking>>() {
                @Override
                public void onResponse(Call<List<Baking>> call, Response<List<Baking>> response) {
                    sharingIsCaringBakery.setValue(response.body());
                    Log.v("BakingViewModel: ", "succeed to parse data");
                }

                @Override
                public void onFailure(Call<List<Baking>> call, Throwable t) {
                    Log.v("BakingViewModel: ", "failed to parse data");
                }
            });
        }
        return sharingIsCaringBakery;
    }

}
