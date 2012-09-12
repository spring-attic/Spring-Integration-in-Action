package com.manning.siia.kitchen.domain;

/**
 * @author Iwein Fuld
 */
public class Grocery extends Product {

	public Grocery(final String name, final Amount amount) {
		super(name, amount);
	}

	@Override
	protected boolean hasType(Ingredient.Type type) {
		return type.equals(Ingredient.Type.Grocery);
	}
}
