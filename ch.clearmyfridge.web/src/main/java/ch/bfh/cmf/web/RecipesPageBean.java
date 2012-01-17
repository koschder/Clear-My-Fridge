package ch.bfh.cmf.web;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import ch.bfh.cmf.domain.Recipe;
import ch.bfh.cmf.domain.RecipeIngredientMapping;
import ch.bfh.cmf.repositories.RecipeRepository;

@Named("recipesPageBean")
@Scope("session")
public class RecipesPageBean {
	@Inject
	private RecipeRepository recipeRepository;

	private Recipe currentRecipe;

	private Object mappingToRemove;
	
	private RecipeIngredientMapping mappingToAdd = new RecipeIngredientMapping();
	

	public List<Recipe> getRecipes() {
		return recipeRepository.findAll();
	}

	public Object create() {
		currentRecipe = new Recipe();
		return "/recipe/create.xhtml";
	}

	public Object edit() {
		return "/recipe/edit.xhtml?faces-redirect=true";
	}

	public Object delete() {
		recipeRepository.delete(currentRecipe);
		return "/recipe/list.xhtml";
	}
	public Object save() {
		recipeRepository.save(currentRecipe);
		return "/search/index.xhtml";
	}

	public Recipe getRecipe() {
		//TODO hack for presentation
		if (currentRecipe == null) {
			currentRecipe = new Recipe();
		}
		return currentRecipe;
	}
	
	public void setRecipe(Recipe recipe) {
		this.currentRecipe = recipe;
	}
	
	public void removeIngredient() {
		currentRecipe.getIngredients().remove(this.mappingToRemove);
		this.save();
	}
	
	public void setMappingToRemove(RecipeIngredientMapping ingredient) {
		this.mappingToRemove = ingredient;
	}
	
	public RecipeIngredientMapping getMappingToAdd() {
		return mappingToAdd;
	}
	
	public void setMappingToAdd(RecipeIngredientMapping mapper) {
		this.mappingToAdd = mapper;
	}
	
	public void saveIngredient() {
		this.mappingToAdd.setRecipe(currentRecipe);
		currentRecipe.getIngredients().add(this.mappingToAdd);
		this.save();
		this.mappingToAdd = new RecipeIngredientMapping();
	}
}
