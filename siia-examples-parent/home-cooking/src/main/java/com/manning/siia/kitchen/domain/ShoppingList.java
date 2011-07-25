package com.manning.siia.kitchen.domain;

import com.google.common.collect.Lists;

import java.util.List;

/**
 */
public class ShoppingList {

	private List<Ingredient> items = Lists.newArrayList();

	private Ingredient.Type type;

	public ShoppingList(final Ingredient.Type type) {
		this.type = type;
	}

	public void addItem(Ingredient ingredient) {
        items.add(ingredient);
    }

    public List<Ingredient> getItems() {
        return items;
    }

	public Ingredient.Type getType() {
		return type;
	}

	@Override
	public String toString() {
		return "ShoppingList[" + type + "] with ingredients [" + items + "]";
	}
}
