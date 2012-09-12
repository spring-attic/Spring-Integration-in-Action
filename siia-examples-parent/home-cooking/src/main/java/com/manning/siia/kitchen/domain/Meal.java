package com.manning.siia.kitchen.domain;

import com.google.common.collect.Lists;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author Iwein Fuld
 */
public class Meal {

	private List<Product> products = Lists.newArrayList();

	private Recipe recipe;

	public Meal(final Recipe recipe) {
		Assert.notNull(recipe, "Cannot make a meal without a recipe");
		this.recipe = recipe;
	}

	public void cook(Product ingredient) {
		products.add(ingredient);
	}

	/**
	 * @return true when All Ingredients from the Recipe are in the Meal
	 */
	public boolean isDone() {
		return recipe.isSatisfiedBy(products);
	}

	public Recipe getRecipe() {
		return recipe;
	}
}
