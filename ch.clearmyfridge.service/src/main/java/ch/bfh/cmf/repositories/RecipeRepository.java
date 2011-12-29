package ch.bfh.cmf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import ch.bfh.cmf.domain.Recipe;


@Transactional(readOnly = true)
public interface RecipeRepository extends JpaRepository<Recipe, Long>, RecipeRepositoryCustom {

	Recipe findByName(String name);
	

}
