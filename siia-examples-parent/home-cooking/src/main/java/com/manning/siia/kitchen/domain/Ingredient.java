package com.manning.siia.kitchen.domain;

import com.manning.siia.kitchen.domain.Amount;
import com.manning.siia.kitchen.domain.AmountConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("ingredient")
public class Ingredient {

	public enum Type {
		Meat, Vegetable, Grocery
	}

	@XStreamAsAttribute
	private String name;

	@XStreamConverter(AmountConverter.class)
	private Amount amount;

	@XStreamAsAttribute
	private Type type;

	public Ingredient(final String name, final Amount amount, final Type type) {
		this.name = name;
		this.amount = amount;
		this.type = type;
	}

	public boolean isSatisfiedBy(Product product) {
		return product.satisfies(name, amount, type);
	}

	public String getName() {
		return name;
	}

	public Amount getAmount() {
		return amount;
	}

	public Type getType() {
		return type;
	}

	@Override
	public String toString() {
		return type + "[" + amount + " of " + name + "]";
	}
}
