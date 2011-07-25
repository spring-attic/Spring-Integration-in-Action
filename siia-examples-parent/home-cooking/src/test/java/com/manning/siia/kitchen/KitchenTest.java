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
