package ch.bfh.cmf.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ch.bfh.cmf.domain.Ingredient;
import ch.bfh.cmf.domain.QIngredient;
import ch.bfh.cmf.domain.QRecipe;
import ch.bfh.cmf.domain.QRecipeIngredientMapping;
import ch.bfh.cmf.domain.Recipe;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;

@Repository
public class RecipeRepositoryImpl implements RecipeRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Recipe> findByIngredients(Collection<Ingredient> ingredients) {
		final List<String> ingredientNames = new ArrayList<String>();
		for (Ingredient ingredient : ingredients) {
			ingredientNames.add(ingredient.getName());
		}
		JPQLQuery query = new JPAQuery(entityManager);
		final QRecipe recipe = QRecipe.recipe;
		final QRecipeIngredientMapping mapping = QRecipeIngredientMapping.recipeIngredientMapping;
		final QIngredient ingredient = QIngredient.ingredient;
		return query.from(recipe).join(recipe.ingredients, mapping).join(mapping.ingredient, ingredient)
				.where(ingredient.name.in(ingredientNames)).distinct().list(recipe);
		// join(recipe.ingredients).join(QRecipeIngredientMapping.recipeIngredientMapping.ingredient).where(recipe.ingredients.)).fetchAll().list(recipe);
	}

}
