package com.manning.siia.kitchen.shop;


import com.manning.siia.kitchen.domain.GroceryBag;
import com.manning.siia.kitchen.domain.Ingredient;
import com.manning.siia.kitchen.domain.Meat;
import com.manning.siia.kitchen.domain.ShoppingList;
import org.springframework.integration.annotation.Transformer;

/**
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
