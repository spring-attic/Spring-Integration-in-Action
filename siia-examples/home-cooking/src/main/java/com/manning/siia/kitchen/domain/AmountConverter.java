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

import com.manning.siia.kitchen.domain.Amount;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import static com.manning.siia.kitchen.domain.Amount.Unit;

/**
 * @author Iwein Fuld
 */
public class AmountConverter implements Converter {
	public void marshal(final Object source, final HierarchicalStreamWriter writer, final MarshallingContext context) {
		Amount amount = (Amount) source;
		StringBuilder builder = new StringBuilder();
		builder.append(amount.getAmount());
		if (amount.getUnit() == Unit.GRAMS) {
			builder.append(" gr");
		}
		writer.setValue(builder.toString());
	}

	public Object unmarshal(final HierarchicalStreamReader reader, final UnmarshallingContext context) {
		final String value = reader.getValue();
		final String[] strings = value.split(" ");
		if (strings.length == 1) {
			return new Amount(Integer.parseInt(strings[0]), Unit.PIECES);
		} else if (strings[1].trim().equalsIgnoreCase("gr")) {
			return new Amount(Integer.parseInt(strings[0]), Unit.GRAMS);
		} else {
			throw new IllegalArgumentException("Could not parse Amount: " + value);
		}
	}

	public boolean canConvert(final Class type) {
		return type.equals(Amount.class);
	}
}
