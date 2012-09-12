package com.manning.siia.kitchen.domain;

/**
 * @author Iwein Fuld
 */
public class Meat extends Product {

	public Meat(final String name, Amount amount) {
		super(name, amount);
	}

	@Override
	protected boolean hasType(Ingredient.Type type) {
		return type.equals(Ingredient.Type.Meat);
	}
}
