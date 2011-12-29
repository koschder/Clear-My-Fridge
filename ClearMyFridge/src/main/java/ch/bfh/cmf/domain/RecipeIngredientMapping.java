package ch.bfh.cmf.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: RecipeIngredientMapping
 * 
 */
@Entity
@Table(name = "Recipe_Ingredient")
@IdClass(RecipeIngredientMappingId.class)
public class RecipeIngredientMapping {
	@Id
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "recipe_id")
	private Recipe recipe;

	@Id
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "ingredient_id")
	private Ingredient ingredient;
	private Integer quantity;
	private String unit;

	public RecipeIngredientMapping() {
		// needed for jpa
	}

	public RecipeIngredientMapping(Recipe recipe, Ingredient ingredient, Integer quantity,
			String unit) {
		super();
		this.recipe = recipe;
		this.ingredient = ingredient;
		this.quantity = quantity;
		this.unit = unit;
	}

	public Recipe getRecipe() {
		return this.recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Ingredient getIngredient() {
		return this.ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ingredient == null) ? 0 : ingredient.hashCode());
		result = prime * result + ((recipe == null) ? 0 : recipe.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecipeIngredientMapping other = (RecipeIngredientMapping) obj;
		if (ingredient == null) {
			if (other.ingredient != null)
				return false;
		} else if (!ingredient.equals(other.ingredient))
			return false;
		if (recipe == null) {
			if (other.recipe != null)
				return false;
		} else if (!recipe.equals(other.recipe))
			return false;
		return true;
	}
}
