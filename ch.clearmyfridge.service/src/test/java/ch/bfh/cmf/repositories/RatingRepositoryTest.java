package ch.bfh.cmf.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ch.bfh.cmf.domain.Rating;
import ch.bfh.cmf.domain.Recipe;
import ch.bfh.cmf.domain.User;

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

		Rating rating = createAndSaveRating(user, recipe);
		assertEquals(rating, ratingRepository.findAll().iterator().next());
	}

	@Test
	public void testfindByRecipe() {
		User user = userRepository.save(new User());
		Recipe recipe = recipeRepository.save(new Recipe());

		Rating rating = createAndSaveRating(user, recipe);

		List<Rating> foundRatings = ratingRepository.findByRecipe(recipe);
		assertEquals(1, foundRatings.size());
		assertTrue(foundRatings.contains(rating));
	}

	@Test
	public void testfindMultipleByRecipe() {
		User user = userRepository.save(new User());
		Recipe recipe = recipeRepository.save(new Recipe("1"));
		Recipe recipe2 = recipeRepository.save(new Recipe("2"));

		Rating rating = createAndSaveRating(user, recipe);
		createAndSaveRating(user, recipe2);
		Rating rating3 = createAndSaveRating(user, recipe);

		List<Rating> foundRatings = ratingRepository.findByRecipe(recipe);
		assertEquals(2, foundRatings.size());
		assertTrue(foundRatings.contains(rating));
		assertTrue(foundRatings.contains(rating3));
	}

	private Rating createAndSaveRating(User user, Recipe recipe) {
		Rating rating = new Rating();
		rating.setRecipe(recipe);
		rating.setUser(user);

		ratingRepository.save(rating);
		return rating;
	}
}
