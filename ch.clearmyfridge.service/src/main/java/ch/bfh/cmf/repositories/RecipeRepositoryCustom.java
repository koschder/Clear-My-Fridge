package ch.bfh.cmf.repositories;

import java.util.Collection;
import java.util.List;


import ch.bfh.cmf.domain.Ingredient;
import ch.bfh.cmf.domain.Recipe;

public interface RecipeRepositoryCustom {

	List<Recipe> findByIngredients(Collection<Ingredient> ingredients);
}
