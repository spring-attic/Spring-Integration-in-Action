package com.manning.siia.kitchen.shop;

import com.manning.siia.kitchen.domain.GroceryBag;
import com.manning.siia.kitchen.domain.Ingredient;
import com.manning.siia.kitchen.domain.ShoppingList;
import com.manning.siia.kitchen.domain.Vegetable;
import org.springframework.integration.annotation.Transformer;

/**
 */
public class GreenGrocer {

	@Transformer
	public Vegetable sell(Ingredient ingredient) {
		return new Vegetable(ingredient.getName(), ingredient.getAmount());
	}

    @Transformer
    public GroceryBag<Vegetable> sell(ShoppingList shoppingList) {
        GroceryBag<Vegetable> groceryBag = new GroceryBag<Vegetable>();
        for (Ingredient ingredient : shoppingList.getItems()) {
            groceryBag.put(sell(ingredient));
        }

        return groceryBag;
    }

}
