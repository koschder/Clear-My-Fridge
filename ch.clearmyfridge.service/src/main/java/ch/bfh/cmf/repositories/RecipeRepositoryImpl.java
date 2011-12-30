package ch.bfh.cmf.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Repository;

import ch.bfh.cmf.domain.Ingredient;
import ch.bfh.cmf.domain.Recipe;

@Repository
public class RecipeRepositoryImpl implements RecipeRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Recipe> findByIngredients(Collection<Ingredient> ingredients) {
		Validate.isTrue(ingredients != null && !ingredients.isEmpty(), "At least one ingredient must be present!");
		
		final List<String> ingredientNames = getIngredientNames(ingredients);
		
		final StringBuilder queryStr = new StringBuilder();
		queryStr.append("select distinct r from Recipe r ");
		queryStr.append("left join r.ingredients ri left join ri.ingredient i ");
		queryStr.append("where i.name in (:ingredientNames) ");
		
		final TypedQuery<Recipe> query = entityManager.createQuery(queryStr.toString(), Recipe.class);
		query.setParameter("ingredientNames", ingredientNames);
		return query.getResultList();

	}

	private List<String> getIngredientNames(Collection<Ingredient> ingredients) {
		final List<String> ingredientNames = new ArrayList<String>();
		for (Ingredient ingredient : ingredients) {
			ingredientNames.add(ingredient.getName());
		}
		return ingredientNames;
	}
}
