package ch.bfh.cmf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.bfh.cmf.domain.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

	Recipe findByName(String name);

}
