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
