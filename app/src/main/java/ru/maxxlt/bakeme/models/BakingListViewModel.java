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
import ru.maxxlt.bakeme.utils.NetworkUtils;

public class BakingListViewModel extends ViewModel {
    private MutableLiveData<List<Baking>> sharingIsCaringBakery;

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
