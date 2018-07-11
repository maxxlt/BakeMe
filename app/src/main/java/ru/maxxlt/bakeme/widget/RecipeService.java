package ru.maxxlt.bakeme.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class RecipeService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new IngredientProvider(this,intent);
    }
}
