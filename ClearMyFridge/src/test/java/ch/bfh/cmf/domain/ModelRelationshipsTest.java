package ch.bfh.cmf.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.bfh.cmf.repositories.RatingRepository;
import ch.bfh.cmf.repositories.RecipeRepository;
import ch.bfh.cmf.repositories.UserRepository;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:repository-context.xml")
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

}
