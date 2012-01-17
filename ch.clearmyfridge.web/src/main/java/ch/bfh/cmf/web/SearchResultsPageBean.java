package ch.bfh.cmf.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import ch.bfh.cmf.domain.Ingredient;
import ch.bfh.cmf.domain.Recipe;
import ch.bfh.cmf.services.RecipeService;

@Named("searchResultPageBean")
@Scope("session")
public class SearchResultsPageBean {

	@Inject
	private RecipeService recipeService;
	private Collection<Ingredient> ingredients = new ArrayList<Ingredient>();

	private Ingredient ingredient;
	private String ingredientName;

	public List<Recipe> getRecipes() {
		if (ingredients.isEmpty())
			return new ArrayList<Recipe>();
		return recipeService.findRecipesUsing(ingredients);
	}

	public Collection<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void addIngredient() {
		ingredients.add(new Ingredient(ingredientName));
	}

	public void removeIngredient() {
		ingredients.remove(ingredient);
	}

	public void setIngredients(Collection<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	

}
