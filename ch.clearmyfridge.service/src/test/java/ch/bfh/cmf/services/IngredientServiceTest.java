package ch.bfh.cmf.services;

import static org.junit.Assert.*;

import java.util.Collection;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ch.bfh.cmf.domain.Ingredient;
import ch.bfh.cmf.repositories.IngredientRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-application-context.xml")
@Transactional
public class IngredientServiceTest {

	@Inject
	private IngredientService ingredientService;
	@Inject
	private IngredientRepository ingredientRepository;
	private Ingredient sweetChili;
	private Ingredient spicyChili;
	private Ingredient sweetener;
	private Ingredient curry;

	@Before
	public void setup() {
		sweetChili = ingredientRepository.save(new Ingredient("Sweet Chili"));
		spicyChili = ingredientRepository.save(new Ingredient("Spicy Chili"));
		sweetener = ingredientRepository.save(new Ingredient("Sweetener"));
		curry = ingredientRepository.save(new Ingredient("Curry"));
	}

	@Test
	public void testFindStartingWith() {
		assertSearchResults("Sweet", sweetChili, sweetener);
	}
	@Test
	public void testFindStartingWithIgnoreCase() {
		assertSearchResults("chili", sweetChili, spicyChili);
	}
	@Test
	public void testFindEndingWith() {
		assertSearchResults("Chili", sweetChili, spicyChili);
	}
	@Test
	public void testFindEndingWithIgnoreCase() {
		assertSearchResults("sweet", sweetChili, sweetener);
	}
	@Test
	public void testFindInbetween() {
		assertSearchResults("chil", sweetChili, spicyChili);
		assertSearchResults("t c", sweetChili);
		assertSearchResults("c", sweetChili, spicyChili, curry);
	}
	@Test
	public void testFindWithSpaces() {
		assertSearchResults("chili ", sweetChili, spicyChili);
		assertSearchResults(" Sweet", sweetChili, sweetener);
		assertSearchResults("");
	}

	private void assertSearchResults(String searchQuery, Ingredient... expectedIngredients) {
		Collection<Ingredient> ingredients = ingredientService.searchIngredients(searchQuery);
		assertEquals(expectedIngredients.length, ingredients.size());
		for (Ingredient ingredient : expectedIngredients) {
			assertTrue(ingredients.contains(ingredient));
		}
	}

}
