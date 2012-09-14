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

package com.manning.siia.kitchen;

import com.manning.siia.kitchen.domain.Ingredient;
import com.manning.siia.kitchen.domain.ShoppingList;
import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.annotation.CorrelationStrategy;

import java.util.List;

/**
 * I write out shoppinglists, whilst ensuring that on each shoppinglist only ingredients
 * of the same type are added, and we write at least 5 items on a single list.
 * 
 * @author Jeroen van Erp
 */
public class ShoppingListWriter {

    @Aggregator
    public ShoppingList sendShoppingList(List<Ingredient> ingredients) {
        ShoppingList list = new ShoppingList(ingredients.get(0).getType());
        for (Ingredient ingredient : ingredients) {
            list.addItem(ingredient);
        }
        return list;
    }

    @CorrelationStrategy
    public Ingredient.Type putOnCorrectList(Ingredient ingredient) {
        return ingredient.getType();
    }

}
