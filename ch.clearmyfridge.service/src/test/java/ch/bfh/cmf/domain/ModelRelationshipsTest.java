package ch.bfh.cmf.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ch.bfh.cmf.repositories.RecipeRepository;
import ch.bfh.cmf.repositories.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-application-context.xml")
@Transactional
public class ModelRelationshipsTest {

	@Inject
	private UserRepository userRepository;
	@Inject
	private RecipeRepository recipeRepository;

	@Test
	public void testPersistingUserDoesNotCascade() {
		User user = new User("user");

		Recipe recipe = new Recipe();
		recipe.setAuthor(user);

		userRepository.save(user);
		assertEquals(user, userRepository.findAll().iterator().next());
		assertTrue(recipeRepository.findAll().isEmpty());
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
		List<RecipeIngredientMapping> ingredients = persistentRecipe.getIngredients();
		assertEquals(2, ingredients.size());
		assertTrue(ingredients.contains(new RecipeIngredientMapping(persistentRecipe, chicken, 100,
				"g")));
		assertTrue(ingredients.contains(new RecipeIngredientMapping(persistentRecipe, curry, 1,
				"tsp")));
	}

}
