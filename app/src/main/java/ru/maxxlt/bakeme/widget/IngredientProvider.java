package ru.maxxlt.bakeme.widget;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.maxxlt.bakeme.R;
import ru.maxxlt.bakeme.data.Baking;
import ru.maxxlt.bakeme.data.Ingredients;
import ru.maxxlt.bakeme.models.BakingListViewModel;
import ru.maxxlt.bakeme.utils.NetworkUtils;

public class IngredientProvider implements RemoteViewsService.RemoteViewsFactory {


    private static final String TAG = "IngredientProvider";


    private Context mContext;
    private Intent mIntent;
    private List<String> mIngrList;

    public IngredientProvider(Context context, Intent intent) {
        mContext = context;
        mIntent = intent;
    }

    @Override
    public void onCreate() {
        mIngrList = mIntent.getStringArrayListExtra("ingrlist");
        Log.v(TAG,"mIngrList created: " + mIngrList.size());

    }

    @Override
    public void onDataSetChanged() {
        mIngrList = mIntent.getStringArrayListExtra("ingrlist");
        Log.v(TAG,"mIngrList updated: " + mIngrList.size());
    }

    @Override
    public void onDestroy() {
        mIngrList.clear();
    }

    @Override
    public int getCount() {
        if (mIngrList != null)
            return mIngrList.size();
        else return 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews view = new RemoteViews(mContext.getPackageName(),
                android.R.layout.simple_list_item_1);
        view.setTextViewText(android.R.id.text1, mIngrList.get(position));
        return view;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }


}
