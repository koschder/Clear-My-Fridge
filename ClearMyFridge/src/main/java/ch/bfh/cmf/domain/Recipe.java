package ch.bfh.cmf.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.Validate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Recipe {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	private String name;
	@ManyToOne
	private User author;

	@OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true, mappedBy = "recipe")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<RecipeIngredientMapping> ingredients;

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "recipe_id")
	private List<Rating> ratings;
	private String description;
	private String picture;

	public Recipe() {
		// needed for jpa
	}

	public Recipe(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public void addRating(Rating rating) {
		if (ratings == null)
			ratings = new ArrayList<Rating>();
		ratings.add(rating);
	}

	public List<RecipeIngredientMapping> getIngredients() {
		return ingredients;
	}

	public void addIngredient(Ingredient ingredient, int quantity, String unit) {
		if (ingredients == null)
			ingredients = new ArrayList<RecipeIngredientMapping>();
		RecipeIngredientMapping mapping = new RecipeIngredientMapping(this, ingredient, quantity,
				unit);
		Validate.isTrue(!ingredients.contains(mapping),
				"This recipe already contains %d, use changeQuantity() instead.", ingredient);
		ingredients.add(mapping);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
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
		Recipe other = (Recipe) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (picture == null) {
			if (other.picture != null)
				return false;
		} else if (!picture.equals(other.picture))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", name=" + name + ", author=" + author + ", ingredients="
				+ ingredients + ", description=" + description + "]";
	}

}
