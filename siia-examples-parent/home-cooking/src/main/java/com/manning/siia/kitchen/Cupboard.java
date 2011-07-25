package com.manning.siia.kitchen;

import com.google.common.collect.Lists;
import com.manning.siia.kitchen.domain.Ingredient;
import com.manning.siia.kitchen.domain.Product;
import org.springframework.integration.annotation.Filter;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 */
public class Cupboard {

	private List<Product> stock = Lists.newArrayList();

	@PostConstruct
	public void loadContents() {
		// TODO
	}

	@Filter
	public boolean inStock(Ingredient ingredient) {
		// TODO
		return false;
	}
}
