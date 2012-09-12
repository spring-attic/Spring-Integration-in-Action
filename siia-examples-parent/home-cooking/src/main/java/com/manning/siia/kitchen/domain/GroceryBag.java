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

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author Iwein Fuld
 */
public class GroceryBag<T extends Product> {

	private Set<T> items = Sets.newHashSet();

	public void put(T item) {
        items.add(item);
    }

    public Set<T> unpack() {
        return items;
    }

	@Override
	public String toString() {
		return "GroceryBag[" + items + "]";
	}
}
