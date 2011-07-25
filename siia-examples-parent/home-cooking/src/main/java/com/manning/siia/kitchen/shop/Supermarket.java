package com.manning.siia.kitchen.shop;

import com.manning.siia.kitchen.domain.Grocery;
import com.manning.siia.kitchen.domain.GroceryBag;
import com.manning.siia.kitchen.domain.Ingredient;
import com.manning.siia.kitchen.domain.ShoppingList;
import org.springframework.integration.annotation.Transformer;

/**
 */
public class Supermarket {

	@Transformer
	public Grocery sell(Ingredient ingredient) {
		return new Grocery(ingredient.getName(), ingredient.getAmount());
	}

    @Transformer
    public GroceryBag<Grocery> sell(ShoppingList shoppingList) {
        GroceryBag<Grocery> groceryBag = new GroceryBag<Grocery>();
        for (Ingredient ingredient : shoppingList.getItems()) {
            groceryBag.put(sell(ingredient));
        }

        return groceryBag;
    }

}
