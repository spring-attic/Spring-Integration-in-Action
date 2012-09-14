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

import com.manning.siia.kitchen.domain.Meal;
import com.manning.siia.kitchen.domain.Product;
import com.manning.siia.kitchen.domain.Recipe;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * @author Iwein Fuld
 */
public class MealTest {

	private final Recipe recipe = mock(Recipe.class);
	private final Meal meal = new Meal(recipe);

	@Test
	public void shouldBeDoneWhenAllIngredientsAreCooked() throws Exception {
		Product soleProduct = mock(Product.class);
		given(recipe.isSatisfiedBy(Arrays.asList(soleProduct))).willReturn(true);

		meal.cook(soleProduct);
        
		assertThat(meal.isDone(), is(true));
	}

}
