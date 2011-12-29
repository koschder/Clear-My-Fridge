package ch.bfh.cmf.services;

import java.util.Collection;

import ch.bfh.cmf.domain.Ingredient;

public interface IngredientService {
	public Collection<Ingredient> searchIngredients(String partialIngredientName);
}
