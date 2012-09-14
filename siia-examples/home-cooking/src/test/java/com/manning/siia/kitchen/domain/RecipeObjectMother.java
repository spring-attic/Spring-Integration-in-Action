/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.manning.siia.kitchen.domain;

import com.manning.siia.kitchen.domain.Amount;
import com.manning.siia.kitchen.domain.Ingredient;
import com.manning.siia.kitchen.domain.Recipe;

/** 
 * @author Iwein Fuld
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
