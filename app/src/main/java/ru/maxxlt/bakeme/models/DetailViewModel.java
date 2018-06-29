package ru.maxxlt.bakeme.models;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import ru.maxxlt.bakeme.data.Baking;

public class DetailViewModel extends ViewModel{
    private MutableLiveData<Baking> bakingMutableLiveData;

    public void setLiveData(Baking baking){
        this.bakingMutableLiveData.setValue(baking);
    }
    public void postLiveData(Baking baking){
        this.bakingMutableLiveData.postValue(baking);
    }
}
