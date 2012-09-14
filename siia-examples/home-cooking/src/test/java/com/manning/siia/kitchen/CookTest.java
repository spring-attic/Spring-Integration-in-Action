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

import com.manning.siia.kitchen.domain.*;
import org.junit.Test;
import org.springframework.beans.DirectFieldAccessor;
import org.springframework.integration.Message;
import org.springframework.integration.support.MessageBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * @author Iwein Fuld
 */
public class CookTest {

	private final Cook cook = new Cook();
	private Recipe recipe = new Recipe("grub");

	@Test
	public void shouldPrepareMeal() throws Exception {
		List<Message<Product>> messages =
				Collections.<Message<Product>>singletonList(
						wrapInMessage(mock(Product.class)));
		assertThat(cook.prepareMeal(messages).getRecipe(), is(recipe));
	}

	@Test
	public void shouldCookAllProducts() {
		Grocery grocery = mock(Grocery.class);
		Meat meat = mock(Meat.class);
		List<Message<Product>> products = Arrays.asList(wrapInMessage(grocery), wrapInMessage(meat));

		Meal meal = cook.prepareMeal(products);
		DirectFieldAccessor accessor = new DirectFieldAccessor(meal);
		assertThat((List<Product>)accessor.getPropertyValue("products"), hasItems(grocery, meat));
	}

	private Message<Product> wrapInMessage(Product grocery) {
		return MessageBuilder.withPayload(grocery).setHeader("recipe", recipe).build();
	}
}
