package ch.bfh.cmf.services;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ch.bfh.cmf.domain.Ingredient;
import ch.bfh.cmf.domain.Recipe;
import ch.bfh.cmf.repositories.RecipeRepository;

@Service("recipeService")
public class RecipeServiceImpl implements RecipeService {

	@Inject 
	RecipeRepository recipeRepository;
	@Override
	public List<Recipe> findRecipesUsing(Collection<Ingredient> ingredients) {
		return recipeRepository.findByIngredients(ingredients);
	}

}
