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

import com.manning.siia.kitchen.domain.Ingredient;
import com.manning.siia.kitchen.domain.Product;
import com.manning.siia.kitchen.domain.Recipe;
import com.manning.siia.kitchen.domain.RecipeObjectMother;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.PollableChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author Iwein Fuld
 */
@ContextConfiguration(locations = {"/home-dinner-flow.xml", "/TEST-shopping.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ShoppingTest {
	@Autowired
	private MessageChannel recipes;

	@Autowired
	private PollableChannel test;

	@Test
	public void shouldConvertRecipeIntoIngredients() {
		Recipe recipe = RecipeObjectMother.steak();
		List<Ingredient> needed = RecipeObjectMother.steak().getIngredients();
		recipes.send(MessageBuilder.withPayload(recipe).build());
		receiveAndCheckProductMessage(needed);
		receiveAndCheckProductMessage(needed);
		receiveAndCheckProductMessage(needed);
	}

	private void receiveAndCheckProductMessage(final List<Ingredient> ingredients) {
		final TimedPollableChannel timed = new TimedPollableChannel(test);
		final Message<Product> message = timed.receive(2500);
		assertThat("Message was null", message, is(notNullValue()));
		assertThat(message.getHeaders().get("recipe"), is(instanceOf(Recipe.class)));
		boolean found = false;
		for (Ingredient ingredient : ingredients) {
			if (ingredient.isSatisfiedBy(message.getPayload())) {
				found = true;
				ingredients.remove(ingredient);
				break;
			}
		}
		assertThat(found, is(true));
	}
}
