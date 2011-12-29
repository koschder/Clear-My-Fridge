package ch.bfh.cmf.web;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import ch.bfh.cmf.domain.Recipe;
import ch.bfh.cmf.services.RecipeService;

@Named("searchPageBean")
@Scope("session")
public class SearchPageBean {

	@Inject
	private RecipeService recipeService;
	
	
	public void search() {
		
	}
	
	public Object found() {
		return null;
		
	}
	
	public void add() {
		
	}
	
	public void remove() {
		
	}
	
	public Object searchRecipe() {
		return recipeService;
		
	}
}
