package ch.bfh.cmf.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ch.bfh.cmf.domain.Ingredient;
import ch.bfh.cmf.domain.Recipe;
import ch.bfh.cmf.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-application-context.xml")
@Transactional
public class RecipeRepositoryTest {

	@Inject
	private RecipeRepository recipeRepository;

	@Inject
	private UserRepository userRepository;

	@Test
	public void testSaveNewRecipe() {
		User author = userRepository.save(new User("author"));

		Recipe recipe = new Recipe();
		recipe.setAuthor(author);
		recipe.setDescription("description");
		recipe.setName("delicious cupcakes");
		recipe.setPicture("/path/to/img");
		recipeRepository.save(recipe);

		assertEquals(recipe, recipeRepository.findAll().iterator().next());
		assertEquals(1, recipeRepository.findAll().size());
	}

	@Test
	public void testSaveRecipeWithoutAuthor() {
		recipeRepository.save(new Recipe());
	}

	@Test
	public void testSaveRecipeWithTransientAuthor() {
		Recipe recipe = new Recipe();
		final User transientAuthor = new User("transient");
		recipe.setAuthor(transientAuthor);
		recipe = recipeRepository.save(recipe);
		assertEquals(transientAuthor, recipe.getAuthor());
		assertNull("The user must not have been persisted", recipe.getAuthor().getId());
	}

	@Test
	public void testSaveRecipeWithIngredients() {
		Recipe recipe = new Recipe();
		recipe.setName("PfefferRezept");
		final Ingredient pepper = new Ingredient("Pfeffer");
		recipe.addIngredient(pepper, 2, "tsp");
		recipeRepository.save(recipe);

		List<Recipe> recipes = recipeRepository.findAll();
		assertEquals(1, recipes.size());
		assertTrue(recipes.contains(recipe));
		assertEquals("Pfeffer", recipes.get(0).getIngredients().iterator().next().getIngredient().getName());
	}

	@Test
	public void testLoadRecipeWithIngredients() {
		Recipe recipe = new Recipe();
		recipe.setName("PfefferRezept");
		recipe.addIngredient(new Ingredient("Pfeffer"), 2, "tsp");
		recipeRepository.save(recipe);

		recipe = recipeRepository.findByName("PfefferRezept");
		assertEquals("Pfeffer", recipe.getIngredients().iterator().next().getIngredient().getName());
	}

}
