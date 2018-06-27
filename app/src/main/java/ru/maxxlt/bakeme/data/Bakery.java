package ru.maxxlt.bakeme.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.maxxlt.bakeme.utils.CallBake;

public class Bakery extends ViewModel {
    private MutableLiveData<List<Baking>> sharingIsCaringBakery = new MutableLiveData<>();

    public LiveData<List<Baking>> getSharingIsCaringBakery() {
        return sharingIsCaringBakery;
    }

    public Bakery() {
        CallBake callBake = new CallBake();
        callBake.getCallBake().enqueue(new Callback<List<Baking>>() {
            @Override
            public void onResponse(Call<List<Baking>> call, Response<List<Baking>> response) {
                sharingIsCaringBakery.setValue(response.body());
                Log.v("Bakery: ", "succeed to parse data");
            }

            @Override
            public void onFailure(Call<List<Baking>> call, Throwable t) {
                Log.v("Bakery: ", "failed to parse data");
            }
        });

    }
}
