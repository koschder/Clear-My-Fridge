package ch.bfh.cmf.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ch.bfh.cmf.domain.Recipe;
import ch.bfh.cmf.domain.User;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:repository-context.xml")
@Transactional
public class RecipeRepositoryTest {

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
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
	}

	@Test
	public void testSaveRecipeWithoutAuthor() {
		recipeRepository.save(new Recipe());
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void testSaveRecipeWithTransientAuthor() {
		Recipe recipe = new Recipe();
		recipe.setAuthor(new User("transient"));
		recipeRepository.save(recipe);
	}

}
