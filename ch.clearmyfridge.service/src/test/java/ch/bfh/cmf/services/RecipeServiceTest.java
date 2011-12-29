package ch.bfh.cmf.services;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ch.bfh.cmf.domain.Ingredient;
import ch.bfh.cmf.domain.Rating;
import ch.bfh.cmf.domain.Recipe;
import ch.bfh.cmf.domain.User;
import ch.bfh.cmf.repositories.RatingRepository;
import ch.bfh.cmf.repositories.RecipeRepository;
import ch.bfh.cmf.repositories.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-application-context.xml")
@Transactional
public class RecipeServiceTest {

	@Inject
	private RecipeService recipeService;

	@Inject
	private RecipeRepository recipeRepository;
	@Inject
	private RatingRepository ratingRepository;
	@Inject
	private UserRepository userRepository;

	private Recipe chickenCurry;
	private Recipe beefCurry;
	private Recipe tiramisu;

	@Before
	public void setup() {
		List<Recipe> recipes = recipeRepository.findAll(new Sort("id"));
		User user = userRepository.save(new User("testUser"));
		chickenCurry = createRecipe("Chicken Curry", "curry", "chicken", "rice", "salt");
		beefCurry = createRecipe("Beef Curry", "curry", "beef", "rice", "salt");
		tiramisu = createRecipe("Tiramisu", "eggs", "mascarpone", "biscuits", "coffee");
		recipes = recipeRepository.findAll(new Sort("id"));
		chickenCurry = recipeRepository.save(chickenCurry);
		beefCurry = recipeRepository.save(beefCurry);
		tiramisu = recipeRepository.save(tiramisu);
		recipes = recipeRepository.findAll(new Sort("id"));
		ratingRepository.save(new Rating(user, chickenCurry, 5));
		recipes = recipeRepository.findAll(new Sort("id"));
		ratingRepository.save(new Rating(user, chickenCurry, 6));
		ratingRepository.save(new Rating(user, chickenCurry, 5));
		recipes = recipeRepository.findAll(new Sort("id"));
		ratingRepository.save(new Rating(user, beefCurry, 5));
		ratingRepository.save(new Rating(user, beefCurry, 4));
		ratingRepository.save(new Rating(user, beefCurry, 5));
		ratingRepository.save(new Rating(user, tiramisu, 8));
		ratingRepository.save(new Rating(user, tiramisu, 8));
		ratingRepository.save(new Rating(user, tiramisu, 8));

	}

	@Test
	public void testFindRecipesUsing_nonMatchingIngredients() {
		List<Recipe> recipes = recipeService.findRecipesUsing(getIngredients("nails", "screws"));
		assertEquals("Non-matching ingredients should find nothing", 0, recipes.size());
	}

	@Test
	public void testFindRecipesUsing_HigherAvgRatingFirst() {
		List<Recipe> recipes = recipeService.findRecipesUsing(getIngredients("curry", "rice", "salt"));
		assertEquals(2, recipes.size());
		assertEquals(chickenCurry, recipes.get(0));
		assertEquals(beefCurry, recipes.get(1));

		recipes = recipeService.findRecipesUsing(getIngredients("curry", "coffee"));
		assertEquals(3, recipes.size());
		assertEquals(tiramisu, recipes.get(0));
		assertEquals(chickenCurry, recipes.get(1));
		assertEquals(beefCurry, recipes.get(2));
	}

	@Test
	public void testFindRecipesUsing_MoreMatchingIngredientsFirst() {
		List<Recipe> recipes = recipeService.findRecipesUsing(getIngredients("beef", "rice", "salt"));
		assertEquals(2, recipes.size());
		assertEquals(beefCurry, recipes.get(0));
		assertEquals(chickenCurry, recipes.get(1));

		recipes = recipeService.findRecipesUsing(getIngredients("beef", "rice", "salt", "coffee"));
		assertEquals(3, recipes.size());
		assertEquals(beefCurry, recipes.get(0));
		assertEquals(chickenCurry, recipes.get(1));
		assertEquals(tiramisu, recipes.get(2));
	}

	private Collection<Ingredient> getIngredients(String... ingredientNames) {
		Set<Ingredient> ingredients = new HashSet<Ingredient>();
		for (String name : ingredientNames) {
			ingredients.add(new Ingredient(name));
		}
		return ingredients;
	}

	private Recipe createRecipe(String name, String... ingredients) {
		Recipe recipe = new Recipe(name);
		for (String ingredient : ingredients) {
			recipe.addIngredient(new Ingredient(ingredient), 1, "unit");
		}
		return recipe;
	}
}
