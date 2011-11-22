package ch.bfh.cmf.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ch.bfh.cmf.domain.Ingredient;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:repository-context.xml")
@Transactional
public class IngredientRepositoryTest {

	@Autowired
	private IngredientRepository ingredientRepository;

	@Test
	public void testSaveNewIngredient() {
		Ingredient ingredient = new Ingredient();
		ingredientRepository.save(ingredient);

		assertEquals(ingredient, ingredientRepository.findAll().iterator()
				.next());
	}

}
