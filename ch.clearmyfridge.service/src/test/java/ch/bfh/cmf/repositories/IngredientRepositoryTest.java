package ch.bfh.cmf.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ch.bfh.cmf.domain.Ingredient;
import ch.bfh.cmf.domain.Recipe;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-application-context.xml")
@Transactional
public class IngredientRepositoryTest {

	@Autowired
	private IngredientRepository ingredientRepository;
	@Autowired
	private RecipeRepository recipeRepository;

	@Test
	public void testSaveNewIngredient() {

		Ingredient ingredient = new Ingredient();
		ingredientRepository.save(ingredient);

		assertEquals(ingredient, ingredientRepository.findAll().iterator().next());
	}

	@Test
	public void testSaveRecipeWithTransientIngredient() {
		Recipe recipe = new Recipe("chickenCurry");
		Ingredient chicken = new Ingredient("chicken");
		recipe.addIngredient(chicken, 100, "g");
		recipeRepository.save(recipe);
	}
}
