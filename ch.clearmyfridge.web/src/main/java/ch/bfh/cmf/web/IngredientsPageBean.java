package ch.bfh.cmf.web;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import ch.bfh.cmf.domain.Ingredient;
import ch.bfh.cmf.repositories.IngredientRepository;

@Named("ingredientsPageBean")
@Scope("session")
public class IngredientsPageBean {
	@Inject
	private IngredientRepository ingredientRepository;

	private Ingredient currentIngredient;

	public List<Ingredient> getIngredients() {
		return ingredientRepository.findAll();
	}

	public Object create() {
		currentIngredient = new Ingredient();
		return "/ingredient/create.xhtml";
	}

	public Object edit() {
		return "/ingredient/edit.xhtml";
	}

	public Object delete() {
		ingredientRepository.delete(currentIngredient);
		return "/ingredient/list.xhtml";
	}
	public Object save() {
		ingredientRepository.save(currentIngredient);
		return "/ingredient/list.xhtml";
	}

	public Ingredient getIngredient() {
		return currentIngredient;
	}
	
	public void setIngredient(Ingredient ingredient) {
		this.currentIngredient = ingredient;
	}
}
