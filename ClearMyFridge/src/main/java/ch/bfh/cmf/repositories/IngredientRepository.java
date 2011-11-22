package ch.bfh.cmf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.bfh.cmf.domain.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
