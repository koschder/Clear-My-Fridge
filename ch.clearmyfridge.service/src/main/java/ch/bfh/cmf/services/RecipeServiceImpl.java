package ch.bfh.cmf.services;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ch.bfh.cmf.domain.Ingredient;
import ch.bfh.cmf.domain.Rating;
import ch.bfh.cmf.domain.Recipe;
import ch.bfh.cmf.domain.RecipeIngredientMapping;
import ch.bfh.cmf.repositories.RecipeRepository;

@Service("recipeService")
public class RecipeServiceImpl implements RecipeService {

	@Inject 
	RecipeRepository recipeRepository;
	@Override
	public List<Recipe> findRecipesUsing(Collection<Ingredient> ingredients) {
		final List<Recipe> recipesContainingIngredients = recipeRepository.findByIngredients(ingredients);
		Collections.sort(recipesContainingIngredients, new RecipeComparator(ingredients));
		return recipesContainingIngredients;
	}

	private class RecipeComparator implements Comparator<Recipe>
	{
		Collection<Ingredient> ingredients;
		
		public RecipeComparator(Collection<Ingredient> ingredients) {
			this.ingredients = ingredients;
		}


		@Override
		public int compare(Recipe r1, Recipe r2) {
			final int matches1 = numberOfMatchingIngredients(r1);
			final int matches2 = numberOfMatchingIngredients(r2);
			final double avgRating1 = avgRating(r1);
			final double avgRating2 = avgRating(r2);
			if (matches1 > matches2)
				return -1;
			else if (matches1 < matches2)
				return 1;
			else if (avgRating1 > avgRating2)
				return -1;
			else if (avgRating1 < avgRating2)
				return 1;
			return 0;
		}
		
		private int numberOfMatchingIngredients(Recipe recipe)
		{
			int n = 0;
			for (RecipeIngredientMapping mapping : recipe.getIngredients()) {
				if (ingredients.contains(mapping.getIngredient()))
					n++;
			}
			return n;
		}
		
		private double avgRating(Recipe recipe)
		{
			final List<Rating> ratings = recipe.getRatings();
			if (ratings == null || ratings.isEmpty())
				return 0;
			
			int sum = 0;
			int count = ratings.size();
			for (Rating rating : ratings) 
				sum += rating.getPoints();
			return sum/count;
		}
	}
}
