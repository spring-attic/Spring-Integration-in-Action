package com.manning.siia.kitchen.domain;

import com.manning.siia.kitchen.domain.Ingredient;
import com.manning.siia.kitchen.domain.Product;
import com.manning.siia.kitchen.domain.Recipe;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * @author Iwein Fuld
 */
public class RecipeTest {

	private final Recipe recipe = new Recipe("grub");

	@Test
	public void shouldNotBeSatisfiedByEmptyList() throws Exception {
		Ingredient ingredient = mock(Ingredient.class);
		recipe.addIngredient(ingredient);
		assertThat(recipe.isSatisfiedBy(Collections.<Product>emptyList()), is(false));
	}
	
	@Test
	public void shouldBeSatisfiedByCorrectIngredients() throws Exception {
		Ingredient ingredient = mock(Ingredient.class);
		Product product = mock(Product.class);
		given(ingredient.isSatisfiedBy(product)).willReturn(true);

		recipe.addIngredient(ingredient);
		assertThat(recipe.isSatisfiedBy(Arrays.asList(product)), is(true));
	}
}
