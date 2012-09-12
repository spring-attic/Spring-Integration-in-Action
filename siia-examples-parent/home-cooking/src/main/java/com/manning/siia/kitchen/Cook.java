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

package com.manning.siia.kitchen;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.manning.siia.kitchen.domain.Meal;
import com.manning.siia.kitchen.domain.Product;
import com.manning.siia.kitchen.domain.Recipe;
import org.springframework.integration.Message;
import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.annotation.CorrelationStrategy;
import org.springframework.integration.annotation.ReleaseStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * I am in control of the kitchen.
 *
 * @author Iwein Fuld
 */
public class Cook {

	@Aggregator
	public Meal prepareMeal(List<Message<Product>> products) {
		Recipe recipe = (Recipe) products.get(0).getHeaders().get("recipe");
		Meal meal = new Meal(recipe);
		for (Message<Product> message : products) {
			meal.cook(message.getPayload());
		}
		return meal;
	}

	@CorrelationStrategy
	public Object correlatingRecipeFor(Message<Product> message) {
		return message.getHeaders().get("recipe");
	}

	@ReleaseStrategy
	public boolean canCookMeal(List<Message<?>> products) {
		Recipe recipe = (Recipe) products.get(0).getHeaders().get("recipe");
		return recipe.isSatisfiedBy(productsFromMessages(products));
	}

	private ArrayList<Product> productsFromMessages(List<Message<?>> group) {
		return new ArrayList<Product>(
				Collections2.transform(group, new Function<Message<?>, Product>() {
					public Product apply(Message<?> from) {
						return (Product) from.getPayload();
					}
				}));
	}
}
