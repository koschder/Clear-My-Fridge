package ch.bfh.cmf.services;

import java.util.Collection;
import java.util.List;

import ch.bfh.cmf.domain.Ingredient;
import ch.bfh.cmf.domain.Recipe;

public interface RecipeService {
	public List<Recipe> findRecipesUsing(Collection<Ingredient> ingredients);
}
