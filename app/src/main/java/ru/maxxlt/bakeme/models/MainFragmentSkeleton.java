package ru.maxxlt.bakeme.models;

import ru.maxxlt.bakeme.data.Baking;

public class MainFragmentSkeleton {
    private Baking baking;
    private DetailViewModel detailViewModel;

    public MainFragmentSkeleton(Baking baking) {
        this.baking = baking;
    }

    public Baking getBaking() {
        return baking;
    }
}
