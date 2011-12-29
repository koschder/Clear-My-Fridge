package ch.bfh.cmf.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.bfh.cmf.domain.Rating;
import ch.bfh.cmf.domain.Recipe;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
	public List<Rating> findByRecipe(Recipe recipe);
}
