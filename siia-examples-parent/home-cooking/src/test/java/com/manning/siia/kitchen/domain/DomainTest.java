package com.manning.siia.kitchen.domain;

import com.manning.siia.kitchen.domain.Ingredient;
import com.manning.siia.kitchen.domain.Recipe;
import com.thoughtworks.xstream.XStream;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.manning.siia.kitchen.domain.Amount.Unit;
import static com.manning.siia.kitchen.domain.Ingredient.Type;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Iwein Fuld
 */
public class DomainTest {
	private XStream xstream;

	@Before
	public void init() {
		xstream = new XStream();
		xstream.autodetectAnnotations(true);
		xstream.processAnnotations(new Class[]{Recipe.class, Ingredient.class});
	}

	@Test
	public void shouldHydrateXmlFile() throws IOException {
		final Object o = xstream.fromXML(new ClassPathResource("pilav.xml").getInputStream());
		assertThat(o, instanceOf(Recipe.class));
		Recipe r = (Recipe) o;
		assertThat(r.getIngredients().size(), is(6));
		System.out.println(r.getIngredients());
	}

	@Test
	public void shouldMarshallDomain() {
		Recipe r = new Recipe("Nasi");
		Ingredient i = new Ingredient("Beef", new Amount(500, Unit.GRAMS), Type.Meat);
		r.addIngredient(i);
		r.addIngredient(new Ingredient("Red Sweet Pepper", new Amount(1, Unit.PIECES), Type.Vegetable));

		System.out.println(xstream.toXML(r));
	}
}
