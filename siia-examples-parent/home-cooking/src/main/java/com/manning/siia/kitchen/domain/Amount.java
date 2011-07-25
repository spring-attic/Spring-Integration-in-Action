package com.manning.siia.kitchen.domain;

/**
 */
public class Amount {

	private int amount;

	private Unit unit;

	public enum Unit { PIECES, GRAMS }

	public Amount(final int amount, final Unit unit) {
		this.amount = amount;
		this.unit = unit;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final Amount amount1 = (Amount) o;

		if (amount != amount1.amount) return false;
		if (unit != amount1.unit) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = amount;
		result = 31 * result + unit.hashCode();
		return result;
	}

	public int getAmount() {
		return amount;
	}

	public Unit getUnit() {
		return unit;
	}

	@Override
	public String toString() {
		return amount + " " + unit.name().toLowerCase();
	}
}
