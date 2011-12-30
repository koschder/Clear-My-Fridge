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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ch.bfh.cmf.domain.Ingredient;
import ch.bfh.cmf.domain.Rating;
import ch.bfh.cmf.domain.Recipe;
import ch.bfh.cmf.domain.User;
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
	private UserRepository userRepository;

	private Recipe chickenCurry;
	private Recipe beefCurry;
	private Recipe tiramisu;

	@Before
	public void setup() {
		User user1 = userRepository.save(new User("testUser1"));
		User user2 = userRepository.save(new User("testUser2"));
		User user3 = userRepository.save(new User("testUser3"));
		
		chickenCurry = createRecipe("Chicken Curry", "curry", "chicken", "rice", "salt");
		beefCurry = createRecipe("Beef Curry", "curry", "beef", "rice", "salt");
		tiramisu = createRecipe("Tiramisu", "eggs", "mascarpone", "biscuits", "coffee");
		
		chickenCurry.addRating(new Rating(user1, 5));
		chickenCurry.addRating(new Rating(user2, 6));
		chickenCurry.addRating(new Rating(user3, 5));
		beefCurry.addRating(new Rating(user1, 5));
		beefCurry.addRating(new Rating(user2, 4));
		beefCurry.addRating(new Rating(user3, 5));
		tiramisu.addRating(new Rating(user1, 8));
		tiramisu.addRating(new Rating(user2, 8));
		tiramisu.addRating(new Rating(user3, 8));

		chickenCurry = recipeRepository.save(chickenCurry);
		beefCurry = recipeRepository.save(beefCurry);
		tiramisu = recipeRepository.save(tiramisu);

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
