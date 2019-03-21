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

package com.manning.siia.kitchen;

import com.manning.siia.kitchen.domain.Meal;
import com.manning.siia.kitchen.domain.Recipe;
import com.manning.siia.kitchen.domain.RecipeObjectMother;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.PollableChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/** 
 * @author Iwein Fuld
 */
@ContextConfiguration(locations = "/home-dinner-flow.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class KitchenTest {
	@Autowired
	private MessageChannel recipes;

	@Autowired
	private PollableChannel meals;

	@Test
	public void shouldPrepareMealFromRecipe() {
		final TimedPollableChannel timed = new TimedPollableChannel(meals);
		Recipe r = RecipeObjectMother.steak();
		recipes.send(MessageBuilder.withPayload(r).build());

		final Message<Meal> message = timed.receive(2500);
		assertThat("Message was null", message, Matchers.is(notNullValue()));
		final Meal meal = message.getPayload();
		assertThat(meal.getRecipe(), is(r));
		assertThat(meal.isDone(), is(true));
	}
}
