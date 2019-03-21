/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.manning.siia.kitchen.domain;

/**
 * @author Iwein Fuld
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
