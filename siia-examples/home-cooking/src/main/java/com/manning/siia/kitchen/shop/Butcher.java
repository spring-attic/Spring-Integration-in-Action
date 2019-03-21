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

package com.manning.siia.kitchen.shop;

import com.manning.siia.kitchen.domain.GroceryBag;
import com.manning.siia.kitchen.domain.Ingredient;
import com.manning.siia.kitchen.domain.Meat;
import com.manning.siia.kitchen.domain.ShoppingList;
import org.springframework.integration.annotation.Transformer;

/** 
 * @author Iwein Fuld
 */
public class Butcher {

	@Transformer
	public Meat sell(Ingredient ingredient) {
		return new Meat(ingredient.getName(), ingredient.getAmount());
	}

    @Transformer
    public GroceryBag<Meat> sell(ShoppingList shoppingList) {
        GroceryBag<Meat> groceryBag = new GroceryBag<Meat>();
        for (Ingredient ingredient : shoppingList.getItems()) {
            groceryBag.put(sell(ingredient));
        }
        return groceryBag;
    }
}
