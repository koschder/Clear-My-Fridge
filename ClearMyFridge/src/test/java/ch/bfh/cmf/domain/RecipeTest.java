package ch.bfh.cmf.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeTest {

	@Test
	public void testAddIngredient_NewIngredient() {
		Recipe recipe = new Recipe();
		Ingredient chicken = new Ingredient("chicken");
		recipe.addIngredient(chicken, 100, "g");
		RecipeIngredientMapping expectedMapping = new RecipeIngredientMapping(
				recipe, chicken, 100, "g");
		assertTrue(recipe.getIngredients().contains(expectedMapping));
	}

	@Test
	public void testAddIngredient_TwoIngredients() {
		Recipe recipe = new Recipe();
		Ingredient chicken = new Ingredient("chicken");
		Ingredient beef = new Ingredient("beef");
		recipe.addIngredient(chicken, 100, "g");
		recipe.addIngredient(beef, 100, "g");
		RecipeIngredientMapping chickenMapping = new RecipeIngredientMapping(
				recipe, chicken, 100, "g");
		RecipeIngredientMapping beefMapping = new RecipeIngredientMapping(
				recipe, chicken, 100, "g");
		assertTrue(recipe.getIngredients().contains(chickenMapping));
		assertTrue(recipe.getIngredients().contains(beefMapping));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddIngredient_SameIngredientTwice() {
		Recipe recipe = new Recipe();
		Ingredient chicken = new Ingredient("chicken");
		recipe.addIngredient(chicken, 100, "g");
		assertEquals(1, recipe.getIngredients().size());
		recipe.addIngredient(chicken, 500, "kg");
	}

}
