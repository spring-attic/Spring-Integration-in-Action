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
