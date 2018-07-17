package ru.maxxlt.bakeme.models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.maxxlt.bakeme.HostActivity;
import ru.maxxlt.bakeme.data.Baking;
import ru.maxxlt.bakeme.test.EspressoIdlingResource;
import ru.maxxlt.bakeme.test.Sample;
import ru.maxxlt.bakeme.utils.NetworkUtils;

public class BakingListViewModel extends AndroidViewModel {
    private static final String TAG = BakingListViewModel.class.getSimpleName();
    private MutableLiveData<List<Baking>> sharingIsCaringBakery;

    public BakingListViewModel(@NonNull Application application) {
        super(application);
        if (getSharingIsCaringBakery() == null){
            sharingIsCaringBakery = new MutableLiveData<>();
            NetworkUtils networkUtils = new NetworkUtils();
            EspressoIdlingResource.increment();
            networkUtils.getCallBake().enqueue(new Callback<List<Baking>>() {
                @Override
                public void onResponse(Call<List<Baking>> call, Response<List<Baking>> response) {
                    EspressoIdlingResource.decrement();
                    Log.v(TAG,"Data parsed!");
                    sharingIsCaringBakery.setValue(response.body());
                }

                @Override
                public void onFailure(Call<List<Baking>> call, Throwable t) {
                    EspressoIdlingResource.decrement();
                    Log.v(TAG,"Failed to pass data!");
                }
            });
            //offline test
//            Sample sample = new Sample();
//            sharingIsCaringBakery.setValue(sample.getBakingList());
        }

    }

    public LiveData<List<Baking>> getSharingIsCaringBakery() {
        return sharingIsCaringBakery;
    }
    public void setListToLiveData(List<Baking> bakingList) {
        this.sharingIsCaringBakery.setValue(bakingList);
    }
    public void postListToLiveData(List<Baking> bakingList){
        this.sharingIsCaringBakery.postValue(bakingList);
    }
}
