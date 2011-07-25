package com.manning.siia.kitchen.domain;

import com.manning.siia.kitchen.domain.Amount;
import com.manning.siia.kitchen.domain.Ingredient;
import com.manning.siia.kitchen.domain.Recipe;

/**
 */
public class RecipeObjectMother {

	public static Recipe friedEggRecipe() {
		Recipe recipe = new Recipe("fried egg");
		recipe.addIngredient(new Ingredient("egg", new Amount(1, Amount.Unit.PIECES), Ingredient.Type.Grocery));
		recipe.addIngredient(new Ingredient("butter", new Amount(20, Amount.Unit.GRAMS), Ingredient.Type.Grocery));
		return recipe;
	}

	public static Recipe steak() {
		Recipe recipe = new Recipe("steak");
		recipe.addIngredient(new Ingredient("steak", new Amount(1, Amount.Unit.PIECES), Ingredient.Type.Meat));
		recipe.addIngredient(new Ingredient("pepper", new Amount(2, Amount.Unit.GRAMS), Ingredient.Type.Grocery));
		recipe.addIngredient(new Ingredient("salt", new Amount(2, Amount.Unit.GRAMS), Ingredient.Type.Grocery));
		return recipe;
	}
}
