package ru.maxxlt.bakeme.test;

import java.util.ArrayList;
import java.util.List;

import ru.maxxlt.bakeme.data.Baking;
import ru.maxxlt.bakeme.data.Ingredients;
import ru.maxxlt.bakeme.data.Steps;

public class Sample {
    private List<Baking> bakingList = new ArrayList<>();
    private List<Ingredients> ingredientsList = new ArrayList<>();
    private List<Steps> stepsList = new ArrayList<>();

    public List<Baking> getBakingList() {
        Baking sampleBaking = new Baking();
        sampleBaking.setId(0);
        sampleBaking.setName("Lemon Drop");
        sampleBaking.setServings(50);
        Ingredients ingredient = new Ingredients();
        ingredient.setQuantity(100);
        ingredient.setMeasure("oz");
        ingredient.setIngredient("Vodka");
        for (int i = 0; i < 10; i++) {
            Steps step = new Steps();
            step.setId(0);
            step.setDescription("asneg asfijege wpofjefpj qo [ fsddjgpoa oe qe[fkafkldngwgpwfkkd afewjowtaff");
            step.setShortDescription("sakdkq qdqogewgm wfnwgnew eifjwe");
            stepsList.add(step);
        }
        ingredientsList.add(ingredient);

        sampleBaking.setIngredients(ingredientsList);
        sampleBaking.setSteps(stepsList);
        bakingList.add(sampleBaking);
        return bakingList;
    }
}
