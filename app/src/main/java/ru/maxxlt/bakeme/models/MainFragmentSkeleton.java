package ru.maxxlt.bakeme.models;

import ru.maxxlt.bakeme.data.Baking;

public class MainFragmentSkeleton {
    private Baking baking;
    private DetailViewModel detailViewModel;

    public MainFragmentSkeleton(Baking baking, DetailViewModel detailViewModel) {
        this.baking = baking;
        this.detailViewModel = detailViewModel;
    }

    public Baking getBaking() {
        return baking;
    }
}
