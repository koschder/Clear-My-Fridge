package ch.bfh.cmf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.bfh.cmf.domain.Ingredient;

@FacesConverter("ingredientConverter")
public class IngredientConverter implements Converter {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		return entityManager.find(Ingredient.class, arg0);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return ((Ingredient) arg2).getId().toString();
	}

}
