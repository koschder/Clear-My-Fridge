package ch.bfh.cmf.web;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import ch.bfh.cmf.domain.Ingredient;
import ch.bfh.cmf.domain.Rating;
import ch.bfh.cmf.domain.Recipe;
import ch.bfh.cmf.domain.RecipeIngredientMapping;
import ch.bfh.cmf.domain.User;
import ch.bfh.cmf.repositories.IngredientRepository;
import ch.bfh.cmf.repositories.RecipeRepository;
import ch.bfh.cmf.repositories.UserRepository;

@Named("recipesPageBean")
@Scope("session")
public class RecipesPageBean {
	@Inject
	private RecipeRepository recipeRepository;
	
	@Inject
	private IngredientRepository ingredientRepository;

	private Recipe currentRecipe;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Inject
	private UserRepository userRepository;

	private Object mappingToRemove;
	
	private RecipeIngredientMapping mappingToAdd = new RecipeIngredientMapping();
	
	private Long fridgeItemId;
	private int quantity;

	private int ratingValueToSave;

	public int getRatingValueToSave() {
		return ratingValueToSave;
	}

	public void setRatingValueToSave(int ratingValue) {
		this.ratingValueToSave = ratingValue;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	private String unit;
	

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
		
		//this.mappingToAdd.setRecipe(currentRecipe);
		Ingredient ingredient = ingredientRepository.findOne(this.fridgeItemId);
		getRecipe().addIngredient(ingredient, this.quantity, this.unit);
		this.save();
		//this.mappingToAdd = new RecipeIngredientMapping();
	}
	
	public String getFridgeItemId()
	{
		return "";
	}

	public void setFridgeItemId(String itemId) {
		this.fridgeItemId = Long.valueOf(itemId);
	}
	
	public void rate() {
		SecurityContext sec = SecurityContextHolder.getContext();
		String name = sec.getAuthentication().getName();
		
		User user = userRepository.findByName(name);
		System.out.println(user);
		
		Rating rating = new Rating();
		rating.setPoints(this.ratingValueToSave);
		rating.setUser(user);
		getRecipe().addRating(rating);
		this.save();
	}
	
	public int getAverageRating() {
		if (getRecipe().getRatings() == null || getRecipe().getRatings().size() == 0) {
			return 0;
		}
		
		int sum = 0;
		for (Rating rating: getRecipe().getRatings()){
			sum += rating.getPoints();
		}

		return sum / getRecipe().getRatings().size();
	}
}
