package ch.bfh.cmf.web;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import ch.bfh.cmf.domain.Recipe;
import ch.bfh.cmf.repositories.RecipeRepository;

@Named("recipesPageBean")
@Scope("session")
public class RecipesPageBean {
	@Inject
	private RecipeRepository recipeRepository;
	
	public List<Recipe> getRecipes()
	{
		return recipeRepository.findAll();
	}
}
