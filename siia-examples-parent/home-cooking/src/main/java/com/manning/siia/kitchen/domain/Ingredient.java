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
import com.manning.siia.kitchen.domain.AmountConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 * @author Iwein Fuld
 */
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

	public Ingredient() {
		// no-arg constructor for unmarshalling
	}

	public Ingredient(final String name, final Amount amount, final Type type) {
		this.name = name;
		this.amount = amount;
		this.type = type;
	}

	public boolean isSatisfiedBy(Product product) {
		return product.satisfies(name, amount, type);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}

	public Amount getAmount() {
		return amount;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	@Override
	public String toString() {
		return type + "[" + amount + " of " + name + "]";
	}
}
