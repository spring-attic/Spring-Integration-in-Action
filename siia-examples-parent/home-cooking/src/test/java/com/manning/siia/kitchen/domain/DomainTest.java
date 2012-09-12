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
