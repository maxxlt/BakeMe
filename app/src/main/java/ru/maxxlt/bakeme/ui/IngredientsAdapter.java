package ru.maxxlt.bakeme.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import ru.maxxlt.bakeme.R;
import ru.maxxlt.bakeme.data.Ingredients;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder>{
    private List<Ingredients> ingredientsList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_detail_ingredients_material,
                viewGroup,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Ingredients ingredient = ingredientsList.get(i);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        viewHolder.quantity.setText(decimalFormat.format(ingredient.getQuantity()));
        viewHolder.measure.setText(ingredient.getMeasure());
        viewHolder.ingredient.setText(ingredient.getIngredient());
    }

    @Override
    public int getItemCount() {
        if (ingredientsList != null){
            return ingredientsList.size();
        }
        return 0;
    }

    public void setIngredientsList(List<Ingredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView quantity,measure,ingredient;

        public ViewHolder(View itemView){
            super(itemView);
            quantity = itemView.findViewById(R.id.quantity_tv);
            measure = itemView.findViewById(R.id.measure_tv);
            ingredient = itemView.findViewById(R.id.ingredient_tv);
        }
    }
}
