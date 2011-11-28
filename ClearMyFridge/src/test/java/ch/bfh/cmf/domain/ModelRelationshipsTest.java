package ch.bfh.cmf.domain;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ch.bfh.cmf.repositories.RatingRepository;
import ch.bfh.cmf.repositories.RecipeRepository;
import ch.bfh.cmf.repositories.UserRepository;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:repository-context.xml")
@Transactional
public class ModelRelationshipsTest {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RecipeRepository recipeRepository;
	@Autowired
	private RatingRepository ratingRepository;

	@Test
	public void testPersistingUserDoesNotCascade() {
		User user = new User("user");

		Recipe recipe = new Recipe();
		recipe.setAuthor(user);

		Rating rating = new Rating();
		rating.setUser(user);

		userRepository.save(user);
		assertEquals(user, userRepository.findAll().iterator().next());
		assertTrue(recipeRepository.findAll().isEmpty());
		assertTrue(ratingRepository.findAll().isEmpty());
	}

	@Test
	public void testLoadRecipeWithIngredients() {
		Recipe recipe = new Recipe("chickenCurry");
		Ingredient chicken = new Ingredient("chicken");
		Ingredient curry = new Ingredient("curry");
		recipe.addIngredient(chicken, 100, "g");
		recipe.addIngredient(curry, 1, "tsp");
		recipeRepository.save(recipe);
		Recipe persistentRecipe = recipeRepository.findAll().iterator().next();
		Set<RecipeIngredientMapping> ingredients = persistentRecipe.getIngredients();
		assertEquals(2, ingredients.size());
		assertTrue(ingredients.contains(new RecipeIngredientMapping(persistentRecipe, chicken, 100,
				"g")));
		assertTrue(ingredients.contains(new RecipeIngredientMapping(persistentRecipe, curry, 1,
				"tsp")));
	}

}
