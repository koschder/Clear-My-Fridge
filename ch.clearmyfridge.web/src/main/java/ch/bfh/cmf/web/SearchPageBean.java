package ch.bfh.cmf.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import ch.bfh.cmf.domain.Ingredient;
import ch.bfh.cmf.domain.Recipe;
import ch.bfh.cmf.repositories.IngredientRepository;
import ch.bfh.cmf.services.RecipeService;

@Named("searchPageBean")
@Scope("session")
public class SearchPageBean {

	@Inject
	private RecipeService recipeService;
	@Inject
	private IngredientRepository ingredientRepository;
	private List<Ingredient> fridgeContents = new ArrayList<Ingredient>();

	private Ingredient fridgeItem;

	public List<Recipe> getRecipes() {
		if (fridgeContents.isEmpty())
			return new ArrayList<Recipe>();
		return recipeService.findRecipesUsing(fridgeContents);
	}
	public Object removeItem()
	{
		fridgeContents.remove(fridgeItem);
		return "/search/index.xhtml";
	}
	public List<Ingredient> getFridgeContents() {
		return fridgeContents;
	}

	public List<Ingredient> getAvailableIngredients() {
		return ingredientRepository.findAll();
	}
	
	public String getFridgeItemId()
	{
		return "";
	}

	public void setFridgeItemId(String itemId) {
		this.fridgeItem = ingredientRepository.findOne(Long.valueOf(itemId));
	}

	public void setFridgeItem(Ingredient fridgeItem) {
		this.fridgeItem = fridgeItem;
	}

	public Ingredient getFridgeItem() {
		return fridgeItem;
	}

	public void addFridgeItem() {
		fridgeContents.add(fridgeItem);
	}
}
