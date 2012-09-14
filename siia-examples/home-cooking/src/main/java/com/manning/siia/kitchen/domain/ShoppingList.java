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

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author Iwein Fuld
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
