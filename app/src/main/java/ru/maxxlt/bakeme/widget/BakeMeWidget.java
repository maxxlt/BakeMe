package ru.maxxlt.bakeme.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.maxxlt.bakeme.R;
import ru.maxxlt.bakeme.data.Baking;
import ru.maxxlt.bakeme.data.Ingredients;
import ru.maxxlt.bakeme.utils.NetworkUtils;

/**
 * Implementation of App Widget functionality.
 */
public class BakeMeWidget extends AppWidgetProvider {

    private static final String TAG = "BakeMeWidget";
    private static final String ACTION_LEFTCLICK = "ACTION_LEFTCLICK";
    private static final String ACTION_RIGHTCLICK = "ACTION_RIGHTCLICK";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        // Construct the RemoteViews object
        setupAdapter(0,context, appWidgetManager, appWidgetId);


    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds,R.id.widget_list);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
    private static void setupAdapter(final int position, final Context context, final AppWidgetManager appWidgetManager, final int appWidgetId){
        final ArrayList<String> stringArrayList = new ArrayList<>();
        NetworkUtils networkUtils = new NetworkUtils();
        networkUtils.getCallBake().enqueue(new Callback<List<Baking>>() {
            @Override
            public void onResponse(Call<List<Baking>> call, Response<List<Baking>> response) {
                Baking baking;
                boolean outOfBound = false;
                if (position >= response.body().size()){
                    baking = response.body().get(0);
                    outOfBound = true;
                }
                else if (position < 0){
                    baking = response.body().get(response.body().size()-1);
                    outOfBound = true;
                }
                else {
                    baking = response.body().get(position);
                }
                for (int i = 0; i < baking.getIngredients().size();i++){
                    Ingredients ingredient = baking.getIngredients().get(i);
                    String string = ingredient.getQuantity() + " " + ingredient.getMeasure() + " " + ingredient.getIngredient();
                    stringArrayList.add(string);
                }

                RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.bake_me_widget);

                //UI
                Log.v(TAG,"test stringArrayList: " + stringArrayList.get(0));
                Intent intent = new Intent(context,RecipeService.class);
                intent.putStringArrayListExtra("ingrlist", stringArrayList);
                Random random = new Random();
                intent.setType(String.valueOf(random.nextInt(1000)));
                views.setTextViewText(R.id.widget_recipe_title_tv,baking.getName());
                views.setRemoteAdapter(R.id.widget_list, intent);


                //ClickHandler
                Intent rightClickIntent = new Intent(context, BakeMeWidget.class);
                rightClickIntent.setAction(ACTION_RIGHTCLICK);
                rightClickIntent.putExtra("appWidgetId",appWidgetId);
                if (outOfBound)
                    rightClickIntent.putExtra("position",0);
                else
                    rightClickIntent.putExtra("position",position);
                PendingIntent rightClickPendingIntent = PendingIntent.getBroadcast(context,0,rightClickIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                Intent leftClickIntent = new Intent(context, BakeMeWidget.class);
                leftClickIntent.setAction(ACTION_LEFTCLICK);
                leftClickIntent.putExtra("appWidgetId",appWidgetId);
                if (outOfBound)
                    leftClickIntent.putExtra("position",response.body().size()-1);
                else
                    leftClickIntent.putExtra("position",position);
                PendingIntent leftClickPendingIntent = PendingIntent.getBroadcast(context,0,leftClickIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                views.setOnClickPendingIntent(R.id.widget_button_left,leftClickPendingIntent);
                views.setOnClickPendingIntent(R.id.widget_button_right,rightClickPendingIntent);

                appWidgetManager.updateAppWidget(appWidgetId, views);
            }

            @Override
            public void onFailure(Call<List<Baking>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        int pos = intent.getIntExtra("position",100);
        if (ACTION_RIGHTCLICK.equals(intent.getAction())){
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            int appWidgetId = intent.getIntExtra("appWidgetId",100);
            setupAdapter(pos+1,context, appWidgetManager, appWidgetId);
        }
        else if (ACTION_LEFTCLICK.equals(intent.getAction())) {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            int appWidgetId = intent.getIntExtra("appWidgetId",100);
            setupAdapter(pos-1,context, appWidgetManager, appWidgetId);
        }

        super.onReceive(context, intent);
    }


}

