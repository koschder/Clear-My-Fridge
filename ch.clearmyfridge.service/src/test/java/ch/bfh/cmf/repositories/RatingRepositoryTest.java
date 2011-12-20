package ch.bfh.cmf.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ch.bfh.cmf.domain.Rating;
import ch.bfh.cmf.domain.Recipe;
import ch.bfh.cmf.domain.User;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-application-context.xml")
@Transactional
public class RatingRepositoryTest {

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RatingRepository ratingRepository;

	@Test
	public void testSaveNewRating() {
		User user = userRepository.save(new User());
		Recipe recipe = recipeRepository.save(new Recipe());

		Rating rating = new Rating();
		rating.setRecipe(recipe);
		rating.setUser(user);

		ratingRepository.save(rating);
		assertEquals(rating, ratingRepository.findAll().iterator().next());
	}

}
