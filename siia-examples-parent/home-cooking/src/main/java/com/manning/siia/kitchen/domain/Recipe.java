package com.manning.siia.kitchen.domain;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.Collection;
import java.util.List;

/**
 * A Recipe consists of Ingredients and has a name.
 */
@XStreamAlias("recipe")
public class Recipe {

	@XStreamAsAttribute
	private String name;

	@XStreamImplicit
	@XStreamAlias("ingredients")
	private List<Ingredient> ingredients = Lists.newArrayList();

	public Recipe() {
		// no-arg constructor for unmarshalling
	}

	public Recipe(final String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void addIngredient(Ingredient i) {
		ingredients.add(i);
	}

	public Integer itemsOfType(final Ingredient.Type type) {
		return Collections2.filter(ingredients, new Predicate<Ingredient>() {
			@Override
			public boolean apply(final Ingredient input) {
				return input.getType() == type;
			}
		}).size();
	}

	public Boolean isSatisfiedBy(final List<Product> products) {
        Collection<Ingredient> unsatisfiedIngredients = findMissingIngredients(products);
        return unsatisfiedIngredients.isEmpty();
	}

    private Collection<Ingredient> findMissingIngredients(final List<Product> products) {
        return Collections2.filter(ingredients, new Predicate<Ingredient>() {
            public boolean apply(Ingredient input) {
                for (Product mealIngredient : products) {
                    if (input.isSatisfiedBy(mealIngredient)) {
                        return false;
                    }
                }
                return true;
            }
        });
    }

	@Override
	public String toString() {
		return "Recipe[" + name + "]";
	}
}
