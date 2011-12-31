package ch.bfh.cmf.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.bfh.cmf.domain.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
	public List<Ingredient> findByNameLikeIgnoringCase(String name);
}
