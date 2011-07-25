package com.manning.siia.kitchen.domain;

/**
 */
public abstract class Product {
	private final String name;
	private final Amount amount;

	public Product(final String name, final Amount amount) {
		this.name = name;
		this.amount = amount;
	}

	public boolean satisfies(String name, Amount amount, Ingredient.Type type) {
		return this.name.equals(name) && this.amount.equals(amount) && hasType(type);

	}

	protected abstract boolean hasType(Ingredient.Type type);

	@Override
	public String toString() {
		return "Product[" + amount + " of " + name + "]";
	}
}
